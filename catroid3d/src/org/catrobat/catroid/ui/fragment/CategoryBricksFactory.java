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
package org.catrobat.catroid.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

//import com.parrot.freeflight.drone.DroneProxy.ARDRONE_LED_ANIMATION;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.common.BrickValues;
//import org.catrobat.catroid.content.BroadcastScript;
//import org.catrobat.catroid.content.CollisionScript;
//import org.catrobat.catroid.content.Project;
//import org.catrobat.catroid.content.RaspiInterruptScript;
import org.catrobat.catroid.content.Sprite;
//import org.catrobat.catroid.content.WhenConditionScript;
//import org.catrobat.catroid.content.WhenGamepadButtonScript;
import org.catrobat.catroid.content.bricks.*;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.FormulaElement;
import org.catrobat.catroid.formulaeditor.FormulaElement.ElementType;
import org.catrobat.catroid.formulaeditor.Operators;
//import org.catrobat.catroid.formulaeditor.Sensors;
//import org.catrobat.catroid.physics.content.bricks.CollisionReceiverBrick;
//import org.catrobat.catroid.physics.content.bricks.SetBounceBrick;
//import org.catrobat.catroid.physics.content.bricks.SetFrictionBrick;
//import org.catrobat.catroid.physics.content.bricks.SetGravityBrick;
//import org.catrobat.catroid.physics.content.bricks.SetMassBrick;
//import org.catrobat.catroid.physics.content.bricks.SetPhysicsObjectTypeBrick;
//import org.catrobat.catroid.physics.content.bricks.SetVelocityBrick;
//import org.catrobat.catroid.physics.content.bricks.TurnLeftSpeedBrick;
//import org.catrobat.catroid.physics.content.bricks.TurnRightSpeedBrick;
//import org.catrobat.catroid.ui.UserBrickSpriteActivity;
//import org.catrobat.catroid.ui.settingsfragments.SettingsFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CategoryBricksFactory {

    public List<Brick> getBricks(String category, Sprite sprite, Context context) {

        //todo fix me?
        boolean isUserScriptMode = false;// context instanceof UserBrickSpriteActivity;
        List<Brick> tempList = new LinkedList<>();
        List<Brick> toReturn = new ArrayList<>();
        if (category.equals(context.getString(R.string.category_event))) {
            tempList = setupEventCategoryList(context);
        } else if (category.equals(context.getString(R.string.category_control))) {
            tempList = setupControlCategoryList(context);
        } else if (category.equals(context.getString(R.string.category_motion))) {
            tempList = setupMotionCategoryList(sprite, context);
        }
//		else if (category.equals(context.getString(R.string.category_sound))) {
//			tempList = setupSoundCategoryList(context);
//		}

		else if (category.equals(context.getString(R.string.category_looks))) {
			boolean isBackgroundSprite = sprite.getName().equals(context.getString(R.string.background));
			tempList = setupLooksCategoryList(context, isBackgroundSprite);
		}

//		else if (category.equals(context.getString(R.string.category_pen))) {
//			tempList = setupPenCategoryList(sprite);
//		}

        else if (category.equals(context.getString(R.string.category_user_bricks))) {
            tempList = setupUserBricksCategoryList();
        } else if (category.equals(context.getString(R.string.category_data))) {
            tempList = setupDataCategoryList(context);
        }
//		else if (category.equals(context.getString(R.string.category_lego_nxt))) {
//			tempList = setupLegoNxtCategoryList();
//		} else if (category.equals(context.getString(R.string.category_lego_ev3))) {
//			tempList = setupLegoEv3CategoryList();
//		} else if (category.equals(context.getString(R.string.category_arduino))) {
//			tempList = setupArduinoCategoryList();
//		} else if (category.equals(context.getString(R.string.category_drone))) {
//			tempList = setupDroneCategoryList();
//		} else if (category.equals(context.getString(R.string.category_jumping_sumo))) {
//			tempList = setupJumpingSumoCategoryList();
//		} else if (category.equals(context.getString(R.string.category_phiro))) {
//			tempList = setupPhiroProCategoryList();
//		} else if (category.equals(context.getString(R.string.category_cast))) {
//			tempList = setupChromecastCategoryList(context);
//		} else if (category.equals(context.getString(R.string.category_raspi))) {
//			tempList = setupRaspiCategoryList();
//		} else if (category.equals(context.getString(R.string.category_embroidery))) {
//			tempList = setupEmbroideryCategoryList();
//		}

        for (Brick brick : tempList) {
            if (!isUserScriptMode || !(brick instanceof ScriptBrick)) {
                toReturn.add(brick);
            }
        }
        return toReturn;
    }

    protected List<Brick> setupEventCategoryList(Context context) {
        FormulaElement defaultIf = new FormulaElement(FormulaElement.ElementType.OPERATOR, Operators.SMALLER_THAN.toString(), null);
        defaultIf.setLeftChild(new FormulaElement(ElementType.NUMBER, "1", null));
        defaultIf.setRightChild(new FormulaElement(ElementType.NUMBER, "2", null));

        List<Brick> eventBrickList = new ArrayList<>();
        eventBrickList.add(new WhenStartedBrick());
//		eventBrickList.add(new WhenBrick());
//		eventBrickList.add(new WhenTouchDownBrick());

//		Project currentProject = ProjectManager.getInstance().getCurrentProject();
//		List<String> broadcastMessages = currentProject.getBroadcastMessageContainer().getBroadcastMessages();
//		String broadcastMessage = context.getString(R.string.brick_broadcast_default_value);
//		if (broadcastMessages.size() > 0) {
//			broadcastMessage = broadcastMessages.get(0);
//		}
//		eventBrickList.add(new BroadcastReceiverBrick(new BroadcastScript(broadcastMessage)));
//		eventBrickList.add(new BroadcastBrick(broadcastMessage));
//		eventBrickList.add(new BroadcastWaitBrick(broadcastMessage));
//		eventBrickList.add(new WhenConditionBrick(new WhenConditionScript(new Formula(defaultIf))));
//		eventBrickList.add(new CollisionReceiverBrick(new CollisionScript(null)));
//		eventBrickList.add(new WhenBackgroundChangesBrick());
//		eventBrickList.add(new WhenClonedBrick());
//
//		if (SettingsFragment.isNfcSharedPreferenceEnabled(context)) {
//			eventBrickList.add(new WhenNfcBrick());
//		}
        return eventBrickList;
    }

    protected List<Brick> setupControlCategoryList(Context context) {
        FormulaElement defaultIf = new FormulaElement(FormulaElement.ElementType.OPERATOR, Operators.SMALLER_THAN.toString(), null);
        defaultIf.setLeftChild(new FormulaElement(ElementType.NUMBER, "1", null));
        defaultIf.setRightChild(new FormulaElement(ElementType.NUMBER, "2", null));

        List<Brick> controlBrickList = new ArrayList<>();
        controlBrickList.add(new WaitBrick(BrickValues.WAIT));
        //controlBrickList.add(new NoteBrick(context.getString(R.string.brick_note_default_value)));
        controlBrickList.add(new ForeverBrick());
        controlBrickList.add(new IfLogicBeginBrick(new Formula(defaultIf)));
        controlBrickList.add(new IfThenLogicBeginBrick(new Formula(defaultIf)));
        //controlBrickList.add(new WaitUntilBrick(new Formula(defaultIf)));
        controlBrickList.add(new RepeatBrick(BrickValues.REPEAT));
//		controlBrickList.add(new RepeatUntilBrick(new Formula(defaultIf)));
//		controlBrickList.add(new SceneTransitionBrick(null));
//		controlBrickList.add(new SceneStartBrick(null));

//		if (SettingsFragment.isPhiroSharedPreferenceEnabled(context)) {
//			controlBrickList.add(new PhiroIfLogicBeginBrick());
//		}
//
//		controlBrickList.add(new StopScriptBrick(BrickValues.STOP_THIS_SCRIPT));
//
//		controlBrickList.add(new CloneBrick());
//		controlBrickList.add(new DeleteThisCloneBrick());
//		controlBrickList.add(new WhenClonedBrick());
//		if (SettingsFragment.isNfcSharedPreferenceEnabled(context)) {
//			controlBrickList.add(new SetNfcTagBrick(context.getString(R.string.brick_set_nfc_tag_default_value)));
//		}

        return controlBrickList;
    }

    private List<Brick> setupUserBricksCategoryList() {
        List<UserBrick> userBrickList = ProjectManager.getInstance().getCurrentSprite().getUserBrickList();
        ArrayList<Brick> newList = new ArrayList<>();

        if (userBrickList != null) {
            for (UserBrick brick : userBrickList) {
                newList.add(brick);
            }
        }
        return newList;
    }

