package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Component;

public class Util {
	
	public enum ObjectType {Asteroid, MissileLauncher, NPS, NPSMissile, PlayerShip, PlayerMissile, Station}
	public enum Direction{left, right};
	public static final int MAX_HEADING = 360;
	public static final int MAX_SPEED = 7;
	public static boolean SOUND_DEFAULT=true;	//false when using Spotify
	public static final int TIME_INCREMENT=20;
	
	//pseudo constants declared when Map is rendered
	private static int MAX_WIDTH;
	private static int MAX_HEIGHT;
	
	public static void init(Component map) {
		MAX_WIDTH=map.getWidth();
		MAX_HEIGHT=map.getHeight();
	}
	
	public static int getMaxHeight() {
		return MAX_HEIGHT;
	}
	
	public static int getMaxWidth() {
		return MAX_WIDTH;
	}
	
	/**
	 * Uses java.util.Random to generate a random number between parameters.
	 * Useful for initializations.
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt(int min, int max) {
		Random random=new Random();
		return (random.nextInt(max-min+1)+min);
	}
	
	public static double findDistance(GameObject go1, GameObject go2) {
		double x1, x2, y1, y2, dx, dy;
		x1=go1.getX();
		y1=go1.getY();
		x2=go2.getX();
		y2=go2.getY();
		
		dx=x2-x1;
		dy=y2-y1;
		
		return Math.sqrt(dx*dx+dy*dy);
	}
	
}
