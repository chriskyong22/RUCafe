package sample.Model;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */

public class Donut extends MenuItem {
    private String type;
    private String flavor;
    private int quantity;

    public static final double YEAST_DONUT_PRICE = 1.39;
    public static final double CAKE_DONUT_PRICE = 1.59;
    public static final double DONUT_HOLES_PRICE = 0.33;

    public Donut(String type, String flavor, int quantity) {
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    @Override
    public void itemPrice() {
        switch(type) {
            case "Yeast Donut":
                super.itemPrice = YEAST_DONUT_PRICE * quantity;
                break;
            case "Cake Donut":
                super.itemPrice = CAKE_DONUT_PRICE * quantity;
                break;
            case "Donut Holes":
                super.itemPrice = DONUT_HOLES_PRICE * quantity;
                break;
            default:
                super.itemPrice = 0;
        }
    }

    @Override
    public String toString() {
        return type + " " + flavor + "(" + quantity + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donut)) return false;
        Donut donut = (Donut) o;
        return this.quantity == donut.quantity &&
                this.type.equals(donut.type) &&
                this.flavor.equals(donut.flavor);
    }

}
