package edu.smith.cs.csc212.p5;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//new changes who this

public class SnakeGame {
	/**
	 * This is the world in which the fish are missing. (It's mostly a List!).
	 */
	World world;
	/**
	 * The player (a Fish.COLORS[0]-colored fish) goes seeking their friends.
	 */
	SnakeHead player;
	/**
	 * The home location.
	 */
	FishHome home;
	/**
	 * These are the missing fish!
	 */
	List<SnakePart> missing;

	/**
	 * These are fish we've found!
	 */
	List<SnakePart> found;

	/**
	 * Number of steps!
	 */
	int stepsTaken;

	//fishFood food;

	/**
	 * Score!
	 */
	int score;

	int z;
	int u;

	/**
	 * Create a FishGame of a particular size.
	 * 
	 * @param w how wide is the grid?
	 * @param h how tall is the grid?
	 */
	public SnakeGame(int w, int h) {
		world = new World(w, h);

		missing = new ArrayList<SnakePart>();
		found = new ArrayList<SnakePart>();
		//home = world.insertFishHome();
		//food = world.insertfishFood();
		z = 10;
		u = 8;
		//for (int i = 0; i < u; i++) {
			//world.insertfishFood();
		//}

		// Make the player out of the 0th fish color.
		player = new SnakeHead(world);
		// Start the player at "home".
		//player.setPosition(home.getX(), home.getY());
		world.register(player);

		// Generate fish of all the colors but the first into the "missing" List.
		for (int ft = 1; ft < SnakePart.COLORS.length; ft++) {
			SnakePart friend = world.insertSnakePartRandomly(ft);
			missing.add(friend);
		}
	}

	/**
	 * How we tell if the game is over: if missingFishLeft() == 0.
	 * 
	 * @return the size of the missing list.
	 */
	public int missingFishLeft() {
		return missing.size();
	}

	/**
	 * This method is how the PlayFish app tells whether we're done.
	 * 
	 * @return true if the player has won (or maybe lost?).
	 */
	public boolean gameOver() {
		// We want to bring the fish home before we win!
		return missing.isEmpty();
	}

	/**
	 * Update positions of everything (the user has just pressed a button).
	 */
	public void step() {
		// Keep track of how long the game has run.
		this.stepsTaken += 1;

		// These are all the objects in the world in the same cell as the player.
		List<WorldObject> overlap = this.player.findSameCell();
		// The player is there, too, let's skip them.
		overlap.remove(this.player);

		// If we find a fish, remove it from missing.
		for (WorldObject wo : overlap) {
			// It is missing if it's in our missing list.
			if (missing.contains(wo)) {
				// Remove this fish from the missing list.
				missing.remove(wo);

				// Tell it to be found instead.

				// found.add((Fish)wo);
				SnakePart justFound = (SnakePart) wo;
				found.add(justFound);

				// Increase score when you find a fish!

				
				score += 10;

			}
		}

		// Make sure missing fish *do* something.
		wanderMissingFish();
		// When fish get added to "found" they will follow the player around.
		World.objectsFollow(player, found);
		// Step any world-objects that run themselves.
		world.stepAll();
	}

	/**
	 * Call moveRandomly() on all of the missing fish to make them seem alive.
	 */
	private void wanderMissingFish() {
		Random rand = ThreadLocalRandom.current();
		// check what kind of fish it is normal or fast scared

	}

	/**
	 * This gets a click on the grid. We want it to destroy rocks that ruin the
	 * game.
	 * 
	 * @param x - the x-tile.
	 * @param y - the y-tile.
	 */
	public void click(int x, int y) {
		
	}

}
