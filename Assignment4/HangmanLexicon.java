/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		readFile();
		return wordList.size();
		
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		/*switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}*/
		
		return wordList.get(index);
	}
	
	private void readFile() {
		BufferedReader rd = null; 
		wordList = new ArrayList<String>();
		
		while (rd == null) {			
			try {
				rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
				while (rd.readLine() != null) {
					wordList.add(rd.readLine());
				}
			} catch (IOException ex) {
				throw new ErrorException(ex);
			}
		}
	}
	
	private ArrayList<String> wordList;
}
