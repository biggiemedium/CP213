package cp213;

/**
 * @author Your name and id here
 * @version 2025-01-05
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {
    //string.toLowerCase();
    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < string.length(); i++) {
    	char c = string.charAt(i); // use this so we don't mutate anything
    	if(Character.isLetterOrDigit(c)) {
    		sb.append(Character.toLowerCase(c));
    	
    	}
    }
    
    //sb.reverse();

	return sb.toString().equals(sb.reverse().toString());
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	if(Character.isDigit(name.charAt(0))) {
		return false;
	}

	
	if(Character.isLetter(name.charAt(0))) {
		return true;
	}

	return name.charAt(0) == '_' && name.length() > 1;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
    
    char c = word.charAt(0);
    boolean upper = Character.isUpperCase(c);

    int i = 1; // iterating for vowels
    while (i < word.length() && VOWELS.indexOf(word.charAt(i)) < 0) {
    	i++;
    }

    String fv = upper ? 
    		word.substring(i) : 
    		Character.toUpperCase(word.substring(i).charAt(0)) + word.substring(i).substring(1); 
    String cnst = word.substring(0, i).toLowerCase(); 
    
   // if (upper) {
   // 	fv = ;
   // }
    
    return fv + cnst + "ay";
    }

}
