package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AimRightCommand extends Command {
	
	private GameWorld gw;

	public AimRightCommand(GameWorld gw) {
		super("Aim Right");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.aimRight();
	}

}
