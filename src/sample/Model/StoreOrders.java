package sample.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */

public class StoreOrders implements Customizable {
    private ArrayList<Order> orders;

    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    public ArrayList<String> getOrderNumbers() {
        if (orders.size() == 0) {
            return null;
        }
        ArrayList<String> orderNumbers = new ArrayList<>();
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

    public void exportDatabase(File file) throws Exception {
        try (BufferedWriter exportFile = new BufferedWriter(
                new FileWriter(file.getAbsolutePath()))) {
            for (Order order : orders) {
                exportFile.write(order.toString());
            }
        }
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
        return orders.remove(obj);
    }
}
