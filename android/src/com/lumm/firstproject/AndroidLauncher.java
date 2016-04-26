package com.lumm.firstproject;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;
import com.sidereal.lumm.architecture.Lumm;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		// set up config to not take entire screen
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.resolutionStrategy = new FillResolutionStrategy();
		config.r = config.g = config.b = config.a = 8;
		
		initialize( new Lumm(new GameScreen()),config);
		
//		// inflate layout
//		setContentView(R.layout.activity_main);
//		
//		// get view to add Lumm to ( Lumm is an ECS for Libgdx) and set up layout params
//		RelativeLayout lummRoot = (RelativeLayout) findViewById(R.id.main_lumm_container);
//		RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		
//		// make Surface View and add it to the proper View
//		View view = initializeForView(new Lumm(new GameScreen()), config);
////		makeSurfaceViewTransparent((GLSurfaceView20) view);
//		view.setLayoutParams(params);
//		((ViewGroup) lummRoot).addView(view);
//		
//		// just making some views to add to a listView to see if it works
//		View listview = findViewById(R.id.main_list_container);
//		LayoutInflater inflater = getLayoutInflater();
//		for(int i=0;i<10;i++)
//		{
//			inflater.inflate(R.layout.view_list_item, ((ViewGroup) listview));
//		}
//		
		
		
	}
	
	// this works provided that the color used for clearing the OpenGL content is cleared using a color with 0 on the alpha channel
	// this also makes the surface to ALWAYS be rendered on top of any other views, so it should be used only for things like overlays 
	private void makeSurfaceViewTransparent(GLSurfaceView20 view)
	{
//		view.getHolder().setFormat(PixelFormat.TRANSLUCENT);
//		view.setZOrderOnTop(true);
	}
}
