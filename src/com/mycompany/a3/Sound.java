package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media m;
	
	public Sound(String filename) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+filename);
			
			m=MediaManager.createMedia(is, "audio/mp3");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void play() {
		if(m==null)
			System.err.println("Error: sound is null");
		else {
			m.setTime(0);
			m.play();
		}
	}

}
