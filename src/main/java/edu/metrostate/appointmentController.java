package edu.metrostate;

import edu.metrostate.Database.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import edu.metrostate.Service;
import edu.metrostate.Appointment;
import edu.metrostate.Database.DBHelper;

import static edu.metrostate.Database.DBHelper.connection;


//import static edu.metrostate.Database.DBHelper.connection;

public class appointmentController implements Initializable {

    public interface AppointmentListener{
        // call complete method
        void onnextComplete();
    }

    @FXML
    private ComboBox<String> Service;

    @FXML
    private ComboBox<String> Stylist;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button nextButton;

    @FXML
    private Button BackToHome;

    @FXML
    private ChoiceBox<String>  startFrom;

    @FXML
    private AppointmentListener appointmentListener;

    public void setNextListener(AppointmentListener appointmentListener){
        this.appointmentListener = appointmentListener;
    }

    String[] stime = {"9:00 am", "10:00 am","11:00 am", "12:00 pm", "1:00 pm" ,"2:00 pm" , "3:00 pm" ,"4:00 pm" , "2:00 pm" ,"3:00 pm" ,"4:00 pm"};


    public void populateServiceDescriptions() {
        List<String> services = DBHelper.fetchServiceDescriptions();
        Service.getItems().clear(); // Clear existing items
        Service.getItems().addAll(services);
    }

    public void populateStylist(){
        List<String> stylistname = DBHelper.fetchStylistNames();
        Stylist.getItems().clear(); // Clear existing items
        Stylist.getItems().addAll(stylistname);

    }





    @FXML
    void selectDate(ActionEvent event) {
        datePicker.getValue().toString();
    }

    @FXML
    void startTime(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populateServiceDescriptions();
        populateStylist();
        //Service.getItems().addAll(serviceName);
        startFrom.getItems().addAll(stime);
        nextButton.setOnAction(actionEvent -> {
            checkAppointment();
        });



    }
    //Handles if user misses a field
    private void checkAppointment() {
        StringBuilder missingFields = new StringBuilder();
        String service = Service.getSelectionModel().selectedItemProperty().getValue();
        String stylist = Stylist.getSelectionModel().selectedItemProperty().getValue();
        String startTime = startFrom.getSelectionModel().selectedItemProperty().getValue();
        LocalDate date = datePicker.getValue();
        if (service == null || service.isBlank()) {
            missingFields.append("Service, ");
        }
        if (stylist == null || stylist.isBlank()) {
            missingFields.append("Stylist, ");
        }
        if (date == null) {
            missingFields.append("Date, ");
        }
        if (startTime == null || startTime.isBlank()) {
            missingFields.append("Start Time, ");
        }

        if (missingFields.length() > 0) {


            missingFields.setLength(missingFields.length() - 2);
            Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
            alert.getDialogPane().setStyle("-fx-background-color:  #826603; -fx-font-size: 18px;");
            alert.setContentText(missingFields + " are required.");
            alert.showAndWait();
        } else {
            showConfirmAppointment();
        }
    }


    //shows confirmation
    private void showConfirmAppointment() {
        String service = Service.getSelectionModel().selectedItemProperty().getValue();
        String stylist = Stylist.getSelectionModel().selectedItemProperty().getValue();
        LocalDate date = datePicker.getValue();
        String startTime = startFrom.getSelectionModel().selectedItemProperty().getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "appointment", new ButtonType("Confirm"), ButtonType.CANCEL);
        alert.setTitle("Confirm Your Appointment");
        alert.setHeaderText("Please Confirm your Appointment");
        alert.getDialogPane().setStyle("-fx-background-color:  #826603; -fx-font-size: 18px;");
        alert.setContentText("\nService:  " + service + "\nStylist:  " + stylist + "\nDate of your Appointment:  " + date + "\nTime of your Appointment:  " + startTime );
        alert.showAndWait()
                .filter(button -> button == new ButtonType("Confirm"))
                .ifPresent(response -> saveAppointment());
    }

    private void saveAppointment() {
        if (appointmentListener != null) {
            LocalDate date = datePicker.getValue();
            LocalTime time = LocalTime.parse(startFrom.getSelectionModel().selectedItemProperty().getValue(), DateTimeFormatter.ofPattern("h:mm a"));
            String stylistName = Stylist.getSelectionModel().selectedItemProperty().getValue();
            String service = Service.getSelectionModel().selectedItemProperty().getValue();
            int clientId = 1; // Assume this is determined somehow

            Appointment apt = new Appointment(date, time, stylistName, service, clientId);
            apt.insert(connection); // Assuming you have access to a connection object or it's managed inside the method

            // Notify the listener that the appointment has been saved
            appointmentListener.onnextComplete();

            // Show confirmation message to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment saved successfully!");
            alert.showAndWait();
        }
    }


    public void goTobackToHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage) BackToHome.getScene().getWindow();
        window.setScene((new Scene(root)));
    }

    public boolean apptIsUnique(Connection connection, String stylistName, LocalDate appointmentDate, LocalTime appointmentTime) {
        String sql = "SELECT COUNT(*) FROM appointments WHERE stylist = ? AND appointment_date = ? AND appointment_time = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, stylistName);
            statement.setDate(2, java.sql.Date.valueOf(appointmentDate));
            statement.setTime(3, Time.valueOf(appointmentTime));

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // If count is 0, then the combination is unique
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false; // Return false if there was an error or if the combination is not unique
    }


}



