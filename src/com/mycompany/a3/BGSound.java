package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	private Media m;
	
	public BGSound(String filename) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+filename);
			
			m=MediaManager.createMedia(is, "audio/mp3", this);
			m.setVolume(50);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void play() {
		m.play();
	}
	
	public void pause() {
		m.pause();
	}

	@Override
	public void run() {
		System.out.println("Running music.");
		m.setTime(0);
		m.play();
		
	}

} 
