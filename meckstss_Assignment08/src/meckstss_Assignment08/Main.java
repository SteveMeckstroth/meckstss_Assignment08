/**
 * Assignment 08
 * @author Stephen Meckstroth
 * Description: An assignment to implement a spellchecker method
 * Due Date: Apr 3, 2018
 * Course: IT2045 Section 001
 * email: meckstss@mail.uc.edu
 * Citations: N/A
 */

package meckstss_Assignment08;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileReader.textFileReader;

public class Main {

	public static void main(String[] args) throws IOException {
		//Define String array with the word table spelled correctly but mixed case, and transposed a few different ways
		/*String[] wordsToSpellCheck = new String[]{
				"tAbLe", //case insensitive check
				"Table", // Init cap check
				"table", // all lowercase check
				"tbale", // transpose 2nd and 3rd characters
				"tabel", // transpose 4th and 5th characters, note this is a real word
				"atble" // transpose 1st and 2nd characters
				};*/
		String[] wordsToSpellCheck = new String[] {"tbale"};
		
		//Load the dictionary and instantiate textFileReader class object
		textFileReader tf = new textFileReader("src/words.txt");
		tf.readTextFile();
		
		//Loop through the String array and test it
		for (int i = 0; i < wordsToSpellCheck.length; i ++){
			System.out.println("Spell checking: " + wordsToSpellCheck[i] + ", Matched to: " + tf.checkSpelling(wordsToSpellCheck[i]));
			
		}
		
		

	}

}
