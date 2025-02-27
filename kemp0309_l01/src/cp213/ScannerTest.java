package cp213;

import java.io.File;
import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author James Kemp
 * @version 2025-01-23
 */
public class ScannerTest {

    /**
     * Count lines in the scanned file.
     *
     * @param source Scanner to process
     * @return number of lines in scanned file
     */
    public static int countLines(final Scanner source) {
	int count = 0;

	 while(source.hasNextLine()) {
		 source.nextLine();
		 count += 1;
	 }
	return count;
    }

    /**
     * Count tokens in the scanned object.
     *
     * @param source Scanner to process
     * @return number of tokens in scanned object
     */
    public static int countTokens(final Scanner source) {
	int tokens = 0;


	while(source.hasNext()) {
		 source.next();
		 tokens++;
	 }
	
	return tokens;
    }

    /**
     * Ask for and total integers from the keyboard.
     *
     * @param source Scanner to process
     * @return total of positive integers entered from keyboard
     */
    public static int readNumbers(final Scanner keyboard) {
	int total = 0;

	while(true) {
		if(keyboard.hasNextInt()) {
			int number = keyboard.nextInt();
            total += number; // sum
            //System.out.println(number);
		} else {
			String in = keyboard.next();
			if(in.equals("q")) { // Use string not char...
				//System.out.println('q');
				break;
			} else {
				System.out.println(in + " is not an integer or 'q'.");
			}
		}

	}

	return total;
    }


}
