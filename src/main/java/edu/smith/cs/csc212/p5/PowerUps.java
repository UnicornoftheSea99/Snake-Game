package edu.smith.cs.csc212.p5;

//Imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class PowerUps extends WorldObject{

	public PowerUps(World world) {
		super(world);
	}

	//Create PowerUps
	@Override
	public void draw(Graphics2D g) {
		Shape powerup = new Rectangle2D.Double(-0.25, -0.25, 0.5, 0.5);
		g.setColor(Color.YELLOW);
		g.fill(powerup);
	
	}
	
	//PowerUps don't move
	@Override
	public void step() {
	}
}

