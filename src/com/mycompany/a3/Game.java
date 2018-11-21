/**
 * 
 */
package com.mycompany.a3;
import com.mycompany.a3.commands.*;

import java.util.LinkedList;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	
	 GameWorld gw;
	 PointsView pv;
	 MapView mv;
	
	 AddPSCommand addPsCmd;
	 FirePSCommand firePsCmd;
	 AddAsteroidCommand addAsteroidCmd;
	 AddStationCommand addStationCmd;
	 IncreaseSpeedCommand increaseSpeedCmd;
	 DecreaseSpeedCommand decreaseSpeedCmd;
	 TurnLeftCommand turnLeftCmd;
	 TurnRightCommand turnRightCmd;
	 AimLeftCommand aimLeftCmd;
	 AimRightCommand aimRightCmd;
	 HyperspaceJumpCommand hyperspaceJumpCmd;
	 QuitCommand quitCmd;
	 AboutCommand aboutCmd;
	 CommandsCommand commandsCmd;
	 SaveGameCommand saveCmd;
	 NewGameCommand newGameCmd ;
	 UndoCommand undoCommand;
	 RefuelCommand refuelCmd;
	 
	 UITimer timer;
	
	public Game() {
		super("Asteroids", BoxLayout.y());
		
		pv = new PointsView();
		gw = new GameWorld();
		mv = new MapView(gw);
		
		timer = new UITimer(this);
		//make the timer tick every second and bind it to this form
		timer.schedule(Util.TIME_INCREMENT, true, this);
		
		//declare all the commands we shall be using
	    addPsCmd = new AddPSCommand(gw);
	    firePsCmd = new FirePSCommand(gw);
	    addAsteroidCmd = new AddAsteroidCommand(gw);
	    addStationCmd = new AddStationCommand(gw);
	    increaseSpeedCmd = new IncreaseSpeedCommand(gw);
	    decreaseSpeedCmd = new DecreaseSpeedCommand(gw);
	    turnLeftCmd = new TurnLeftCommand(gw);
	    turnRightCmd = new TurnRightCommand(gw);
	    aimLeftCmd = new AimLeftCommand(gw);
	    aimRightCmd = new AimRightCommand(gw);
	    hyperspaceJumpCmd = new HyperspaceJumpCommand(gw);
	    quitCmd = new QuitCommand(this);
	    aboutCmd = new AboutCommand();
	    commandsCmd = new CommandsCommand();
	    saveCmd = new SaveGameCommand(gw);
	    newGameCmd = new NewGameCommand(gw);
	    undoCommand = new UndoCommand(gw);
	    refuelCmd=new RefuelCommand(gw);
	    
	    //refuel is disabled by default, enabled when game is paused
	    refuelCmd.setEnabled(false);
		
		
		
		play();
	}
	
	private void play() {
		this.show();
		
		this.setLayout(new BorderLayout());
		Container cmdWrapper = new Container();
		cmdWrapper.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

	    

		//Add components to Box Layout
		this.add(BorderLayout.WEST, cmdWrapper);
		this.add(BorderLayout.NORTH, pv);
		this.add(BorderLayout.CENTER, mv);

	    
	    //=================Sound checkbox========================
	    CheckBox soundBox = new CheckBox("Sound");
	    soundBox.setSelected(Util.SOUND_DEFAULT);
	    soundBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Sound toggled");
				gw.setSound(soundBox.isSelected());
				
			}
	    		
	    });
	    
		
		//Declare toolbar
	    Toolbar toolbar = new Toolbar();	
	    this.setToolbar(toolbar);
	    toolbar.addComponentToSideMenu(soundBox);

	    Form runnable=this;
	    
		addKeyListeners();
		
	
		//add buttons for cmds
		/*
		GameButton addStationButton =new GameButton();
		addStationButton.setCommand(addStationCmd);
		GameButton addAsteroidButton =new GameButton();
		addAsteroidButton.setCommand(addAsteroidCmd);
		GameButton addPsButton =new GameButton();
		addPsButton.setCommand(addPsCmd);
		GameButton increaseSpeedButton =new GameButton();
		increaseSpeedButton.setCommand(increaseSpeedCmd);
		GameButton decreaseSpeedButton =new GameButton();
		decreaseSpeedButton.setCommand(decreaseSpeedCmd);
		GameButton firePsButton =new GameButton();
		firePsButton.setCommand(firePsCmd);
		GameButton turnLeftButton =new GameButton();
		turnLeftButton.setCommand(turnLeftCmd);
		GameButton turnRightButton =new GameButton();
		turnRightButton.setCommand(turnRightCmd);
		GameButton aimRightButton =new GameButton();
		aimRightButton.setCommand(aimRightCmd);
		GameButton aimLeftButton =new GameButton();
		aimLeftButton.setCommand(aimLeftCmd);
		GameButton hyperspaceJumpButton =new GameButton();
		hyperspaceJumpButton.setCommand(hyperspaceJumpCmd);*/
		GameButton refuelButton =new GameButton();
		refuelButton.setCommand(refuelCmd);
		/*
		LinkedList<GameButton> buttons = new LinkedList<GameButton>();
		buttons.add(addStationButton);
		buttons.add(addAsteroidButton);
		buttons.add(addPsButton);
		buttons.add(increaseSpeedButton);
		buttons.add(decreaseSpeedButton);
		buttons.add(firePsButton);
		buttons.add(turnLeftButton);
		buttons.add(turnRightButton);
		buttons.add(aimRightButton);
		buttons.add(aimLeftButton);
		buttons.add(hyperspaceJumpButton);
		buttons.add(refuelButton);
		
		
		for(int i=0; i<buttons.size(); i++) {
			cmdWrapper.add(buttons.get(i));
			toolbar.addComponentToSideMenu(buttons.get(i));
		}
		*/
		
		//add commands that should have buttons to list
		LinkedList<Command> cmds = new LinkedList<Command>();
		cmds.add(addStationCmd);
		cmds.add(addAsteroidCmd);
		cmds.add(addPsCmd);
		cmds.add(increaseSpeedCmd);
		cmds.add(decreaseSpeedCmd);
		cmds.add(firePsCmd);
		cmds.add(turnLeftCmd);
		cmds.add(turnRightCmd);
		cmds.add(aimRightCmd);
		cmds.add(aimLeftCmd);
		cmds.add(hyperspaceJumpCmd);
		
		
		//allocate buttons for commands
		LinkedList<GameButton> buttons = new LinkedList<GameButton>();
		for(int i=0; i<cmds.size(); i++) {
			buttons.add(new GameButton());
		}
		
		//add buttons in list to east pane of box layout.
		//Assign each button a command from the list
		Command cmd; GameButton button;
		for(int i=0; i<cmds.size(); i++) {
			button=buttons.get(i);
			cmd=cmds.get(i);
			button.setCommand(cmd);
			cmdWrapper.add(button);
		}
		
		cmdWrapper.add(refuelButton);
		

		//new list of buttons to use for side menu
		LinkedList<GameButton> sideButtons = new LinkedList<GameButton>();
		
		//allocate buttons for commands
		for(int i=0; i<cmds.size(); i++) {
			sideButtons.add(new GameButton());
		}
		
		//add buttons in list to side menu
		//Assign each button a command from the list
		for(int i=0; i<cmds.size(); i++) {
			button=sideButtons.get(i);
			cmd=cmds.get(i);
			button.setCommand(cmd);
	    	toolbar.addComponentToSideMenu(button);
		}
		
		
		//reset lists to use for overflow menu
		cmds.clear();
		
	    
		
	    //same show different star
		cmds = new LinkedList<Command>();
	    cmds.add(quitCmd);
	    cmds.add(aboutCmd);
	    cmds.add(commandsCmd);
	    cmds.add(newGameCmd);
	    cmds.add(saveCmd);
	    cmds.add(undoCommand);
	    
	    //assign commands to fresh buttons
		LinkedList<GameButton> menuButtons = new LinkedList<GameButton>();
		menuButtons = new LinkedList<GameButton>();
	    for(int i=0; i<cmds.size(); i++) {
	    	menuButtons.add(new GameButton());
	    	menuButtons.get(i).setCommand(cmds.get(i));
	    }
	    
	    //add list of buttons to toolbar's overflow menu
	    for(Command temp: cmds) {
	    	toolbar.addCommandToOverflowMenu(temp);
	    }

	    
	    //==========Pause button=========================================================
	    GameButton pauseButton=new GameButton("Pause");
	    pauseButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent evt) {
	    		if(gw.isPaused()) {
	    			gw.setPaused(false);
	    			timer.schedule(Util.TIME_INCREMENT, true, runnable);
	    			pauseButton.setText("Pause");
	    			setButtons(buttons, true);
	    			setButtons(sideButtons, true);
	    			refuelButton.setEnabled(false);
	    			addKeyListeners();
	    			
	    		}
	    		else {
	    			gw.setPaused(true);
	    			timer.cancel();
	    			pauseButton.setText("Play");
	    			setButtons(buttons, false);
	    			setButtons(sideButtons, false);
	    			refuelButton.setEnabled(true);
	    			removeKeyListeners();
	    		}
	    	}
	    });
	    
	    toolbar.add(BorderLayout.CENTER, pauseButton);
	    
		this.show();
		//Assign dimensions of map into Util
		Util.init(mv);
		//add observers to GameWorld
		gw.addObserver(pv);
		gw.addObserver(mv);
		gw.init();	
		
			
	}

	@Override
	public void run() {
		gw.tick();
		
	}
	
	private void addKeyListeners() {
	    //< - aim left
		addKeyListener(44, aimLeftCmd);
		//> - aim right
		addKeyListener(46, aimRightCmd);
		//up - increase speed
		addKeyListener(-91, increaseSpeedCmd);
		//down - decrease speed
		addKeyListener(-92, decreaseSpeedCmd);
		//left - turn left
		addKeyListener(-93, turnLeftCmd);
		//right - turn right
		addKeyListener(-94, turnRightCmd);
		//space - fire
		addKeyListener(-90, firePsCmd);
		//j - hyperspace jump
		addKeyListener(106, hyperspaceJumpCmd);
	}
	
	private void removeKeyListeners() {
	    //< - aim left
		removeKeyListener(44, aimLeftCmd);
		//> - aim right
		removeKeyListener(46, aimRightCmd);
		//up - increase speed
		removeKeyListener(-91, increaseSpeedCmd);
		//down - decrease speed
		removeKeyListener(-92, decreaseSpeedCmd);
		//left - turn left
		removeKeyListener(-93, turnLeftCmd);
		//right - turn right
		removeKeyListener(-94, turnRightCmd);
		//space - fire
		removeKeyListener(-90, firePsCmd);
		//j - hyperspace jump
		removeKeyListener(106, hyperspaceJumpCmd);
	}
	
	private void setButtons(LinkedList<GameButton> buttons, boolean yesno) {
		for (GameButton button:buttons) {
			button.setEnabled(yesno);
		}
	}
}
