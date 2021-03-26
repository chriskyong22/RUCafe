package sample.Model;

import java.util.ArrayList;

public class StoreOrders implements Customizable {
    private ArrayList<Order> orders;

    public StoreOrders() {
        this.orders = new ArrayList<Order>();
    }

    public ArrayList<String> getOrderNumbers() {
        if (orders.size() == 0) {
            return null;
        }
        ArrayList<String> orderNumbers = new ArrayList<String>();
        for(Order order : orders) {
            orderNumbers.add(order.getOrderNumber() + "");
        }
        return orderNumbers;
    }

    public Order findOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
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
