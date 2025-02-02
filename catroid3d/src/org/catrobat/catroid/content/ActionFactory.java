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
package org.catrobat.catroid.content;

import org.catrobat.catroid.content.actions.ScriptSequenceAction;

//import com.parrot.freeflight.drone.DroneProxy.ARDRONE_LED_ANIMATION;

//import org.catrobat.catroid.ProjectManager;
//import org.catrobat.catroid.camera.CameraManager;
//import org.catrobat.catroid.common.LookData;
//import org.catrobat.catroid.common.SoundInfo;
import org.catrobat.catroid.content.actions.*;
//import org.catrobat.catroid.content.actions.conditional.GlideToAction;
//import org.catrobat.catroid.content.actions.conditional.IfOnEdgeBounceAction;
//import org.catrobat.catroid.content.bricks.JumpingSumoAnimationsBrick;
//import org.catrobat.catroid.content.bricks.JumpingSumoSoundBrick;
//import org.catrobat.catroid.content.bricks.LegoEv3MotorMoveBrick;
//import org.catrobat.catroid.content.bricks.LegoEv3MotorStopBrick;
//import org.catrobat.catroid.content.bricks.LegoEv3MotorTurnAngleBrick;
//import org.catrobat.catroid.content.bricks.LegoEv3SetLedBrick;
//import org.catrobat.catroid.content.bricks.LegoNxtMotorMoveBrick;
//import org.catrobat.catroid.content.bricks.LegoNxtMotorStopBrick;
//import org.catrobat.catroid.content.bricks.LegoNxtMotorTurnAngleBrick;
//import org.catrobat.catroid.content.bricks.PhiroMotorMoveBackwardBrick;
//import org.catrobat.catroid.content.bricks.PhiroMotorMoveForwardBrick;
//import org.catrobat.catroid.content.bricks.PhiroMotorStopBrick;
//import org.catrobat.catroid.content.bricks.PhiroPlayToneBrick;
//import org.catrobat.catroid.content.bricks.PhiroRGBLightBrick;
import org.catrobat.catroid.content.bricks.UserBrick;
//import org.catrobat.catroid.content.eventids.BroadcastEventId;
//import org.catrobat.catroid.content.eventids.EventId;
import org.catrobat.catroid.content.actions.conditional.GlideToAction;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.UserList;
import org.catrobat.catroid.formulaeditor.UserVariable;
import org.catrobat.catroid.libgdx3dwrapper.actions.Action3d;
import org.catrobat.catroid.libgdx3dwrapper.actions.Actions3d;
//import org.catrobat.catroid.physics.PhysicsObject;

public class ActionFactory extends Actions3d {

//	public EventAction createBroadcastAction(String broadcastMessage, @EventWrapper.WaitMode int waitMode) {
//		BroadcastEventId id = new BroadcastEventId(broadcastMessage);
//		return createEventAction(id, waitMode);
//	}
//
//	private EventAction createEventAction(EventId eventId, @EventWrapper.WaitMode int waitMode) {
//		EventWrapper event = new EventWrapper(eventId, waitMode);
//		Project currentProject = ProjectManager.getInstance().getCurrentProject();
//		EventAction action = Actions.action(EventAction.class);
//		action.setEvent(event);
//		action.setReceivingSprites(currentProject.getSpriteListWithClones());
//		return action;
//	}

    public static Action3d eventSequence(Script script) {
        return new ScriptSequenceAction(script);
    }

    public static Action3d createEventThread(Script script) {
        return new EventThread(script);
    }
//
//	public Action createWaitForBubbleBrickAction(Sprite sprite, Formula delay) {
//		WaitForBubbleBrickAction action = Actions.action(WaitForBubbleBrickAction.class);
//		action.setSprite(sprite);
//		action.setDelay(delay);
//		return action;
//	}
//
//	public Action createChangeBrightnessByNAction(Sprite sprite, Formula changeBrightness) {
//		ChangeBrightnessByNAction action = Actions.action(ChangeBrightnessByNAction.class);
//		action.setSprite(sprite);
//		action.setBrightness(changeBrightness);
//		return action;
//	}
//
//	public Action createChangeColorByNAction(Sprite sprite, Formula changeColor) {
//		ChangeColorByNAction action = Actions.action(ChangeColorByNAction.class);
//		action.setSprite(sprite);
//		action.setColor(changeColor);
//		return action;
//	}
//
//	public Action createChangeTransparencyByNAction(Sprite sprite, Formula transparency) {
//		ChangeTransparencyByNAction action = Actions.action(ChangeTransparencyByNAction.class);
//		action.setSprite(sprite);
//		action.setTransparency(transparency);
//		return action;
//	}
//
//	public Action createChangeSizeByNAction(Sprite sprite, Formula size) {
//		ChangeSizeByNAction action = Actions.action(ChangeSizeByNAction.class);
//		action.setSprite(sprite);
//		action.setSize(size);
//		return action;
//	}
//
//	public Action createChangeVolumeByNAction(Sprite sprite, Formula volume) {
//		ChangeVolumeByNAction action = Actions.action(ChangeVolumeByNAction.class);
//		action.setVolume(volume);
//		action.setSprite(sprite);
//		return action;
//	}

