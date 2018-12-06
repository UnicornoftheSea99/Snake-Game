package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * Most Fish behavior lives up in WorldObject (a Fish just looks special!). Or
 * it is in PlayFish, where the missing/found and player fish all act different!
 * 
 * @author jfoley
 */
public class SnakeHead extends WorldObject {

	/**
	 * A Fish knows what World it belongs to, because all WorldObjects do.
	 * 
	 * @param color Color by number.
	 * @param world The world itself.
	 */
	public SnakeHead(World world) {
		super(world);
	}

	/**
	 * Animate our fish by facing left and then right over time.
	 */
	private int dt = 0;

	/**
	 * Go ahead and ignore this method if you're not into graphics. We use "dt" as a
	 * trick to make the fish change directions every second or so; this makes them
	 * feel a little more alive.
	 */
	@Override
	public void draw(Graphics2D g) {
		dt += 1;
		if (dt > 100) {
			dt = 0;
		}
		//Shape circle = new Ellipse2D.Double(-0.6, -0.6, 1.2, 1.2);
	
		Shape body = new Ellipse2D.Double(-.40, -.4, .8, .8);
		//Shape body = new drawRect(int x, int y, int width, int height); 
		//Shape tail = new Ellipse2D.Double(+.2, -.3, .2, .6);
		Shape eyeL = new Ellipse2D.Double(-.2, -.05, .15, .15);
		Shape eyeLW = new Ellipse2D.Double(-0.3, -0.1, .3, .3);
		Shape eyeR = new Ellipse2D.Double(0.15,-0.05,0.15,0.15);
		Shape eyeRW = new Ellipse2D.Double(0.03, -0.1, 0.3, 0.3);

		Color color = Color.red;
		Color tailColor = color.darker();

		Graphics2D flipped = (Graphics2D) g.create();
		if (dt < 50) {
			flipped.scale(-1, 1);
		}

		// Draw the fish of size (1x1, roughly, at 0,0).
		flipped.setColor(color);
		flipped.fill(body);

		flipped.setColor(Color.WHITE);
		flipped.fill(eyeLW);
		flipped.fill(eyeRW);
		flipped.setColor(Color.black);
		flipped.fill(eyeL);
		flipped.fill(eyeR);
		

		// draw tail:
		//flipped.setColor(tailColor);
		//flipped.fill(tail);

		flipped.dispose();
	}

	@Override
	public void step() {
		// Fish are controlled at a higher level; see FishGame.
	}
}
