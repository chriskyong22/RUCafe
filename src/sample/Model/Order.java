package sample.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */

public class Order implements Customizable {
    private static int generatingOrderNumbers = 1;
    private int orderNumber;
    private double subTotalCost;
    private double totalCost;
    public static final double SALES_TAX = 0.06625;
    private ArrayList<MenuItem> items;

    public Order() {
        this.items = new ArrayList<>();
        this.orderNumber = generatingOrderNumbers++;
        this.subTotalCost = 0;
        this.totalCost = 0;
    }

    @Override
    public boolean add(Object obj) {
        //TO DO
        if (!(obj instanceof MenuItem)) {
            return false;
        }
        items.add((MenuItem) obj);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        //TO DO
        if (!(obj instanceof MenuItem)) {
            return false;
        }
        return items.remove(obj);
    }

    public int getNumberOfMenuItems() {
        return items.size();
    }

    public MenuItem getItem(int index) {
        return items.get(index);
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void calculateSubTotalCost() {
        this.subTotalCost = 0;
        for(MenuItem item : items) {
            item.itemPrice();
            subTotalCost += item.getItemPrice();
        }
    }

    public double getSubTotalCost() {
        return this.subTotalCost;
    }

    public void calculateTotalCost() {
        this.totalCost = this.subTotalCost + getSaleTax();
    }

    public double getSaleTax() {
        return this.subTotalCost * SALES_TAX;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public ArrayList<String> stringifiedMenuItems() {
        ArrayList<String> toReturn = new ArrayList<>();
        for (MenuItem item : items) {
            toReturn.add(item.toString());
        }
        return toReturn;
    }

    @Override
    public String toString() {
        String orderString = "[ORDER NUMBER " + orderNumber + "]\n";
        for (MenuItem item : items) {
            orderString += (item.toString()) + "\n";
        }
        this.calculateSubTotalCost();
        this.calculateTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        orderString += "[TOTAL PRICE: " + decimalFormat.format(
                this.getTotalCost()) + "]\n";
        return orderString;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if (!(obj instanceof Order)) {
            return false;
        }
        Order temp = (Order) obj;
        return this.orderNumber == temp.orderNumber;
    }


}