    public Action3d createNotifyEventWaiterAction(Sprite sprite, EventWrapper event) {
        NotifyEventWaiterAction action = Actions3d.action(NotifyEventWaiterAction.class);
        action.setEvent(event);
        action.setSprite(sprite);
        return action;
    }

    public Action3d createWaitAction(Sprite sprite, Formula delay) {
        WaitAction action = action(WaitAction.class);
        action.setSprite(sprite);
        action.setDelay(delay);
        return action;
    }

    public Action3d createChangeXByNAction(Sprite sprite, Formula xMovement) {
        ChangeXByNAction action = Actions3d.action(ChangeXByNAction.class);
        action.setSprite(sprite);
        action.setxMovement(xMovement);
        //action.setDuration(1);
        return action;
    }

//	public Action createSetRotationStyleAction(Sprite sprite, @Look.RotationStyle int mode) {
//		SetRotationStyleAction action = Actions.action(SetRotationStyleAction.class);
//		action.setRotationStyle(mode);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createClearGraphicEffectAction(Sprite sprite) {
//		ClearGraphicEffectAction action = Actions.action(ClearGraphicEffectAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createComeToFrontAction(Sprite sprite) {
//		ComeToFrontAction action = Actions.action(ComeToFrontAction.class);
//		action.setSprite(sprite);
//		return action;
//	}