//	private List<Brick> setupChromecastCategoryList(Context context) {
//		List<Brick> chromecastBrickList = new ArrayList<Brick>();
//		chromecastBrickList.add(new WhenGamepadButtonBrick(new WhenGamepadButtonScript(
//				context.getString(R.string.cast_gamepad_A))));
//
//		return chromecastBrickList;
//	}

    protected List<Brick> setupMotionCategoryList(Sprite sprite, Context context) {
        List<Brick> motionBrickList = new ArrayList<>();
        //motionBrickList.add(new PlaceAtBrick(BrickValues.X_POSITION, BrickValues.Y_POSITION));
//        motionBrickList.add(new SetXBrick(new Formula(BrickValues.X_POSITION)));
//        motionBrickList.add(new SetYBrick(BrickValues.Y_POSITION));
//        motionBrickList.add(new SetZBrick(BrickValues.Z_POSITION));
        motionBrickList.add(new ChangeXByNBrick(BrickValues.CHANGE_X_BY));
        motionBrickList.add(new ChangeYByNBrick(BrickValues.CHANGE_Y_BY));
        motionBrickList.add(new ChangeZByNBrick(BrickValues.CHANGE_Z_BY));
        //motionBrickList.add(new GoToBrick(null));

//		if (!isBackground(sprite)) {
//			motionBrickList.add(new IfOnEdgeBounceBrick());
//		}

        motionBrickList.add(new MoveNStepsBrick(BrickValues.MOVE_STEPS));
		motionBrickList.add(new TurnLeftBrick(BrickValues.TURN_DEGREES));
		motionBrickList.add(new TurnRightBrick(BrickValues.TURN_DEGREES));
        motionBrickList.add(new PointInDirectionBrick(90));
//		motionBrickList.add(new PointToBrick(null));
//		motionBrickList.add(new SetRotationStyleBrick());
        motionBrickList.add(new GlideToBrick(BrickValues.X_POSITION, BrickValues.Y_POSITION, BrickValues.Z_POSITION,
                BrickValues.GLIDE_SECONDS));

//		if (!isBackground(sprite)) {
//			motionBrickList.add(new GoNStepsBackBrick(BrickValues.GO_BACK));
//			motionBrickList.add(new ComeToFrontBrick());
//		}

//		motionBrickList.add(new VibrationBrick(BrickValues.VIBRATE_SECONDS));
//
//		motionBrickList.add(new SetPhysicsObjectTypeBrick(BrickValues.PHYSIC_TYPE));
//		motionBrickList.add(new SetVelocityBrick(BrickValues.PHYSIC_VELOCITY));
//		motionBrickList.add(new TurnLeftSpeedBrick(BrickValues.PHYSIC_TURN_DEGREES));
//		motionBrickList.add(new TurnRightSpeedBrick(BrickValues.PHYSIC_TURN_DEGREES));
//		motionBrickList.add(new SetGravityBrick(BrickValues.PHYSIC_GRAVITY));
//		motionBrickList.add(new SetMassBrick(BrickValues.PHYSIC_MASS));
//		motionBrickList.add(new SetBounceBrick(BrickValues.PHYSIC_BOUNCE_FACTOR * 100));
//		motionBrickList.add(new SetFrictionBrick(BrickValues.PHYSIC_FRICTION * 100));
//
//		if (SettingsFragment.isPhiroSharedPreferenceEnabled(context)) {
//			motionBrickList.add(new PhiroMotorMoveForwardBrick(PhiroMotorMoveForwardBrick.Motor.MOTOR_LEFT,
//					BrickValues.PHIRO_SPEED));
//			motionBrickList.add(new PhiroMotorMoveBackwardBrick(PhiroMotorMoveBackwardBrick.Motor.MOTOR_LEFT,
//					BrickValues.PHIRO_SPEED));
//			motionBrickList.add(new PhiroMotorStopBrick(PhiroMotorStopBrick.Motor.MOTOR_BOTH));
//		}

        return motionBrickList;
    }

