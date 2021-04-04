package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Model.Donut;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Donut controller to link the Donut View to the Donut model.
 * It updates the sub-total and donut list view upon adding/removing donuts,
 * and it allows the user to add the list's donuts to the current order.
 * @author Christopher Yong, Maya Ravichandran
 */
public class DonutController implements Initializable {
    @FXML
    private ComboBox<String> donutTypes;
    @FXML
    private ComboBox<String> donutFlavors;
    @FXML
    private ListView<String> donutListView;
    @FXML
    private TextField donutAmount;
    @FXML
    private TextField donutSubtotalPrice;

    private ArrayList<Donut> storedDonuts = new ArrayList<>();

    /**
     * Initializes the donut type and donut flavor combobox.
     * @param url URL if provided
     * @param resourceBundle resource bundle if provided
     */
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

    /**
     * Retrieves the selected donut type, flavor, and quantity from the view,
     * creates the donut object to be stored, and updates the donut list
     * view and sub-total price.
     * Also performs the necessary validation for the quantity and will
     * display an alert message if the quantity is invalid.
     */
    public void handleAdd() {
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        String donutFlavor = donutFlavors.getSelectionModel()
                .getSelectedItem();
        try {
            int quantity = Integer.parseInt(donutAmount.getText());
            if (quantity > 0) {
                Donut donut = new Donut(donutType, donutFlavor, quantity);
                donutListView.getItems().add(donut.toString());
                storedDonuts.add(donut);
                updateSubTotal();
                return;
            }
        } catch (NumberFormatException | NullPointerException e) {
            // Catch the exception when it occurs and go straight to the
            // Alert message afterwards below
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RUCAFE: WARNING");
        alert.setHeaderText("INVALID QUANTITY");
        alert.setContentText("Please enter a non-negative and" +
                " non-empty/zero quantity that is below or " +
                "equal to " + Integer.MAX_VALUE + ".");
        alert.showAndWait();
    }

    /**
     * Retrieves the selected donut from the donut list view to remove, and
     * updates the list view and sub-total upon deletion of the donut.
     * If no donut was selected, it will display an alert telling the user to
     * select an item from the list view.
     */
    public void handleRemove() {
        int selectedIndex = donutListView.getSelectionModel()
                .getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("RUCAFE: WARNING");
            alert.setHeaderText("INVALID SELECTION");
            alert.setContentText("Please select a valid item from the list!");
            alert.showAndWait();
        } else {
            donutListView.getItems().remove(selectedIndex);
            storedDonuts.remove(selectedIndex);
            donutListView.getSelectionModel().select(-1);
            updateSubTotal();
        }
    }


    /**
     * Performs the necessary donut(s) price calculations for the sub-total
     * and displays the new sub-total rounded to the nearest hundredths
     * place.
     */
    public void updateSubTotal() {
        double price = 0;
        for (Donut donut : storedDonuts) {
            donut.itemPrice();
            price += donut.getItemPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("'$'#,##0.00");
        donutSubtotalPrice.setText(decimalFormat.format(price));
    }

    /**
     * Adds list of donuts that are displayed in the list view into the
     * current order object.
     * Upon success, it will generate an alert telling the user all the
     * donuts were successfully added to the current order or the cart.
     * If no donuts were added (meaning the donut list view is empty), it
     * will generate a warning telling the user to add donuts before adding
     * to the cart.
     */
    public void addToShoppingCart() {
        if (storedDonuts.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("RUCAFE: Empty List");
            alert.setHeaderText("EMPTY LIST");
            alert.setContentText("Please add some items before adding to " +
                    "the shopping cart");
            alert.showAndWait();
            return;
        }
        storedDonuts.forEach((item) -> CurrentOrderController.
                getCurrentOrder().add(item));
        storedDonuts.clear();
        donutListView.getItems().clear();
        donutListView.getSelectionModel().select(-1);
        updateSubTotal();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RUCAFE: Confirmation");
        alert.setHeaderText("Added to Shopping Cart!");
        alert.setContentText("Successfully added to your shopping cart! " +
                "Please check your current orders or the shopping cart " +
                "icon to checkout your items!");
        alert.showAndWait();
    }

}
