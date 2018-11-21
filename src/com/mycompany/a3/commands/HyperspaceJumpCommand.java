package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HyperspaceJumpCommand extends Command {
	
	private GameWorld gw;

	public HyperspaceJumpCommand(GameWorld gw) {
		super("Hyperspace Jump");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.hyperspaceJump();
	}

}
