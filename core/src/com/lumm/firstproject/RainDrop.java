package com.lumm.firstproject;

import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummObject;
import com.sidereal.lumm.architecture.core.Input;
import com.sidereal.lumm.architecture.core.Audio.AudioChannel;
import com.sidereal.lumm.architecture.core.Input.Action;
import com.sidereal.lumm.architecture.core.Input.ActionType;
import com.sidereal.lumm.architecture.core.input.ActionData;
import com.sidereal.lumm.architecture.core.input.OnClickListener;
import com.sidereal.lumm.architecture.core.input.TouchData;
import com.sidereal.lumm.architecture.core.input.OnTouchListener;
import com.sidereal.lumm.architecture.listeners.OnDisposeListener;
import com.sidereal.lumm.architecture.listeners.OnPauseListener;
import com.sidereal.lumm.components.audio.AudioSource;
import com.sidereal.lumm.components.audio.SoundClip;
import com.sidereal.lumm.components.input.Clickable;
import com.sidereal.lumm.components.renderer.Renderer;
import com.sidereal.lumm.components.renderer.texture.TextureBuilder;
import com.sidereal.lumm.components.triggers.Collider;
import com.sidereal.lumm.components.triggers.CollisionArea;
import com.sidereal.lumm.components.triggers.OnColliderEventListener;

public class RainDrop extends LummObject {

	private Renderer renderer;
	private Collider collider;
	private Clickable clickable;
	private AudioSource audioSource;
	private SoundClip soundClip;
	public static int size = 100;

	@Override
	protected void onCreate(Object... params) {

		// add a rendering component to the system.
		renderer = new Renderer(this);
		TextureBuilder textureBuilder = new TextureBuilder("droplet.png")
				.setSize(size, size)
				.setOffsetPosition(-size / 2f, -size / 2f);
		renderer.addDrawer("Main", textureBuilder);

		audioSource = new AudioSource(this);
		soundClip = new SoundClip("drop.wav", AudioChannel.Effects, audioSource);

		// add a collision detection component to the system
		collider = new Collider(this);
		collider.addCollisionArea(new CollisionArea("Default", size));
		collider.setOnColliderEventListener(new OnColliderEventListener() {

			@Override
			public void onCollisionEvent(Collider ownCollider, Collider targetCollider, int collisionStatus) {

				if (collisionStatus == Collider.COLLISION_ENTER) {
					Lumm.debug.log("Target collider is: "+targetCollider.object.getName(), null);
					// if(targetCollider.object instanceof Bucket)
					// {
//					soundClip.play();
					// }
				}

			}
		});

		clickable = new Clickable(this);
		clickable.setAreaSize(size, size);
		clickable.addTouchEvent(Input.DEFAULT_INPUT_PROCESSOR, Input.Action.ANY_ACTION, new OnTouchListener() {

			@Override
			public boolean run(TouchData inputData) {

				Lumm.debug.log(Action.toString(inputData.getCode()), null);
				getScene().removeobject(RainDrop.this);
				return true; // the action event will stop here and not trigger items "below it"
			}
		}, ActionType.Up, true);

		onPauseListener = new OnPauseListener<LummObject>() {

			@Override
			public void onPause(LummObject caller, boolean value) {

				if (value)
					soundClip.pause();
				else
					soundClip.resume();
			}
		};
		
		onDisposeListener = new OnDisposeListener<LummObject>() {
			
			@Override
			public void onDispose(LummObject caller) {
				soundClip.play();
			}
		};
	}

	@Override
	protected void onUpdate() {
		position.setRelative(0, -Lumm.time.getDeltaTime() * 100);
		if (position.getY() < -size / 2) {
//			soundClip.play();
			Lumm.debug.log("Removing raindrop", null);
			getScene().removeobject(this);
		}
	}

	@Override
	protected void onRender() {

	}

	@Override
	protected void onSceneChange() {

	}

}
