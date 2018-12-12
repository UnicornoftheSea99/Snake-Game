package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;
import me.jjfoley.gfx.TextBox;
/***
 * This is a snake game uses the implementation of P2 (the fish game)
 * 
 * @author libby
 *
 */

public class P5Main extends GFX {
	public static int VISUAL_GRID_SIZE = 550;
	/**
	 * Game size (logical).
	 */

	public static int LOGICAL_GRID_SIZE = 13;
	/**
	 * The words appear in the top part of the screen.
	 */
	public static int TOP_PART = 100;
	/**
	 * There's a border to make it look pretty (the board is inset by this much).
	 */
	public static int BORDER = 1;
	/**
	 * This is where the game logic lives.
	 */
	SnakeGame game;
	/**
	 * This TextBox wraps up making fonts and centering text.
	 */
	TextBox gameState = new TextBox("");

	/**
	 * This is a rectangle representing the TOP_PART of the screen.
	 */
	Rectangle2D topRectangle;
	Rectangle2D secRectangle;
	Rectangle2D lowRectangle;
	
	int highScore = 0;
	String name;
	
	public P5Main() {
		super(VISUAL_GRID_SIZE + BORDER * 2, VISUAL_GRID_SIZE + BORDER * 2 + TOP_PART);
		game = new SnakeGame(LOGICAL_GRID_SIZE, LOGICAL_GRID_SIZE);
		gameState.color = Color.WHITE;
		gameState.setFont(TextBox.BOLD_FONT);
		gameState.setFontSize(TOP_PART / 3.0);
		topRectangle = new Rectangle2D.Double(0, 0, getWidth(), TOP_PART);
		secRectangle= new Rectangle2D.Double(0,50, getWidth(),TOP_PART);
		lowRectangle= new Rectangle2D.Double(0,550,getWidth(),TOP_PART);
		
		//Figured out how to get an option dialog window, might come in handy later
		name=JOptionPane.showInputDialog("Hello Player. What is your name?");
		
	}

	/**
	 * How big is a tile?
	 * @return this returns the tile width.
	 */
	private int getTileW() {
		return VISUAL_GRID_SIZE / game.world.getWidth();
	}

	/**
	 * How big is a tile?
	 * @return this returns the tile height.
	 */
	private int getTileH() {
		return VISUAL_GRID_SIZE / game.world.getWidth();
	}

	/**
	 * Black background.
	 */
	public static Color BOARD_COLOR = new Color(0, 0, 0);
	/**
	 * White grid
	 */
	public static Color GRID_COLOR = new Color(255, 255, 225);
	
	boolean inMenu = true;

	/**
	 * Draw the game state.
	 */

