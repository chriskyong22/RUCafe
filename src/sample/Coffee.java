package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

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
    CheckBox cream;
    @FXML
    CheckBox syrup;
    @FXML
    CheckBox milk;
    @FXML
    CheckBox caramel;
    @FXML
    CheckBox whippedCream;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coffeeSize.getItems().addAll("Short",
                "Tall",
                "Grande",
                "Venti");
        coffeeSize.getSelectionModel().select(0);
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
