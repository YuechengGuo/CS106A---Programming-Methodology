/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 20;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Acceleration of the ball */
	private static final int BALL_ACC = 11;

/** modify the acceleration to 1 pixel, which ensure the ball moves 1 pixel per running ballMove() */
	private static final double BALL_ACC_INDEX = BALL_ACC; 
	
	private static final double DELAY = 1000 / (60 * BALL_ACC_INDEX);
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {		
		gameSetup();
		// gameStart();
		// gameUpdate();
	}

// Setup *****************************************************************************
	private void gameSetup() {

		backgroundSetup();	
		// setSize(WIDTH, HEIGHT);
		addMouseListeners();
		addKeyListeners();
		brickSetup();
		paddleSetup();
		ballSetup();
		
		while (ball.getX() < WIDTH && ball.getY() < HEIGHT - PADDLE_Y_OFFSET) {
			moveBall();
			checkForCollision();
			pause(DELAY);
		}
		// scoreSetup();
	}

	
	private void backgroundSetup() {
		leftWall = new GLine(0, 0, 0, HEIGHT);
		rightWall = new GLine(WIDTH, 0, WIDTH, HEIGHT);
		roofWall = new GLine(0, 0, WIDTH, 0);
		bottomWall = new GLine(0, HEIGHT, WIDTH, HEIGHT);
		add(leftWall);
		add(rightWall);
		add(roofWall);
		add(bottomWall);
			
	}
	
	private void brickSetup() {
		createBricks();
		// fillBricks();
	}
	
	private void createBricks() {
		bricks = new GRect [NBRICK_ROWS][NBRICKS_PER_ROW]; 
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				bricks[i][j] = new GRect(j * (BRICK_WIDTH + BRICK_SEP), i * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT);				
				fillBricks(bricks[i][j], i);
				add(bricks[i][j]);
			}	
		}
	}
	
	private void fillBricks(GRect bricks, int i) {
		bricks.setFilled(true);		
		if (i % 10 < 2) {
			bricks.setColor(Color.RED);
			bricks.setFillColor(Color.RED);
		} else if (i % 10 < 4) {
			bricks.setColor(Color.ORANGE);
			bricks.setFillColor(Color.ORANGE);
		} else if (i % 10 < 6) {
			bricks.setColor(Color.YELLOW);
			bricks.setFillColor(Color.YELLOW);
		} else if (i % 10 < 8) {
			bricks.setColor(Color.GREEN);
			bricks.setFillColor(Color.GREEN);
		} else if (i % 10 < 10) {
			bricks.setColor(Color.CYAN);
			bricks.setFillColor(Color.CYAN);
		}
		/*switch (i / 2) {
		case 0:
			bricks.setColor(Color.RED);
			bricks.setFillColor(Color.RED);
		case 1:
			bricks.setColor(Color.ORANGE);
			bricks.setFillColor(Color.ORANGE);
		case 2:
			bricks.setColor(Color.YELLOW);
			bricks.setFillColor(Color.YELLOW);
		case 3:
			bricks.setColor(Color.GREEN);
			bricks.setFillColor(Color.GREEN);
		case 4:
			bricks.setColor(Color.CYAN);
			bricks.setFillColor(Color.CYAN);
		}	*/	
	}
	
	/* setup paddle */
	private void paddleSetup() {
		paddle = new GRect((WIDTH - PADDLE_WIDTH) / 2, HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	/* keep track on the mouse and get the obj when clicked */
//	public void mousePressed(MouseEvent e) {
//		last = new GPoint(e.getPoint());
//		gobj = getElementAt(last);		
//	}
	
	/* update the object's location */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() > WIDTH - PADDLE_WIDTH){
			paddle.setLocation(WIDTH - PADDLE_WIDTH, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		} else if (e.getX() < 0) {
			paddle.setLocation(0, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		} else {
			paddle.setLocation(e.getX(), HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
	}
	
	/* setup ball */	
	private void ballSetup() {
		ball = new GOval((WIDTH - BALL_RADIUS) / 2, HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.RED);
		add(ball);
		ballVelocitySetup();
	}
	
	
	private void ballVelocitySetup() {
		double launchDirection = rgen.nextDouble(75/180.0 * Math.PI, 30/180.0 *Math.PI);
		if (rgen.nextBoolean(0.5)) xAcc = -xAcc;
		xAcc = BALL_ACC / BALL_ACC_INDEX * Math.cos(launchDirection);
		yAcc = BALL_ACC / BALL_ACC_INDEX * Math.sin(launchDirection);
	}
	
	private void moveBall() {
		double xVel = 0;
		double yVel = 0;
		xVel += xAcc;
		yVel += -yAcc;
		ball.move(xVel, yVel);
	}
	
	private void checkForCollision() {
		hitWall();
		// hitPaddle();
		GObject collider = getCollidingObject();
		if (collider != null) {
			if (collider == paddle){
				ball.setLocation(ball.getX(), paddle.getY() - BALL_RADIUS - 1);
				yAcc = -yAcc;
			} else if (collider != leftWall && collider != rightWall && collider != roofWall && collider != bottomWall)	{
				hitBricks(collider);
			}
		}
	}
	
	private void hitWall() {
		/* bounce back when hitting the left/right side wall */
		if (ball.getX() <= 0 || ball.getX() >= WIDTH - BALL_RADIUS) {
			xAcc = -xAcc;
		}
		/* bounce back when hitting roof */
		if (ball.getY() <= 0) {
			yAcc = -yAcc;
		}
	}	
	
/*	private void hitPaddle() {
		if (ball.getY() > HEIGHT - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT && ball.getX() > paddle.getX() - BALL_RADIUS && ball.getX() < paddle.getX() + PADDLE_WIDTH) {
			 if the ball moves below the paddle in y direct and x coordinate is located within paddle's width
			ball.setLocation(ball.getX(), paddle.getY() - BALL_RADIUS);
			yAcc = -yAcc;
		} else {
			score -= 1;
		}
	}*/

	private void hitBricks(GObject collider) {
		if (ball.getX() + BALL_RADIUS - 1> collider.getX() && ball.getX() + 1 < collider.getX() + BRICK_WIDTH) {			
			yAcc = -yAcc;
		} else { // if (ball.getY() + BALL_RADIUS > collider.getY() && ball.getY() < collider.getY() + BRICK_HEIGHT) {
			xAcc = -xAcc;
		}
		remove(collider);
	}
	
	/* get the colliding object... */
	private GObject getCollidingObject(){
		double x = ball.getX();
		double y = ball.getY();
		GObject collider = null;
		if (getElementAt(x, y) != null) {
			collider = getElementAt(x, y);
		} else if (getElementAt(x + BALL_RADIUS, y) != null) {
			collider = getElementAt(x + BALL_RADIUS, y);
		} else if (getElementAt(x, y + BALL_RADIUS) != null) {
			collider = getElementAt(x, y + BALL_RADIUS);
		} else if (getElementAt(x + BALL_RADIUS, y + BALL_RADIUS) != null) {
			collider = getElementAt(x + BALL_RADIUS, y + BALL_RADIUS);
		}
		return collider;
	}
	
	private void newround() {
		ballSetup();
	}
	
	private void newgame() {
		
	}
// Update *****************************************************************************
	
// Start the game *****************************************************************************
	
	
// initialization *****************************************************************************
	private GLine leftWall, rightWall, roofWall, bottomWall; /* background setting */
	private GRect [][] bricks;
	private GObject gobj; /* the object that mouse dragged */
	private GPoint last; /* keep track the location of mouse */
	private GRect paddle; /* track the paddle */
	private GOval ball; /* track the ball */
	private double xAcc; /* track the velocity of ball */
	private double yAcc; /* track the velocity of ball */
	private int score = NTURNS; 
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
}
