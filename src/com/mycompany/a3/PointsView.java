package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer {
	
	Label points, ammo, time, sound, lives;
	public PointsView(){
		setLayout(new BoxLayout(BoxLayout.X_AXIS));
		points=new Label("0");
		ammo=new Label("0");
		time=new Label("0");
		sound=new Label("ON");
		lives=new Label("3");
		
		points.getAllStyles().setMargin(0, 0, 5, 5);
		ammo.getAllStyles().setMargin(0, 0, 5, 5);		
		time.getAllStyles().setMargin(0, 0, 5, 5);		
		sound.getAllStyles().setMargin(0, 0, 5, 5);		
		lives.getAllStyles().setMargin(0, 0, 5, 5);

		
		this.add(new Label("Points: "));
		this.add(points);
		this.add(new Label("Missile count: "));
		this.add(ammo);
		this.add(new Label("Elapsed time: "));
		this.add(time);
		this.add(new Label("Sound: "));
		this.add(sound);
		this.add(new Label("Lives: "));
		this.add(lives);
		
	}
	@Override
	public void update(Observable observable, Object data) {
		IGameWorld gw = (GameWorldProxy) data;
		points.setText(Integer.toString(gw.getPoints()));
		ammo.setText(Integer.toString(gw.getAmmo()));
		time.setText(Integer.toString(gw.getTime()));
		lives.setText(Integer.toString(gw.getLives()));
		if(gw.getSound()) 
			sound.setText("ON");
		else
			sound.setText("OFF");
		this.repaint();
	}

}
