package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import helper.HibernateController;
public class APIConsumer implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private TextField usuarioTextField;
    @FXML
    private TextField senhaTextField;

    @FXML
    public void loginIntoSystem(ActionEvent event) {
        String user = usuarioTextField.getText();
        String password = senhaTextField.getText();

        if(!HibernateController.login(user, password)) {
            new Alert(AlertType.ERROR).show();
        };
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(this::loginIntoSystem);
    }
}
