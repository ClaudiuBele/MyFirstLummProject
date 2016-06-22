package com.lumm.firstproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummObject;
import com.sidereal.lumm.architecture.LummScene;
import com.sidereal.lumm.architecture.LummSceneLayer;
import com.sidereal.lumm.architecture.core.Audio.AudioChannel;
import com.sidereal.lumm.architecture.core.Debug;
import com.sidereal.lumm.architecture.core.Input;
import com.sidereal.lumm.architecture.core.input.ActionData;
import com.sidereal.lumm.architecture.core.input.OnActionEventListener;
import com.sidereal.lumm.architecture.core.input.OnClickListener;
import com.sidereal.lumm.architecture.core.input.TouchData;
import com.sidereal.lumm.architecture.core.input.OnTouchListener;
import com.sidereal.lumm.architecture.listeners.OnResizeListener;
import com.sidereal.lumm.architecture.listeners.OnUpdateListener;
import com.sidereal.lumm.ui.TextBuilder;

public class GameScreen extends LummScene {

	private ClickableText startSession,startLog,endLog,generateException;
	@Override
	protected void onCreate (Object[] params) {
		
		Lumm.assets.load("drop.wav", Sound.class);
		Lumm.assets.load("rain.mp3", Music.class);
		
		new RainDropCreator();
		bgColor.set(1,1,1,0);
		
		Lumm.net.startLNet();
		
		
		LummSceneLayer layer = getSceneLayer(LummSceneLayer.DEFAULT_SCENE_LAYER);
		generateException = new ClickableText(this, "Throw exception", true, new OnClickListener() {
			
			@Override
			public boolean onClick(ActionData inputData) {
				
				if(Lumm.net.isInSession())
				{
					String x = null;
					Integer y = Integer.parseInt(x);
				}
				return true;
			}
		});
		startSession = new ClickableText(this, "Start session", true, new OnClickListener() {
			
			@Override
			public boolean onClick(ActionData inputData) {

				Lumm.net.getLNetSession();
				
				return true;
			}
			
		});
		startLog = new ClickableText(this, "Start log", true, new OnClickListener() {
			
			@Override
			public boolean onClick(ActionData inputData) {
				
				if(!Lumm.debug.isLogging())
					Lumm.debug.startLog(Debug.Log.LOG_ALL);
				
				return true;
			}
		});
		endLog = new ClickableText(this, "End log", true, new OnClickListener() {
		
			@Override
			public boolean onClick(ActionData inputData) {
				if(Lumm.debug.isLogging())
					Lumm.debug.finishLog();
				
				return true;
				
			}
		});
		
		OnResizeListener<LummObject> listener = new OnResizeListener<LummObject>() {
			
			@Override
			public void onResize(LummObject caller, float x, float y, float oldX, float oldY) {
				
				setPosition( (int)(x), (int)(y), startSession);
				setPosition( (int)(x), (int)(y), startLog);
				setPosition( (int)(x), (int)(y), endLog);
				setPosition( (int)(x), (int)(y), generateException);
				
			}
		};
		listener.attachTo(startSession,startLog,endLog,generateException);
		
		setPosition( layer.camera.position.x+layer.camera.viewportWidth/2, Gdx.graphics.getHeight() , startSession);
		setPosition( layer.camera.position.x+layer.camera.viewportWidth/2, Gdx.graphics.getHeight(), startLog);
		setPosition( layer.camera.position.x+layer.camera.viewportWidth/2, Gdx.graphics.getHeight(), endLog);
		setPosition( layer.camera.position.x+layer.camera.viewportWidth/2, Gdx.graphics.getHeight(), generateException);

	}

	private void setPosition(float x, float y, LummObject caller)
	{
		int yOffset = 0;
		if(caller.equals(startSession))
		{	
			startSession.position.set(x - x/100 * 5, y - startSession.bounds.y - yOffset);
			return;
		}
		yOffset+= startSession.bounds.y + 20 ;
		if(caller.equals(startLog))
		{	startLog.position.set(x - x/100 * 5, y - startLog.bounds.y  - yOffset);
			return;
		}
		yOffset+= startLog.bounds.y + 20 ;
		if(caller.equals(endLog))
		{	endLog.position.set(x - x/100 * 5, y - endLog.bounds.y - yOffset);
			return;
		}
		yOffset+= endLog.bounds.y + 20 ;
		if(caller.equals(generateException))
		{	generateException.position.set(x - x/100 * 5, y - generateException.bounds.y - yOffset);
			return;
		}
	}
	
	@Override
	protected void onCreateSceneLayers () {
		
	}

	@Override
	protected void onPause (boolean value) {
		Lumm.audio.setVolume(AudioChannel.Master, value ? 0 : 1);
	}
	
	
}
