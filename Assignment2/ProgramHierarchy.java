/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	/** Width of the label box*/
	private static final int WIDTH = 130;
	
	/** Height of the label box*/
	private static final int HEIGHT = 40;
	
	/** Distance between upper and lower classes */
	private static final int VERTICAL_DIST = 40;
	
	/** Horizontal distance between classes */
	private static final int HORIZONTAL_DIST = 50;
	
	
	public void run() {
		int cx = getWidth() / 2;  // center coordinate in x direction
		int cy = getHeight() / 2; // center coordinate in y direction
		addProgHierachy(cx, cy);
	}
	
	private void addProgHierachy(int cx, int cy) {
		addUpperProg(cx, cy);
		addLowerProg(cx, cy);
		linkUpperLowerProgs(cx, cy);
	}
	
	/* drawing upper program */
	private void addUpperProg(int cx, int cy) {
		double INIT_X = cx - WIDTH / 2; // initial x coordinate of the label background 
		double INIT_Y = cy - HEIGHT - VERTICAL_DIST / 2; // initial y coordinate of the label background 
		String label_content = "Program"; 	
		addLabel(INIT_X, INIT_Y, label_content);
		addLabelBackground(INIT_X, INIT_Y);		
	}
	
	/* drawing lower class program */
	private void addLowerProg(int cx, int cy) {
		addLeftLabel(cx, cy);
		addMidLabel(cx, cy);
		addRightLabel(cx, cy);
	}
	
	/* drawing the left lower program */
	private void addLeftLabel(int cx, int cy) {
		double INIT_X = cx - WIDTH / 2 - WIDTH - HORIZONTAL_DIST; // initial x coordinate of the label background 
		double INIT_Y = cy + VERTICAL_DIST / 2; // initial y coordinate of the label background 
		String label_content = "GraphicsProgram"; 	
		addLabel(INIT_X, INIT_Y, label_content);
		addLabelBackground(INIT_X, INIT_Y);
	}
	
	/* drawing the middle lower program */
	private void addMidLabel(int cx, int cy) {
		double INIT_X = cx - WIDTH / 2; // initial x coordinate of the label background 
		double INIT_Y = cy + VERTICAL_DIST / 2; // initial y coordinate of the label background 
		String label_content = "ConsoleProgram"; 	
		addLabel(INIT_X, INIT_Y, label_content);
		addLabelBackground(INIT_X, INIT_Y);
	}
	
	/* drawing the right lower program */
	private void addRightLabel(int cx, int cy) {
		double INIT_X = cx + WIDTH / 2 + HORIZONTAL_DIST; // initial x coordinate of the label background 
		double INIT_Y = cy + VERTICAL_DIST / 2; // initial y coordinate of the label background 
		String label_content = "DialogProgram"; 	
		addLabel(INIT_X, INIT_Y, label_content);
		addLabelBackground(INIT_X, INIT_Y);
	}
	
	/* drawing label */
	private void addLabel(double INIT_X, double INIT_Y, String label_content) {
		GLabel label = new GLabel(label_content);
		double label_width = label.getWidth();
		double label_height = label.getAscent();
		label.setLocation(INIT_X + WIDTH / 2 - label_width / 2, INIT_Y + HEIGHT / 2 + label_height / 2);
		add(label);
	}
	
	/* drawing label background */
	private void addLabelBackground(double INIT_X, double INIT_Y) {
		GRect label_background = new GRect(INIT_X, INIT_Y, WIDTH, HEIGHT);
		add(label_background);
	}
	/* drawing lines from upper class to lower classes */
	private void linkUpperLowerProgs(int cx, int cy) {
		double upper_cx = cx;
		double upper_cy = cy - VERTICAL_DIST / 2;
		double left_lower_cx = cx - WIDTH - HORIZONTAL_DIST;
		double mid_lower_cx = cx;
		double right_lower_cx = cx + WIDTH + HORIZONTAL_DIST;
		double lower_cy = cy + VERTICAL_DIST / 2; 
		addUpperToLowerLine(upper_cx, upper_cy, left_lower_cx, lower_cy);
		addUpperToLowerLine(upper_cx, upper_cy, mid_lower_cx, lower_cy);
		addUpperToLowerLine(upper_cx, upper_cy, right_lower_cx, lower_cy);
	}
	
	/* drawing the specific line for the paired classes */
	private void addUpperToLowerLine(double cx_0, double cy_0, double cx_1, double cy_1) {
		GLine line = new GLine(cx_0, cy_0, cx_1, cy_1);
		add(line);
		
		int[]  myarr;
		
		myarr = new int[10];
		
	     String[]  mystring;
		
		
	}
}

