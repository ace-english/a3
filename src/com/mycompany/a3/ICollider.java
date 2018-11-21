package com.mycompany.a3;

import java.util.Vector;

public interface ICollider {
	public boolean collidesWith(ICollider otherObject, IGameWorld gw);
	public boolean handleCollision(ICollider otherObject, IGameWorld gw);
	public Vector<ICollider> getCollisions();
}
