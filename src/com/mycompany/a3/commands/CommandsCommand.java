package com.mycompany.a3.commands;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class CommandsCommand extends Command {

		public CommandsCommand() {
			super("Commands");
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("Commands selected.");
			Dialog dialog = new Dialog("Commands");
			String info=new String("Add Station: Adds a space station to the map. Fly over it to refill your ship's missiles.\n"
					+ "Add Asteroid: Adds an asteroid to the map. Shooting an asteroid rewards points. Bigger asteroids are worth more points.\n"
					+ "Add PS: Add player ship to the map. Can only have one PlayerShip on the map at once.\n"
					+ "Increase Speed (up arrow): Speed up your ship\n"
					+ "Decrease Speed (down arrow): Slow down your ship. Cannot go backwards.\n"
					+ "Fire PS (space): Fire a missile from your ship\n"
					+ "Turn Left (left arrow) Rotate ship's trajectory 45 degrees clockwise\n"
					+ "Turn Right (right arrow): Rotate ship's trajectory 45 degrees counterclockwise\n"
					+ "Aim Left (<): Rotate ship's gun 45 degrees counterclockwise\n"
					+ "Aim Right (>): Rotate ship's gun 45 degrees clockwise\n"
					+ "Hyperspace Jump (j): Teleport ship to the center of the map.\n"
					+ "Refuel Missile: While the game is paused, select a missile and click this button to refuel it, causing it to travel farther before dispersing.");
			TextArea text = new TextArea(info);
			text.setEditable(false);
			dialog.setLayout(new BorderLayout());
			dialog.add(BorderLayout.CENTER, text);
			dialog.showPopupDialog(new Button("Ok"));
		}

	

}
