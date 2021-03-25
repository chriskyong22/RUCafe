package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class MenuController implements Initializable {
    private static ObservableList<Order> orders;
    public static Order currentOrder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentOrder = new Order();
    }

    public void handleCoffeeOrdering() {
        createNewStage("Coffee.fxml", "RUCAFE: Coffee Menu", 450, 400);
    }

    public void handleDonutOrdering() {
        createNewStage("Donut.fxml", "RUCAFE: Donut Menu", 450, 400);
    }

    public void handleCheckCurrentOrder() {
        createNewStage("CurrentOrder.fxml", "RUCAFE: Current Order", 800, 600);
    }

    public void handleCheckOrders() {
        createNewStage("StoreOrders.fxml", "RUCAFE: All Orders", 800, 600);
    }

    private void createNewStage(String path, String title, int width, int height) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            stage.show();
        } catch (IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("RUCAFE: ERROR");
            alert.setHeaderText("An exception has occurred!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}
