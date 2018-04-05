/**Steve Meckstroth
 * Assignment08
 * Due Date: 04/03/2018
 * Computer Programming 2 IT 2045C/001/Spring2018
 * Citations: Modified code from https://github.com/meckstss/meckstss_Assignment07
 * https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 * Reads in a text file and offers several methods to perform actions with the data contained within
 */
package fileReader;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections; 
import java.util.LinkedHashMap; 
import java.util.Map.Entry;

import static java.util.stream.Collectors.*; 
import static java.util.Map.Entry.*;



/**
 * Opens words.txt and calculates statistics
 * @author Steve Meckstroth and Jennifer Palazzolo
 *
 */
public class textFileReader {
	InputStreamReader fileReader;
	String fileName = "";
	Map<String, String> words = new HashMap<String, String>();
	
	private String strMostCommonLetter = "";
	private int intMostCommonLetterCnt = 0;
	private String strLeastCommonLetter = "";
	private int intLeastCommonLetterCnt = 0;
	
	private Double avgWordLength = 0.0;
	private String strLongestWord = "";
	private int intLongestWord = 0;
	
	public textFileReader (String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		this.fileName = fileName;
		openTextFile();
	}
	
	private void openTextFile() throws FileNotFoundException, UnsupportedEncodingException{
		fileReader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
	}
	
