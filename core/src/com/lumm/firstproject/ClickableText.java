package com.lumm.firstproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.sidereal.lumm.architecture.LummScene;
import com.sidereal.lumm.architecture.core.Input;
import com.sidereal.lumm.architecture.core.input.ActionData;
import com.sidereal.lumm.architecture.core.input.OnClickListener;
import com.sidereal.lumm.components.input.Clickable;
import com.sidereal.lumm.ui.TextBuilder;

public class ClickableText extends TextBuilder {

	
	private Clickable clickable;
	int size;
	public ClickableText(LummScene scene, String text, boolean wrap, OnClickListener listener) {
		super(scene, wrap);
		
		size = (int)(Gdx.graphics.getWidth()/2f);
		setWindowSize(size);
		
		
		clickable = new Clickable(this);
		clickable.addOnClickListener(Input.DEFAULT_INPUT_PROCESSOR, Input.Action.FINGER_1, listener, Input.ActionType.Up, true);
		setText(text, Color.WHITE);
		
		
		clickable.setAreaSize(size, bounds.y, -size/2, -bounds.y/2);
		setAllign(Allign.Right);
		setScale(Gdx.graphics.getWidth()/250);
		setLineSpacing(Gdx.graphics.getHeight()*0.05f); 
	}
	
	
	@Override
	public void onRender() {
		// TODO Auto-generated method stub
		super.onRender();
	
		size = (int)(Gdx.graphics.getWidth()/2f);
		clickable.setAreaSize(size, bounds.y, -size, -bounds.y/2);
		setScale(Gdx.graphics.getWidth()/250);
		setLineSpacing(Gdx.graphics.getHeight()*0.05f);

	}
	
	
	
}
