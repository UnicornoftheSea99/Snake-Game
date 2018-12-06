package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class SnakePart extends WorldObject {
	public static Color[] COLORS = { Color.red, Color.green, Color.magenta, Color.pink, Color.cyan,
			Color.red, Color.GREEN,Color.BLUE, Color.CYAN,Color.MAGENTA, Color.ORANGE, Color.PINK, Color.orange, 
			Color.WHITE, Color.white

	};
	/**
	 * This is an index into the {@link #COLORS} array.
	 */
	int color;

	public SnakePart(int color, World world) {
		super(world);
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		//Shape circle = new Ellipse2D.Double(-0.6, -0.6, 1.2, 1.2);
		
			Shape body = new Ellipse2D.Double(-.40, -.4, .8, .8);
			//Shape body = new drawRect(int x, int y, int width, int height); 
			//Shape tail = new Ellipse2D.Double(+.2, -.3, .2, .6);
			Shape body2 = new Ellipse2D.Double(-.25, -.25, .5, .5);

			Color color = COLORS[this.color];
			//Color tailColor = color.darker();

			Graphics2D flipped = (Graphics2D) g.create();

			// Draw the fish of size (1x1, roughly, at 0,0).
			flipped.setColor(color);
			flipped.fill(body);
			flipped.setColor(Color.black);
			flipped.fill(body2);


			// draw tail:
			//flipped.setColor(tailColor);
			//flipped.fill(tail);

			flipped.dispose();

	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

}
