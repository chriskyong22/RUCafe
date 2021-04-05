package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The driver method to start the menu of the RUCAFE program.
 * @author Christopher Yong, Maya Ravichandran
 */
public class Main extends Application {
    /**
     * Starts the javafx with the Menu.FXML as the first view.
     * @param primaryStage the window
     * @throws Exception exception that is thrown if Menu.fxml could not be
     *                   found
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(
                "View/Menu.fxml"));
        primaryStage.setTitle("RUCafe Menu");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * THe main function that starts the FXML program.
     * @param args the cmdline arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
