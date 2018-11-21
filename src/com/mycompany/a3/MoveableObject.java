package com.mycompany.a3;

public abstract class MoveableObject extends GameObject implements IMoveable{
	
	private int speed; //can be 0-10
	private int heading; //can be 0-359
	private double dx, dy;
	
	public int getSpeed() {return speed;}
	public int getHeading() {return heading;}
	
	/**
	 * Defaults speed and heading to 0
	 */
	public MoveableObject() {
		speed=0; heading=0;
		updateVectors();
	}
	
	private void updateVectors() {
		dx=Math.cos(Math.toRadians(90-heading))*speed;
		dy=Math.sin(Math.toRadians(90-heading))*speed;
		
	}
	
	private void updateAngle() {
		heading=(int)Math.toDegrees( Util.atan(dy/dx));
		if(dy==0&&dx>0){
			heading=90;
		}
		else if(dy>0&&dx>0) {	//quadrant I
			while(heading>180||heading<90) {
				setHeading(heading+90);
			}
		}
		else if(dy>0&&dx==0) {
			heading=180;
		}
		else if(dy>0&&dx<0) {	//quadrant II
			while(heading>270||heading<180) {
				setHeading(heading+90);
			}
		}
		else if(dy==0&&dx<0) {
			heading=270;
		}
		else if(dx<0&&dy<0) {	//quadrant III
			while(heading<270) {
				setHeading(heading+90);
			}
		}
		else if(dx==0&&dy<0) {
			heading=0;
		}
		else if(dy<0&&dx>0) {	//quadrant IV
			while(heading>90) {
				setHeading(heading+90);
			}
		}
		else {
			System.err.println("Unforseen case: <"+dx+","+dy+">");
		}
		System.out.println("<"+dx+","+dy+">: "+heading);
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
		updateVectors();
	}

	/**
	 * Uses trigonometry to increment object's position by the speed
	 * in the direction of the heading.
	 * Rounds to the tenths place.
	 */
	@Override
	public boolean move(int elapsedTime) {
		//double x, y;
		//x=Math.cos(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
		//y=Math.sin(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);

		updateVectors();
		dx=Math.cos(Math.toRadians(90-heading))*speed*(elapsedTime/10);
		dy=Math.sin(Math.toRadians(90-heading))*speed*(elapsedTime/10);
		
		/*
		if(getY()<=getSize()/2||getY()>=Util.getMaxHeight()-getSize()/2) {	//verticle edge
			System.out.println("<"+dx+","+dy+"> Old heading: "+getHeading());
			dy=-dy;
			updateAngle();
			System.out.println("New heading: "+getHeading());
		}
		if(getX()<=getSize()/2||getX()>=Util.getMaxWidth()-getSize()/2) {	//horizontal edge
			System.out.println("Old heading: "+getHeading());
			dx=-dx;
			updateAngle();
			System.out.println("New heading: "+getHeading());
		}
		*/
			/*
			boolean collision=false;
			int collisionAngle=0;
			if(getX()+x>=Util.getMaxWidth()) {	//right wall
				collision=true;
				collisionAngle=0;
			}else if(getX()+x<=0) { //left wall
				collision=true;
				collisionAngle=180;
			}else if(getY()+y<=0){	//bottom wall
				collision=true;
				collisionAngle=90;
			}else if(getY()+y>=Util.getMaxHeight()) {	//top wall
				collision = true;
				collisionAngle=270;
			}
			if(collision) {
				System.out.println("Old heading: "+getHeading());
				setHeading(180-2*collisionAngle-getHeading());
				System.out.println("New heading: "+getHeading());
				x=Math.cos(Math.toRadians(90-getHeading()))*getSpeed();
				y=Math.sin(Math.toRadians(90-getHeading()))*getSpeed();
			}
			
			//works some times
			
			if(getY()<=getSize()/2||getY()>=Util.getMaxHeight()-getSize()/2) {	//verticle edge
				int theta=(int) Math.toDegrees(Util.atan(x/y));
				System.out.println("Old heading: "+getHeading());
				if(x>0)
					setHeading(getHeading()-2*theta);
				else if(x<0)
					setHeading(getHeading()+2*theta);
				else
					setHeading(theta);
				System.out.println("New heading: "+getHeading());
				x=Math.cos(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
				y=Math.sin(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
			}
			if(getX()<=getSize()/2||getX()>=Util.getMaxWidth()-getSize()/2) {	//horizontal edge
				int theta=(int) Math.toDegrees(Util.atan(x/y));
				System.out.println("Old heading: "+getHeading());
				if(y>0)
					setHeading(getHeading()-2*theta);
				else if(y<0)
					setHeading(getHeading()+2*theta);
				else
					setHeading(theta);
				System.out.println("New heading: "+getHeading());
				x=Math.cos(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
				y=Math.sin(Math.toRadians(90-getHeading()))*getSpeed()*(elapsedTime/10);
			}
			
		
		*/
		
		if(getY()<=getSize()/2||getY()>=Util.getMaxHeight()-getSize()/2) {	//verticle edge
			return false;
		}
		if(getX()<=getSize()/2||getX()>=Util.getMaxWidth()-getSize()/2) {	//horizontal edge
			return false;
		}
		
		
		setX(getX()+dx);
		setY(getY()+dy);
		
		return true;
	}
	
	public String toString() {
		return super.toString() + "speed="+ speed + " dir=" + heading + " ";
	}
}