//	protected List<Brick> setupSoundCategoryList(Context context) {
//		List<Brick> soundBrickList = new ArrayList<>();
//		soundBrickList.add(new PlaySoundBrick());
//		soundBrickList.add(new PlaySoundAndWaitBrick());
//		soundBrickList.add(new StopAllSoundsBrick());
//		soundBrickList.add(new SetVolumeToBrick(BrickValues.SET_VOLUME_TO));
//
//		// workaround to set a negative default value for a Brick
//		double positiveDefaultValueChangeVolumeBy = Math.abs(BrickValues.CHANGE_VOLUME_BY);
//		FormulaElement defaultValueChangeVolumeBy = new FormulaElement(ElementType.OPERATOR, Operators.MINUS.name(),
//				null, null, new FormulaElement(ElementType.NUMBER, String.valueOf(positiveDefaultValueChangeVolumeBy),
//				null)
//		);
//		soundBrickList.add(new ChangeVolumeByNBrick(new Formula(defaultValueChangeVolumeBy)));
//
//		soundBrickList.add(new SpeakBrick(context.getString(R.string.brick_speak_default_value)));
//		soundBrickList.add(new SpeakAndWaitBrick(context.getString(R.string.brick_speak_default_value)));
//
//		if (SettingsFragment.isPhiroSharedPreferenceEnabled(context)) {
//			soundBrickList.add(new PhiroPlayToneBrick(PhiroPlayToneBrick.Tone.DO,
//					BrickValues.PHIRO_DURATION));
//		}
//		soundBrickList.add(new AskSpeechBrick(context.getString(R.string.brick_ask_speech_default_question)));
//
//		return soundBrickList;
//	}

	protected List<Brick> setupLooksCategoryList(Context context, boolean isBackgroundSprite) {
		List<Brick> looksBrickList = new ArrayList<>();
		looksBrickList.add(new SetFirstPersonViewBrick());
		looksBrickList.add(new ResetFirstPersonViewBrick());
//
//		if (!isBackgroundSprite) {
//			looksBrickList.add(new SetLookBrick());
//			looksBrickList.add(new SetLookByIndexBrick(BrickValues.SET_LOOK_BY_INDEX));
//		}
//		looksBrickList.add(new NextLookBrick());
//		looksBrickList.add(new PreviousLookBrick());
//		looksBrickList.add(new SetSizeToBrick(BrickValues.SET_SIZE_TO));
//		looksBrickList.add(new ChangeSizeByNBrick(BrickValues.CHANGE_SIZE_BY));
//		looksBrickList.add(new HideBrick());
//		looksBrickList.add(new ShowBrick());
//		looksBrickList.add(new AskBrick(context.getString(R.string.brick_ask_default_question)));
//		if (!isBackgroundSprite) {
//			looksBrickList.add(new SayBubbleBrick(context.getString(R.string.brick_say_bubble_default_value)));
//			looksBrickList.add(new SayForBubbleBrick(context.getString(R.string.brick_say_bubble_default_value), 1.0f));
//			looksBrickList.add(new ThinkBubbleBrick(context.getString(R.string.brick_think_bubble_default_value)));
//			looksBrickList.add(new ThinkForBubbleBrick(context.getString(R.string.brick_think_bubble_default_value), 1.0f));
//		}
//		looksBrickList.add(new SetTransparencyBrick(BrickValues.SET_TRANSPARENCY));
//		looksBrickList.add(new ChangeTransparencyByNBrick(BrickValues.CHANGE_TRANSPARENCY_EFFECT));
//		looksBrickList.add(new SetBrightnessBrick(BrickValues.SET_BRIGHTNESS_TO));
//		looksBrickList.add(new ChangeBrightnessByNBrick(BrickValues.CHANGE_BRITHNESS_BY));
//		looksBrickList.add(new SetColorBrick(BrickValues.SET_COLOR_TO));
//		looksBrickList.add(new ChangeColorByNBrick(BrickValues.CHANGE_COLOR_BY));
//		looksBrickList.add(new ClearGraphicEffectBrick());
//		looksBrickList.add(new WhenBackgroundChangesBrick());
//		looksBrickList.add(new SetBackgroundBrick());
//		looksBrickList.add(new SetBackgroundByIndexBrick(BrickValues.SET_LOOK_BY_INDEX));
//		looksBrickList.add(new SetBackgroundAndWaitBrick());
//		looksBrickList.add(new SetBackgroundByIndexAndWaitBrick(BrickValues.SET_LOOK_BY_INDEX));
//
//		if (!ProjectManager.getInstance().getCurrentProject().isCastProject()) {
//			looksBrickList.add(new CameraBrick());
//			looksBrickList.add(new ChooseCameraBrick());
//			looksBrickList.add(new FlashBrick());
//		}
//
//		if (SettingsFragment.isPhiroSharedPreferenceEnabled(context)) {
//			looksBrickList.add(new PhiroRGBLightBrick(PhiroRGBLightBrick.Eye.BOTH,
//					BrickValues.PHIRO_VALUE_RED, BrickValues.PHIRO_VALUE_GREEN, BrickValues.PHIRO_VALUE_BLUE));
//		}

		return looksBrickList;
	}

