package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;

public class GameButton extends Button {
	
	public GameButton(String name) {
		super(name);
		getAllStyles().setFgColor(ColorUtil.rgb(85,0,85));
		
		getAllStyles().setPadding(1, 1, 1, 1);
	}
	
	public GameButton() {
		this("Button");
	}
	
}
