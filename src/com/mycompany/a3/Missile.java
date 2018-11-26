package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public abstract class Missile extends MoveableObject implements ICollider, ISelectable{
	private int fuelLevel;
	private static int SPEED_INCREMENT = 10;
	public static final int MISSILE_MAX_FUEL = 30;
	private boolean selected;
	
	/**
	 * Copies the speed and heading of the missileLauncher that fired it.
	 * @param speed
	 * @param heading
	 */
	Missile(MissileLauncher ml){
		setSpeed(ml.getSpeed()+SPEED_INCREMENT);
		setHeading(ml.getHeading());
		setX(ml.getX()+(ml.getSize()*Math.cos(Math.toRadians(90-ml.getHeading()))));
		setY(ml.getY()+(ml.getSize()*Math.sin(Math.toRadians(90-ml.getHeading()))));
		fuelLevel=MISSILE_MAX_FUEL;
		setSize(30);
		selected=false;
	}

	/**
	 * Checks to see if fuel is not depleted, and increments position if there is still fuel
	 * Returns false(and is destroyed) if fuel is depleted or we hit the edge of the screen
	 */
	@Override
	public boolean move(int elapsedTime) {
		if(fuelLevel<=0)
			return false;
		super.move(elapsedTime);
		fuelLevel--;
		return true;
	}
	
	public String toString() {
		return super.toString()+ "fuel="+fuelLevel+" "; 
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
	public void draw(Graphics g, Point pCmpRelPrnt){
		super.draw(g, pCmpRelPrnt);
		int x, y;
		x=(int) (pCmpRelPrnt.getX()+getX())-getSize()/2;
		y=(int) (pCmpRelPrnt.getY()+getY())-getSize()/2;
		g.fillArc(x, y, getSize(), getSize(), 0, 360);
		if(selected) {
			g.drawRect(x, y, getSize(), getSize());
		}
		
	}

	public void refuel() {
		fuelLevel=MISSILE_MAX_FUEL;
	}

	@Override
	public void setSelected(boolean yesNo) {
		selected=yesNo;
		
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Used for Selectable
	 * Checks to see if point touched overlaps with Missile
	 */
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc=(int) (pCmpRelPrnt.getX()+getX()-getSize()/2);
		int yLoc=(int) (pCmpRelPrnt.getY()+getY()-getSize()/2);
		if ( (px >= xLoc) && (px <= xLoc+getSize())
		&& (py >= yLoc) && (py <= yLoc+getSize()) )
		return true; else return false;
	}

}
