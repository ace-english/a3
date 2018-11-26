package com.mycompany.a3;

public abstract class MoveableObject extends GameObject implements IMoveable{
	
	private int speed; //can be 0-10
	private int heading; //can be 0-359
	
	public int getSpeed() {return speed;}
	public int getHeading() {return heading;}
	
	/**
	 * Defaults speed and heading to 0
	 */
	public MoveableObject() {
		speed=0; heading=0;
	}
	/**
	 * Checks to make sure speed is within acceptable range.
	 * If going faster than max, sets speed to max.
	 * If slower than 0, sets speed to 0.
	 * @param speed
	 */
	public void setSpeed(int speed) {
		if(speed<0) {
			System.err.println("Speed cannot be lower than 0.");
			speed=0;
		}
		this.speed=speed;
		}
	
	/**
	 * Checks to make sure heading is in acceptable range
	 * @param heading
	 */
	public void setHeading(int heading) {
		while(heading<0)
			heading+=Util.MAX_HEADING;
		while(heading>=Util.MAX_HEADING)
			heading-=Util.MAX_HEADING;
		if(heading>=0&&heading<Util.MAX_HEADING)
			this.heading=heading;
	}

	/**
	 * Uses trigonometry to increment object's position by the speed
	 * in the direction of the heading.
	 * Rounds to the tenths place.
	 * Uses peripheral egress
	 */

	@Override
	public boolean move(int elapsedTime) {
		double x, y;
		x=Math.cos(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
		y=Math.sin(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
		
		if(getY()+y<=getSize()/2) {	//north edge
			setY(Util.getMaxHeight()-getSize()/2);
		}
		else if(getY()+y>=Util.getMaxHeight()-getSize()/2) {	//south edge
			setY(getSize()/2);
		}
		else
			setY(getY()+y);
		if(getX()+x>=Util.getMaxWidth()-getSize()/2) {	//east edge
			setX(getSize()/2);
		}
		else if(getX()+x<=getSize()/2) {	//west edge
			setX(Util.getMaxWidth()-getSize()/2);
		}
		else
			setX(getX()+x);
		
		
		return true;
	}
	
	public String toString() {
		return super.toString() + "speed="+ speed + " dir=" + heading + " ";
	}
}
