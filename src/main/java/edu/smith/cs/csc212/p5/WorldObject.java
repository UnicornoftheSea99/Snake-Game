package edu.smith.cs.csc212.p5;

import java.awt.Graphics2D;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import me.jjfoley.gfx.IntPoint;

/**
 * A WorldObject is an abstract "item" in the game.
 * 
 * All movement is defined in this class.
 * 
 * @author jfoley
 */
public abstract class WorldObject {
	/**
	 * Random in case you want random numbers!
	 */
	Random rand = ThreadLocalRandom.current();
	/**
	 * Where am I? x-tile in the grid.
	 */
	private int x;
	/**
	 * Where am I? y-tile in the grid.
	 */
	private int y;
	/**
	 * What world do I belong to?
	 */
	protected World world;
	/**
	 * How many snake parts do we anticipate having? This is used to make them follow you.
	 */
	public static int NUM_RECENT_POSITIONS = 256;
	/**
	 * Here, we use a special kind of list that makes it easy to add to the front
	 * and remove from the back, so we keep up to NUM_RECENT_POSITIONS locations for every snake part.
	 */
	public Deque<IntPoint> recentPositions;

	/**
	 * Create a new WorldObject -- this is the call to super(world) in Snake part.
	 * 
	 * @param world the world filled with other objects.
	 */
	public WorldObject(World world) {
		this.world = world;
		this.recentPositions = new LinkedList<>();
	}
	
	/**
	 * Remove this WorldObject from its world.
	 */
	public void remove() {
		this.world.remove(this);
		this.world = null;
	}

	/**
	 * Move this object to a given position (ignoring rules).
	 * 
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.updatePosition();
	}

	/**
	 * Move this object to a given position (ignoring rules).
	 * 
	 * @param pt The (x,y) pair as an IntPoint.
	 */
	public void setPosition(IntPoint pt) {
		this.setPosition(pt.x, pt.y);
	}

	/**
	 * Private method to keep track of this object's position history. Used to make
	 * Snake part follow the Player.
	 */
	private void updatePosition() {
		// Add to front.
		this.recentPositions.offerFirst(new IntPoint(this.x, this.y));
		if (this.recentPositions.size() > NUM_RECENT_POSITIONS) {
			// Remove from back.
			this.recentPositions.pollLast();
		}

	}

	/**
	 * Move this object up if possible.
	 * 
	 * @return true if it moved!
	 */
	public boolean moveUp() {
		if (world.canSwim(this, x, y - 1)) {
			this.y -= 1;
			updatePosition();
			return true;
		}
		return false;
	}

	/**
	 * Is this a snake?
	 * 
	 * @return true if this is a Snake.
	 */
	public boolean isFish() {
		return this instanceof SnakeHead;
	}

	/**
	 * Is this the player?
	 * 
	 * @return true if this is a Snake that is the player.
	 */
	public boolean isPlayer() {
		return this instanceof SnakeHead;
	}

	/**
	 * Move this object down if possible.
	 * 
	 * @return true if it moved!
	 */
	public boolean moveDown() {
		if (world.canSwim(this, x, y + 1)) {
			this.y += 1;
			updatePosition();
			return true;
		}
		return false;
	}

	/**
	 * Move this object left if possible.
	 * 
	 * @return true if it moved!
	 */
	public boolean moveLeft() {
		if (world.canSwim(this, x - 1, y)) {
			this.x -= 1;
			updatePosition();
			return true;
		}
		return false;
	}

	/**
	 * Move this object right if possible.
	 * 
	 * @return true if it moved!
	 */
	public boolean moveRight() {
		if (world.canSwim(this, x + 1, y)) {
			this.x += 1;
			updatePosition();
			return true;
		}
		return false;
		
	}

	/**
	 * Move randomly! 
	 */
	public void moveRandomly() {
		// Can we move right, left, down, or up?
		boolean canMove = 
				world.canSwim(this, x+1, y) ||
				world.canSwim(this, x-1, y) ||
				world.canSwim(this, x, y+1) ||
				world.canSwim(this, x, y-1);
		
		// If not, don't try to pick one.
		if (!canMove) {
			// "this" is stuck, and can't go anywhere!
			return;
		}
		
		// Pick one at random (that works):
		while (true) {
			
			// Choose a direction at random.
			int direction = ThreadLocalRandom.current().nextInt(4);
			
			boolean success = false;
			if (direction == 0) {
				success = moveUp();
			} else if (direction == 1) {
				success = moveDown();
			} else if (direction == 2) {
				success = moveRight();
			} else {
				success = moveLeft();
			}
			
			// Did the direction we picked work?
			if (success) {
				// If so, exit this method now.
				break;
			}
			// Otherwise go pick another.
		}
	}

	/**
	 * Part of my position!
	 * 
	 * @return the x-coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Part of my position!
	 * 
	 * @return the y-coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * I'm a world object! I exist in the world somewhere! This method tests that!
	 */
	public void checkFindMyself() {
		if (!findSameCell().contains(this)) {
			throw new AssertionError("Couldn't find myself! Check World.register still works!");
		}
	}

	/**
	 * Find all the items at the same position as me!
	 * 
	 * @return a list of WorldObject.
	 */
	public List<WorldObject> findSameCell() {
		return world.find(this.x, this.y);
	}

	/**
	 * Check whether this object is in the same spot as another.
	 * 
	 * @param other the other WorldObject.
	 * @return true if their x and y coordinates are the same.
	 */
	public boolean inSameSpot(WorldObject other) {
		return this.x == other.getX() && this.y == other.getY();
	}

	/**
	 * Draw this WorldObject!
	 * 
	 * Abstract so that Snake and other subclasses MUST implement.
	 * 
	 * @param g Graphics2D API.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Step this WorldObject!
	 * 
	 * Abstract so that Snake and Powerup, etc. MUST implement!
	 */
	public abstract void step();
}
