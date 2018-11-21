package com.mycompany.a3;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable{

	public SteerableMissileLauncher(Ship ship) {
		super(ship);
	}

	/**
	 * Rotates missile launcher 45 degrees counter-clockwise
	 */
	@Override
	public void turn(Util.Direction direction) {
		int interval=45;
		if(direction==Util.Direction.left) interval*=-1;
		setHeading(getHeading()+interval);
	}


	@Override
	public Missile fire() {
		return new PlayerMissile(this);
	}
}
