package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MissileLauncher extends MoveableObject{

	private Ship ship;
	
	public MissileLauncher(Ship ship){
		this.ship=ship;
		setSize(ship.getSize());
		setColor(ColorUtil.YELLOW);
		setX(ship.getX());
		setY(ship.getY());
		setHeading(ship.getHeading());
		setSpeed(ship.getSpeed());
	}
	
	/**
	 * The following are getter overrides.
	 * MissileLauncher should not have its own members for
	 * x, y and speed: it should be the same as its ship's
	 */
	public int getSpeed() {return ship.getSpeed();}
	public double getX() {return ship.getX();}
	public double getY() {return ship.getY();}
	
	/**
	 * Creates missile with heading of owning Launcher
	 */
	public Missile fire() {
		return new NPSMissile(this);
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		super.draw(g, pCmpRelPrnt);
		int x1=(int) (getX()+pCmpRelPrnt.getX());
		int y1=(int) (getY()+pCmpRelPrnt.getY());
		int x2=(int) (x1+(Math.cos(Math.toRadians(90-getHeading())))*getSize());
		int y2=(int) (y1+(Math.sin(Math.toRadians(90-getHeading())))*getSize());
		g.drawLine(x1, y1, x2, y2);
		//g.drawRect((int)(getX()+pCmpRelPrnt.getX()), (int)(getY()+pCmpRelPrnt.getY()), getSize()/10, getSize());
	}

}
