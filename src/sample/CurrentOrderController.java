package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Model.Order;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class CurrentOrderController implements Initializable {
    @FXML
    ListView<String> currentOrderListView;
    @FXML
    Button removeItem;
    @FXML
    Button placeOrder;
    @FXML
    TextField salesTax;
    @FXML
    TextField totalPrice;
    @FXML
    TextField subTotal;

    public static Order currentOrder = new Order();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateItems();
        updateCosts();
        if(checkEmptyOrder()) {
            return;
        }
    }

    public boolean checkEmptyOrder() {
        if (currentOrder.getNumberOfMenuItems() == 0) {
            generateEmptyWarning();
            disableButtons();
            return true;
        }
        return false;
    }
    private void disableButtons() {
        placeOrder.setDisable(true);
        removeItem.setDisable(true);
    }

    private void generateEmptyWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RUCAFE: WARNING");
        alert.setHeaderText("No Stored Items in the cart!");
        alert.setContentText("There are no current items in the cart!" +
                " Please navigate back to the menu and select some items" +
                " to checkout!");
        alert.showAndWait();
    }

    public void updateItems() {
        currentOrderListView.getItems().addAll(currentOrder.stringifiedMenuItems());
    }

    public void updateCosts() {
        currentOrder.calculateSubTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(currentOrder.getSubTotalCost()));
        salesTax.setText(decimalFormat.format(currentOrder.getSaleTax()));
        currentOrder.calculateTotalCost();
        totalPrice.setText(decimalFormat.format(currentOrder.getTotalCost()));
    }

    public void handleRemoveItem() {
        int selectedIndex = currentOrderListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("RUCAFE: WARNING");
            alert.setHeaderText("INVALID SELECTION");
            alert.setContentText("Please select a valid item from the list!");
            alert.showAndWait();
        } else {
            currentOrderListView.getItems().remove(selectedIndex);
            currentOrder.remove(currentOrder.getItem(selectedIndex));
            currentOrderListView.getSelectionModel().select(-1);
            updateCosts();
            if (checkEmptyOrder()) {
                return;
            }
        }
    }

    public void addToStoredOrders() {
        if (checkEmptyOrder()) {
            return;
        }
        StoreOrdersController.orders.add(currentOrder);
        currentOrderListView.getItems().clear();
        currentOrder = new Order();
        updateCosts();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RUCAFE: Confirmation");
        alert.setHeaderText("Added to Stored Orders!");
        alert.setContentText("Successfully added your current order! " +
                "To view your previous orders, please click on the" +
                " clipboard icon in the main menu");
        alert.showAndWait();
        disableButtons();
    }

}