    public Action3d createChangeYByNAction(Sprite sprite, Formula yMovement) {
        ChangeYByNAction action = Actions3d.action(ChangeYByNAction.class);
        action.setSprite(sprite);
        action.setyMovement(yMovement);
        //action.setDuration(1);
        return action;
    }

//	public Action createGlideToAction(Sprite sprite, Formula x, Formula y, Formula duration, Interpolation interpolation) {
//		GlideToAction action = Actions.action(GlideToAction.class);
//		action.setPosition(x, y);
//		action.setDuration(duration);
//		action.setInterpolation(interpolation);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createPlaceAtAction(Sprite sprite, Formula x, Formula y) {
//		GlideToAction action = Actions.action(GlideToAction.class);
//		action.setPosition(x, y);
//		action.setDuration(0);
//		action.setInterpolation(null);
//		action.setSprite(sprite);
//		return action;
//	}

//	public Action createGoToAction(Sprite sprite, Sprite destinationSprite, int spinnerSelection) {
//		switch (spinnerSelection) {
//			case BrickValues.GO_TO_TOUCH_POSITION:
//				GoToTouchPositionAction touchPositionAction = Actions.action(GoToTouchPositionAction.class);
//				touchPositionAction.setSprite(sprite);
//				return touchPositionAction;
//			case BrickValues.GO_TO_RANDOM_POSITION:
//				GoToRandomPositionAction randomPositionAction = Actions.action(GoToRandomPositionAction.class);
//				randomPositionAction.setSprite(sprite);
//				return randomPositionAction;
//			case BrickValues.GO_TO_OTHER_SPRITE_POSITION:
//				GoToOtherSpritePositionAction otherSpritePositionAction = Actions
//						.action(GoToOtherSpritePositionAction.class);
//				otherSpritePositionAction.setSprite(sprite);
//				otherSpritePositionAction.setDestinationSprite(destinationSprite);
//				return otherSpritePositionAction;
//			default:
//				return null;
//		}
//	}

//	public Action createGoNStepsBackAction(Sprite sprite, Formula steps) {
//		GoNStepsBackAction action = Actions.action(GoNStepsBackAction.class);
//		action.setSprite(sprite);
//		action.setSteps(steps);
//		return action;
//	}
//
//	public Action createHideAction(Sprite sprite) {
//		SetVisibleAction action = Actions.action(SetVisibleAction.class);
//		action.setSprite(sprite);
//		action.setVisible(false);
//		return action;
//	}
//
//	public Action createIfOnEdgeBounceAction(Sprite sprite) {
//		IfOnEdgeBounceAction action = Actions.action(IfOnEdgeBounceAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createLegoNxtMotorMoveAction(Sprite sprite, LegoNxtMotorMoveBrick.Motor motorEnum, Formula speed) {
//		LegoNxtMotorMoveAction action = Actions.action(LegoNxtMotorMoveAction.class);
//		action.setMotorEnum(motorEnum);
//		action.setSprite(sprite);
//		action.setSpeed(speed);
//		return action;
//	}
//
//	public Action createLegoNxtMotorStopAction(LegoNxtMotorStopBrick.Motor motorEnum) {
//		LegoNxtMotorStopAction action = Actions.action(LegoNxtMotorStopAction.class);
//		action.setMotorEnum(motorEnum);
//		return action;
//	}
//
//	public Action createLegoNxtMotorTurnAngleAction(Sprite sprite,
//                                                    LegoNxtMotorTurnAngleBrick.Motor motorEnum, Formula degrees) {
//		LegoNxtMotorTurnAngleAction action = Actions.action(LegoNxtMotorTurnAngleAction.class);
//		action.setMotorEnum(motorEnum);
//		action.setSprite(sprite);
//		action.setDegrees(degrees);
//		return action;
//	}
//
//	public Action createLegoNxtPlayToneAction(Sprite sprite, Formula hertz, Formula durationInSeconds) {
//		LegoNxtPlayToneAction action = Actions.action(LegoNxtPlayToneAction.class);
//		action.setHertz(hertz);
//		action.setSprite(sprite);
//		action.setDurationInSeconds(durationInSeconds);
//		return action;
//	}
//
//	public Action createLegoEv3SingleMotorMoveAction(Sprite sprite,
//                                                     LegoEv3MotorMoveBrick.Motor motorEnum, Formula speed) {
//		LegoEv3MotorMoveAction action = action(LegoEv3MotorMoveAction.class);
//		action.setSprite(sprite);
//		action.setMotorEnum(motorEnum);
//		action.setSpeed(speed);
//		return action;
//	}
//
//	public Action createLegoEv3MotorStopAction(LegoEv3MotorStopBrick.Motor motorEnum) {
//		LegoEv3MotorStopAction action = action(LegoEv3MotorStopAction.class);
//		action.setMotorEnum(motorEnum);
//		return action;
//	}
//
//	public Action createLegoEv3SetLedAction(LegoEv3SetLedBrick.LedStatus ledStatusEnum) {
//		LegoEv3SetLedAction action = action(LegoEv3SetLedAction.class);
//		action.setLedStatusEnum(ledStatusEnum);
//		return action;
//	}
//
//	public Action createLegoEv3PlayToneAction(Sprite sprite, Formula hertz, Formula
//			durationInSeconds, Formula volumeInPercent) {
//		LegoEv3PlayToneAction action = action(LegoEv3PlayToneAction.class);
//		action.setHertz(hertz);
//		action.setSprite(sprite);
//		action.setDurationInSeconds(durationInSeconds);
//		action.setVolumeInPercent(volumeInPercent);
//		return action;
//	}
//
//	public Action createLegoEv3MotorTurnAngleAction(Sprite sprite,
//                                                    LegoEv3MotorTurnAngleBrick.Motor motorEnum, Formula degrees) {
//		LegoEv3MotorTurnAngleAction action = action(LegoEv3MotorTurnAngleAction.class);
//		action.setMotorEnum(motorEnum);
//		action.setSprite(sprite);
//		action.setDegrees(degrees);
//		return action;
//	}
//
//	public Action createPhiroPlayToneActionAction(Sprite sprite, PhiroPlayToneBrick.Tone toneEnum,
//                                                  Formula duration) {
//		PhiroPlayToneAction action = action(PhiroPlayToneAction.class);
//		action.setSelectedTone(toneEnum);
//		action.setSprite(sprite);
//		action.setDurationInSeconds(duration);
//		return action;
//	}
//
//	public Action createPhiroMotorMoveForwardActionAction(Sprite sprite, PhiroMotorMoveForwardBrick.Motor motorEnum,
//                                                          Formula speed) {
//		PhiroMotorMoveForwardAction action = action(PhiroMotorMoveForwardAction.class);
//		action.setMotorEnum(motorEnum);
//		action.setSprite(sprite);
//		action.setSpeed(speed);
//		return action;
//	}
//
//	public Action createPhiroMotorMoveBackwardActionAction(Sprite sprite, PhiroMotorMoveBackwardBrick.Motor motorEnum,
//                                                           Formula speed) {
//		PhiroMotorMoveBackwardAction action = action(PhiroMotorMoveBackwardAction.class);
//		action.setMotorEnum(motorEnum);
//		action.setSprite(sprite);
//		action.setSpeed(speed);
//		return action;
//	}
//
//	public Action createPhiroRgbLedEyeActionAction(Sprite sprite, PhiroRGBLightBrick.Eye eye,
//                                                   Formula red, Formula green, Formula blue) {
//		PhiroRGBLightAction action = action(PhiroRGBLightAction.class);
//		action.setSprite(sprite);
//		action.setEyeEnum(eye);
//		action.setRed(red);
//		action.setGreen(green);
//		action.setBlue(blue);
//		return action;
//	}
//
//	public Action createPhiroSendSelectedSensorAction(Sprite sprite, int sensorNumber, Action ifAction, Action
//			elseAction) {
//		PhiroSensorAction action = action(PhiroSensorAction.class);
//		action.setSprite(sprite);
//		action.setSensor(sensorNumber);
//		action.setIfAction(ifAction);
//		action.setElseAction(elseAction);
//		return action;
//	}
//
//	public Action createPhiroMotorStopActionAction(PhiroMotorStopBrick.Motor motorEnum) {
//		PhiroMotorStopAction action = action(PhiroMotorStopAction.class);
//		action.setMotorEnum(motorEnum);
//		return action;
//	}

