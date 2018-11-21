package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class QuitCommand extends Command {

	Form form;
	public QuitCommand(Form form) {
		super("Quit");
		this.form=form;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	    form.setTitle("Closing App Demo");
	    Boolean bOk = Dialog.show("Quit?", "Are you sure you want to quit?", "Yes", "No");
	    if (bOk){
		         Display.getInstance().exitApplication();
	    }
	    form.show();
	}

}
