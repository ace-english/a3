package com.mycompany.a3;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class GameWorld extends Observable implements IGameWorld{
	

	private GameCollection objectList;
	private int score, lives, time;
	private Sound playerExplode, explode, playerShoot, enemyShoot, restock, noAmmo, gameOver;
	private boolean sound, pause;
	//private GameObject selectedObject;
	
	BGSound music;
	
	public GameWorld() {
		sound=Util.SOUND_DEFAULT;
		music = new BGSound("MegaHyperUltrastorm.mp3");
		restock = new Sound("restock.mp3");
		playerExplode=new Sound("player_explosion.mp3");
		explode=new Sound("explosion.mp3");
		playerShoot=new Sound("shoot.mp3");
		enemyShoot=new Sound("enemy_shoot.mp3");
		noAmmo=new Sound("out_of_ammo.mp3");
		gameOver=new Sound("game_over.mp3");
	}
	
	
	public void init() {
		score=0;
		time=0;
		lives=3;
		objectList = new GameCollection();
		setPaused(false);
		
		
		
		if(sound) {
			music.run();
		}
		
	}
	
	/**
	 * Returns the static playerShip
	 * @return
	 */
	public PlayerShip getPS() {
		if(findAll(Util.ObjectType.PlayerShip).isEmpty()) {
			//System.err.println("PS is null!");
			return null;
		}
		return PlayerShip.getPS();
	}
	
	/**
	 * Function finds all objects of the corresponding type and
	 * returns them in a vector
	 * @param type
	 * @return
	 */
	public Vector<GameObject> findAll(Util.ObjectType type) {
		Vector<GameObject> subset=new Vector<GameObject>();
		IIterator it = objectList.getIterator();
		GameObject temp;
		while(it.hasNext()) {
			temp=(GameObject) it.getNext();
			switch(type) {
			case PlayerShip:
				if(temp instanceof PlayerShip) {
					subset.add(temp);
				}
				break;
			case Asteroid:
				if(temp instanceof Asteroid) {
					subset.add(temp);
				}
				break;
			case NPSMissile:
				if(temp instanceof NPSMissile) {
					subset.add(temp);
				}
				break;
			case PlayerMissile:
				if(temp instanceof PlayerMissile) {
					subset.add(temp);
				}
				break;
			case NPS:
				if(temp instanceof NPS) {
					subset.add(temp);
				}
				break;
			
			default:
				//System.err.println("No "+type+ " found.");
				break;
			}
		}
		return subset;
	}
	
	/**
	 * Creates new object in vector that matches parameter.
	 * Only allows one PlayerShip to exist at a time
	 * @param gameobject
	 */
	public void add(Util.ObjectType type) {
		switch (type) {
		case Asteroid:
			objectList.add(new Asteroid());
			break;
		case NPS:
			NPS nps = new NPS();
			objectList.add(nps);
			break;
		case PlayerShip:
			//verify that we don't already have a PS
			if(findAll(Util.ObjectType.PlayerShip).isEmpty()) {
				objectList.add(PlayerShip.getPS());
			}
			else{
				System.err.println("Error: player ship already exists.");
			}
			break;
		case Station:
			objectList.add(new Station());
			break;
		default:
			System.err.println("Invalid input.");
			return;
		
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * Adds object to vector. Useful for missiles.
	 * @param gameObject
	 * @return 
	 */
	public boolean add(GameObject gameObject) {
		if(gameObject!=null) {
			objectList.add(gameObject);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Removes returned item from array. Useful for collisions
	 */
	public void remove(GameObject target) {
		objectList.remove(target);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/**
	 * Increment play ship speed by 1, to a maximum of 10
	 */
	public void increaseSpeed(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			ps.setSpeed(ps.getSpeed()+1);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Decrement player ship speed by 1, to a minimum of 0
	 */
	public void decreaseSpeed(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			ps.setSpeed(ps.getSpeed()-1);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Rotate PlayerShip 45 degrees left
	 */
	public void turnLeft(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			ps.turn(Util.Direction.left);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Rotate PlayerShip 45 degrees right
	 */
	public void turnRight(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else{
			ps.turn(Util.Direction.right);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Rotate missileLauncher 45 degrees counterclockwise
	 */
	public void aimLeft(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			((ISteerable) getPS().getMissileLauncher()).turn(Util.Direction.left);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Rotate missileLauncher 45 degrees clockwise
	 */
	public void aimRight(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			((ISteerable) getPS().getMissileLauncher()).turn(Util.Direction.right);
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * PS fires a missile from its missileLauncher, which
	 * is added to the game world
	 */
	public void firePS(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			Missile playerMissile=getPS().fire();
			if(playerMissile==null) {
				System.out.println("Out of ammo");
				if(sound) {
					noAmmo.play();
				}
			}
			else {
				add(playerMissile);
				if(sound)
					playerShoot.play();
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}
		}
	
	}
	/**
	 * NPS fires a missile from its missileLauncher, which
	 * is added to the game world
	 */
	public void fireNPS(NPS nps){
		if(nps==null) {
			System.err.println("Error: no NPS exists.");
		}
		else {
			enemyShoot.play();
			add(nps.fire());
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Reset position to middle.
	 */
	public void hyperspaceJump(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			ps.hyperspaceJump();
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	/**
	 * Player visits a space station
	 */
	public void restock(){
		PlayerShip ps=getPS();
		if(ps==null) {
			System.err.println("Error: player ship does not yet exist.");
		}
		else {
			getPS().restock();
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	
	
	/**
	 * Outputs the toString from everything in objectList
	 */
	public String printAll() {
		String ret= new String();
		IIterator it=objectList.getIterator();
		while(it.hasNext()) {
			ret+=it.getNext()+"\n";
		}
		return ret;
		
	}
	/**
	 * Increments game clock
	 * Sometimes creates NPSs
	 * Calls the move() function for anything that is moveable.
	 * Removes missiles that are out of fuel
	 * Checks for collisions
	 */
	public void tick() {
		time++;
		LinkedList<GameObject> objectsToBeDeleted = new LinkedList<GameObject>();
		
		//0.5% chance each tick to generate an NPS
		if(Util.randomInt(0, 200)==1)	
			add(Util.ObjectType.NPS);
		
		//loop through all gameobjects
		IIterator it=objectList.getIterator();
		while (it.hasNext()){
			GameObject currentObject = (GameObject) it.getNext();
			
			//check for collisions
			if(currentObject instanceof ICollider) {
				ICollider current=(ICollider) currentObject;
				//inner loop to check for collisions with all other objects
				IIterator it2 = objectList.getIterator();
				while (it2.hasNext()) {
					GameObject checkNext = (GameObject) it2.getNext();
					if(checkNext instanceof ICollider) {
						ICollider check = (ICollider) checkNext;
						if(check!=current) {
							if(current.collidesWith(check)) {
								//call collision handling function
								if(current.handleCollision(check, this)) {
									//if function returns true, object should be deleted
									objectsToBeDeleted.add((GameObject) current);
								}
							}
						}
					}
				}
			}
			//all collisions checked
			
			//move all MoveableObjects
			if(currentObject instanceof IMoveable) {
				if(((IMoveable) currentObject).move(Util.TIME_INCREMENT)==false) {
					//rockets than run out of fuel should be removed
					objectsToBeDeleted.add(currentObject);
				}
				//NPS have a 10% chance to fire a missile in current direction every second
				if(currentObject instanceof NPS) {
					if(Util.randomInt(0, 50)==1)	//10 percent chance each second to fire
						add(((Ship)currentObject).fire());
				}
			}
		}
		//remove all marked objects
		for(GameObject obj:objectsToBeDeleted) {
			objectList.remove(obj);
		}
		
		//notify MapView of changes
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * Player crashes
	 */
	public void explodePS(){
		lives--;
		if(lives<0) lives=0;
		if(sound)
			playPlayerExplosion();
		objectList.remove(getPS());
		if(lives<=0) {
			gameOver();
		}
		else {
			add(PlayerShip.getNewPS());
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void gameOver() {
		pause=true;
		if(sound)
			gameOver.play();
		music.pause();
	    Boolean bOk = Dialog.show("Game Over", "Final score: "+score+"\nTime: "+time+"\nPlay again?", "Yes", "No");
	    if (bOk){
		        init();
	    }
	    else {
	    	Display.getInstance().exitApplication();
	    }
	}

	@Override
	public int getPoints() {
		return score;
	}
	
	public void addPoint(int points) {
		score+=points;
	}

	@Override
	public int getLives() {
		return lives;
	}

	@Override
	public boolean getSound() {
		return sound;
	}
	
	public void setSound(boolean sound) {
		this.sound=sound;
		if(sound) {
			music.play();
		}
		else {
			music.pause();
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public int getAmmo() {
		PlayerShip ps=getPS();
		if (ps==null)
			return 0;
		return ps.getMissileCount();
	}

	@Override
	public void paintAll(Graphics g, Point pCmpRelPrnt) {
		IIterator it=objectList.getIterator();
		IDrawable object;
		while(it.hasNext()) {
			object=(IDrawable)it.getNext();
			object.draw(g, pCmpRelPrnt);
		}
		
	}
	
	/**
	 * collision-based sounds need a handle so other classes can access them
	 */
	public void playRestock() { if(sound) restock.play();}
	public void playExplosion() { if(sound) explode.play();}
	public void playPlayerExplosion() { if(sound)playerExplode.play();}

	
	public boolean isPaused() {
		return pause;
	}

	public void setPaused(boolean pause) {
		this.pause = pause;
		if(!pause) {
			clearSelected();
			if(sound)
				music.play();
		}
		else {
			music.pause();
		}
	}
	/*
	public void pauseMusic() {
		music.pause();
	}
	public void unpauseMusic() {
		if(sound)
			music.play();
	}
*/
	public GameObject getSelectedObject() {
		IIterator it=objectList.getIterator();
		Object obj;
		while(it.hasNext()) {
			obj=it.getNext();
			if(obj instanceof ISelectable) {
				if(((ISelectable) obj).isSelected())
					return (GameObject) obj;
			}
		}
		return null;
		
	}

	
	@Override
	public IIterator getIterator() {
		return objectList.getIterator();
		
	}


	public void clearSelected() {
		IIterator it=objectList.getIterator();
		Object obj;
		while(it.hasNext()) {
			obj=it.getNext();
			if(obj instanceof ISelectable) {
				if(((ISelectable) obj).isSelected())
					((ISelectable) obj).setSelected(false);
			}
		}
		
	}
	

	
}
