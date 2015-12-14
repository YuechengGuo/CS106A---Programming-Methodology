/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	/** Starting x coordinate of this Pyramid (the very left-bottom point) */	
	private static final int INIT_X = 0;
	
	/** Starting y coordinate of this Pyramid (the very left-bottom point) */	
	private static final int INIT_Y = 300;
	
	public void run() {
		for (int i = BRICKS_IN_BASE; i > 0; i--) {
			for (int j = 0; j <= BRICKS_IN_BASE - i; j++) {
				BuildCurrentFloor(i, j);
			}
		}
	}
	
	private void BuildCurrentFloor(int Num_Brick, int jth_Floor) {
		GRect Brick = new GRect(INIT_X + Num_Brick * BRICK_WIDTH + jth_Floor * BRICK_WIDTH / 2, INIT_Y - (jth_Floor + 1) * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
		add(Brick);
	}
}

