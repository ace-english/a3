package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer {

	IGameWorld gw;
	
	public MapView(IGameWorld gw) {
		this.gw=gw;
		getStyle().setBgColor(000000);
		getStyle().setBgTransparency(255);
	}
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorldProxy) data;
		this.repaint();
		//System.out.println(gw.printAll());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(),getY());
		gw.paintAll(g, pCmpRelPrnt);
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		//make pointer location relative to parent’s origin
		if(gw.isPaused()) {
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			IIterator it=gw.getIterator();
			while(it.hasNext()) {
				Object obj=it.getNext();
				if(obj instanceof ISelectable) {
					ISelectable shape=(ISelectable) obj;
					if (shape.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						shape.setSelected(true);
					} else {
						shape.setSelected(false);
					}
				}
			}
			repaint();
		}
	}

}
