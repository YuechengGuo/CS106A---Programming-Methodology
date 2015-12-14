/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	
	public void run() {
		PutBeepersAtTwoBorders();
		while (noBeepersPresent()) {
			PutBeepersAtOppositeNewBorder();
		}
		turnAround();
		if (frontIsClear()) {
			move();
		}
		putBeeper();
		CollectOneBeeperForEachCellOfFirstRow();
		turnAround();
		FindTheLastBeeper();		
	}
	
	/* Pre-condition: initial positon, no beeper in the world
	 * Post-condition: Karel facing mid, stands one grid aside the right border
	 * There are two beepers in the world, each one sits at one of the bottom corner
	 */
	private void PutBeepersAtTwoBorders() {
		putBeeper();
		while (frontIsClear()) {
			move();
		}
		if (noBeepersPresent()) {
			putBeeper();
		}
		turnAround();
		if (frontIsClear()) {
			move();
		}
	}
	
	/* Pre-condition: beepers on two sides of row 1, Karel facing mid, no beeper on foot
	 * Post-condition: add one beeper on the opposite side, Karel facing mid, no beeper on foot
	 * There are two beepers in the world, each one sits at one of the bottom corner
	 */
	private void PutBeepersAtOppositeNewBorder() {
		while (noBeepersPresent()) {
			move();
		}
		turnAround();
		move();
		putBeeper();
		move();
	}
	
	private void CollectOneBeeperForEachCellOfFirstRow() {
		MoveToCorner();
		turnAround();
		while (frontIsClear()) {
			//if (beepersPresent()) {
				pickBeeper();
			//}
			move();
		}
		pickBeeper();
	}
	
	private void FindTheLastBeeper() {
		while (noBeepersPresent()) {
			move();
		}		
	}
	
	private void MoveToCorner() {
		while (frontIsClear()) {
			move();
		}
	}
}
