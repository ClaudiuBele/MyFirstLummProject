package com.lumm.firstproject;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummScene;
import com.sidereal.lumm.architecture.core.input.ActionData;
import com.sidereal.lumm.architecture.core.input.OnActionEventListener;
import com.sidereal.lumm.architecture.core.input.Input;
import com.sidereal.lumm.architecture.core.input.TouchData;
import com.sidereal.lumm.architecture.core.input.TouchEvent;

public class GameScreen extends LummScene {

	@Override
	protected void onCreate (Object[] params) {
		
		Lumm.assets.load("drop.wav", Sound.class);
		Lumm.assets.load("rain.mp3", Music.class);
		
		new RainDropCreator();
		bgColor.set(1,1,1,0);
		
//		Lumm.input.addTouchEvent(Input.DEFAULT_INPUT_PROCESSOR, Input.InputAction.FINGER_1, new TouchEvent() {
//			
//			@Override
//			public boolean run(TouchData inputData) {
//				
//				Lumm.debug.log("Clicked");
//				// TODO Auto-generated method stub
//				return true;
//			}
//		}, InputEventType.Up);
//		
//		Lumm.input.addActionEvent(Input.DEFAULT_INPUT_PROCESSOR, Input.InputAction.ANY_ACTION, new ActionEvent() {
//		
//			@Override
//			public boolean run(ActionData inputData) {
//				
//				Lumm.debug.log(Input.InputAction.toString(inputData.getCode()));
//				
//				return super.run(inputData);
//			}
//		}, InputEventType.Up);
	}

	@Override
	protected void onCreateSceneLayers () {
		
	}

	@Override
	protected void onPause (boolean value) {
		
	}
}
