package edu.smith.cs.csc212.p5;

//Imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class PowerUps extends WorldObject{

	public PowerUps(World world) {
		super(world);
	}

	//Create Fish Food
	@Override
	public void draw(Graphics2D g) {
		Shape food = new Ellipse2D.Double(0, 0, 0.25, 0.25);
		g.setColor(Color.orange);
		g.fill(food);


	}

	//Fish food doesn't move
	@Override
	public void step() {
	}
}

