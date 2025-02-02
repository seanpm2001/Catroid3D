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
package org.catrobat.catroid.content.actions.conditional;

import android.util.Log;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.InterpretationException;
import org.catrobat.catroid.libgdx3dwrapper.actions.TemporalAction3d;

public class GlideToAction extends TemporalAction3d {

    protected Sprite sprite;
    private float startX;
    private float startY;
    private float startZ;
    private float currentX;
    private float currentY;
    private float currentZ;
    private Formula endX;
    private Formula endY;
    private Formula endZ;
    private Formula duration;
    private float endXValue;
    private float endYValue;
    private float endZValue;

    private boolean restart = false;


    @Override
    protected void begin() {
        Float durationInterpretation;
        Float endXInterpretation = 0f;
        Float endYInterpretation = 0f;
        Float endZInterpretation = 0f;

        try {
            durationInterpretation = duration == null ? Float.valueOf(0f) : duration.interpretFloat(sprite);
        } catch (InterpretationException interpretationException) {
            durationInterpretation = 0f;
            Log.d(getClass().getSimpleName(), "Formula interpretation for this specific Brick failed.", interpretationException);
        }

        try {
            endXInterpretation = endX == null ? Float.valueOf(0f) : endX.interpretFloat(sprite);
        } catch (InterpretationException interpretationException) {
            durationInterpretation = 0f;
            Log.d(getClass().getSimpleName(), "Formula interpretation for this specific Brick failed.", interpretationException);
        }

        try {
            endYInterpretation = endY == null ? Float.valueOf(0f) : endY.interpretFloat(sprite);
        } catch (InterpretationException interpretationException) {
            durationInterpretation = 0f;
            Log.d(getClass().getSimpleName(), "Formula interpretation for this specific Brick failed.", interpretationException);
        }

        try {
            endZInterpretation = endZ == null ? Float.valueOf(0f) : endZ.interpretFloat(sprite);
        } catch (InterpretationException interpretationException) {
            durationInterpretation = 0f;
            Log.d(getClass().getSimpleName(), "Formula interpretation for this specific Brick failed.", interpretationException);
        }

        if (!restart) {
            if (duration != null) {
                super.setDuration(durationInterpretation);
            }
            endXValue = endXInterpretation;
            endYValue = endYInterpretation;
            endZValue = endZInterpretation;
        }
        restart = false;

        startX = sprite.ActorEntity.getXInUserInterfaceDimensionUnit();
		startY = sprite.ActorEntity.getYInUserInterfaceDimensionUnit();
		startZ = sprite.ActorEntity.getZInUserInterfaceDimensionUnit();
        currentX = startX;
        currentY = startY;
        currentZ = startZ;
        if (startX == endXInterpretation && startY == endYInterpretation && startZ == endZInterpretation) {
            super.finish();
        }
    }

    @Override
    protected void update(float percent) {
		float deltaX = sprite.ActorEntity.getXInUserInterfaceDimensionUnit() - currentX;
		float deltaY = sprite.ActorEntity.getYInUserInterfaceDimensionUnit() - currentY;
        float deltaZ = sprite.ActorEntity.getZInUserInterfaceDimensionUnit() - currentZ;
        if ((-0.1f > deltaX || deltaX > 0.1f) || (-0.1f > deltaY || deltaY > 0.1f) || (-0.1f > deltaZ || deltaZ > 0.1f)) {
			restart = true;
			setDuration(getDuration() - getTime());
			restart();
		} else {
			currentX = startX + (endXValue - startX) * percent;
			currentY = startY + (endYValue - startY) * percent;
            currentZ = startZ + (endZValue - startZ) * percent;
            sprite.ActorEntity.setPositionInUserInterfaceDimensionUnit(currentX, currentY, currentZ);
		}
    }

    public void setDuration(Formula duration) {
        this.duration = duration;
    }

    public void setPosition(Formula x, Formula y, Formula z) {
        endX = x;
        endY = y;
        endZ = z;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
