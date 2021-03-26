package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    }

    public void updateItems() {
        currentOrderListView.getItems().addAll(currentOrder.stringified());
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

    }

    public void addToStoredOrders() {
        StoreOrdersController.orders.add(currentOrder);
        currentOrderListView.getItems().clear();
        currentOrder = new Order();
        updateCosts();
    }

}
