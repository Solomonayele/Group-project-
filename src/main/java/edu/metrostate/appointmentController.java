package edu.metrostate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

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
    private ChoiceBox<String>  FinishBy;
    private AppointmentListener appointmentListener;

    public void setNextListener(AppointmentListener appointmentListener){
        this.appointmentListener = appointmentListener;
    }


    String[] categoryName = {"Hair","Makeup","massage"};
    String[] serviceName ={"hair cut", "Hair braid", "makeup"};
    String[] stylistName= {"Neimo","Myriam", "Solmon","Julia", "Logan"};
    String[] stime = {"9:00 am", "10:00 am","11:00 am", "12:00 pm", "1:00 pm" ,"2:00 pm" , "3:00 pm" ,"4:00 pm" , "2:00 pm" ,"3:00 pm" ,"4:00 pm"};







    @FXML
    void selectDate(ActionEvent event) {
        datePicker.getValue().toString();
    }

    @FXML
    void startTime(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stylist.getItems().addAll(stylistName);
        Service.getItems().addAll(serviceName);
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
            //save appointment
        }
    }

    public void goTobackToHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage) BackToHome.getScene().getWindow();
        window.setScene((new Scene(root)));
    }



}