//	private List<Brick> setupPenCategoryList(Sprite sprite) {
//		List<Brick> penBrickList = new ArrayList<>();
//
//		if (!isBackground(sprite)) {
//			penBrickList.add(new PenDownBrick());
//			penBrickList.add(new PenUpBrick());
//			penBrickList.add(new SetPenSizeBrick(BrickValues.PEN_SIZE));
//			penBrickList.add(new SetPenColorBrick(0, 0, 255));
//			penBrickList.add(new StampBrick());
//		}
//
//		penBrickList.add(new ClearBackgroundBrick());
//		return penBrickList;
//	}

    protected List<Brick> setupDataCategoryList(Context context) {
        List<Brick> dataBrickList = new ArrayList<>();
        //dataBrickList.add(new SetVariableBrick(BrickValues.SET_VARIABLE));
        dataBrickList.add(new ChangeVariableBrick(BrickValues.CHANGE_VARIABLE));
        //dataBrickList.add(new ShowTextBrick(BrickValues.X_POSITION, BrickValues.Y_POSITION));
        //dataBrickList.add(new HideTextBrick());
        dataBrickList.add(new AddItemToUserListBrick(BrickValues.ADD_ITEM_TO_USERLIST));
        dataBrickList.add(new DeleteItemOfUserListBrick(BrickValues.DELETE_ITEM_OF_USERLIST));
        dataBrickList.add(new InsertItemIntoUserListBrick(BrickValues.INSERT_ITEM_INTO_USERLIST_VALUE,
                BrickValues.INSERT_ITEM_INTO_USERLIST_INDEX));
        dataBrickList.add(new ReplaceItemInUserListBrick(BrickValues.REPLACE_ITEM_IN_USERLIST_VALUE,
                BrickValues.REPLACE_ITEM_IN_USERLIST_INDEX));
        //dataBrickList.add(new AskBrick(context.getString(R.string.brick_ask_default_question)));
        //dataBrickList.add(new AskSpeechBrick(context.getString(R.string.brick_ask_speech_default_question)));
        return dataBrickList;
    }

