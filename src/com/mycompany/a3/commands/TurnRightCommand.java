package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRightCommand extends Command {
	
	private GameWorld gw;

	public TurnRightCommand(GameWorld gw) {
		super("Turn right");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		 if (e.getKeyEvent() != -1) { 
			 gw.turnRight();
			 System.out.println("Turning right");
		 }
	}

}
