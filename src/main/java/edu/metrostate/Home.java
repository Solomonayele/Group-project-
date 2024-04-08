package edu.metrostate;

import edu.metrostate.Database.DBHelper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static edu.metrostate.Database.DBHelper.fetchAppointmentsByClientId;

public class Home {

    @FXML
    private AnchorPane A;

    @FXML
    private Button logOutID;

    @FXML
    private Button bookApptID;

    @FXML
    private VBox appointmentsVBox;

    @FXML
    private Button cancelApptID;



    @FXML
    private TableView<Appointment> appointmentTableView;

    public void initialize() {
        //displayAppointments(1);
    }

    /*
    public void displayAppointments(int clientId) {
        List<Appointment> appointments = fetchAppointmentsByClientId(clientId);
        appointmentsVBox.getChildren().clear(); // Clearing previous appointment details

        for (Appointment appt : appointments) {
            // Using the correct getters according to your Appointment class
            Label dateLabel = new Label(appt.getDatePicker().toString());
            Label timeLabel = new Label(appt.getTimePicker().toString());
           // Label stylistLabel = new Label(appt.getStylist().toString());
            //Label serviceLabel = new Label(appt.getService().toString());

            VBox appointmentDetails = new VBox(5); // VBox for grouping each appointment's details
            appointmentDetails.getChildren().addAll(dateLabel, timeLabel);

            appointmentsVBox.getChildren().add(appointmentDetails); // Adding the grouped details to the main VBox
        }
    }*/



    public void goToAppointment() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("appointment.fxml"));
        Stage window = (Stage) bookApptID.getScene().getWindow();
        window.setScene((new Scene(root)));
    }

    public void Logout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        Stage window = (Stage) bookApptID.getScene().getWindow();
        window.setScene((new Scene(root)));
    }



}
