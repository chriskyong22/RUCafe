package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import sample.Model.Order;
import sample.Model.StoreOrders;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * StoreOrders controller to link the StoreOrders View to the StoreOrders
 * Model. It updates the list view and total price upon selection and removal
 * of an order. In addition, you can export ALL stored orders to a text file.
 * @author Christopher Yong, Maya Ravichandran
 */
public class StoreOrdersController implements Initializable {

    @FXML
    private ComboBox<String> orderNumber;
    @FXML
    private ListView<String> orderListView;
    @FXML
    private TextField orderTotalPrice;
    @FXML
    private Button deleteOrder;
    @FXML
    private Button exportOrder;

    private static StoreOrders orders = new StoreOrders();

    /**
     * Initializes the order combobox to show all the added orders that
     * have not been removed.
     * @param url url if provided.
     * @param resourceBundle resourceBundle if provided.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (checkEmptyStoredOrders()) {
            return;
        }
        for (String order : orders.getOrderNumbers()) {
            orderNumber.getItems().add(order);
        }
        orderNumber.getSelectionModel().select(0);
        handleSelectedOrder();
    }

    /**
     * Getter fro the store orders object to add new orders.
     * @return StoreOrders object that represents all the orders that are
     *         currently stored that have not been removed.
     */
    public static StoreOrders getOrders() {
        return orders;
    }

    /**
     * Handles the checking if the stored orders has any orders.
     * If no orders, generate a warning and disable the buttons.
     * @return true if empty, otherwise false.
     */
    public boolean checkEmptyStoredOrders() {
        if (orders.getOrderNumbers() == null) {
            disableFields();
            generateEmptyWarning();
        }
        return orders.getOrderNumbers() == null;
    }

    /**
     * Disables the combobox to select orders, the delete order button, and
     * the export order button.
     */
    private void disableFields() {
        orderNumber.setDisable(true);
        deleteOrder.setDisable(true);
        exportOrder.setDisable(true);
    }

    /**
     * Updates the list view with the menu items stored in the order, the
     * total price displayed.
     * @param order the order to display in the list view and associated total
     *              price.
     */
    private void updateOrderDetails(Order order) {
        orderListView.getItems().addAll(order.stringifiedMenuItems());
        order.calculateSubTotalCost();
        order.calculateTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        orderTotalPrice.setText(decimalFormat.format(order.getTotalCost()));
    }

    /**
     * Handles the selection of an order in the order combobox.
     * Upon selection, it will update the order displayed in the list view
     * and associated total price.
     * If there are no orders, it will generate an Alert warning the user that
     * there are no orders stored and disable all buttons.
     */
    public void handleSelectedOrder() {
        if (checkEmptyStoredOrders()) {
            return;
        }
        clearOrderDetails();
        updateOrderDetails(orders.findOrder(Integer.parseInt(
                orderNumber.getSelectionModel().getSelectedItem())));
    }

    /**
     * Handles the deletion of the current selected order in the combobox.
     * Upon deletion, it will display the order before it and if there are no
     * orders before it, it will display the next order. In addition, it will
     * update the associated total price and the menu items of the
     * order in the list view.
     * If there are no orders after removing the current selected order, it
     * will disable all buttons and display an alert to the user informing
     * there are no more orders to display.
     */
    public void handleDeleteOrder() {
        if (checkEmptyStoredOrders()) {
            return;
        }
        orders.remove(orders.findOrder(Integer.parseInt(
                orderNumber.getSelectionModel().getSelectedItem())));
        int selectedIndex = orderNumber.getSelectionModel().getSelectedIndex();
        orderNumber.getItems().remove(selectedIndex);
        if (selectedIndex > 0) {
            orderNumber.getSelectionModel().select(selectedIndex - 1);
        } else {
            orderNumber.getSelectionModel().select(0);
        }
        clearOrderDetails();
        if (checkEmptyStoredOrders()) {
            return;
        }
        handleSelectedOrder();
    }

    /**
     * Clears the displayed order list view and the total price.
     */
    public void clearOrderDetails() {
        orderListView.getItems().clear();
        orderTotalPrice.clear();
    }

    /**
     * Handles the exporting of ALL non-removed orders. (The orders displayed
     * in the combobox)
     * Upon export, it will generate an alert informing the user if it
     * succeeded or not. If it did not succeed, it will contain the reasoning
     * in the content section.
     * In addition, if orders are stored then it will not export but display
     * the appropriate alert and disable all the buttons.
     */
    public void handleExportOrder() {
        if (checkEmptyStoredOrders()) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("RUCAFE: ERROR");
        alert.setHeaderText("Export Failed!");
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter =
                new FileChooser.ExtensionFilter("text files (*.txt)",
                        "*.txt");
        fc.getExtensionFilters().add(filter);
        File selectedFile = fc.showSaveDialog(orderNumber.getScene().getWindow());
        if (selectedFile == null || !selectedFile.canWrite()) {
            alert.setContentText("The selected File did not exist/or could" +
                    " not write to for exporting!");
            alert.showAndWait();
            return;
        }
        final int NOT_FOUND = -1;
        if (selectedFile.getName().lastIndexOf('.') == NOT_FOUND ||
                !selectedFile.getName().substring(selectedFile.getName().
                        lastIndexOf('.') + 1).equals("txt")) {
            alert.setContentText("File is not a textfile! Can only export " +
                    "to textfiles!\n");
            alert.showAndWait();
            return;
        }
        try {
            orders.exportDatabase(selectedFile);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("RUCAFE: Export");
            alert.setHeaderText("Successful Export");
            alert.setContentText("All stored orders have been successfully" +
                    " exported to " + selectedFile.getName() + "!");
            alert.showAndWait();
        } catch (Exception e) {
            alert.setContentText("Error occurred when writing to " +
                    selectedFile.getName() + "!");
            alert.showAndWait();
        }
    }

    /**
     * Generates an Alert that indicates the user should place some orders
     * because there are no more stored orders.
     */
    private void generateEmptyWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RUCAFE: WARNING");
        alert.setHeaderText("No Stored Orders!");
        alert.setContentText("There are no orders placed! Please" +
                " navigate back to the menu and place some orders!");
        alert.showAndWait();
    }
}
