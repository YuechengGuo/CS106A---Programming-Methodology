/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			RepairCurrentColumn();
			MoveToNextColumn();
		}
		RepairCurrentColumn();
	}
	
	/* pre-condition: facing east
	 * post-condition: facing east
	 * repair current column from bottom to up
	 */	
	private void RepairCurrentColumn() {
		turnLeft();
		FillStonesUp();
		BackDownToBottom();
		turnLeft();
	}
	
	/* pre-condition: facing north
	 * post-condition: facing north, front is blocked by wall
	 * fill stones into where they are needed
	 */	
	private void FillStonesUp() {
		while (frontIsClear()) {
			putStones();
			move();
		}
		putStones();
	}
	
	/* check if current place needs repairing
	 */
	private void putStones() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}
	
	/* pre-condition: facing north
	 * post-condition: facing south
	 * back to the bottom of this column
	 */	
	private void BackDownToBottom() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
	}
	
	/* pre-condition: facing east
	 * post-condition: facing east
	 * move from current column to the next
	 */	
	private void MoveToNextColumn() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}
			
}
