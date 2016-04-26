package com.lumm.firstproject;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummObject;
import com.sidereal.lumm.architecture.core.Audio.AudioChannel;
import com.sidereal.lumm.architecture.core.Debug.Log;
import com.sidereal.lumm.architecture.core.input.ActionData;
import com.sidereal.lumm.architecture.core.input.ActionEvent;
import com.sidereal.lumm.architecture.core.input.OnActionEventListener;
import com.sidereal.lumm.architecture.core.input.Input;
import com.sidereal.lumm.architecture.core.input.Input.ActionType;
import com.sidereal.lumm.components.audio.AudioSource;
import com.sidereal.lumm.components.audio.MusicClip;


public class RainDropCreator extends LummObject{
	private Random rand;
	
	private AudioSource source;
	private MusicClip clip;
	
	float timeRemaining;
	float timePerDrop;
	
	@Override
	protected void onCreate (Object... params) {
		
		rand = new Random();
		timeRemaining = timePerDrop = 1f;

		source = new AudioSource(this);
		clip = new MusicClip("rain.mp3", AudioChannel.Master, source);
		clip.setLooping(true);
		clip.play();

	}
	
	@Override
	protected void onUpdate () {
		timeRemaining-= Lumm.time.getDeltaTime();
		if(timeRemaining<= timePerDrop)
		{
			timeRemaining+= timePerDrop;
			
			RainDrop drop = 	new RainDrop();
			drop.position.set(rand.nextInt(Gdx.graphics.getWidth()-RainDrop.size) + RainDrop.size/2f, Gdx.graphics.getHeight() + RainDrop.size/2f);
		}
	
		Lumm.input.addActionEvent(Input.DEFAULT_INPUT_PROCESSOR, Input.Action.KEY_ESCAPE, new ActionEvent() {
			
			public boolean run(ActionData inputData) {
				
				boolean pause = !Lumm.isPaused();
				
				Lumm.pause(pause);
				if(pause)
				{
					if(Lumm.debug.isLogging())
					{
						Lumm.debug.finishLog();					
						return true;
					}
				}
				else
				{
					Lumm.debug.startLog(Log.LOG_ALL);
					return true;
				}
				
				return false;
				
			}
		}, ActionType.Up);
		
		
	}

	@Override
	protected void onRender () {
	}

	@Override
	protected void onSceneChange () {
	}

	@Override
	protected void onDispose () {
	}

	@Override
	protected void onResize (float x, float y, float oldX, float oldY) {
	}

	@Override
	protected void onPause (boolean pause) {
		if(pause) clip.pause();
		else clip.resume();
		
	}

}
