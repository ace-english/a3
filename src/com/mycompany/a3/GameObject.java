package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public abstract class GameObject implements IDrawable{

	private Point2D coords;
	private int color;
	private int size;
	
	public double getX(){return coords.getX();}
	public double getY(){return coords.getY();}
	public int getColor() {return color;}
	
	/**
	 * Creates random starting locations for object that do not override
	 * Compensates for size of object since point represents center
	 */
	public GameObject() {
		coords=new Point2D(Util.randomInt(size/2, Util.getMaxWidth()-size/2),Util.randomInt(size/2, Util.getMaxHeight()-size/2));
	}

	/**
	 * Checks to make sure x is in acceptable range
	 * @param x
	 */
	public void setX(double x) {
		//if(x>=size/2&&x<=Util.getMaxWidth()-size/2)
			this.coords.setX(x);
	}
	
	/**
	 * Checks to make sure y is in acceptable range
	 * @param y
	 */
	public void setY(double y) {
		//if(y>=size/2&&y<=Util.getMaxHeight()-size/2)
			this.coords.setY(y);
	}
	
	public void setColor(int color) {
		this.color=color;
	}
	
	public int getSize() {return size;}
	
	/**
	 * Setter checks to make sure size is in acceptable range
	 * @param size
	 */
	public void setSize(int size) {
			this.size=size;
	}
	
	public String toString() {
		return "loc="+coords+" color=["+ ColorUtil.red(color)+
				","+ColorUtil.green(color)+
				","+ColorUtil.blue(color)+"] ";
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		//drawHitbox(g, pCmpRelPrnt);
	}
	
	public void drawHitbox(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (getX()+pCmpRelPrnt.getX()-getSize()/2);
		int y = (int) (getY()+pCmpRelPrnt.getY()-getSize()/2);
		g.drawArc(x, y, getSize(), getSize(), 0, 360);
	}
	
	
}
