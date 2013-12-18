/**
 *  Catroid: An on-device visual programming system for Android devices
 *  Copyright (C) 2010-2013 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://developer.catrobat.org/license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid3d.ui.element;

import org.catrobat.catroid3d.common.Constants;
import org.catrobat.catroid3d.io.GestureHandler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;

public class GroundButton extends ToggleOnOffButton {

	private Array<Button> buttonArray;
	private GestureHandler gestureHandler;

	public GroundButton(GestureHandler gestureHandler, Button... buttons) {
		super(Constants.UI_GROUND_BUTTON);
		buttonArray = new Array<Button>(buttons);
		this.gestureHandler = gestureHandler;
		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				boolean otherButtonsVisible;
				if (isPressed()) {
					otherButtonsVisible = true;
					GroundButton.this.gestureHandler.getGroundBuilder().activateCursor(true);
				} else {
					otherButtonsVisible = false;
					GroundButton.this.gestureHandler.getGroundBuilder().activateCursor(false);
				}
				for (Button currentButton : buttonArray) {
					currentButton.setVisible(otherButtonsVisible);
				}
				return true;
			}
		});
	}
}