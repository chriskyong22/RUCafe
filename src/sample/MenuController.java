package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the main menu for the RUCAFE. It loads the other views
 * upon user selection.
 * @author Christopher Yong, Maya Ravichandran
 */
public class MenuController {

    /**
     * Handles the opening of the coffee menu.
     */
    public void handleCoffeeOrdering() {
        createNewStage("Coffee.fxml", "RUCAFE: Coffee Menu",
                375, 400);
    }

    /**
     * Handles the opening of the donut menu.
     */
    public void handleDonutOrdering() {
        createNewStage("Donut.fxml", "RUCAFE: Donut Menu",
                450, 400);
    }

    /**
     * Handles the opening of the current order menu.
     */
    public void handleCheckCurrentOrder() {
        createNewStage("CurrentOrder.fxml", "RUCAFE: Current Order",
                425, 400);
    }

    /**
     * Handles the opening of the stored orders menu.
     */
    public void handleCheckOrders() {
        createNewStage("StoreOrders.fxml", "RUCAFE: All Orders",
                375, 350);
    }

    /**
     * Helper method to call the necessary FXML methods to load the desired
     * view.
     * @param path path to the view to load
     * @param title title of the window
     * @param width width of the window
     * @param height height of the window
     */
    private void createNewStage(String path, String title, int width, int height) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            stage.initModality(Modality.APPLICATION_MODAL);
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
