package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TO ADD: DESCRIPTION
 * @author Christopher Yong, Maya Ravichandran
 */
public class Coffee extends MenuItem implements Customizable, Initializable {

    @FXML
    ComboBox<String> coffeeSize;
    @FXML
    ComboBox<String> coffeeAddin;
    @FXML
    ListView<String> coffeeListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coffeeSize.getItems().addAll("Short",
                "Tall",
                "Grande",
                "Venti");
        coffeeAddin.getItems().addAll("Cream",
                "Syrup",
                "Milk",
                "Caramel",
                "Whipped-Cream");
        coffeeSize.getSelectionModel().select(0);
        coffeeAddin.getSelectionModel().select(0);
    }

    @Override
    public boolean add(Object obj) {
        //TO DO
        return false;
    }

    @Override
    public boolean remove(Object obj) {

        //TO DO
        return false;
    }


}
