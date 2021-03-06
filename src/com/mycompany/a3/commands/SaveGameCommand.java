package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SaveGameCommand extends Command {
	
	private GameWorld gw;

	public SaveGameCommand(GameWorld gw) {
		super("Save");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Save command chosen.");
	}

}
