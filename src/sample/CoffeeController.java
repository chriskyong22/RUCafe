package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.Model.Coffee;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Coffee controller to link the Coffee View to the Coffee Model.
 * It updates the sub-total upon adding/removing add-ins, changing the coffee
 * quantity or price, and upon adding to the shopping cart.
 * @author Christopher Yong, Maya Ravichandran
 */
public class CoffeeController implements Initializable {

    @FXML
    private ComboBox<String> coffeeSize;
    @FXML
    private ComboBox<String> coffeeQuantity;
    @FXML
    private CheckBox cream;
    @FXML
    private CheckBox syrup;
    @FXML
    private CheckBox milk;
    @FXML
    private CheckBox caramel;
    @FXML
    private CheckBox whippedCream;
    @FXML
    private TextField subTotal;

    private Coffee coffee;

    /**
     * Initializes the combo box and a coffee object to represent the
     * current coffee selected with its add-ins, flavors, and quantities.
     * @param url URL if provided
     * @param resourceBundle resource bundle if provided
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coffeeSize.getItems().addAll("Short",
                "Tall",
                "Grande",
                "Venti");
        coffeeSize.getSelectionModel().select(0);
        coffeeQuantity.getItems().addAll("1",
                "2",
                "3",
                "4",
                "5");
        coffeeQuantity.getSelectionModel().select(0);
        createNewCoffee();
    }

    /**
     * Performs the necessary coffee price calculations for the sub total and
     * displays the new sub total rounded to the nearest hundredths place.
     */
    public void updateSubTotal() {
        coffee.itemPrice();
        double price = coffee.getItemPrice();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(price));
    }

    /**
     * Retrieves and updates the size of the coffee wanted and updates
     * the sub-total.
     */
    public void updateSize() {
        coffee.setSize(coffeeSize.getSelectionModel().getSelectedItem());
        updateSubTotal();
    }

    /**
     * Retrieves and updates the quantity of coffee desired and updates
     * the sub-total.
     */
    public void updateQuantity() {
        coffee.setQuantity(Integer.parseInt(coffeeQuantity.
                getSelectionModel().getSelectedItem()));
        updateSubTotal();
    }

    /**
     * Checks if the Cream add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     */
    public void checkCream() {
        if (cream.isSelected()) {
            coffee.add("Cream");
        } else {
            coffee.remove("Cream");
        }
        updateSubTotal();
    }

    /**
     * Checks if the syrup add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     */
    public void checkSyrup() {
        if (syrup.isSelected()) {
            coffee.add("Syrup");
        } else {
            coffee.remove("Syrup");
        }
        updateSubTotal();
    }

    /**
     * Checks if the milk add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     */
    public void checkMilk() {
        if (milk.isSelected()) {
            coffee.add("Milk");
        } else {
            coffee.remove("Milk");
        }
        updateSubTotal();
    }

    /**
     * Checks if the caramel add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     */
    public void checkCaramel() {
        if (caramel.isSelected()) {
            coffee.add("Caramel");
        } else {
            coffee.remove("Caramel");
        }
        updateSubTotal();
    }

    /**
     * Checks if the whipped cream add-in should be added to the current
     * coffee object, does the corresponding remove/add operation,
     * and updates the sub-total.
     */
    public void checkWhippedCream() {
        if (whippedCream.isSelected()) {
            coffee.add("Whipped Cream");
        } else {
            coffee.remove("Whipped Cream");
        }
        updateSubTotal();
    }

    /**
     * Creates a new coffee object, sets the correct add-ins according to
     * what is checked in the view, and updates the sub total.
     */
    private void createNewCoffee() {
        coffee = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(),
                Integer.parseInt(coffeeQuantity.getSelectionModel().
                        getSelectedItem()));
        checkCream();
        checkCaramel();
        checkMilk();
        checkSyrup();
        checkWhippedCream();
    }

    /**
     * Adds the current coffee to the current order and generates
     * a new coffee if the user wishes to add another coffee.
     * Generates Alert confirming the coffee was added to the cart.
     */
    public void addToShoppingCart() {
        CurrentOrderController.getCurrentOrder().add(coffee);
        createNewCoffee();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RUCAFE: Confirmation");
        alert.setHeaderText("Added to Shopping Cart!");
        alert.setContentText("Successfully add to your shopping cart! " +
                "Please check your current orders or the shopping cart " +
                "icon to checkout your items!");
        alert.showAndWait();
    }
}
