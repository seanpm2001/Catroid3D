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

package org.catrobat.catroid.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.catrobat.catroid.ProjectManager;

public final class UiUtils {

    private UiUtils() {
        throw new AssertionError("No.");
    }

    public static AppCompatActivity getActivityFromContextWrapper(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof AppCompatActivity) {
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        return null;
    }

    public static AppCompatActivity getActivityFromView(View view) {
        return getActivityFromContextWrapper(view.getContext());
    }

    public static void InvokeOnUiThread(Runnable runnable) {
        Looper mainLooper = Looper.getMainLooper();
        if (Thread.currentThread().getId() == mainLooper.getThread().getId()) {
            runnable.run();
        } else {
            new Handler(mainLooper).post(runnable);
        }
    }
}
