package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Util;

public class AddAsteroidCommand extends Command {
	
	private GameWorld gw;

	public AddAsteroidCommand(GameWorld gw) {
		super("Add Asteroid");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		 if (e.getKeyEvent() != -1) { 
			 gw.add(Util.ObjectType.Asteroid);
			 System.out.println("Added asteroid");
		 }
	}

}
