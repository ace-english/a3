package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnLeftCommand extends Command {
	
	private GameWorld gw;

	public TurnLeftCommand(GameWorld gw) {
		super("Turn left");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.turnLeft();
	}

}
