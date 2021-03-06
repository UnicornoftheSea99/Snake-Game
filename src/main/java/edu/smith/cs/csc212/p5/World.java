package edu.smith.cs.csc212.p5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import me.jjfoley.gfx.IntPoint;

/**
 * A World is a 2d grid, represented as a width, a height, and a list of
 * WorldObjects in that world.
 * 
 * @author jfoley
 *
 */
public class World {
	/**
	 * The size of the grid (x-tiles).
	 */
	private int width;
	/**
	 * The size of the grid (y-tiles).
	 */
	private int height;
	/**
	 * A list of objects in the world 
	 */
	private List<WorldObject> items;
	/**
	 * A reference to a random object, so we can randomize placement of objects in
	 * this world.
	 */
	private Random rand = ThreadLocalRandom.current();

	/**
	 * Create a new world of a given width and height.
	 * 
	 * @param w - width of the world.
	 * @param h - height of the world.
	 */
	public World(int w, int h) {
		items = new ArrayList<>();
		width = w;
		height = h;
	}

	/**
	 * What is under this point?
	 * 
	 * @param x - the tile-x.
	 * @param y - the tile-y.
	 * @return a list of objects!
	 */
	public List<WorldObject> find(int x, int y) {
		List<WorldObject> found = new ArrayList<>();

		// Check out every object in the world to find the ones at a particular point.
		for (WorldObject w : this.items) {
			// But only the ones that match are "found".
			if (x == w.getX() && y == w.getY()) {
				found.add(w);
			}
		}

		// Give back the list, even if empty.
		return found;
	}

	/**
	 * This is used by PlayGame to draw all our items!
	 * 
	 * @return the list of items.
	 */
	public List<WorldObject> viewItems() {
		// Don't let anybody add to this list!
		// Make them use "register" and "remove".

		// This is kind of an advanced-Java trick to return a list where add/remove
		// crash instead of working.
		return Collections.unmodifiableList(items);
	}

	/**
	 * Add an item to this World.
	 * 
	 * @param item 
	 */
	public void register(WorldObject item) {
		// Print out what we've added, for our sanity.
		System.out.println("register: " + item.getClass().getSimpleName());
		items.add(item);
	}

	/**
	 * This is the opposite of register. It removes an item (like a snake part) from the
	 * World.
	 * 
	 * @param item - the item to remove.
	 */
	public void remove(WorldObject item) {
		// Print out what we've removed, for our sanity.
		System.out.println("remove: " + item.getClass().getSimpleName());
		items.remove(item);
	}

	/**
	 * How big is the world we model?
	 * 
	 * @return the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * How big is the world we model?
	 * 
	 * @return the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Try to find an unused part of the World for a new object!
	 * 
	 * @return a point (x,y) that has nothing else in the grid.
	 */
	public IntPoint pickUnusedSpace() {
		int tries = width * height;
		for (int i = 0; i < tries; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			if (this.find(x, y).size() != 0) {
				continue;
			}
			return new IntPoint(x, y);
		}
		// If we get here, we tried a lot of times and couldn't find a random point.
		// Let's crash our Java program!
		throw new IllegalStateException(
				"Tried to pickUnusedSpace " + tries + " times and it failed! Maybe your grid is too small!");
	}

	/**
	 * Insert an item randomly into the grid.
	 * 
	 * @param item
	 */
	public void insertRandomly(WorldObject item) {
		item.setPosition(pickUnusedSpace());
		this.register(item);
		item.checkFindMyself();
	}


	/**
	 * Insert a new Snake Part into the world at random of a specific color.
	 * 
	 * @param color - the color of the fish.
	 * @return the new fish itself.
	 */

	public SnakePart insertSnakePartRandomly(int color) {
		SnakePart f = new SnakePart(color, this);
		insertRandomly(f);
		return f;
	}


	/**
	 * Determine if a WorldObject can swim to a particular point.
	 * 
	 * @param whoIsAsking - the object (not just the player!)
	 * @param x           - the x-tile.
	 * @param y           - the y-tile.
	 * @return true if they can move there.
	 */
	public boolean canSwim(WorldObject whoIsAsking, int x, int y) {

		// If we didn't see an obstacle, we can move there!
		return true;
	}

	/**
	 * This is how objects may move.
	 */
	public void stepAll() {
		for (WorldObject it : this.items) {
			it.step();
		}
	}

	/**
	 * This signature is a little scary, but we need to support any subclass of
	 * WorldObject. We don't know followers is a {@code List<Fish>} but it should
	 * work no matter what!
	 * 
	 * @param target    the leader.
	 * @param followers a set of objects to follow the leader.
	 */
	public static void objectsFollow(WorldObject target, List<? extends WorldObject> followers) {
		if (followers.isEmpty() || target.recentPositions.isEmpty()) {
			return;
		}

		List<IntPoint> putWhere = new ArrayList<>(target.recentPositions);
		IntPoint last = putWhere.get(putWhere.size()-1);
		for (int i = 0; i < followers.size(); i++) {
			IntPoint past;
			if (i+1 >= putWhere.size()) {
				past = last;
			} else {
				past = putWhere.get(i + 1);
			}
			followers.get(i).setPosition(past.x, past.y);
		}
	}
}
