package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */

public class DonutController implements Initializable {
    @FXML
    ComboBox<String> donutTypes;
    @FXML
    ComboBox<String> donutFlavors;
    @FXML
    ListView<String> donutListView;
    @FXML
    TextField donutAmount;

    private ArrayList<Donut> storedDonuts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donutTypes.getItems().addAll("Yeast Donut",
                "Cake Donut",
                "Donut Holes");
        donutFlavors.getItems().addAll("Chocolate",
                "Glazed",
                "Vanilla");
        donutTypes.getSelectionModel().select(0);
        donutFlavors.getSelectionModel().select(0);
    }

    public void handleAdd() {
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        String donutFlavor = donutFlavors.getSelectionModel().getSelectedItem();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(donutAmount.getText());
            if (quantity > 0) {
                Donut donut = new Donut(donutType, donutFlavor, quantity);
                donutListView.getItems().add(donut.toString());
                storedDonuts.add(donut);
                updateSubTotal();
                return;
            }
        } catch (NumberFormatException | NullPointerException e) {

        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RUCAFE: Warning");
        alert.setHeaderText("Invalid Quantity");
        alert.setContentText("Please enter a non-negative and non-empty/zero quantity.");
        alert.showAndWait();
    }

    public void handleRemove() {
        //TO DO
        int selectedIndex = donutListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("RUCAFE: WARNING");
            alert.setHeaderText("INVALID SELECTION");
            alert.setContentText("Please select a valid item!");
            alert.showAndWait();
        } else {
            donutListView.getItems().remove(selectedIndex);
            storedDonuts.remove(selectedIndex);
            donutListView.getSelectionModel().select(-1);
            updateSubTotal();
        }
    }

    public void updateSubTotal() {
        double price = 0;
        for (Donut donut : storedDonuts) {
            donut.itemPrice();
            price += donut.getItemPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        donutAmount.setText(decimalFormat.format(price));
    }

    public void addToShoppingCart() {
        storedDonuts.forEach((item) -> MenuController.currentOrder.add(item));
    }

}
