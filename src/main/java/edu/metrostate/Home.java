package edu.metrostate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

    @FXML
    private AnchorPane A;

    @FXML
    private Label logOutID;

    @FXML
    private Button bookApptID;

    @FXML
    private Button cancelApptID;

    public void goToAppointment() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("appointment.fxml"));
        Stage window = (Stage) bookApptID.getScene().getWindow();
        window.setScene((new Scene(root)));
    }


}
