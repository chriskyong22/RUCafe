package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class CoffeeController implements Initializable {

    @FXML
    ComboBox<String> coffeeSize;
    @FXML
    ComboBox<String> coffeeQuantity;
    @FXML
    CheckBox cream;
    @FXML
    CheckBox syrup;
    @FXML
    CheckBox milk;
    @FXML
    CheckBox caramel;
    @FXML
    CheckBox whippedCream;
    @FXML
    TextField subTotal;

    private Coffee coffee;
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

    public void updateSubTotal() {
        coffee.itemPrice();
        double price = coffee.getItemPrice();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(price));
    }

    public void updateSize() {
        coffee.setSize(coffeeSize.getSelectionModel().getSelectedItem());
        updateSubTotal();
    }

    public void updateQuantity() {
        coffee.setQuantity(Integer.parseInt(coffeeQuantity.
                getSelectionModel().getSelectedItem()));
        updateSubTotal();
    }

    public void checkCream() {
        if (cream.isSelected()) {
            coffee.add("Cream");
        } else {
            coffee.remove("Cream");
        }
        updateSubTotal();
    }

    public void checkSyrup() {
        if (syrup.isSelected()) {
            coffee.add("Syrup");
        } else {
            coffee.remove("Syrup");
        }
        updateSubTotal();
    }

    public void checkMilk() {
        if (milk.isSelected()) {
            coffee.add("Milk");
        } else {
            coffee.remove("Milk");
        }
        updateSubTotal();
    }

    public void checkCaramel() {
        if (caramel.isSelected()) {
            coffee.add("Caramel");
        } else {
            coffee.remove("Caramel");
        }
        updateSubTotal();
    }

    public void checkWhippedCream() {
        if (whippedCream.isSelected()) {
            coffee.add("Whipped Cream");
        } else {
            coffee.remove("Whipped Cream");
        }
        updateSubTotal();
    }

    private void createNewCoffee() {
        coffee = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(),
                Integer.parseInt(coffeeQuantity.getSelectionModel().
                        getSelectedItem()));
        checkCream();
        checkCaramel();
        checkMilk();
        checkSyrup();
        checkWhippedCream();
        updateSubTotal();
    }

    public void addToShoppingCart() {
        CurrentOrderController.currentOrder.add(coffee);
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
