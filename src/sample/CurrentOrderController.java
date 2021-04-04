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
 * CurrentOrder controller links the CurrentOrder View to the CurrentOrder
 * Model. It updates the sub total, sales tax, and total price upon
 * adding/removing menu items. In addition, you can add the current orders to
 * a stored order list.
 * @author Christopher Yong, Maya Ravichandran
 */
public class CurrentOrderController implements Initializable {
    @FXML
    private ListView<String> currentOrderListView;
    @FXML
    private Button removeItem;
    @FXML
    private Button placeOrder;
    @FXML
    private TextField salesTax;
    @FXML
    private TextField totalPrice;
    @FXML
    private TextField subTotal;

    private static Order currentOrder = new Order();

    /**
     * Initializes the sales, total price, and sub total and menu items in
     * the menu items list view.
     * @param url URL if provided
     * @param resourceBundle resource bundle if provided
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateItems();
        updateCosts();
        checkEmptyOrder();
    }

    /**
     * Getter for the Current Order object to add the menu items.
     * @return Order object which represents the current order
     */
    public static Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Checks if the current order has no menu items in it.
     * If so, it will generate a popup warning to notify the user that they
     * need to add some menu items.
     * It will also disable the Place Order and Remove Item buttons.
     * @return true if empty, otherwise false
     */
    public boolean checkEmptyOrder() {
        if (currentOrder.getNumberOfMenuItems() == 0) {
            generateEmptyWarning();
            disableButtons();
            return true;
        }
        return false;
    }

    /**
     * Disables the place order and remove item buttons.
     */
    private void disableButtons() {
        placeOrder.setDisable(true);
        removeItem.setDisable(true);
    }

    /**
     * Generates the alert message for an empty cart (current order has no
     * menu items).
     */
    private void generateEmptyWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RUCAFE: WARNING");
        alert.setHeaderText("No Stored Items in the cart!");
        alert.setContentText("There are no current items in the cart!" +
                " Please navigate back to the menu and select some items" +
                " to checkout!");
        alert.showAndWait();
    }

    /**
     * Updates the menu items list view with all the menu items currently
     * added to the cart.
     */
    public void updateItems() {
        currentOrderListView.getItems()
                .addAll(currentOrder.stringifiedMenuItems());
    }

    /**
     * Performs the necessary menu item calculations for each menu item and
     * updates the sub total, sales tax, and total price of all menu items
     * rounded to the nearest hundredths place.
     */
    public void updateCosts() {
        currentOrder.calculateSubTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        subTotal.setText(decimalFormat.format(currentOrder
                .getSubTotalCost()));
        salesTax.setText(decimalFormat.format(currentOrder.getSalesTax()));
        currentOrder.calculateTotalCost();
        totalPrice.setText(decimalFormat.format(currentOrder.getTotalCost()));
    }

    /**
     * Handles the removal of a selected item in the menu item listview.
     * Updates the current order object and the sub total, sales tax, and
     * total price and the menu item listview.
     * If no item was selected, it will display a warning alert that tells
     * the user to select an item from the menu item list view.
     * If the user removes the last item in the list view, it will display
     * a warning and disable the buttons.
     */
    public void handleRemoveItem() {
        int selectedIndex = currentOrderListView.getSelectionModel()
                .getSelectedIndex();
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
            checkEmptyOrder();
        }
    }

    /**
     * Handles the adding to the stored orders.
     * If there are no items currently in the current order, it will not
     * add it to the stored orders and just display a warning instead and
     * disables the buttons.
     * Upon addition of the current order to the stored orders, it will
     * generate a new order object and update the sub total, sales, and total
     * price and the list view and disable all the buttons and display a
     * alert indicating it has successfully added the current order to the
     * stored orders.
     */
    public void addToStoredOrders() {
        if (checkEmptyOrder()) {
            return;
        }
        StoreOrdersController.getOrders().add(currentOrder);
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