    public Action3d createChangeZByNAction(Sprite sprite, Formula zMovement) {
        ChangeZByNAction action = Actions3d.action(ChangeZByNAction.class);
        action.setSprite(sprite);
        action.setzMovement(zMovement);
        //action.setDuration(1);
        return action;
    }

//	public Action createPenDownAction(Sprite sprite) {
//		PenDownAction action = Actions.action(PenDownAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createPenUpAction(Sprite sprite) {
//		PenUpAction action = Actions.action(PenUpAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createSetPenSizeAction(Sprite sprite, Formula penSize) {
//		SetPenSizeAction action = Actions.action(SetPenSizeAction.class);
//		action.setSprite(sprite);
//		action.setPenSize(penSize);
//		return action;
//	}
//
//	public Action createSetPenColorAction(Sprite sprite, Formula red, Formula green, Formula blue) {
//		SetPenColorAction action = Actions.action(SetPenColorAction.class);
//		action.setSprite(sprite);
//		action.setRed(red);
//		action.setGreen(green);
//		action.setBlue(blue);
//		return action;
//	}
//
//	public Action createClearBackgroundAction() {
//		return Actions.action(ClearBackgroundAction.class);
//	}
//
//	public Action createStampAction(Sprite sprite) {
//		StampAction action = Actions.action(StampAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createNextLookAction(Sprite sprite) {
//		NextLookAction action = Actions.action(NextLookAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createPlaySoundAction(Sprite sprite, SoundInfo sound) {
//		PlaySoundAction action = Actions.action(PlaySoundAction.class);
//		action.setSprite(sprite);
//		action.setSound(sound);
//		return action;
//	}

    public Action3d createGlideToAction(Sprite sprite, Formula x, Formula y, Formula z, Formula duration) {
        GlideToAction action = Actions3d.action(GlideToAction.class);
        action.setPosition(x, y, z);
        action.setDuration(duration);
        action.setSprite(sprite);
        return action;
    }

//	public Action createPointToAction(Sprite sprite, Sprite pointedSprite) {
//		PointToAction action = Actions.action(PointToAction.class);
//		action.setSprite(sprite);
//		action.setPointedSprite(pointedSprite);
//		return action;
//	}
//
//	public Action createCloneAction(Sprite sprite) {
//		CloneAction action = Actions.action(CloneAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createDeleteThisCloneAction(Sprite sprite) {
//		DeleteThisCloneAction action = Actions.action(DeleteThisCloneAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createSetBrightnessAction(Sprite sprite, Formula brightness) {
//		SetBrightnessAction action = Actions.action(SetBrightnessAction.class);
//		action.setSprite(sprite);
//		action.setBrightness(brightness);
//		return action;
//	}
//
//	public Action createSetColorAction(Sprite sprite, Formula color) {
//		SetColorAction action = Actions.action(SetColorAction.class);
//		action.setSprite(sprite);
//		action.setColor(color);
//		return action;
//	}
//
//	public Action createSetTransparencyAction(Sprite sprite, Formula transparency) {
//		SetTransparencyAction action = Actions.action(SetTransparencyAction.class);
//		action.setSprite(sprite);
//		action.setTransparency(transparency);
//		return action;
//	}
//
//	public Action createSetLookAction(Sprite sprite, LookData lookData, @EventWrapper.WaitMode int waitMode) {
//		return createSetLookEventAction((SetLookAction) createSetLookAction(sprite, lookData), waitMode);
//	}
//
//	public Action createSetLookAction(Sprite sprite, LookData lookData) {
//		SetLookAction action = Actions.action(SetLookAction.class);
//		action.setSprite(sprite);
//		action.setLookData(lookData);
//		return action;
//	}
//
//	private Action createSetLookEventAction(SetLookAction action, @EventWrapper.WaitMode int waitMode) {
//		Project currentProject = ProjectManager.getInstance().getCurrentProject();
//		action.setWaitMode(waitMode);
//		action.setReceivingSprites(currentProject.getSpriteListWithClones());
//		return action;
//	}
//
//	public Action createSetLookByIndexAction(Sprite sprite, Formula formula, @EventWrapper.WaitMode int waitMode) {
//		return createSetLookEventAction((SetLookAction) createSetLookByIndexAction(sprite, formula), waitMode);
//	}
//
//	public Action createSetLookByIndexAction(Sprite sprite, Formula formula) {
//		SetLookByIndexAction action = Actions.action(SetLookByIndexAction.class);
//		action.setSprite(sprite);
//		action.setFormula(formula);
//		return action;
//	}
//
//	public Action createSetSizeToAction(Sprite sprite, Formula size) {
//		SetSizeToAction action = Actions.action(SetSizeToAction.class);
//		action.setSprite(sprite);
//		action.setSize(size);
//		return action;
//	}
//
//	public Action createSetVolumeToAction(Sprite sprite, Formula volume) {
//		SetVolumeToAction action = Actions.action(SetVolumeToAction.class);
//		action.setVolume(volume);
//		action.setSprite(sprite);
//		return action;
//	}

