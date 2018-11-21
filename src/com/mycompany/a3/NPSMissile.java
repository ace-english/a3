package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class NPSMissile extends Missile {

	public NPSMissile(MissileLauncher ml) {
		super(ml);
		setColor(ColorUtil.rgb(0xff, 0, 0));
	}
	
	public String toString() {
		return "NPS's Missile: " + super.toString();
	}

	/**
	 * missiles should be destroyed no matter what they hit
	 */
	@Override
	public boolean handleCollision(ICollider otherObject, GameWorld gw) {
		if (otherObject instanceof NPSMissile) return false;
		if(otherObject instanceof PlayerShip)
			gw.explodePS();
		return true;
		
	}

}
