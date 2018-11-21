package com.mycompany.a3;

import java.util.Vector;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public abstract class Ship extends MoveableObject implements ICollider{
	protected MissileLauncher missileLauncher;
	private int missileCount;
	private int maxMissiles;
	private Vector<ICollider> collisions;
	
	/**
	 * Checks to see if ship can fire a missile.
	 * Returns a new Missile with this ship's heading
	 * Returns null if missile count is less than 0.
	 * @return
	 */
	public Missile fire() {
		if(missileCount<=0) {
			return null;
		}
		missileCount--;
		return missileLauncher.fire();
	}
	
	public void setSpeed(int speed) {
		super.setSpeed(speed);
	}
	
	public abstract void restock();

	/**
	 * Returns missileLauncher of ship`
	 * @return
	 * */
	public MissileLauncher getMissileLauncher() {
		return missileLauncher;
	}
	 
	
	/**
	 * Verifies argument is 0 or more before assigning to missiles
	 * @param missileCount
	 */
	public void setMissileCount(int missileCount) {
		if(missileCount>0)
			this.missileCount=missileCount;
	}

	public int getMissileCount() {
		return missileCount;
	}
	
	public String toString() {
		return super.toString()+" missiles="+missileCount+" ";
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
	public Vector<ICollider> getCollisions() {
		return collisions;
	}
	
	@Override
	public void draw(Graphics g, Point parent){
		super.draw(g, parent);
		int x1, y1, x2, y2, x3, y3;
		
		x1=x2=x3=(int) (getX()+parent.getX());
		y1=y2=y3=(int) (getY()+parent.getY());
		
		y1+=getSize();
		
		x2+=getSize()/2;
		x3-=getSize()/2;
		
		y2-=getSize()/2;
		y3-=getSize()/2;
		
		
		
		g.fillTriangle(x1, y1, x2, y2, x3, y3);
		missileLauncher.draw(g, parent);
		//apply transformation based on direction
	}

	public int getMaxMissiles() {
		return maxMissiles;
	}

	public void setMaxMissiles(int max_missiles) {
		maxMissiles = max_missiles;
	}

	public int getAmmo() {
		return missileCount;
	}

}
