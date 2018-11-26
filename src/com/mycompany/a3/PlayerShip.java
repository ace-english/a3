package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class PlayerShip extends Ship implements ISteerable{

	//ensures that only one instance of PlayerShip can exist at once.
	private static PlayerShip PS;
	
	//private data members
	private final static int MAX_MISSILES=10;
	
	private PlayerShip() {
		//Initialize to center of screen, facing up.
		hyperspaceJump();
		setColor(ColorUtil.GREEN);
		setHeading(0);
		setSpeed(0);
		setSize(50);
		missileLauncher=new SteerableMissileLauncher(this);
		setMaxMissiles(MAX_MISSILES);
		setMissileCount(MAX_MISSILES);
	}
	
	public void hyperspaceJump() {
		setX(Util.getMaxWidth()/2);
		setY(Util.getMaxHeight()/2);
	}


	/**
	 * returns reference to static PlayerShip
	 */
	public static PlayerShip getPS() {
		if(PS==null)
			PS=new PlayerShip();
		return PS;
	}
	
	public static PlayerShip getNewPS() {
		PS=new PlayerShip();
		return PS;
	}
	
	/**
	 * Checks to make sure speed is within acceptable range.
	 * If going faster than max, sets speed to max.
	 * If slower than 0, sets speed to 0.
	 * @param speed
	 */
	public void setSpeed(int speed) {
		if(speed>Util.MAX_SPEED) {
			System.err.println("Speed cannot be greater than "+Util.MAX_SPEED);
			super.setSpeed(Util.MAX_SPEED);
		}
		super.setSpeed(speed);
	}
	
	/**
	 * Resets missile count. Useful for visiting space stations.
	 */
	public void restock() {
		setMissileCount(MAX_MISSILES);
	}

	/**
	 * Rotates ship by 45 degrees, either left or right.
	 */
	@Override
	public void turn(Util.Direction direction) {
		int interval=45;
		if(direction==Util.Direction.right) interval*=-1;
		setHeading(getHeading()+interval);
	}
	
	
	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean collision =super.collidesWith(otherObject);
		return collision;
	}
	
	public String toString() {
		return "Player Ship: "+super.toString() + "Missile launcher dir = "+missileLauncher.getHeading()+" ";
	}

	/**
	 * because of collision detection issues, player exploding is handled in GW
	 */
	@Override
	public boolean handleCollision(ICollider otherObject, GameWorld gw) {
		if(otherObject instanceof Station) {
			if(getAmmo()<getMaxMissiles()) {
				restock();
				gw.playRestock();
			}
		}
		return false;
		
	}
}
