package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Util;

public class AddStationCommand extends Command {
	
	private GameWorld gw;

	public AddStationCommand(GameWorld gw) {
		super("Add Station");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.add(Util.ObjectType.Station);
	}

}
