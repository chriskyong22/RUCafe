package sample;

import java.util.ArrayList;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class Order implements Customizable {
    private static int generatingOrderNumbers = 0;
    private int orderNumber;
    private ArrayList<MenuItem> items;

    public Order() {
        this.orderNumber = generatingOrderNumbers++;
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
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
        return items.remove((MenuItem) obj);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        Order temp = (Order) obj;
        return this.orderNumber == temp.orderNumber;
    }
}
