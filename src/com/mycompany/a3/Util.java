package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Component;

public class Util {
	
	public enum ObjectType {Asteroid, MissileLauncher, NPS, NPSMissile, PlayerShip, PlayerMissile, Station}
	public enum Direction{left, right};
	public static final int MAX_HEADING = 360;
	public static final int MAX_SPEED = 7;
	public static final int MISSILE_MAX_FUEL = 50;
	public static boolean SOUND_DEFAULT=false;	//false when using Spotify
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

	/** Square root from 3 */
	final static public double SQRT3 = 1.732050807568877294;

	static public double atan(double x)
	{
    boolean signChange=false;
    boolean Invert=false;
    int sp=0;
    double x2, a;
    // check up the sign change
    if(x<0.){
        x=-x;
        signChange=true;
    }
    // check up the invertation
    if(x>1.){
        x=1/x;
        Invert=true;
    }
    // process shrinking the domain until x<PI/12
    while(x>Math.PI/12){
        sp++;
        a=x+SQRT3;
        a=1/a;
        x=x*SQRT3;
        x=x-1;
        x=x*a;
    }
    // calculation core
    x2=x*x;
    a=x2+1.4087812;
    a=0.55913709/a;
    a=a+0.60310579;
    a=a-(x2*0.05160454);
    a=a*x;
    // process until sp=0
    while(sp>0){
        a=a+Math.PI/6;
        sp--;
    }
    // invertation took place
    if(Invert) a=Math.PI/2-a;
    // sign change took place
    if(signChange)
    	a=-a;
    return a;
	}    
	
}
