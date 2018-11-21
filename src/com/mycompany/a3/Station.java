package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Station extends GameObject implements ICollider{
	private int blinkrate, timer;
	private final static int BLINKRATE_MIN=1;
	private final static int BLINKRATE_MAX=4;
	
	public Station(){
		setBlinkrate(Util.randomInt(BLINKRATE_MIN, BLINKRATE_MAX));
		setColor(ColorUtil.rgb(0, 0xb4, 0xff));
		setSize(100);
		//initialize to a lovely blue
		timer=0;	//nanoseconds passed
	}
	
	public int getBlinkrate() {return blinkrate;}
	
	/**
	 * Checks to make sure blinkrate it within acceptable range when assigning
	 * @param blinkrate
	 */
	private void setBlinkrate(int blinkrate) {
		if(blinkrate>0 && blinkrate<=BLINKRATE_MAX)
			this.blinkrate=blinkrate;
		else
			System.err.println("Error setting blinkrate to "+blinkrate);
	}
	
	public String toString() {
		return "Station: " + super.toString()+"rate="+getBlinkrate()+" ";
	}

	/**
	 * Stations cannot be destroyed.
	 */
	@Override
	public boolean handleCollision(ICollider otherObject, GameWorld gw) {
		return false;
	}

	/**
	 * returns true if the distance is less than the sum of the radii
	 */
	@Override
	public boolean collidesWith(ICollider otherObject) {
		GameObject check = (GameObject) otherObject;
		return Util.findDistance(this, check)<=((getSize()/2+check.getSize()/2));
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		super.draw(g, pCmpRelPrnt);
		int x= (int)(pCmpRelPrnt.getX()+getX())-getSize()/2;
		int y=(int)(pCmpRelPrnt.getY()+getY())-getSize()/2;
		
		timer+=50;
		if(timer>=blinkrate*1000) {
			g.drawRect(x, y, getSize(), getSize());
			if (timer>blinkrate*1000+1000)
				timer=0;
		}
		else {
			g.fillRect(x,y, getSize(), getSize());
		}
	}
}
