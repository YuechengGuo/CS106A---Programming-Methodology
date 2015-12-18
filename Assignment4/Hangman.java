/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	/** number of turns you have */
	private static final int NTURNS = 8;
	
	public void init () {
		setSize(800, 600);
		
		canvas = new HangmanCanvas();		
		add(canvas);
	}
	
    public void run() {
		setupGame();
		updateGame();
	}
    
    /* 
     * Initialize the secret word (all set to be upper case) and user's input word 
     * setup console/canvas
     */
    private void setupGame() {
    	// rgen.setSeed(1);
    	int canvasWidth = canvas.getWidth();
    	int canvasHeight = canvas.getHeight();
    	canvas.reset(canvasWidth, canvasHeight);
    	println(getWidth());
    	println(getHeight());
    	NTurns = NTURNS;
    	HangmanLexicon gw = new HangmanLexicon();
    	secretWord = gw.getWord(rgen.nextInt(gw.getWordCount()));
    	secretWord = secretWord.toUpperCase();
    	// println(secretWord);
    	userGuess = mockDash(secretWord);
    	setupConsole();
    }
    
    /* display the sercret word by dashes (track the length of the word) */
    private String mockDash(String str) {
    	String outputStr = "";
    	for (int i = 0; i < str.length(); i++) {
    		outputStr += "-";
    	}
    	return outputStr;
    }
    
    /* add brief description of this game at beginning */
    private void setupConsole() {
    	println("Welcome to Hangman!");
    }
    
    /* main logic to detect user's input */
    private void updateGame() {
    	userInputWord = "";
    	String ch = "";
    	while (NTurns > 0) {
    		displayUserGuess();    		
    		ch = getUserInput();    		
    		/* check if user inputs replicated letters */
    		checkReplicates(ch);    		
    		/* print the # of turns user left */
    		printResults();
    		/* winning check */
    		if (secretWord.equals(userGuess)) {
    			displayUserGuess();
    			println("You WIN!!! :D");
    			break;
    		}
    	}    	
    }
    
    private void displayUserGuess() {
		println("The word now looks like this: " + userGuess);
		canvas.displayWord(userGuess);
    }
    
    private void checkReplicates(String ch) {
    	if (! userInputWord.contains(ch)) {
			userInputWord += ch;
			updateInput(userInputWord);
		} else {
			println("You've guessed " + "\"" + ch + "\", tring some others.");
		}
    }
    
    private String getUserInput() {
    	String ch = readLine("Your guess: ");
    	while (ch.length() != 1 || inputNotCharacter(ch)) {
    		ch = readLine("Input ONE CHARACTER as your guess: ");
    	}    	
    	ch = ch.toUpperCase();
    	return ch;
    }
    
    private boolean inputNotCharacter(String Char) {
    	if ((Char.charAt(0) >= 'a' && Char.charAt(0) <= 'z') || (Char.charAt(0) >= 'A' && Char.charAt(0) <= 'Z')) {
    		return false;
    	}
    	return true;
    }
    
    private void updateInput(String Str) {
    	String lastChar = Str.substring(Str.length() - 1);    	
    	if (secretWord.contains(lastChar)) {
    		updateUserGuess(lastChar);
    	} else {
    		NTurns -= 1;
    		println("There's no " + lastChar + "\'s in the word.");
    		char ch = lastChar.charAt(0);
    		canvas.noteIncorrectGuess(ch);
    	}
    }
    
    private void updateUserGuess(String lastChar) {
    	for (int i = 0; i < secretWord.length(); i++) {
    		if (secretWord.charAt(i) == lastChar.charAt(0)) {
    			userGuess = userGuess.substring(0, i) + lastChar + userGuess.substring(i + lastChar.length());    			
    		}
    	}
    }
 
    private void printResults() {		
    	if (NTurns == 1) {
    		println("You only have one guess left.");
    	} else if (NTurns == 0) {
    		println("Busted! :(");
    		println("The word is " + secretWord);
    	} else {
    		println("You still have " + NTurns + " guesses.");
    	}
    	println("");
    }
    
    /* declare variables */
    private String secretWord;
    private String userGuess;
    private String userInputWord;
    private int NTurns;
    private HangmanCanvas canvas;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
}