	public void readTextFile() throws IOException{
		String line;
		
		// Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        
        
		while((line = bufferedReader.readLine()) != null) {
            words.put(line.toUpperCase(), line);
        }   
		
		 // Always close files.
        bufferedReader.close();   
	}
	/**
	 * Calculate the least/most common letters among the words in the text file
	 * @return String representing the most common letter
	 */
	private void commonLetter(){
		
		Map<String, Integer> letters = new HashMap<String, Integer>();
		Iterator<Entry<String, String>> it = words.entrySet().iterator();
		//iterate over the words array, and then each letter for each word, collect counts for each letter into the letters HashMap
		
		while (it.hasNext()){
			Entry<String, String> pair = it.next();
			for (int j = 0; j < pair.getValue().length(); j++){
				String c =  String.valueOf(pair.getValue().charAt(j));
				if (letters.get(c) != null){
					letters.put(c, letters.get(c) + 1);
				} else {
					letters.put(c,  1);
				}  
			}	
		}
		
		//Sort the letters HasMap by values and store it into a new HasMap
		//Modified code from http://javarevisited.blogspot.com/2017/09/java-8-sorting-hashmap-by-values-in.html
		 Map<String, Integer> sortedLetters = letters .entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		 
		 //After the descending sort the first key/value pair should be the most common letter
		 setStrMostCommonLetter(sortedLetters.entrySet().iterator().next().getKey());
		 setIntMostCommonLetterCnt(sortedLetters.entrySet().iterator().next().getValue());
		 
		 //Rebuild sortedLetters but sort ascending by count
		 sortedLetters = letters .entrySet() .stream() .sorted(comparingByValue()) .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		 
		 //After the descending sort the first key/value pair should be the most common letter
		 setStrLeastCommonLetter(sortedLetters.entrySet().iterator().next().getKey());
		 setIntLeastCommonLetterCnt(sortedLetters.entrySet().iterator().next().getValue());
	}
	/**
	 * Executes private method mostCommonLetter()
	 * @return String representing most common letter
	 */
	public String getStrMostCommonLetter() {
		if (strMostCommonLetter == ""){
			commonLetter();
		}
		return strMostCommonLetter;
	}
	/**
	 * The setter is private to the class to avoid bad data
	 * @param strMostCommonLetter
	 */
	private void setStrMostCommonLetter(String strMostCommonLetter) {
		this.strMostCommonLetter = strMostCommonLetter;
	}
	/**
	 * Executes private method mostCommonLetter()
	 * @return Int representing the most common letter count
	 */
	public int getIntMostCommonLetterCnt() {
		//If the most common letter hasn't been calculated then calculate it
		if (strMostCommonLetter == ""){
			commonLetter();
		}
		return intMostCommonLetterCnt;
	}
	/**
	 * The setter is private to avoid bad data
	 * @param intMostCommonLetterCnt
	 */
	private void setIntMostCommonLetterCnt(int intMostCommonLetterCnt) {
		this.intMostCommonLetterCnt = intMostCommonLetterCnt;
	}
	/**
	 * Executes private method mostCommonLetter() 
	 * @return String for least common letter
	 */
	public String getStrLeastCommonLetter() {
		//If the most common letter hasn't been calculated then calculate it
				if (strMostCommonLetter == ""){
					commonLetter();
				}
		return strLeastCommonLetter;
	}
	/**
	 * Setter is private to avoid bad data
	 * @param strLeastCommonLetter
	 */
	private void setStrLeastCommonLetter(String strLeastCommonLetter) {
		this.strLeastCommonLetter = strLeastCommonLetter;
	}
	/**
	 * Executes private method mostCommonLetter()
	 * @return Int representing least common letter's count
	 */
	public int getIntLeastCommonLetterCnt() {
		//If the most common letter hasn't been calculated then calculate it
				if (strMostCommonLetter == ""){
					commonLetter();
				}
		return intLeastCommonLetterCnt;
	}
	/**
	 * Setter is private to avoid bad data
	 * @param intLeastCommonLetterCnt
	 */
	private void setIntLeastCommonLetterCnt(int intLeastCommonLetterCnt) {
		this.intLeastCommonLetterCnt = intLeastCommonLetterCnt;
	}
	/**
	 * Calculate Average word length, and longest word lenght, and shortest word length
	 */
	private void calculateWordCounts(){
		Iterator<Entry<String, String>> it = words.entrySet().iterator();
		
		int wordLengthTotal = 0;
		//iterate over the words array, and sum the length of all of the words
		while (it.hasNext()){
			Entry<String, String> pair = it.next();
			 wordLengthTotal += pair.getValue().length();
			 if (pair.getValue().length() > intLongestWord){
				 setStrLongestWord(pair.getValue());
				 setIntLongestWord(pair.getValue().length());
			 }
		}
		//Set the class variable for average word length, and round to 2 decimals
		avgWordLength = wordLengthTotal / ((double) words.size()) ;
		avgWordLength = Math.round(avgWordLength*100.0)/100.0;
		
	}
	/**
	 * Executes calculateWordCounts() if not already executed, and then returns the appropriate value
	 * @return Double representing average word length
	 */
	public Double getAvgWordLength() {
		if (avgWordLength == 0.0){
			calculateWordCounts();
		}
		return avgWordLength;
	}
	/**
	 * Private setter for average word length
	 * @param avgWordLength Double
	 */
	public void setAvgWordLength(Double avgWordLength) {
		this.avgWordLength = avgWordLength;
	}
	/**
	 * Executes calculateWordCounts() if not already executed, and then returns the appropriate value
	 * @return String of longest word
	 */
	public String getStrLongestWord() {
		if (getAvgWordLength() == 0.0){
			calculateWordCounts();
		}
		return strLongestWord;
	}
	/**
	 * Private setter for longest word
	 * @param strLongestWord
	 */
	private void setStrLongestWord(String strLongestWord) {
		this.strLongestWord = strLongestWord;
	}
	/**
	 * Executes calculateWordCounts() if not already executed, and then returns the appropriate value
	 * @return Int representing the longest word's size
	 */
	public int getIntLongestWord() {
		if (avgWordLength == 0.0){
			calculateWordCounts();
		}
		return intLongestWord;
	}
	/**
	 * Private setter for the Longest word size
	 * @param intLongestWord
	 */
	private void setIntLongestWord(int intLongestWord) {
		this.intLongestWord = intLongestWord;
	}
	/**
	 * Checks the spelling of the target word provided against a dictionary.  
	 * If matched it returns the match, if not it returns a suggestion of a possible transposed word.  
	 * Else returns empty string. 
	 * @param target
	 * @return
	 */
	public String checkSpelling(String target) {
		String retVal = "";
		String strSearch = "";
		
		
		if (words.get(target.toUpperCase()) != null){
			retVal = words.get(target.toUpperCase());
		} else {
			String[] c = target.split("(?!^)");
			System.out.println("current target: " + target);
			for (int i = 0; i < c.length; i++) {
				strSearch = "";
				
				//If we are at least 2 characters into the string then add a prefix
				System.out.println("i = " + i);
				switch(i){
				case 0:	strSearch = target;
					break;
				case 1: strSearch += c[1] +  c[0] + target.substring(2);
					break;
				case 2: strSearch += c[0] + c[2] + c[1] + target.substring(3);
					break;
				default: strSearch += target.substring(0,i-2) +  c[i] + c[i-1] + target.substring(i+1); 
					break;
				}
				System.out.println("Search String: " + strSearch);
			}
		}
		return retVal;
	}
}
