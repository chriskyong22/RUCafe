package sample;

import java.util.ArrayList;

public class StoreOrders implements Customizable {
    private ArrayList<Order> orders;

    public StoreOrders() {

    }

    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        return orders.add((Order) obj);
    }

    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        return orders.remove((Order) obj);
    }
}
