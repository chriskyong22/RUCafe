package sample.Model;

import java.text.DecimalFormat;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class MenuItem {
    protected double itemPrice;
    private DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");

    public MenuItem() {
        this.itemPrice = 0;
    }

    public void itemPrice() { }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public String itemPriceString() {
        return decimalFormat.format(itemPrice);
    }
}
