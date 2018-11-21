package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Missile;

public class RefuelCommand extends Command {
	
	private GameWorld gw;

	public RefuelCommand(GameWorld gw) {
		super("Refuel Missile");
		this.gw=gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		GameObject obj=gw.getSelectedObject();
		if(obj instanceof Missile) {
			System.out.println("Missile refueled");
			((Missile) obj).refuel();
		}
	}

}
