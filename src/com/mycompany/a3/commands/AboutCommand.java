package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {

	public AboutCommand() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent e) {
		
	    Dialog.show("About", "Ace English\nCSC 133 Section 4\nVersion 3.0\n"
	    		+ "Background music credit: Mega Hyper Ultrastorm Kevin MacLeod (incompetech.com)\r\n" + 
	    		"Licensed under Creative Commons: By Attribution 3.0 License\r\n" + 
	    		"http://creativecommons.org/licenses/by/3.0/", "OK", "Close");
	}

}
