package edu.metrostate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
    @FXML
    private Button submitButton;
    @FXML
    private Button returnToLoginButton;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField setPasswordField;
    @FXML
    private TextField confirmPasswordField;

    @FXML
    private DatePicker dateOfBirthPicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!firstNameTextField.getText().trim().isEmpty() && !lastNameTextField.getText().trim().isEmpty() && !phoneTextField.getText().trim().isEmpty() && !emailTextField.getText().trim().isEmpty() && !setPasswordField.getText().trim().isEmpty()){
                    try {
                        DBUtils.registerUser(actionEvent, firstNameTextField.getText(), lastNameTextField.getText(), dateOfBirthPicker,  emailTextField.getText(), phoneTextField.getText(),  setPasswordField.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Please fill out all fields.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill out all the fields to register.");
                    alert.show();
                }

            }
        });

        returnToLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "login.fxml"); //insert tabular main page for fxml file
            }
        });
    }

   // public
}
