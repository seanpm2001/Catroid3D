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

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;
//
//import org.catrobat.catroid.CatroidApplication;
//import org.catrobat.catroid.cast.CastManager;
//import org.catrobat.catroid.ui.settingsfragments.AccessibilityProfile;
//import org.catrobat.catroid.ui.settingsfragments.SettingsFragment;
//import org.catrobat.catroid.utils.CrashReporter;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String RECOVERED_FROM_CRASH = "RECOVERED_FROM_CRASH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //applyAccessibilityStyles();

        Thread.setDefaultUncaughtExceptionHandler(new BaseExceptionHandler(this));
        checkIfCrashRecoveryAndFinishActivity(this);

//		if (SettingsFragment.isCastSharedPreferenceEnabled(this)) {
//			CastManager.getInstance().initializeCast(this);
//		}
    }

//	private void applyAccessibilityStyles() {
//		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//		AccessibilityProfile profile = AccessibilityProfile.fromCurrentPreferences(sharedPreferences);
//		profile.applyAccessibilityStyles(getTheme());
//	}

    @Override
    protected void onResume() {
        super.onResume();

//		if (SettingsFragment.isCastSharedPreferenceEnabled(this)) {
//			CastManager.getInstance().initializeCast(this);
//		}

        invalidateOptionsMenu();
        //googleAnalyticsTrackScreenResume();
    }

//	protected void googleAnalyticsTrackScreenResume() {
//		Tracker googleTracker = ((CatroidApplication) getApplication()).getDefaultTracker();
//		googleTracker.setScreenName(this.getClass().getName());
//		googleTracker.send(new HitBuilders.ScreenViewBuilder().build());
//	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void checkIfCrashRecoveryAndFinishActivity(final Activity activity) {
        if (isRecoveringFromCrash()) {
            //CrashReporter.logUnhandledException();
//			if (activity instanceof MainMenuActivity) {
//				PreferenceManager.getDefaultSharedPreferences(this).edit()
//						.putBoolean(RECOVERED_FROM_CRASH, false)
//						.commit();
//			} else {
            activity.finish();
            //}
        }
    }

    private boolean isRecoveringFromCrash() {
        return PreferenceManager.getDefaultSharedPreferences(this).getBoolean(RECOVERED_FROM_CRASH, false);
    }
}
