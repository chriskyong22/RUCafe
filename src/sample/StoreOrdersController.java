package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class StoreOrdersController implements Initializable {

    @FXML
    ComboBox<String> orderNumber;
    @FXML
    ListView<String> orderListView;
    @FXML
    TextField orderTotalPrice;
    @FXML
    Button deleteOrder;
    @FXML
    Button exportOrder;

    public static StoreOrders orders = new StoreOrders();

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

    public boolean checkEmptyStoredOrders() {
        if (orders.getOrderNumbers() == null) {
            disableFields();
            generateEmptyWarning();
        }
        return orders.getOrderNumbers() == null;
    }

    private void disableFields() {
        orderNumber.setDisable(true);
        deleteOrder.setDisable(true);
        exportOrder.setDisable(true);
    }

    private void updateOrderDetails(Order order) {
        orderListView.getItems().addAll(order.stringified());
        order.calculateSubTotalCost();
        order.calculateTotalCost();
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        orderTotalPrice.setText(decimalFormat.format(order.getTotalCost()));
    }

    public void handleSelectedOrder() {
        if (checkEmptyStoredOrders()) {
            return;
        }
        clearOrderDetails();
        updateOrderDetails(orders.findOrder(Integer.parseInt(
                orderNumber.getSelectionModel().getSelectedItem())));
    }

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

    public void clearOrderDetails() {
        orderListView.getItems().clear();
        orderTotalPrice.clear();
    }

    public void handleExportOrder() {
        if (checkEmptyStoredOrders()) {
            return;
        }
    }

    public void generateEmptyWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RUCAFE: WARNING");
        alert.setHeaderText("No Stored Orders!");
        alert.setContentText("There are no current orders placed! Please" +
                " navigate back to the menu and place some orders!");
        alert.showAndWait();
    }
}
