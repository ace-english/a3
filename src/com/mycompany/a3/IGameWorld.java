package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface IGameWorld{
	public int getPoints();
	public int getLives();
	public boolean getSound();
	public int getTime();
	public int getAmmo();
	public String printAll();
	public void paintAll(Graphics g, Point pCmpRelPrnt);
	public IIterator getIterator();
	public boolean isPaused();
	public void playExplosion();
	public void explodePS();
	public void remove(GameObject obj);
}
