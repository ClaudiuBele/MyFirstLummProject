package com.lumm.firstproject;

import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummObject;
import com.sidereal.lumm.components.renderer.Renderer;
import com.sidereal.lumm.components.renderer.texture.TextureBuilder;

public class Bucket extends LummObject {
	
	private float size = 200;
	
	@Override
	protected void onCreate(Object... params) {


		Renderer render = new Renderer(this);
		
		TextureBuilder builder = new TextureBuilder("bucket.png")
				.setSize(size, size)
				.setOffsetPosition(-size/2, -size/2);
		
		
		render.addDrawer("Main", builder);
		
		
		

		
		
	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSceneChange() {
		// TODO Auto-generated method stub
		
	}

}