	public void draw(Graphics2D g) {
		if (inMenu) {
			// Strings
			this.gameState.setString("Hello "+name);
			this.gameState.centerInside(secRectangle);
			this.gameState.setFontSize(16);
			this.gameState.draw(g);
			this.gameState.setString("SNAKE GAME");
			this.gameState.setFont(TextBox.BOLD_FONT);
			this.gameState.centerInside(topRectangle);
			this.gameState.setFontSize(40);
			this.gameState.draw(g);
			this.gameState.setString("Click anywhere to start");
			this.gameState.centerInside(lowRectangle);
			this.gameState.setFontSize(16);
			this.gameState.draw(g);
			
			//Snake 
			Shape body = new Ellipse2D.Double(20, 40, 100, 100);
			Shape eyeLW = new Ellipse2D.Double(30, 70, 35, 35);
			Shape eyeL = new Ellipse2D.Double(35, 70, 20, 20);
			Shape eyeRW = new Ellipse2D.Double(80,70,35,35);
			Shape eyeR = new Ellipse2D.Double(85, 70, 20, 20);
			Shape body1= new Ellipse2D.Double(20, 155, 100, 100);
			Shape body1b= new Ellipse2D.Double(34, 170, 70, 70);
			Shape body2=new Ellipse2D.Double(20, 270, 100, 100);
			Shape body2b=new Ellipse2D.Double(35, 285, 70, 70);
			Shape body3=new Ellipse2D.Double(135, 270, 100, 100);
			Shape body3b=new Ellipse2D.Double(150, 285, 70, 70);
			Shape body4=new Ellipse2D.Double(250, 270, 100, 100);
			Shape body4b=new Ellipse2D.Double(265, 285, 70, 70);
			Shape body5=new Ellipse2D.Double(250, 380, 100, 100);
			Shape body5b=new Ellipse2D.Double(265, 395, 70, 70);
			Shape body6=new Ellipse2D.Double(250, 495, 100, 100);
			Shape body6b=new Ellipse2D.Double(265, 510, 70, 70);
			Shape body7=new Ellipse2D.Double(365, 495, 100, 100);
			Shape body7b=new Ellipse2D.Double(380, 510, 70, 70);
			
			Color color = Color.red;
			Graphics2D flipped = (Graphics2D) g.create();
			flipped.setColor(color);
			flipped.fill(body);
			flipped.setColor(Color.WHITE);
			flipped.fill(eyeLW);
			flipped.fill(eyeRW);
			
			flipped.setColor(Color.CYAN);
			flipped.fill(body1);
			flipped.setColor(Color.ORANGE);
			flipped.fill(body2);
			flipped.setColor(Color.green);
			flipped.fill(body3);
			flipped.setColor(Color.MAGENTA);
			flipped.fill(body4);
			flipped.setColor(Color.WHITE);
			flipped.fill(body5);
			flipped.setColor(Color.PINK);
			flipped.fill(body6);
			flipped.setColor(Color.blue);
			flipped.fill(body7);
			
			flipped.setColor(Color.black);
			flipped.fill(eyeL);
			flipped.fill(eyeR);
			flipped.fill(body2b);
			flipped.fill(body1b);
			flipped.fill(body3b);
			flipped.fill(body4b);
			flipped.fill(body5b);
			flipped.fill(body6b);
			flipped.fill(body7b);
			
			return;
		}
		// Background of window is dark-dark green.
		g.setColor(Color.green.darker().darker());
		g.fillRect(0, 0, getWidth(), getHeight());

		// Get a a reference to the game world to draw.
		World world = game.world;

		// Draw TOP_PART TextBox.
		this.gameState.centerInside(this.topRectangle);
		this.gameState.draw(g);

		// Slide the world down, and into the box.
		// This makes our rendering of the board easier.
		g.translate(BORDER, BORDER + TOP_PART);

		// Use the tile-sizes.
		int tw = getTileW();
		int th = getTileH();

		// Draw the ocean (not the whole screen).
		g.setColor(BOARD_COLOR);
		g.fillRect(0, 0, tw * world.getWidth(), th * world.getHeight());
		// Draw a grid to better picture how the game works.
		g.setColor(GRID_COLOR);
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				g.drawRect(x * tw, y * th, tw, th);
			}
		}

		// For everything in our world:
		for (WorldObject wo : world.viewItems()) {
			// Draw it with a 1x1 graphical world, with the center right in the middle of the tile.
			// I fiddled with this translate to get pixel-perfect. Maybe there's a nicer way, but it works for now.

			Graphics2D forWo = (Graphics2D) g.create();
			forWo.translate((int) ((wo.getX() + 0.5) * tw) + 1, (int) ((wo.getY() + 0.5) * th) + 1);
			forWo.scale(tw, th);
			wo.draw(forWo);
			forWo.dispose();
		}

		IntPoint hover = mouseToGame(this.getMouseLocation());
		if (hover != null) {
			g.setColor(new Color(0,1,0,0.5f));
			g.fillRect(hover.x * tw, hover.y * th, tw, th);
		}
	}

	/**
	 * Convert Mouse coordinates to Grid coordinates.
	 * @param mouse maybe a Mouse location (or null).
	 * @return null or the grid coordinates of the Mouse.
	 */
	public IntPoint mouseToGame(IntPoint mouse) {
		if (mouse == null) return null;
		int x = mouse.x - BORDER;
		int y = mouse.y - BORDER - TOP_PART;
		if (x > 0 && x <= VISUAL_GRID_SIZE &&
				y > 0 && y <= VISUAL_GRID_SIZE) {
			int tx = x / getTileW();
			int ty = y / getTileH();
			return new IntPoint(tx, ty);
		}
		return null;
	}


	int delay = 0;


		

			/**
			 * We separate our "PlayFish" game logic update here.
			 * @param secondsSinceLastUpdate - my GFX code can tell us how long it is between each update, but we don't actually care here.
			 */
			@Override
			public void update(double secondsSinceLastUpdate) {
				if (inMenu) {
					if (this.processClick() != null) {
						inMenu = false;
						this.game = new SnakeGame(LOGICAL_GRID_SIZE, LOGICAL_GRID_SIZE);
					}
					return;
				}
				if (game.score > highScore) {
					highScore = game.score;
				}
				// Handle game-over and restart.
				if (game.gameOverLOSE()) {
					
					this.gameState.setString("You Lose! Please try again!");
					//"You win! Click anywhere start again!"
					if (this.processClick() != null) {
						this.inMenu = true;
					}
					return;
				}
				
				if (game.gameOverLOSE2()) {
					
					this.gameState.setString("You Lose! Please try again!");
					//"You win! Click anywhere start again!"
					if (this.processClick() != null) {
						this.inMenu = true;
					}
					return;
				}

				 if(game.gameOverWIN()) {

					this.gameState.setString("You win! Click anywhere start again!");
					//"You win! Click anywhere start again!"
					if (this.processClick() != null) {
						this.inMenu = true;
					}
					return;
				}

				
				// Update the text in the TextBox.
				this.gameState.setString(
						//"Time: " + game.stepsTaken + 
						//" ... Fish Left: " + game.missingFishLeft() +
						" Score: "+ game.score + " | High Score: "+highScore 
						);


		// Read the state of the keyboard:
		boolean up = this.processKey(KeyEvent.VK_W) || this.processKey(KeyEvent.VK_UP);
		boolean down = this.processKey(KeyEvent.VK_S) || this.processKey(KeyEvent.VK_DOWN);
		boolean left = this.processKey(KeyEvent.VK_A) || this.processKey(KeyEvent.VK_LEFT);
		boolean right = this.processKey(KeyEvent.VK_D) || this.processKey(KeyEvent.VK_RIGHT);
		boolean skip = this.processKey(KeyEvent.VK_SPACE);

		// Move the player if we can:
		boolean moved = false;
		if (up) {
			moved = this.game.player.moveUp();
		} else if (down) {
			moved = this.game.player.moveDown();
		} else if (left) {
			moved = this.game.player.moveLeft();
		} else if (right) {
			moved = this.game.player.moveRight();
		}

		IntPoint click = mouseToGame(this.processClick());

		delay += 1;
		// Only advance the game if the player presses something!
		if (skip || moved || click != null || delay > 20) {
			delay = 0;
			if (click != null) {
				this.game.click(click.x, click.y);
			}
			// Update game logic!
			this.game.step(moved);
			// Update message at the top!
		}
	}

	public static void main(String[] args) {
		//System.out.println("P5 Main Started!");
		GFX app = new P5Main();
		app.start();
	}
}
