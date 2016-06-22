package com.lumm.firstproject.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lumm.firstproject.GameScreen;
import com.sidereal.lumm.architecture.Lumm;
import com.sidereal.lumm.architecture.LummConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.height = LwjglApplicationConfiguration.getDisplayModes()[0].height;
		config.width = LwjglApplicationConfiguration.getDisplayModes()[0].width;

		try {

			LummConfiguration lConfig = new LummConfiguration();
			lConfig.debugEnabled = true;
			lConfig.applicationLNetKey = "ZCDV3GMJYT58Y32CCYDR8DABX";
			lConfig.applicationVersion = "0.1.2";
			lConfig.isExecutable = true;
			lConfig.startDebugLogOnStartup = true;

			Lumm lumm = new Lumm(new GameScreen(), lConfig);

			new LwjglApplication(lumm, config);

		} catch (Exception e) {
			if(Lumm.net != null)
			{
				Lumm.net.logThrowable(e);
			}
		}
	}
}
	