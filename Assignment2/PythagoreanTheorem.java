/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean Theorem.");
		int a = readInt("Length of side a (int): ");
		int b = readInt("Length of side b (int): ");
		double c = Math.sqrt(a * a + b * b);
		println("Length of side c: " + c);
	}
}
