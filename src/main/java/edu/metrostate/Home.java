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

public class Home {

    @FXML
    private AnchorPane A;

    @FXML
    private Label logOutID;

    @FXML
    private Button bookApptID;

    @FXML
    private VBox appointmentsVBox;

    @FXML
    private Button cancelApptID;

    @FXML
    private TableView<Appointment> appointmentTableView;

    public void initialize() {
        //populateAppointmentsVBox();
    }
   /* private void populateAppointmentsVBox() {
        List<Appointment> appointments = DBHelper.fetchAppointmentsByClientId(1); // Example client ID
        appointmentsVBox.getChildren().clear(); // Clear previous entries

        for (Appointment appointment : appointments) {
            String details = String.format("Date: %s, Time: %s, Stylist: %s, Service: %s",
                    Appointment.getDatePicker(),
                    appointment.getTimePicker(),
                    Appointment.getStylistName(),
                    appointment.getServiceDesc());
            Label label = new Label(details);
            appointmentsVBox.getChildren().add(label);
        }
    }
*/


    public void goToAppointment() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("appointment.fxml"));
        Stage window = (Stage) bookApptID.getScene().getWindow();
        window.setScene((new Scene(root)));
    }


}