//	private List<Brick> setupLegoNxtCategoryList() {
//		List<Brick> legoNXTBrickList = new ArrayList<>();
//		legoNXTBrickList.add(new LegoNxtMotorTurnAngleBrick(LegoNxtMotorTurnAngleBrick.Motor.MOTOR_A,
//				BrickValues.LEGO_ANGLE));
//		legoNXTBrickList.add(new LegoNxtMotorStopBrick(LegoNxtMotorStopBrick.Motor.MOTOR_A));
//		legoNXTBrickList.add(new LegoNxtMotorMoveBrick(LegoNxtMotorMoveBrick.Motor.MOTOR_A,
//				BrickValues.LEGO_SPEED));
//		legoNXTBrickList.add(new LegoNxtPlayToneBrick(BrickValues.LEGO_FREQUENCY, BrickValues.LEGO_DURATION));
//
//		return legoNXTBrickList;
//	}
//
//	private List<Brick> setupLegoEv3CategoryList() {
//		List<Brick> legoEV3BrickList = new ArrayList<Brick>();
//		legoEV3BrickList.add(new LegoEv3MotorTurnAngleBrick(LegoEv3MotorTurnAngleBrick.Motor.MOTOR_A,
//				BrickValues.LEGO_ANGLE));
//		legoEV3BrickList.add(new LegoEv3MotorMoveBrick(LegoEv3MotorMoveBrick.Motor.MOTOR_A,
//				BrickValues.LEGO_SPEED));
//		legoEV3BrickList.add(new LegoEv3MotorStopBrick(LegoEv3MotorStopBrick.Motor.MOTOR_A));
//		legoEV3BrickList.add(new LegoEv3PlayToneBrick(BrickValues.LEGO_FREQUENCY,
//				BrickValues.LEGO_DURATION, BrickValues.LEGO_VOLUME));
//		legoEV3BrickList.add(new LegoEv3SetLedBrick(LegoEv3SetLedBrick.LedStatus.LED_GREEN));
//
//		return legoEV3BrickList;
//	}
//
//	private List<Brick> setupDroneCategoryList() {
//		List<Brick> droneBrickList = new ArrayList<>();
//		droneBrickList.add(new DroneTakeOffLandBrick());
//		droneBrickList.add(new DroneEmergencyBrick());
//		droneBrickList.add(new DroneMoveUpBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneMoveDownBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneMoveLeftBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneMoveRightBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneMoveForwardBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneMoveBackwardBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneTurnLeftBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneTurnRightBrick(BrickValues.DRONE_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.DRONE_MOVE_BRICK_DEFAULT_POWER_PERCENT));
//		droneBrickList.add(new DroneFlipBrick());
//		droneBrickList.add(new DronePlayLedAnimationBrick(ARDRONE_LED_ANIMATION.ARDRONE_LED_ANIMATION_BLINK_GREEN_RED));
//		droneBrickList.add(new DroneSwitchCameraBrick());
//
//		return droneBrickList;
//	}
//
//	private List<Brick> setupJumpingSumoCategoryList() {
//		List<Brick> jumpingSumoBrickList = new ArrayList<>();
//		jumpingSumoBrickList.add(new JumpingSumoMoveForwardBrick(
//				BrickValues.JUMPING_SUMO_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues.JUMPING_SUMO_MOVE_BRICK_DEFAULT_MOVE_POWER_PERCENT));
//		jumpingSumoBrickList.add(new JumpingSumoMoveBackwardBrick(
//				BrickValues.JUMPING_SUMO_MOVE_BRICK_DEFAULT_TIME_MILLISECONDS,
//				BrickValues
//						.JUMPING_SUMO_MOVE_BRICK_DEFAULT_MOVE_POWER_PERCENT));
//		jumpingSumoBrickList.add(new JumpingSumoAnimationsBrick(JumpingSumoAnimationsBrick.Animation.SPIN));
//		jumpingSumoBrickList.add(new JumpingSumoSoundBrick(JumpingSumoSoundBrick.Sounds.DEFAULT,
//				BrickValues.JUMPING_SUMO_SOUND_BRICK_DEFAULT_VOLUME_PERCENT));
//		jumpingSumoBrickList.add(new JumpingSumoNoSoundBrick());
//		jumpingSumoBrickList.add(new JumpingSumoJumpLongBrick());
//		jumpingSumoBrickList.add(new JumpingSumoJumpHighBrick());
//		jumpingSumoBrickList.add(new JumpingSumoRotateLeftBrick(BrickValues.JUMPING_SUMO_ROTATE_DEFAULT_DEGREE));
//		jumpingSumoBrickList.add(new JumpingSumoRotateRightBrick(BrickValues.JUMPING_SUMO_ROTATE_DEFAULT_DEGREE));
//		jumpingSumoBrickList.add(new JumpingSumoTurnBrick());
//		jumpingSumoBrickList.add(new JumpingSumoTakingPictureBrick());
//
//		return jumpingSumoBrickList;
//	}
//
//	private List<Brick> setupPhiroProCategoryList() {
//		List<Brick> phiroProBrickList = new ArrayList<>();
//		phiroProBrickList.add(new PhiroMotorMoveForwardBrick(PhiroMotorMoveForwardBrick.Motor.MOTOR_LEFT,
//				BrickValues.PHIRO_SPEED));
//		phiroProBrickList.add(new PhiroMotorMoveBackwardBrick(PhiroMotorMoveBackwardBrick.Motor.MOTOR_LEFT,
//				BrickValues.PHIRO_SPEED));
//		phiroProBrickList.add(new PhiroMotorStopBrick(PhiroMotorStopBrick.Motor.MOTOR_BOTH));
//		phiroProBrickList.add(new PhiroPlayToneBrick(PhiroPlayToneBrick.Tone.DO,
//				BrickValues.PHIRO_DURATION));
//		phiroProBrickList.add(new PhiroRGBLightBrick(PhiroRGBLightBrick.Eye.BOTH, BrickValues.PHIRO_VALUE_RED,
//				BrickValues.PHIRO_VALUE_GREEN, BrickValues.PHIRO_VALUE_BLUE));
//		phiroProBrickList.add(new PhiroIfLogicBeginBrick());
//		phiroProBrickList.add(new SetVariableBrick(Sensors.PHIRO_FRONT_LEFT));
//		phiroProBrickList.add(new SetVariableBrick(Sensors.PHIRO_FRONT_RIGHT));
//		phiroProBrickList.add(new SetVariableBrick(Sensors.PHIRO_SIDE_LEFT));
//		phiroProBrickList.add(new SetVariableBrick(Sensors.PHIRO_SIDE_RIGHT));
//		phiroProBrickList.add(new SetVariableBrick(Sensors.PHIRO_BOTTOM_LEFT));
//		phiroProBrickList.add(new SetVariableBrick(Sensors.PHIRO_BOTTOM_RIGHT));
//
//		return phiroProBrickList;
//	}
//
//	private List<Brick> setupArduinoCategoryList() {
//		List<Brick> arduinoBrickList = new ArrayList<>();
//		arduinoBrickList.add(new ArduinoSendDigitalValueBrick(BrickValues.ARDUINO_DIGITAL_INITIAL_PIN_NUMBER,
//				BrickValues.ARDUINO_DIGITAL_INITIAL_PIN_VALUE));
//		arduinoBrickList.add(new ArduinoSendPWMValueBrick(BrickValues.ARDUINO_PWM_INITIAL_PIN_NUMBER,
//				BrickValues.ARDUINO_PWM_INITIAL_PIN_VALUE));
//
//		return arduinoBrickList;
//	}
//
//	private List<Brick> setupRaspiCategoryList() {
//		RaspiInterruptScript defaultScript = new RaspiInterruptScript(
//				Integer.toString(BrickValues.RASPI_DIGITAL_INITIAL_PIN_NUMBER), BrickValues.RASPI_EVENTS[0]);
//
//		List<Brick> raspiBrickList = new ArrayList<>();
//		raspiBrickList.add(new WhenRaspiPinChangedBrick(defaultScript));
//		raspiBrickList.add(new RaspiIfLogicBeginBrick(BrickValues.RASPI_DIGITAL_INITIAL_PIN_NUMBER));
//		raspiBrickList.add(new RaspiSendDigitalValueBrick(BrickValues.RASPI_DIGITAL_INITIAL_PIN_NUMBER,
//				BrickValues.RASPI_DIGITAL_INITIAL_PIN_VALUE));
//		raspiBrickList.add(new RaspiPwmBrick(BrickValues.RASPI_DIGITAL_INITIAL_PIN_NUMBER,
//				BrickValues.RASPI_PWM_INITIAL_FREQUENCY, BrickValues.RASPI_PWM_INITIAL_PERCENTAGE));
//
//		return raspiBrickList;
//	}
//
//	private List<Brick> setupEmbroideryCategoryList() {
//		return new ArrayList<>();
//	}

    protected boolean isBackground(Sprite sprite) {
        return ProjectManager.getInstance().getCurrentlyEditedScene().getSpriteList().indexOf(sprite) == 0;
    }

    public String getBrickCategory(Brick brick, Sprite sprite, Context context) {
        List<Brick> categoryBricks;
        categoryBricks = setupControlCategoryList(context);

        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        Locale savedLocale = config.locale;
        config.locale = Locale.ENGLISH;
        res.updateConfiguration(config, null);
        String category = "No match";

        for (Brick categoryBrick : categoryBricks) {
            if (brick.getClass().equals(categoryBrick.getClass())) {
                category = res.getString(R.string.category_control);
            }
        }
        categoryBricks = setupEventCategoryList(context);
        for (Brick categoryBrick : categoryBricks) {
            if (brick.getClass().equals(categoryBrick.getClass())) {
                category = res.getString(R.string.category_event);
            }
        }
        categoryBricks = setupMotionCategoryList(sprite, context);
        for (Brick categoryBrick : categoryBricks) {
            if (brick.getClass().equals(categoryBrick.getClass())) {
                category = res.getString(R.string.category_motion);
            }
        }
//		categoryBricks = setupSoundCategoryList(context);
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_sound);
//			}
//		}
//		boolean isBackgroundSprite = sprite.getName().equals(context.getString(R.string.background));
//		categoryBricks = setupLooksCategoryList(context, isBackgroundSprite);
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_looks);
//			}
//		}
//		categoryBricks = setupPenCategoryList(sprite);
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_pen);
//			}
//		}
        categoryBricks = setupUserBricksCategoryList();
        for (Brick categoryBrick : categoryBricks) {
            if (brick.getClass().equals(categoryBrick.getClass())) {
                category = res.getString(R.string.category_user_bricks);
            }
        }
        categoryBricks = setupDataCategoryList(context);
        for (Brick categoryBrick : categoryBricks) {
            if (brick.getClass().equals(categoryBrick.getClass())) {
                category = res.getString(R.string.category_data);
            }
        }