    public Action3d createMoveNStepsAction(Sprite sprite, Formula steps) {
        MoveNStepsAction action = Actions3d.action(MoveNStepsAction.class);
        action.setSprite(sprite);
        action.setSteps(steps);
        action.setDuration(1);
        return action;
    }

    public Action3d createPointInDirectionAction(Sprite sprite, Formula degrees) {
        PointInDirectionAction action = Actions3d.action(PointInDirectionAction.class);
        action.setSprite(sprite);
        action.setDegreesInUserInterfaceDimensionUnit(degrees);
        action.setDuration(1);
        return action;
    }

    public Action3d createSetXAction(Sprite sprite, Formula x) {
        SetXAction action = Actions3d.action(SetXAction.class);
        action.setSprite(sprite);
        action.setX(x);
        return action;
    }

//	public Action createShowAction(Sprite sprite) {
//		SetVisibleAction action = Actions.action(SetVisibleAction.class);
//		action.setSprite(sprite);
//		action.setVisible(true);
//		return action;
//	}
//
//	public Action createSpeakAction(Sprite sprite, Formula text) {
//		SpeakAction action = action(SpeakAction.class);
//		action.setSprite(sprite);
//		action.setText(text);
//		return action;
//	}
//
//	public Action createStopAllSoundsAction() {
//		return Actions.action(StopAllSoundsAction.class);
//	}

	public Action3d createTurnLeftAction(Sprite sprite, Formula degrees) {
		TurnLeftAction action = Actions3d.action(TurnLeftAction.class);
		action.setSprite(sprite);
		action.setDegrees(degrees);
		return action;
	}

	public Action3d createTurnRightAction(Sprite sprite, Formula degrees) {
		TurnRightAction action = Actions3d.action(TurnRightAction.class);
		action.setSprite(sprite);
		action.setDegrees(degrees);
		return action;
	}

    public Action3d createSetYAction(Sprite sprite, Formula y) {
        SetYAction action = Actions3d.action(SetYAction.class);
        action.setSprite(sprite);
        action.setY(y);
        return action;
    }

    public Action3d createSetZAction(Sprite sprite, Formula z) {
        SetZAction action = Actions3d.action(SetZAction.class);
        action.setSprite(sprite);
        action.setZ(z);
        return action;
    }

//	public Action createAskAction(Sprite sprite, Formula questionFormula, UserVariable answerVariable) {
//		AskAction action = Actions.action(AskAction.class);
//		action.setSprite(sprite);
//		action.setQuestionFormula(questionFormula);
//		action.setAnswerVariable(answerVariable);
//		return action;
//	}
//
//	public Action createAskSpeechAction(Sprite sprite, Formula questionFormula, UserVariable answerVariable) {
//		AskSpeechAction action = Actions.action(AskSpeechAction.class);
//		action.setSprite(sprite);
//		action.setQuestionFormula(questionFormula);
//		action.setAnswerVariable(answerVariable);
//		return action;
//	}

    public Action3d createChangeVariableAction(Sprite sprite, Formula variableFormula, UserVariable userVariable) {
        ChangeVariableAction action = Actions3d.action(ChangeVariableAction.class);
        action.setSprite(sprite);
        action.setChangeVariable(variableFormula);
        action.setUserVariable(userVariable);
        return action;
    }

    public Action3d createSetVariableAction(Sprite sprite, Formula variableFormula, UserVariable userVariable) {
        SetVariableAction action = Actions3d.action(SetVariableAction.class);
        action.setSprite(sprite);
        action.setChangeVariable(variableFormula);
        action.setUserVariable(userVariable);
        return action;
    }

    public Action3d createDeleteItemOfUserListAction(Sprite sprite, Formula userListFormula, UserList userList) {
        DeleteItemOfUserListAction action = action(DeleteItemOfUserListAction.class);
        action.setSprite(sprite);
        action.setFormulaIndexToDelete(userListFormula);
        action.setUserList(userList);
        return action;
    }

