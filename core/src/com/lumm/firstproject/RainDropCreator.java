package com.lumm.firstproject;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.sidereal.lumm.architecture.AbstractEvent;
import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummObject;
import com.sidereal.lumm.architecture.core.Input;
import com.sidereal.lumm.architecture.core.Audio.AudioChannel;
import com.sidereal.lumm.architecture.core.Debug;
import com.sidereal.lumm.architecture.core.Debug.Log;
import com.sidereal.lumm.architecture.core.Input.ActionType;
import com.sidereal.lumm.architecture.core.Net.NetRequestQueue;
import com.sidereal.lumm.architecture.core.input.ActionData;
import com.sidereal.lumm.architecture.core.input.OnClickListener;
import com.sidereal.lumm.architecture.core.input.OnActionEventListener;
import com.sidereal.lumm.architecture.listeners.OnPauseListener;
import com.sidereal.lumm.components.audio.AudioSource;
import com.sidereal.lumm.components.audio.MusicClip;

public class RainDropCreator extends LummObject {
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
		
		onPauseListener = new OnPauseListener<LummObject>() {
			
			@Override
			public void onPause(LummObject caller, boolean value) {
				if(value) clip.pause();
				else clip.resume();
			}
		};
		
		OnClickListener event = new OnClickListener() {
			@Override
			public boolean onClick(ActionData inputData) {
				// TODO Auto-generated method stub
				
				
				switch(inputData.code)
				{
				case Input.Action.KEY_Q:
					Lumm.net.getLNetSession();
					Lumm.debug.log("Pressed Q", null);

					break;
				case Input.Action.KEY_NUM_1:
					if(!Lumm.debug.isLogging())
						Lumm.debug.startLog(Debug.Log.LOG_ALL);
					Lumm.debug.log("Pressed 1", null);

					break;
				case Input.Action.KEY_NUM_2:
					if(Lumm.debug.isLogging())
						Lumm.debug.finishLog();
					Lumm.debug.log("Pressed 2", null);

					break;
				case Input.Action.KEY_NUM_3:
					
					if(Lumm.net.isInSession())
					{
						String x = null;
						Integer y = Integer.parseInt(x);
					}
					
					break;
				}
				
				
				
				return super.onClick(inputData);
			
			
			}
		};
		
		
		Lumm.input.addOnClickListener(Input.DEFAULT_INPUT_PROCESSOR, Input.Action.ANY_ACTION, event, ActionType.Up);

	}

	@Override
	protected void onUpdate() {
		timeRemaining -= Lumm.time.getDeltaTime();
		if (timeRemaining <= timePerDrop) {
			timeRemaining += timePerDrop;

			RainDrop drop = new RainDrop();
			drop.position.set(rand.nextInt(Gdx.graphics.getWidth() - RainDrop.size) + RainDrop.size / 2f,
					Gdx.graphics.getHeight() + RainDrop.size / 2f);
		}

		Lumm.input.addOnClickListener(Input.DEFAULT_INPUT_PROCESSOR, Input.Action.KEY_ESCAPE, new OnClickListener() {

			public boolean onClick(ActionData inputData) {

				boolean pause = !Lumm.isPaused();

				Lumm.pause(pause);
				if (pause) {
					if (Lumm.debug.isLogging()) {
						Lumm.debug.finishLog();
						return true;
					}
				} else {
					Lumm.debug.startLog(Log.LOG_ALL);
					return true;
				}

				return false;

			}
		}, ActionType.Up);

	}

	@Override
	protected void onRender() {
	}

	@Override
	protected void onSceneChange() {
	}

}
