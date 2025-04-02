package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
public class Order implements Printable {

    private static final String lineFormat = "%-14s%2d @ $%5.2f = $%6.2f\n";
    private static final String totalFormat = "%-9s                   $%6.2f\n";
    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);

    // define a Map of MenuItem objects
    // Note that this must be a *Map* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Map.html

    // I haven't read the GUI Src but in case we need it thread safe im keeping this concurrent
    // I saw that this week we are going over multi-threading
    Map<MenuItem, Integer> foodMap = new ConcurrentHashMap<>();

    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {
    	// get item - otherwise default to 0 (+ whatevers already on list)
    // gets current item quantity -> defaults to 0 if not found, adds new quantity, updates map with total
	this.foodMap.put(item, foodMap.getOrDefault(item, 0) + quantity);

    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total cost for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {
    	BigDecimal bd = new BigDecimal(0); // BigDecimal.ZERO
    	//this.foodMap.forEach((K, V) -> { // K = menu item | V = double
    	//	bd = bd.add(bd) // Compilation error
    	//});
    	for (Map.Entry<MenuItem, Integer> entry : foodMap.entrySet()) {
            bd = bd.add(entry.getKey().getAmount().multiply(new BigDecimal(entry.getValue())));
        }
    	return bd;
    	
 
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {
	return getSubTotal().multiply(TAX_RATE);
    }

    /**
     * Calculates and returns the total cost of all MenuItems order, including tax.
     *
     * @return total cost
     */
    public BigDecimal getTotal() {

	return this.getSubTotal().add(this.getTaxes());
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {

	StringBuilder sb = new StringBuilder();
	  for (Map.Entry<MenuItem, Integer> entry : foodMap.entrySet()) {
	        MenuItem item = entry.getKey(); // we are using local variables (fields?) bc its easier
	        int quantity = entry.getValue();
	        double itemTotal = item.getAmount().doubleValue() * quantity;
	        
	        sb.append(String.format(lineFormat, 
	            item.getEntity(), 
	            quantity, 
	            item.getAmount().doubleValue(), 
	            itemTotal));
	    }
	  
	  // Check this bc idk if I have to indent
	  sb.append(String.format(totalFormat, "Subtotal", getSubTotal().doubleValue()));
	  sb.append(String.format(totalFormat, "Tax", getTaxes().doubleValue()));
	  sb.append(String.format(totalFormat, "Total", getTotal().doubleValue()));
	return sb.toString();
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {

    	 if (quantity <= 0) { // Double.NEGATIVE_INFINITY
    	        foodMap.remove(item);
    	    } else {
    	        foodMap.put(item, quantity);
    	}

    }
}