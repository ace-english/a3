package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class IncreaseSpeedCommand extends Command {
	
	private GameWorld gw;

	public IncreaseSpeedCommand(GameWorld gw) {
		super("Increase Speed");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		 if (e.getKeyEvent() != -1) { 
			 gw.increaseSpeed();
			 System.out.println("Speed increased");
		 }
	}

}
