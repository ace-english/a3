package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class DecreaseSpeedCommand extends Command {
	
	private GameWorld gw;

	public DecreaseSpeedCommand(GameWorld gw) {
		super("Decrease Speed");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.decreaseSpeed();
	}

}
