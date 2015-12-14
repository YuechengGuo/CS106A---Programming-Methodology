/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;



public class Target extends GraphicsProgram {	
	
	private static final double RADIUS_OF_OUTTER_CIRCLE = 72;
	
	private static final double RADIUS_OF_MID_CIRCLE = 0.65 * RADIUS_OF_OUTTER_CIRCLE;
	
	private static final double RADIUS_OF_INNER_CIRCLE = 0.3 * RADIUS_OF_OUTTER_CIRCLE;
	
	public void run() {
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		addCircles(cx, cy);	
	}
	
	private void addCircles(double cx, double cy) {
		addOutterCircle(cx - RADIUS_OF_OUTTER_CIRCLE / 2, cy - RADIUS_OF_OUTTER_CIRCLE / 2);
		addMidCircle(cx - RADIUS_OF_MID_CIRCLE / 2, cy - RADIUS_OF_MID_CIRCLE / 2);
		addInnerCircle(cx - RADIUS_OF_INNER_CIRCLE / 2, cy - RADIUS_OF_INNER_CIRCLE / 2);
		
		
	}
	
	private void addOutterCircle(double cx, double cy) {
		GOval OUT_Circle = new GOval(cx, cy, RADIUS_OF_OUTTER_CIRCLE, RADIUS_OF_OUTTER_CIRCLE);
		OUT_Circle.setColor(Color.RED);
		OUT_Circle.setFilled(true);
		OUT_Circle.setFillColor(Color.RED);
		add(OUT_Circle);
	}
	
	private void addMidCircle(double cx, double cy) {
		GOval MID_Circle = new GOval(cx, cy, RADIUS_OF_MID_CIRCLE, RADIUS_OF_MID_CIRCLE);
		MID_Circle.setColor(Color.WHITE);
		MID_Circle.setFilled(true);
		MID_Circle.setFillColor(Color.WHITE);
		add(MID_Circle);
	}
	
	private void addInnerCircle(double cx, double cy) {
		GOval INN_Circle = new GOval(cx, cy, RADIUS_OF_INNER_CIRCLE, RADIUS_OF_INNER_CIRCLE);
		INN_Circle.setColor(Color.RED);
		INN_Circle.setFilled(true);
		INN_Circle.setFillColor(Color.RED);
		add(INN_Circle);
	}
}