//		categoryBricks = setupLegoNxtCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_lego_nxt);
//			}
//		}
//		categoryBricks = setupLegoEv3CategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_lego_ev3);
//			}
//		}
//		categoryBricks = setupArduinoCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_arduino);
//			}
//		}
//		categoryBricks = setupDroneCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_drone);
//			}
//		}
//		categoryBricks = setupJumpingSumoCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_jumping_sumo);
//			}
//		}
//		categoryBricks = setupPhiroProCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_phiro);
//			}
//		}
//		categoryBricks = setupRaspiCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_raspi);
//			}
//		}
//		categoryBricks = setupChromecastCategoryList(context);
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_cast);
//			}
//		}
//		categoryBricks = setupEmbroideryCategoryList();
//		for (Brick categoryBrick : categoryBricks) {
//			if (brick.getClass().equals(categoryBrick.getClass())) {
//				category = res.getString(R.string.category_embroidery);
//			}
//		}
//
//		if (brick instanceof AskBrick) {
//			category = res.getString(R.string.category_looks);
//		} else if (brick instanceof AskSpeechBrick) {
//			category = res.getString(R.string.category_sound);
//		} else if (brick instanceof WhenClonedBrick) {
//			category = res.getString(R.string.category_control);
//		} else if (brick instanceof WhenBackgroundChangesBrick) {
//			category = res.getString(R.string.category_event);
//		} else if (brick instanceof SetVariableBrick) {
//			category = res.getString(R.string.category_data);
//		}

        config.locale = savedLocale;
        res.updateConfiguration(config, null);

        return category;
    }
}
