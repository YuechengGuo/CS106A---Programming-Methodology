/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	/** Set the sentinel value */
	private static final int SENTINEL_VALUE = 0;
	
	public void run() {	
		println("Find the maximum and minimum number. Input 0 to stop.");
		while (true) {
			int inputValue = readInt("Input an integer number: ");			
			if (inputValue == SENTINEL_VALUE) {
				break;
			}
			compareInput(inputValue);
		}
		outPut();
	}
	
	/* compare input integer with instance var max_num and min_num */
	private void compareInput(int input) {
		if (max_num == SENTINEL_VALUE || min_num == SENTINEL_VALUE)  {
			max_num = input;
			min_num = input;		
		} else {
			if (input > max_num) {
				max_num = input;
			}
			
			if (input < min_num) {
				min_num = input;
			}	
		}		
	}
	
	/* output the comparison results */
	private void outPut() {
		if (max_num == SENTINEL_VALUE || min_num == SENTINEL_VALUE) {
			println("No valid input detected.");
		} else {
			println("smallest: " + min_num);
			println("largest: " + max_num);
		}
		
	}
	
	/* def. instance var */
	private int max_num = SENTINEL_VALUE;
	private int min_num = SENTINEL_VALUE;
}

