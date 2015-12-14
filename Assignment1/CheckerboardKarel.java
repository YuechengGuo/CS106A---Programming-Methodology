/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		FillTwoRows();
		while (frontIsClear()) {
			move();
			turnRight();
			FillTwoRows();
		}
	}
	
	/* Pre-condition: facing east, at column 1, row i
	 * Post-condition: facing north, at column 1, row i + 1
	 */
	private void FillTwoRows() {
		FillRow();
		if (leftIsBlocked()) {
			turnLeft();			
		} else {
			turnLeft();
			if (leftIsClear()) {
				MoveToUpperRowTurnLeft();
				FillRow();
				turnRight();
			} else {
				move();	
			}
		}
	}
	
	/* fill the row in current direction
	 */
	private void FillRow() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
				
			}
		}
	}
	
	/* Pre-condition: facing north after filling ith row
	 * Post-condition: facing west, move to (i+1)th row, start at the right position.
	 */
	private void MoveToUpperRowTurnLeft() {
		if (frontIsClear()) {
			if (beepersPresent()) {
				move();
				turnLeft();
				if (frontIsClear()) {
					move();
				}
			} else {
				move();
				turnLeft();
			}
		}
	}
}
