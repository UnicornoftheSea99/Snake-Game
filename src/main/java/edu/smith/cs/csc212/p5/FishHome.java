package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class FishHome extends WorldObject {

	/**
	 * For graphically drawing the roof.
	 */
	final Polygon roof;
	/**
	 * For graphically drawing the house.
	 */
	final Polygon house;
	
	public FishHome(World world) {
		super(world);
		
		// I drew this out on graph paper.
		roof = new Polygon();
		roof.addPoint(1, 4);
		roof.addPoint(9, 4);
		roof.addPoint(5, 1);
		
		house = new Polygon();
		house.addPoint(2, 4);
		house.addPoint(2, 9);
		house.addPoint(3, 9);
		house.addPoint(3, 6);
		house.addPoint(5, 5);
		house.addPoint(7, 6);
		house.addPoint(7, 9);
		house.addPoint(8, 9);
		house.addPoint(8, 4);
	}
	


	@Override
	public void draw(Graphics2D g) {
		Graphics2D scale = (Graphics2D) g.create();
		scale.scale(1.0/10.0, 1.0/10.0);
		scale.translate(-5, -5);
		scale.setColor(Color.black);
		scale.fillPolygon(roof);
		scale.setColor(Color.red);
		scale.fillPolygon(house);
	}

	@Override
	public void step() {
		// Fish home doesn't move, does it?
	}

}
