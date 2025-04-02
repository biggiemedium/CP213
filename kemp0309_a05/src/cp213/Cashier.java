package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Reads keyboard input. Returns a positive quantity, or 0 if the input is not a
     * valid positive integer.
     *
     * @param scan A keyboard Scanner.
     * @return
     */
    private int askForQuantity(Scanner scan) {
	int quantity = 0;
	System.out.print("How many do you want? ");

	try {
	    String line = scan.nextLine();
	    quantity = Integer.parseInt(line);
	} catch (NumberFormatException nfex) {
	    System.out.println("Not a valid number");
	}
	return quantity;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");
	Order order = new Order();
	Scanner in = new Scanner(System.in);
	
	while(true) {
		this.printCommands();
		int option = 0;
		// just realized we need this -> Check for input
		try {
			option = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number"); // Must input a number
            continue; // skip this loop
        }
		
		if (option == 0) { // if 0 end loop
            break;
        } else if (option > 0 && option <= menu.size()) { // if within bounds
            MenuItem item = menu.getItem(option - 1);
            int quantity = this.askForQuantity(in);
            if (quantity > 0) {
                order.add(item, quantity);
            } else {
                System.out.println("Not a valid number");
            }
        } else { // out of bounds. try catch works here but whatever it works
            System.out.println("Not a valid number");
        }
    
	}
	System.out.println(order.toString());
	return order;
    }
}