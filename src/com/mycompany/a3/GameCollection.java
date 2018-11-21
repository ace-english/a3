package com.mycompany.a3;

import java.util.Vector;

public class GameCollection implements ICollection {

	private Vector<GameObject> objectList;
	
	public GameCollection() {
		objectList=new Vector<GameObject>();
	}
	
	@Override
    public IIterator getIterator() {
        IIterator it = new GameIterator();
        return it;
    }

	@Override
	public void add(Object newObject) {
		if(newObject instanceof GameObject)
			objectList.add((GameObject) newObject);
		
	}
	
	public void remove(int i) {
		objectList.remove(i);
	}

	public boolean remove(GameObject target) {
		return objectList.remove(target);
		
	}
	
	private class GameIterator implements IIterator{

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
        	if(objectList.size()==0)
        		return false;
            return currentIndex < objectList.size();
        }

        @Override
        public GameObject getNext() {
        	GameObject ret = null;
        	if(hasNext()) {
        		ret= objectList.get(currentIndex);
        		currentIndex++;
        		
        	}
        	return ret;
        }
		 
    }

	public int size() {
		return objectList.size();
	}

	public GameObject get(int i) {
		return objectList.get(i);
	}

	/*public void moveAll() {
		GameObject item;
		for(int i=0; i<objectList.size(); i++) {
			item=objectList.get(i);
			if(item instanceof IMoveable) {
				if(((MoveableObject) objectList.get(i)).move()==false) {	//if move function returns false, destroy it
					objectList.remove(i);
					i--;
				}
			}
			else {	//if it isn't movable, it's a SpaceStation
					((Station) item).blink();
			}
		}
		
	}
	*/
	
	
    
}
