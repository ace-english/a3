package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class PlayerMissile extends Missile {

	public PlayerMissile(MissileLauncher ml) {
		super(ml);
		setColor(ColorUtil.WHITE);
	}
	public String toString() {
		return "PS's Missile: " + super.toString();
	}
	
	/**
	 * Adds points to score based on size of target.
	 * Always destroys missile
	 */
	@Override
	public boolean handleCollision(ICollider otherObject, GameWorld gw) {
		if(otherObject instanceof Asteroid || otherObject instanceof NPS)
			gw.addPoint(((GameObject) otherObject).getSize()*10);
		if(otherObject instanceof PlayerMissile)
			return false;
		if(otherObject instanceof PlayerShip)
			return false;
		return true;
	}

}
