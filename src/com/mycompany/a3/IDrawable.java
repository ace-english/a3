package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface IDrawable {
	//receives MapView's graphics and component location
	public void draw(Graphics g, Point pCmpRelPrnt);
}
