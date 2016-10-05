package com.gorkarevilla.countwords;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 * Specification:
 * 
 * The program has to read a text from one or several files. The names will be
 * provided as an input to the program. For each file provided, the program has
 * to show: the frequency of appearance of each word it contains, excepting:
 * articles, prepositions and pronouns, which will not be taken into
 * consideration.
 * 
 * @version 1.0
 * @author Gorka Revilla
 *
 */
public class CountWords {

	// Words to exclude
	private static final Set<String> EXCLUDE_LIST = new HashSet<String>(Arrays.asList(new String[] {
			// Articles
			"a", "an", "the",
			// Prepositions short list
			"aboard", "about", "above", "across", "after", "against", "along", "amid", "among", "anti", "around", "as",
			"at", "before", "behind", "below", "beneath", "beside", "besides", "between", "beyond", "but", "by",
			"concerning", "considering", "despite", "down", "during", "except", "excepting", "excluding", "following",
			"for", "from", "in", "inside", "into", "like", "minus", "near", "of", "off", "on", "onto", "opposite",
			"outside", "over", "past", "per", "plus", "regarding", "round", "save", "since", "than", "through", "to",
			"toward", "towards", "under", "underneath", "unlike", "until", "up", "upon", "versus", "via", "with",
			"within", "without",
			// Pronouns
			"i", "me", "we", "us", "you", "she", "her", "he", "him", "it", "they", "them", "that", "which", "who",
			"whom", "whose", "whichever", "whoever", "whomever", "this", "these", "those", "anybody", "anyone",
			"anything", "each", "either", "everybody", "everyone", "everything", "neither", "nobody", "nothing",
			"somebody", "someone", "something", "both", "few", "many", "several", "all", "any", "most", "none", "some",
			"myself", "ourself", "yourself", "yourselves", "himselves", "herselves", "ifself", "themselves" }));

	// Table with Words used, <word, number of times in the files>
	private static Hashtable<String, Integer> WORDS = new Hashtable<String, Integer>();

	/**
	 * 
	 * Process all the files of the parameters and print the result in the
	 * output
	 * 
	 * @param args
	 *            the names of the files to process, the file must be in the
	 *            same directory of the application.
	 */
	public static void main(String[] args) {

		// all the parameters should be processed
		for (String file : args) {
			processFile(file);
		}

		// Print in the default output the result.
		System.out.println(getWORDS());

	}

	/**
	 * Process all the file specified in the parameter path Updating the list of
	 * words (WORDS).
	 * 
	 * @param path
	 *            the path to the file to be processed
	 */
	private static void processFile(String path) {

		// Try to open the file
		try {
			Scanner file = new Scanner(new FileReader(path));

			// Read all the lines in the File
			while (file.hasNextLine()) {
				Scanner line = new Scanner(file.nextLine());
				
				// Read all the Strings in the line
				while (line.hasNext()) {
					String word = line.next();

					// First clean it
					word = cleanWord(word);


					//Convert to lower case to avoid repeating the same word
					word = word.toLowerCase();
					
					// If the word is not in the Exclusion list, add to the
					// table words.
					if (!EXCLUDE_LIST.contains(word)) {

						// If it is in the table update it, otherwise insert in
						// the table
						if (WORDS.containsKey(word)) {
							WORDS.replace(word, WORDS.get(word) + 1);

						} else {
							WORDS.put(word, 1);

						}

					}
				}
				//Close the Scanner
				line.close();
			}
			//Close the Scanner
			file.close();

		} catch (FileNotFoundException e) {
			//Show error message with the path of the file.
			System.err.println("The file " + path + " can not be loaded, please check it.");
		}

	}

	/**
	 * Clean the word in the parameter to remove the non necessary characters,
	 * for example: ',' or '.'
	 * 
	 * @param word
	 *            the word to be clean
	 * @return the word cleared
	 */
	private static String cleanWord(String word) {

		word = word.replace(",", ""); // ,
		word = word.replace(".", ""); // .
		word = word.replace(":", ""); // :
		word = word.replace("(", ""); // (
		word = word.replace(")", ""); // )
		word = word.replace("[", ""); // [
		word = word.replace("]", ""); // ]
		word = word.replace("-", ""); // -

		return word;
	}

	/**
	 * 
	 * @return all the WORDS and the frecuency of them
	 */
	private static String getWORDS() {

		return WORDS.toString();
	}

}
