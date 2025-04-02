package cp213;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
public class Menu {

    // Attributes.

    // define a List of MenuItem objects
    // Note that this must be a *List* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

	
    private List<MenuItem> list;

    /**
     * Creates a new Menu from an existing List of MenuItems. MenuItems are copied
     * into the Menu List.
     *
     * @param items an existing List of MenuItems.
     */
    public Menu(List<MenuItem> items) {

	this.list = items;

    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {
    	this.list = new ArrayList<>(); // can't call on something null
    	
    	String line = "";
    	while(fileScanner.hasNextLine()) {
    		line = fileScanner.nextLine().trim();
    		if(!line.isEmpty()) {
    			String[] split = line.split(" "); // we are making a regex that checeks for space character
    			double price = Double.parseDouble(split[0]); // 1.25 Hot Dog
    			String name = split[1].trim(); // this will turn Hot Dog --> HotDog
    			this.list.add(new MenuItem(name, price));
    			
    		}
    	}
    	
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {
	return(this.list.get(i));
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	return this.list.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder(); // lets use this because strings are immutable and we have a lot to add
    	
    	// a non enhanced loop is better so we can track the index
    	for(int i = 0; i < this.size(); i++) {
    		sb.append(String.format("%d) %s%n", i + 1, list.get(i).toString()));
    	}
    	//for(MenuItem i : this.list) {
    	//	sb.append((i + 1) + ")" + this.getItem(i) + "\t" + ));
    	//}
    	return sb.toString();
    }
}