package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    System.out.println(order.toString());

	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item = null;

	QuantityListener(final MenuItem item) {
	    this.item = item;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
        try {
            int quantity = Integer.parseInt(((JTextField) e.getSource()).getText());
            if (quantity < 0) {
                quantity = 0;
            }
            if (quantity == 0) {
                order.update(item, quantity);  
            } else {
                order.add(item, quantity);  
            }
           
        } catch (NumberFormatException ex) { // default 0
            ((JTextField) e.getSource()).setText("0");
        }
		
	}

    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final DecimalFormat priceFormat = new DecimalFormat("$##0.00");
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {

    	// rows = menu size + 4
	this.setLayout(new GridLayout(menu.size() + 5, 3));
	this.add(new JLabel("Item"));
	this.add(new JLabel("Price"));
	this.add(new JLabel("Quantity"));
	for(int i = 0; i < menu.size(); i++) {
		MenuItem item = menu.getItem(i);
		this.nameLabels[i] = new JLabel(item.getEntity());
		this.priceLabels[i] = new JLabel(priceFormat.format(item.getAmount().doubleValue()));
		this.quantityFields[i] = new JTextField("0");
		
		// from super class
		this.add(nameLabels[i]);
		this.add(priceLabels[i]);
		this.add(quantityFields[i]);
	}
	
	// this is so messy but it works somehow
	JLabel st;
	this.add(st = new JLabel("Subtotal:"));
	this.add(new JLabel(" "));
	this.add(subtotalLabel); 

	subtotalLabel.setAlignmentX(RIGHT_ALIGNMENT);
	st.setAlignmentX(LEFT_ALIGNMENT);
	
	JLabel t;
	this.add(t = new JLabel("Tax:"));
	this.add(new JLabel(" "));
	this.add(taxLabel); 
	taxLabel.setAlignmentX(RIGHT_ALIGNMENT);
	t.setAlignmentX(LEFT_ALIGNMENT);
	
	this.add(new JLabel("Total:"));
	this.add(new JLabel(" "));
	this.add(totalLabel);
	totalLabel.setAlignmentX(LEFT_ALIGNMENT);
	
	this.add(printButton);
	printButton.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Register the widget listeners with the widgets.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());
	for (int i = 0; i < menu.size(); i++) {
		MenuItem item = menu.getItem(i);
		quantityFields[i].addFocusListener(new QuantityListener(item));
	}
	
	
    }
}