    public Action3d createAddItemToUserListAction(Sprite sprite, Formula userListFormula, UserList userList) {
        AddItemToUserListAction action = action(AddItemToUserListAction.class);
        action.setSprite(sprite);
        action.setFormulaItemToAdd(userListFormula);
        action.setUserList(userList);
        return action;
    }

//	public Action createThinkSayBubbleAction(Sprite sprite, Formula text, int type) {
//		ThinkSayBubbleAction action = action(ThinkSayBubbleAction.class);
//		action.setText(text);
//		action.setSprite(sprite);
//		action.setType(type);
//		return action;
//	}
//
//	public Action createThinkSayForBubbleAction(Sprite sprite, Formula text, int type) {
//		ThinkSayBubbleAction action = action(ThinkSayBubbleAction.class);
//		action.setText(text);
//		action.setSprite(sprite);
//		action.setType(type);
//		return action;
//	}
//
//	public Action createSceneTransitionAction(String sceneName) {
//		SceneTransitionAction action = action(SceneTransitionAction.class);
//		action.setScene(sceneName);
//		return action;
//	}
//
//	public Action createSceneStartAction(String sceneName) {
//		SceneStartAction action = action(SceneStartAction.class);
//		action.setScene(sceneName);
//		return action;
//	}

    public Action3d createInsertItemIntoUserListAction(Sprite sprite, Formula userListFormulaIndexToInsert,
                                                     Formula userListFormulaItemToInsert, UserList userList) {
        InsertItemIntoUserListAction action = action(InsertItemIntoUserListAction.class);
        action.setSprite(sprite);
        action.setFormulaIndexToInsert(userListFormulaIndexToInsert);
        action.setFormulaItemToInsert(userListFormulaItemToInsert);
        action.setUserList(userList);
        return action;
    }

    public Action3d createReplaceItemInUserListAction(Sprite sprite, Formula userListFormulaIndexToReplace,
                                                    Formula userListFormulaItemToInsert, UserList userList) {
        ReplaceItemInUserListAction action = action(ReplaceItemInUserListAction.class);
        action.setSprite(sprite);
        action.setFormulaIndexToReplace(userListFormulaIndexToReplace);
        action.setFormulaItemToInsert(userListFormulaItemToInsert);
        action.setUserList(userList);
        return action;
    }

//	public Action createWaitUntilAction(Sprite sprite, Formula condition) {
//		WaitUntilAction action = Actions.action(WaitUntilAction.class);
//		action.setSprite(sprite);
//		action.setCondition(condition);
//		return action;
//	}
//
//	public Action createRepeatUntilAction(Sprite sprite, Formula condition, Action repeatedAction) {
//		RepeatUntilAction action = action(RepeatUntilAction.class);
//		action.setRepeatCondition(condition);
//		action.setAction(repeatedAction);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createDelayAction(Sprite sprite, Formula delay) {
//		WaitAction action = Actions.action(WaitAction.class);
//		action.setSprite(sprite);
//		action.setDelay(delay);
//		return action;
//	}

    public Action3d createIfLogicAction(Sprite sprite, Formula condition, Action3d ifAction, Action3d elseAction) {
        IfLogicAction action = Actions3d.action(IfLogicAction.class);
        action.setIfAction(ifAction);
        action.setIfCondition(condition);
        action.setElseAction(elseAction);
        action.setSprite(sprite);
        return action;
    }

    public Action3d createRepeatAction(Sprite sprite, Formula count, Action3d repeatedAction) {
        RepeatAction action = Actions3d.action(RepeatAction.class);
        action.setRepeatCount(count);
        action.setAction(repeatedAction);
        action.setSprite(sprite);
        return action;
    }

    public Action3d createForeverAction(Sprite sprite, ScriptSequenceAction foreverSequence) {
        RepeatAction action = Actions3d.action(RepeatAction.class);
        action.setIsForeverRepeat(true);
        action.setAction(foreverSequence);
        action.setSprite(sprite);
        return action;
    }

    public Action3d createUserBrickAction(Action3d userBrickAction, UserBrick userBrick) {
        UserBrickAction action = action(UserBrickAction.class);
        action.setAction(userBrickAction);
        action.setUserBrick(userBrick);
        return action;
    }

    public Action3d setFirstPersonViewAction(Sprite sprite) {
        SetFirstPersonViewAction action = Actions3d.action(SetFirstPersonViewAction.class);
		action.setSprite(sprite);
		return action;
    }

