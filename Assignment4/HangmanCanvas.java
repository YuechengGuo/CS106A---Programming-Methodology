/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset(int canvasWidth, int canvasHeight) {
		removeAll();
		WIDTH = canvasWidth;
		HEIGHT = canvasHeight;
		X_BODY_BOTTOM = WIDTH / 2;
		Y_BODY_BOTTOM = HEIGHT / 2;
		inputLetters = "";
		addScaffold();		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		int xLabel = WIDTH / 10;
		int yLabel = HEIGHT - WORD_Y_OFFSET;
		if (userGuess == null) {
			userGuess = new GLabel("", xLabel, yLabel);
		}
		userGuess.setLabel(word);
		add(userGuess);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		int xLabel = WIDTH / 10;
		int yLabel = HEIGHT - LETTERS_Y_OFFSET;
		inputLetters += letter;
		GLabel label = new GLabel(inputLetters, xLabel, yLabel);	
		add(label);
		addMan(inputLetters);
	}
	
	private void addScaffold() {
		int xScaffoldBot = X_BODY_BOTTOM - BEAM_LENGTH;
		int yScaffoldBot = Y_BODY_BOTTOM - BODY_LENGTH - 2 * HEAD_RADIUS - ROPE_LENGTH + SCAFFOLD_HEIGHT;
		int xScaffoldTop = X_BODY_BOTTOM - BEAM_LENGTH;
		int yScaffoldTop = yScaffoldBot - SCAFFOLD_HEIGHT;
		
		int xBeamRight = X_BODY_BOTTOM;
		int yBeamRight = yScaffoldTop;
		
		int xRopeBot = X_BODY_BOTTOM;
		int yRopeBot = yScaffoldTop + ROPE_LENGTH;
		
		GLine scaffoldSupport = new GLine(xScaffoldBot, yScaffoldBot, xScaffoldTop, yScaffoldTop);
		GLine scaffoldBeam = new GLine(xScaffoldTop, yScaffoldTop, xBeamRight, yBeamRight);
		GLine scaffoldRope = new GLine(xBeamRight, yBeamRight, xRopeBot, yRopeBot);
		add(scaffoldSupport);
		add(scaffoldBeam);
		add(scaffoldRope);
	}
	
	private void addMan(String str) {
		if (str.length() <= 1) {
			addHead();
		} else if (str.length() <= 2) {
			addBody();
		} else if (str.length() <= 3) {
			addLeftArm();
		} else if (str.length() <= 4) {
			addRightArm();
		} else if (str.length() <= 5) {
			addLeftLeg();
		} else if (str.length() <= 6) {
			addRightLeg();
		} else if (str.length() <= 7) {
			addLeftFoot();
		} else if (str.length() <= 8) {
			addRightFoot();
		} 
	}
	
	private void addHead() {
		GOval head = new GOval(X_BODY_BOTTOM - HEAD_RADIUS, Y_BODY_BOTTOM - BODY_LENGTH - 2 * HEAD_RADIUS, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS );
		add(head);
	}
	
	private void addBody() {
		GLine body = new GLine(X_BODY_BOTTOM, Y_BODY_BOTTOM, X_BODY_BOTTOM, Y_BODY_BOTTOM - BODY_LENGTH);
		add(body);
		}
	
	private void addLeftArm() {
		int xLLABot = X_BODY_BOTTOM - UPPER_ARM_LENGTH;
		int yLLABot = Y_BODY_BOTTOM - (BODY_LENGTH - ARM_OFFSET_FROM_HEAD - LOWER_ARM_LENGTH);
		int xLLATop = xLLABot;
		int yLLATop = yLLABot - LOWER_ARM_LENGTH;
		int xLUARight = xLLABot + UPPER_ARM_LENGTH;
		int yLUARight = yLLATop;
		
		GLine leftLowerArm = new GLine(xLLABot, yLLABot, xLLATop, yLLATop);
		GLine leftUpperArm = new GLine(xLLATop, yLLATop, xLUARight, yLUARight);
		
		add(leftLowerArm);
		add(leftUpperArm);
		
		
	}
	private void addRightArm() {
		int xRLABot = X_BODY_BOTTOM + UPPER_ARM_LENGTH;
		int yRLABot = Y_BODY_BOTTOM - (BODY_LENGTH - ARM_OFFSET_FROM_HEAD - LOWER_ARM_LENGTH);
		int xRLATop = xRLABot;
		int yRLATop = yRLABot - LOWER_ARM_LENGTH;
		int xRUALeft = xRLATop - UPPER_ARM_LENGTH;
		int yRUALeft = yRLATop;
		
		GLine rightLowerArm = new GLine(xRLABot, yRLABot, xRLATop, yRLATop);
		GLine rightUpperArm = new GLine(xRUALeft, yRUALeft, xRLATop, yRLATop);
		
		add(rightLowerArm);
		add(rightUpperArm);
	}
	private void addLeftLeg() {
		int xLLBot = X_BODY_BOTTOM - HIP_WIDTH;
		int yLLBot = Y_BODY_BOTTOM + LEG_LENGTH;
		int xLLTop = xLLBot;
		int yLLTop = Y_BODY_BOTTOM;
		int xLHRight = X_BODY_BOTTOM;
		int yLHRight = Y_BODY_BOTTOM;
		
		GLine leftLeg = new GLine(xLLBot, yLLBot, xLLTop, yLLTop);
		GLine leftHip = new GLine(xLLTop, yLLTop, xLHRight, yLHRight);
		
		add(leftLeg);
		add(leftHip);
	}
	private void addRightLeg() {
		int xRLBot = X_BODY_BOTTOM + HIP_WIDTH;
		int yRLBot = Y_BODY_BOTTOM + LEG_LENGTH;
		int xRLTop = xRLBot;
		int yRLTop = Y_BODY_BOTTOM;
		int xRHLeft = X_BODY_BOTTOM;
		int yRHLeft = Y_BODY_BOTTOM;
		
		GLine rightLeg = new GLine(xRLBot, yRLBot, xRLTop, yRLTop);
		GLine rightHip = new GLine(xRLTop, yRLTop, xRHLeft, yRHLeft);
		
		add(rightLeg);
		add(rightHip);
	}
	private void addLeftFoot() {
		int xLFLeft = X_BODY_BOTTOM - HIP_WIDTH - FOOT_LENGTH;
		int yLFLeft = Y_BODY_BOTTOM + LEG_LENGTH;
		int xLFRight = xLFLeft + FOOT_LENGTH;
		int yLFRight = yLFLeft;
		
		GLine leftFoot = new GLine(xLFLeft, yLFLeft, xLFRight, yLFRight);
		
		add(leftFoot);		
	}
	private void addRightFoot() {
		int xRFRight = X_BODY_BOTTOM + HIP_WIDTH + FOOT_LENGTH;
		int yRFRight = Y_BODY_BOTTOM + LEG_LENGTH;
		int xRFLeft = xRFRight - FOOT_LENGTH;
		int yRFLeft = yRFRight;
		
		GLine leftFoot = new GLine(xRFLeft, yRFLeft, xRFRight, yRFRight);
		
		add(leftFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int WORD_Y_OFFSET = 50;
	private static final int LETTERS_Y_OFFSET = 35;
	
	private int HEIGHT;
	private int WIDTH;
	private int X_BODY_BOTTOM;
	private int Y_BODY_BOTTOM;
	
	
/*	private GObject scaffold;
	private GObject head;
	private GObject body;
	private GObject leftArm;
	private GObject rightArm;
	private GObject leftLeg;
	private GObject rightLeg;
	private GObject leftFoot;
	private GObject rightFoot;*/
	
	private String inputLetters;
	private GLabel userGuess;
}
