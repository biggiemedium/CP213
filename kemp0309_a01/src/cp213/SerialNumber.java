package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Your name and id here
 * @version 2025-01-05
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	for(int i = 0; i < str.length(); i++) {
		if(Character.isLetter(str.charAt(i)) || str.charAt(i) == '.') {
			return false;
		}
	}

	return true;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	if(!sn.startsWith("SN/") || sn.length() != 11) { 
		return false;
	}
	
	// Checking first 4 digits
	for(int i = 3; i < 7; i++) {
		if(!Character.isDigit(sn.charAt(i))) {
			return false;
		}
	}
	
	if(sn.charAt(7) != '-') { // split regex
		return false;
	}
	
	// checking last 3 digits - char 7 already accounted for
	for(int i = 8; i < 11; i++) {
		if(!Character.isDigit(sn.charAt(i))) {
			return false;
		}
	}

	return true;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

    	String line;
    	// don't have to do empty check bc we alr checked it in the while loop
    	while ((line = fileIn.nextLine()) != null) { 
             line.trim();
             
             if(validSn(line)) { // we can just reuse the validSn method
            	 goodSns.print(line);
             } else {
            	 badSns.print(line);
             }
         }
    

    	return;
    }

}
