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
package org.catrobat.catroid.content.actions;

import android.util.Log;

import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.InterpretationException;
import org.catrobat.catroid.libgdx3dwrapper.actions.Action3d;
import org.catrobat.catroid.libgdx3dwrapper.scene.Actor3d;

public class IfLogicAction extends Action3d {

    private Sprite sprite;
    private Action3d ifAction;
    private Action3d elseAction;
    private Formula ifCondition;
    private Boolean ifConditionValue;
    private boolean isInitialized = false;
    private boolean isInterpretedCorrectly;

    protected void begin() {
        try {
            if (ifCondition == null) {
                isInterpretedCorrectly = false;
                return;
            }
            Double interpretation = ifCondition.interpretDouble(sprite);
            ifConditionValue = interpretation.intValue() != 0;
            isInterpretedCorrectly = true;
        } catch (InterpretationException interpretationException) {
            isInterpretedCorrectly = false;
            Log.d(getClass().getSimpleName(), "Formula interpretation for this specific Brick failed.", interpretationException);
        }
    }

    @Override
    public boolean act(float delta) {
        if (!isInitialized) {
            begin();
            isInitialized = true;
        }

        if (!isInterpretedCorrectly) {
            return true;
        }

        if (ifConditionValue) {
            return ifAction.act(delta);
        } else {
            if (elseAction == null) {
                return true;
            }
            return elseAction.act(delta);
        }
    }

    @Override
    public void restart() {
        ifAction.restart();
        if (elseAction != null) {
            elseAction.restart();
        }
        isInitialized = false;
        super.restart();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setIfAction(Action3d ifAction) {
        this.ifAction = ifAction;
    }

    public void setElseAction(Action3d elseAction) {
        this.elseAction = elseAction;
    }

    public void setIfCondition(Formula ifCondition) {
        this.ifCondition = ifCondition;
    }

    @Override
    public void setActor(Actor3d actor) {
        super.setActor(actor);
        ifAction.setActor(actor);
        if (elseAction != null) {
            elseAction.setActor(actor);
        }
    }
}
