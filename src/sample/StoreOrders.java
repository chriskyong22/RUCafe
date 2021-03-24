package sample;

import java.util.ArrayList;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class StoreOrders implements Customizable {
    private ArrayList<Order> orders;

    public StoreOrders() {
        //TO DO
    }

    @Override
    public boolean add(Object obj) {
        // TO DO STILL, not sure if correct
        if (!(obj instanceof Order)) {
            return false;
        }
        orders.add((Order) obj);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        // TO DO
        if (!(obj instanceof Order)) {
            return false;
        }
        return orders.remove((Order) obj);
    }
}
