/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.bricks;

import android.content.Context;
import android.view.View;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.actions.ScriptSequenceAction;
import org.catrobat.catroid.formulaeditor.Formula;

import java.util.List;

public class WaitBrick extends FormulaBrick {

    private static final long serialVersionUID = 1L;

    public WaitBrick() {
        addAllowedBrickField(Brick.BrickField.TIME_TO_WAIT_IN_SECONDS, R.id.brick_wait_edit_text);
    }

    public WaitBrick(int timeToWaitInMillisecondsValue) {
        this(new Formula(timeToWaitInMillisecondsValue / 1000.0));
    }

    public WaitBrick(Formula formula) {
        this();
        setFormulaWithBrickField(Brick.BrickField.TIME_TO_WAIT_IN_SECONDS, formula);
    }

    @Override
    public int getViewResource() {
        return R.layout.brick_wait;
    }

    @Override
    public View getPrototypeView(Context context) {
        View prototypeView = super.getPrototypeView(context);
        setSecondsLabel(prototypeView, Brick.BrickField.TIME_TO_WAIT_IN_SECONDS);
        return prototypeView;
    }

    @Override
    public View getView(Context context) {
        super.getView(context);
        setSecondsLabel(view, Brick.BrickField.TIME_TO_WAIT_IN_SECONDS);
        return view;
    }

    @Override
    public List<ScriptSequenceAction> addActionToSequence(Sprite sprite, ScriptSequenceAction sequence) {
        sequence.addAction(sprite.getActionFactory()
                .createWaitAction(sprite, getFormulaWithBrickField(Brick.BrickField.TIME_TO_WAIT_IN_SECONDS)));
        return null;
    }
}
