/**
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2013 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p>
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.ui.worldeditscreen;

import org.catrobat.catroid.common.Constants;
import org.catrobat.catroid.io.GestureDetector;
import org.catrobat.catroid.io.GestureHandler;
import org.catrobat.catroid.ui.ObjectHandler;
import org.catrobat.catroid.ui.element.AddGroundButton;
import org.catrobat.catroid.ui.element.AddObjectButton;
import org.catrobat.catroid.ui.element.GroundButton;
import org.catrobat.catroid.ui.element.MoveCameraButton;
import org.catrobat.catroid.ui.element.MoveObjectButton;
import org.catrobat.catroid.ui.element.RemoveGroundButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

public class ProjectBuildScreen extends BaseScreen {

    private VerticalGroup controlPane;
    private Stage stage;
    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;
    private ObjectHandler objectHandler;

    public ProjectBuildScreen() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        stage = new Stage();

        gestureHandler = new GestureHandler(this);
        gestureDetector = new GestureDetector(gestureHandler);
        Gdx.input.setInputProcessor(gestureDetector);

        MoveCameraButton moveCameraButton = new MoveCameraButton();
        MoveObjectButton moveObjectButton = new MoveObjectButton();
        AddGroundButton addGroundButton = new AddGroundButton(gestureHandler);
        addGroundButton.setVisible(false);
        RemoveGroundButton removeGroundButton = new RemoveGroundButton(gestureHandler);
        removeGroundButton.setVisible(false);
        GroundButton groundButton = new GroundButton(gestureHandler, addGroundButton, removeGroundButton);
        buttonMap.put(Constants.UI_MOVE_CAMERA_BUTTON, moveCameraButton);
        buttonMap.put(Constants.UI_MOVE_OBJECT_BUTTON, moveObjectButton);
        buttonMap.put(Constants.UI_GROUND_BUTTON, groundButton);
        buttonMap.put(Constants.UI_ADD_GROUND_BUTTON, addGroundButton);
        buttonMap.put(Constants.UI_REMOVE_GROUND_BUTTON, removeGroundButton);
        controlPane = new VerticalGroup();
        controlPane.align(Align.left | Align.top);
        controlPane.columnLeft();
        controlPane.addActor(moveCameraButton);
        controlPane.addActor(moveObjectButton);

        HorizontalGroup groundPane = new HorizontalGroup();
        groundPane.addActor(groundButton);
        groundPane.addActor(addGroundButton);
        groundPane.addActor(removeGroundButton);
        controlPane.setX(10);
        controlPane.setY(Gdx.graphics.getHeight());
        controlPane.addActor(groundPane);

        objectHandler = new ObjectHandler(this);

        AddObjectButton addObjectButton = new AddObjectButton(objectHandler);
        buttonMap.put(Constants.UI_ADD_OBJECT_BUTTON, addObjectButton);
        controlPane.addActor(addObjectButton);
        controlPane.validate();
        stage.addActor(controlPane);
    }

    public void hideControlPane() {
        controlPane.setVisible(false);
    }

    public void showControlPane() {
        controlPane.setVisible(true);
    }

    public Stage getStage() {
        return stage;
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public ObjectHandler getObjectHandler() {
        return objectHandler;
    }

}
