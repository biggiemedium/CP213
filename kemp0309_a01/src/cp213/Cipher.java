package cp213;

/**
 * @author Your name and id here
 * @version 2025-01-05
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {
    	if(n == 0) { // nothing changed
    		return s;
    	}

    	StringBuilder sb = new StringBuilder();
    	
    	 for (char c : s.toCharArray()) { // use char array instead of index loop
    		 int index = (ALPHA.indexOf(c) + n % ALPHA_LENGTH + ALPHA_LENGTH) % ALPHA_LENGTH;
             sb.append(ALPHA.charAt(index));

    	 }
    	 
    	 return sb.toString();
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

    	StringBuilder sb = new StringBuilder();
	
    	for(char c : s.toCharArray()) {
    		switch(ALPHA.indexOf(c)) { // who needs if statements
    		case -1:
    			sb.append(c);
    			break;
    			
    		default:
    			sb.append(ciphertext.charAt(ALPHA.indexOf(c)));
    			break;
    		}
    	}

	return sb.toString();
    }

}
