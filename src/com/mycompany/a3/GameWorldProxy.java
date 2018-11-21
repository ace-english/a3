package com.mycompany.a3;

import java.util.Observable;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class GameWorldProxy extends Observable implements IGameWorld {

	GameWorld gw;
	
	public GameWorldProxy(GameWorld gw) {
		this.gw=gw;
	}
	
	@Override
	public int getPoints() {
		return gw.getPoints();
	}
	@Override
	public int getLives() {
		return gw.getLives();
	}
	@Override
	public boolean getSound() {
		return gw.getSound();
	}
	@Override
	public int getTime() {
		return gw.getTime();
	}
	@Override
	public int getAmmo() {
		return gw.getAmmo();
	}

	@Override
	public String printAll() {
		return gw.printAll();
	}
	@Override
	public void paintAll(Graphics g, Point pCmpRelPrnt) {
		gw.paintAll(g, pCmpRelPrnt);
	}

	@Override
	public IIterator getIterator() {
		return gw.getIterator();
		
	}

	@Override
	public boolean isPaused() {
		return gw.isPaused();
	}

}
