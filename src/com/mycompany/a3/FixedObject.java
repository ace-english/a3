package com.mycompany.a3;

public abstract class FixedObject extends GameObject {

	private static int NEXT_ID;
	private final int id;
	
	/**
	 * Uses next ID number in sequence, then increments the static
	 */
	public FixedObject(){
		id=NEXT_ID;
		NEXT_ID++;
	}
	
	public int getID() {return id;}
	
}
