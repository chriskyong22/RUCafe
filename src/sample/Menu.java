package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class Menu {

    public void handleCoffeeOrdering() {
        createNewStage("Coffee.fxml", "RUCAFE: Coffee Menu");
    }

    public void handleDonutOrdering() {
        createNewStage("Donut.fxml", "RUCAFE: Donut Menu");
    }

    public void handleCheckCurrentOrder() {
        createNewStage("CurrentOrder.fxml", "RUCAFE: Current Order");
    }

    public void handleCheckOrders() {
        createNewStage("StoreOrders.fxml", "RUCAFE: All Orders");
    }

    private void createNewStage(String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 800, 600));
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