    public Action3d resetFirstPersonViewAction(Sprite sprite) {
        ResetFirstPersonViewAction action = Actions3d.action(ResetFirstPersonViewAction.class);
        action.setSprite(sprite);
        return action;
    }

//	public Action createSetBounceFactorAction(Sprite sprite, Formula bounceFactor) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createTurnRightSpeedAction(Sprite sprite, Formula degreesPerSecond) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createTurnLeftSpeedAction(Sprite sprite, Formula degreesPerSecond) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createSetVelocityAction(Sprite sprite, Formula velocityX, Formula velocityY) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createSetPhysicsObjectTypeAction(Sprite sprite, PhysicsObject.Type type) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createSetMassAction(Sprite sprite, Formula mass) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createSetGravityAction(Sprite sprite, Formula gravityX, Formula gravityY) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createSetFrictionAction(Sprite sprite, Formula friction) {
//		throw new RuntimeException("No physics action available in non-physics sprite!");
//	}
//
//	public Action createDroneTakeOffAndLandAction() {
//		return action(DroneTakeoffAndLandAction.class);
//	}
//
//	public Action createDroneFlipAction() {
//		return action(DroneFlipAction.class);
//	}
//
//	public Action createDroneMoveUpAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneMoveUpAction action = action(DroneMoveUpAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneMoveDownAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneMoveDownAction action = action(DroneMoveDownAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneMoveLeftAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneMoveLeftAction action = action(DroneMoveLeftAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneMoveRightAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneMoveRightAction action = action(DroneMoveRightAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneMoveForwardAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneMoveForwardAction action = action(DroneMoveForwardAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneMoveBackwardAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneMoveBackwardAction action = action(DroneMoveBackwardAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneTurnRightAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneTurnRightAction action = action(DroneTurnRightAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneTurnLeftAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneTurnLeftAction action = action(DroneTurnLeftAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneTurnLeftMagnetoAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneTurnLeftWithMagnetometerAction action = action(DroneTurnLeftWithMagnetometerAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDroneTurnRightMagnetoAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		DroneTurnRightWithMagnetometerAction action = action(DroneTurnRightWithMagnetometerAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createDronePlayLedAnimationAction(ARDRONE_LED_ANIMATION ledAnimationType) {
//		DronePlayLedAnimationAction action = action(DronePlayLedAnimationAction.class);
//		action.setAnimationType(ledAnimationType);
//		return action;
//	}
//
//	public Action createDroneSwitchCameraAction() {
//		return action(DroneSwitchCameraAction.class);
//	}
//
//	public Action createDroneGoEmergencyAction() {
//		return action(DroneEmergencyAction.class);
//	}
//
//	public Action createJumpingSumoMoveForwardAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		JumpingSumoMoveForwardAction action = action(JumpingSumoMoveForwardAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createJumpingSumoMoveBackwardAction(Sprite sprite, Formula seconds, Formula powerInPercent) {
//		JumpingSumoMoveBackwardAction action = action(JumpingSumoMoveBackwardAction.class);
//		action.setSprite(sprite);
//		action.setDelay(seconds);
//		action.setPower(powerInPercent);
//		return action;
//	}
//
//	public Action createJumpingSumoAnimationAction(JumpingSumoAnimationsBrick.Animation animationType) {
//		JumpingSumoAnimationAction action = action(JumpingSumoAnimationAction.class);
//		action.setAnimationType(animationType);
//		return action;
//	}
//
//	public Action createJumpingSumoNoSoundAction() {
//		return action(JumpingSumoNoSoundAction.class);
//	}
//
//	public Action createJumpingSumoSoundAction(Sprite sprite, JumpingSumoSoundBrick.Sounds soundType, Formula volume) {
//		JumpingSumoSoundAction action = action(JumpingSumoSoundAction.class);
//		action.setSoundType(soundType);
//		action.setSprite(sprite);
//		action.setVolume(volume);
//		return action;
//	}
//
//	public Action createJumpingSumoJumpLongAction() {
//		return action(JumpingSumoJumpLongAction.class);
//	}
//
//	public Action createJumpingSumoJumpHighAction() {
//		return action(JumpingSumoJumpHighAction.class);
//	}
//
//	public Action createJumpingSumoRotateLeftAction(Sprite sprite, Formula degree) {
//		JumpingSumoRotateLeftAction action = action(JumpingSumoRotateLeftAction.class);
//		action.setSprite(sprite);
//		action.setDegree(degree);
//		return action;
//	}
//
//	public Action createJumpingSumoRotateRightAction(Sprite sprite, Formula degree) {
//		JumpingSumoRotateRightAction action = action(JumpingSumoRotateRightAction.class);
//		action.setSprite(sprite);
//		action.setDegree(degree);
//		return action;
//	}
//
//	public Action createJumpingSumoTurnAction() {
//		return action(JumpingSumoTurnAction.class);
//	}
//
//	public Action createJumpingSumoTakingPictureAction() {
//		return action(JumpingSumoTakingPictureAction.class);
//	}
//
//	public Action createSetTextAction(Sprite sprite, Formula x, Formula y, Formula text) {
//		SetTextAction action = action(SetTextAction.class);
//
//		action.setPosition(x, y);
//		action.setText(text);
//		action.setDuration(5);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createShowVariableAction(Sprite sprite, Formula xPosition, Formula yPosition, UserVariable userVariable) {
//		ShowTextAction action = action(ShowTextAction.class);
//		action.setPosition(xPosition, yPosition);
//		action.setVariableToShow(userVariable);
//		action.setSprite(sprite);
//		UserBrick userBrick = ProjectManager.getInstance().getCurrentUserBrick();
//		action.setUserBrick(userBrick);
//		return action;
//	}
//
//	public Action createHideVariableAction(Sprite sprite, UserVariable userVariable) {
//		HideTextAction action = action(HideTextAction.class);
//		action.setVariableToHide(userVariable);
//		UserBrick userBrick = ProjectManager.getInstance().getCurrentUserBrick();
//		action.setUserBrick(userBrick);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createTurnFlashOnAction() {
//		FlashAction action = action(FlashAction.class);
//		action.turnFlashOn();
//		return action;
//	}
//
//	public Action createTurnFlashOffAction() {
//		FlashAction action = action(FlashAction.class);
//		action.turnFlashOff();
//		return action;
//	}
//
//	public Action createVibrateAction(Sprite sprite, Formula duration) {
//		VibrateAction action = action(VibrateAction.class);
//		action.setSprite(sprite);
//		action.setDuration(duration);
//		return action;
//	}

//	public Action createUpdateCameraPreviewAction(CameraManager.CameraState state) {
//		CameraBrickAction action = action(CameraBrickAction.class);
//		action.setCameraAction(state);
//		return action;
//	}

//	public Action createSetFrontCameraAction() {
//		ChooseCameraAction action = action(ChooseCameraAction.class);
//		action.setFrontCamera();
//		return action;
//	}
//
//	public Action createSetBackCameraAction() {
//		ChooseCameraAction action = action(ChooseCameraAction.class);
//		action.setBackCamera();
//		return action;
//	}
//
//	public Action createSendDigitalArduinoValueAction(Sprite sprite, Formula pinNumber,
//                                                      Formula
//					pinValue) {
//		ArduinoSendDigitalValueAction action = action(ArduinoSendDigitalValueAction.class);
//		action.setSprite(sprite);
//		action.setPinNumber(pinNumber);
//		action.setPinValue(pinValue);
//		return action;
//	}
//
//	public Action createSendPWMArduinoValueAction(Sprite sprite, Formula pinNumber, Formula
//			pinValue) {
//		ArduinoSendPWMValueAction action = action(ArduinoSendPWMValueAction.class);
//		action.setSprite(sprite);
//		action.setPinNumber(pinNumber);
//		action.setPinValue(pinValue);
//		return action;
//	}
//
//	public Action createSendDigitalRaspiValueAction(Sprite sprite, Formula pinNumber,
//                                                    Formula pinValue) {
//		RaspiSendDigitalValueAction action = action(RaspiSendDigitalValueAction.class);
//		action.setSprite(sprite);
//		action.setPinNumber(pinNumber);
//		action.setPinValue(pinValue);
//		return action;
//	}
//
//	public Action createSendRaspiPwmValueAction(Sprite sprite, Formula pinNumber, Formula
//			pwmFrequency, Formula pwmPercentage) {
//		RaspiPwmAction action = action(RaspiPwmAction.class);
//		action.setSprite(sprite);
//		action.setPinNumberFormula(pinNumber);
//		action.setPwmFrequencyFormula(pwmFrequency);
//		action.setPwmPercentageFormula(pwmPercentage);
//		return action;
//	}
//
//	public Action createRaspiIfLogicActionAction(Sprite sprite, Formula pinNumber, Action ifAction,
//                                                 Action elseAction) {
//		RaspiIfLogicAction action = action(RaspiIfLogicAction.class);
//		action.setSprite(sprite);
//		action.setPinNumber(pinNumber);
//		action.setIfAction(ifAction);
//		action.setElseAction(elseAction);
//		return action;
//	}
//
//	public Action createPreviousLookAction(Sprite sprite) {
//		PreviousLookAction action = action(PreviousLookAction.class);
//		action.setSprite(sprite);
//		return action;
//	}
//
//	public Action createStopScriptAction(int spinnerSelection, Script currentScript) {
//		switch (spinnerSelection) {
//			case BrickValues.STOP_THIS_SCRIPT:
//				StopThisScriptAction stopThisScriptAction = Actions.action(StopThisScriptAction.class);
//				stopThisScriptAction.setCurrentScript(currentScript);
//				return stopThisScriptAction;
//			case BrickValues.STOP_OTHER_SCRIPTS:
//				StopOtherScriptsAction stopOtherScriptsAction = Actions.action(StopOtherScriptsAction.class);
//				stopOtherScriptsAction.setCurrentScript(currentScript);
//				return stopOtherScriptsAction;
//			default:
//				return Actions.action(StopAllScriptsAction.class);
//		}
//	}
//
//	public Action createSetNfcTagAction(Sprite sprite, Formula nfcNdefMessage, int nfcNdefSpinnerSelection) {
//		SetNfcTagAction setNfcTagAction = Actions.action(SetNfcTagAction.class);
//		setNfcTagAction.setSprite(sprite);
//		setNfcTagAction.setNfcTagNdefSpinnerSelection(nfcNdefSpinnerSelection);
//		setNfcTagAction.setNfcNdefMessage(nfcNdefMessage);
//		return setNfcTagAction;
//	}
}
