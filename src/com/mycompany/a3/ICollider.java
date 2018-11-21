package com.mycompany.a3;

public interface ICollider {
	public boolean collidesWith(ICollider otherObject);
	//returns true if object should be destroyed
	public boolean handleCollision(ICollider otherObject, GameWorld gw);
}
