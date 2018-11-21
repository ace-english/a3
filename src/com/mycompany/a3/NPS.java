package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class NPS extends Ship{

	//List of sizes which are allowed for NPS
	private static final int SIZES[]= {100,50};
	private static final int MAX_MISSILES=2;
	
	/**
	 * Constructor randomizes values for heading, size and speed (and position, in the super)
	 */
	public NPS(){
		//randomize members
		setHeading(Util.randomInt(0, 360));
		setSpeed(Util.randomInt(0, Util.MAX_SPEED));
		setSize(SIZES[Util.randomInt(0, SIZES.length-1)]);
		setColor(ColorUtil.MAGENTA);
		missileLauncher= new MissileLauncher(this);
		setMaxMissiles(MAX_MISSILES);
		setMissileCount(MAX_MISSILES);
	}
	
	/**
	 * Checks to see if argument is included in array of acceptable sizes
	 * If input is acceptable, assigns to private data member size.
	 * Otherwise outputs to System.err - does not assign
	 */
	public void setSize(int size) {
		boolean valid=false; int i=0;
		while(!valid||i<SIZES.length) {
			if(SIZES[i]==size)
				valid=true;
			i++;
		}
		if(valid)
			super.setSize(size);
		else
			System.err.println("Error setting size to "+size);
	}
	/**
	 * Useful for making sure missile_launcher gets added to GameWorld.objectList
	 * @return
	 */
	public MissileLauncher getMissileLauncher() {
		return missileLauncher;
	}
	
	public String toString() {
		return "Non-Player Ship: "+super.toString() + " size="+getSize()+" ";
	}

	@Override
	public void restock() {
		setMissileCount(MAX_MISSILES);
		
	}

	/**
	 * NPS should be destroyed if it collides with a ship, missile, or asteroid.
	 */
	@Override
	public void handleCollision(ICollider otherObject, 	IGameWorld gw) {
		boolean destroy=false;
		//System.out.println("NPS collided with "+otherObject);
		if(otherObject instanceof Asteroid) {
			destroy=true;
			gw.playExplosion();
		}
		if(otherObject instanceof PlayerMissile) {
			destroy=true;
			gw.playExplosion();
		}
		if(otherObject instanceof Ship) {
			destroy=true;
			gw.playExplosion();
			if(otherObject instanceof PlayerShip) {
				gw.explodePS();
			}
		}
		if(otherObject instanceof Station) {
			if(getAmmo()<getMaxMissiles()) {
				restock();
			}
		}
		
	}
	
}
