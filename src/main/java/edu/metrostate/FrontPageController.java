package edu.metrostate;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class FrontPageController {

    @FXML
    private Button signInButton;

    public void handleSignInButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) signInButton.getScene().getWindow();
        window.setScene((new Scene(root)));
    }
}
