package edu.metrostate;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private Button newClientButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.signInUser(actionEvent, emailTextField.getText(), passwordTextField.getText());

            }
        });

        newClientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "register.fxml"); //insert tabular main page for fxml file
            }
        });
    }

}
