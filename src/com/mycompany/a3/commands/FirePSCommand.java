package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FirePSCommand extends Command {
	
	private GameWorld gw;

	public FirePSCommand(GameWorld gw) {
		super("Fire PS");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.firePS();
	}

}
