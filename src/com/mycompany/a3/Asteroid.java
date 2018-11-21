package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableObject implements ISelectable, ICollider{
	static int MAX_SIZE = 300;
	static int MIN_SIZE = 60;
	private boolean selected;
	private Vector<ICollider> collisions;
	
	public Asteroid(){
		collisions=new Vector<ICollider>();
		setX(Util.randomInt(0, Util.getMaxWidth()));
		setY(Util.randomInt(0, Util.getMaxHeight()));
		setHeading(Util.randomInt(0, 360));
		setSize(Util.randomInt(MIN_SIZE, MAX_SIZE));
		setSpeed(Util.randomInt(1, Util.MAX_SPEED));
		setColor(ColorUtil.GRAY);
		selected=false;
	}
	
	/**
	 * Setter checks to make sure size is in acceptable range
	 * @param size
	 */
	public void setSize(int size) {
		if(size>=MIN_SIZE&&size<=MAX_SIZE)
			super.setSize(size);
		else
			System.err.println("Error setting size to "+size);
	}
	
	public String toString() {
		return "Asteroid: "+ super.toString() + "size="+getSize()+" ";
	}

	/**
	 * Draws a gray circle with point at center and diameter of size.
	 * If selected, shows a box around
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		super.draw(g, pCmpRelPrnt);
		int x =(int)getX()+pCmpRelPrnt.getX()-getSize()/2;
		int y= (int)(getY()+pCmpRelPrnt.getY())-getSize()/2;
		g.fillArc(x, y, getSize(), getSize(), 0, 360);
		if(selected) {
			g.drawRect(x, y , getSize(), getSize());
		}
	}

	/**
	 * Asteroids should blow up no matter what they hit
	 */
	@Override
	public void handleCollision(ICollider otherObject, IGameWorld gw) {
		gw.playExplosion();
		gw.remove(this);
		if(otherObject instanceof PlayerShip) {
			gw.explodePS();
		}
	}

	/**
	 * returns true if the distance is less than the sum of the radii
	 */
	@Override
	public boolean collidesWith(ICollider otherObject, IGameWorld gw) {
		GameObject check = (GameObject) otherObject;
		boolean collides= Util.findDistance(this, check)<=((getSize()/2+check.getSize()/2));
		if(collides) {
			if(!collisions.contains(otherObject)) {
				collisions.add(otherObject);
				handleCollision(otherObject, gw);
			}
		}
		else {
			if(collisions.contains(otherObject)) {
				collisions.remove(otherObject);
			}
		}
		return collides;
	}

	@Override
	public void setSelected(boolean yesNo) {
		selected=yesNo;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

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

	@Override
	public Vector<ICollider> getCollisions() {
		return collisions;
	}
	
}
