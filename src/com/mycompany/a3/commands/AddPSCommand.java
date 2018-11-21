package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.PlayerShip;
import com.mycompany.a3.Util;

public class AddPSCommand extends Command {
	
	private GameWorld gw;

	public AddPSCommand(GameWorld gw) {
		super("Add PS");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(gw.findAll(Util.ObjectType.PlayerShip).isEmpty())
			gw.add(PlayerShip.getNewPS());
	}

}
