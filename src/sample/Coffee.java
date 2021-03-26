package sample;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */

public class Coffee extends MenuItem implements Customizable {
    private ArrayList<String> addins;
    private String size;
    private int quantity;

    public static final double BASE_PRICE = 1.99;
    public static final double ADDIN_PRICE = 0.20;
    public static final double TALL_PRICE = 0.50;
    public static final double GRANDE_PRICE = 1.00;
    public static final double VENTI_PRICE = 1.50;

    public Coffee(String size, int quantity) {
        this.addins = new ArrayList<String>();
        this.size = size;
        this.quantity = quantity;
    }

    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return addins.add((String) obj);
    }

    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return addins.remove((String) obj);
    }

    @Override
    public void itemPrice() {
        switch(size) {
            case "Short":
                super.itemPrice = BASE_PRICE;
                break;
            case "Tall":
                super.itemPrice = BASE_PRICE + TALL_PRICE;
                break;
            case "Grande":
                super.itemPrice = BASE_PRICE + GRANDE_PRICE;
                break;
            case "Venti":
                super.itemPrice = BASE_PRICE + VENTI_PRICE;
                break;
        }
        super.itemPrice += (addins.size() * ADDIN_PRICE);
        super.itemPrice *= quantity;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        String toReturn = size + "(" + quantity + ") [";
        StringJoiner join = new StringJoiner(",");
        addins.forEach((item) -> join.add(item.toString()));
        return toReturn + join + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee)) return false;
        Coffee coffee = (Coffee) o;
        return this.quantity == coffee.quantity &&
                this.addins.equals(coffee.addins) &&
                this.size.equals(coffee.size);
    }

}
