/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = inputNumber();
		int step = 0;
		while (true) {
			if (n == 1) {
				break;
			}
			n = findTheNextNumber(n); // assign the value of next number to n
			step++;
		}		
		output(n, step);		
	}
	
	/* Enter a number to start */
	private int inputNumber() {
		println("Hailstones Problem.");
		int input_num = readInt("Enter a positive integer number to start: ");
		return input_num;
	}
	
	/* find the next number by Hofstadter's algorithm and print the number out */
	private int findTheNextNumber(int n) {
		int next_num;
		if (n % 2 == 0) {
			next_num = n / 2;
			println(n + " is even, so I take half: " + next_num);
		} else {
			next_num = 3 * n + 1;
			println(n + " is odd, so I multiply it by 3 and add 1: " + next_num);
		}
		return next_num;
	}
	
	/* output the final results */
	private void output(int next_num, int step) {
		println("The process took " + step + " steps to reach " + next_num);
	}
}

