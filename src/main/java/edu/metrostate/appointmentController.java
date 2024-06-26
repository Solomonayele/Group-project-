package edu.metrostate;

import edu.metrostate.Database.DBHelper;
import edu.metrostate.Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;




public class appointmentController implements Initializable {

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
    private ComboBox<LocalTime>  startFrom;

    String[] stime = {"8:00 am","9:00 am", "10:00 am","11:00 am", "12:00 pm", "1:00 pm" ,"2:00 pm" , "3:00 pm" ,"4:00 pm" , "2:00 pm" ,"3:00 pm" ,"4:00 pm","5:00 pm","6:00 pm","7:00 pm","8:00 pm"};

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


    public void goTobackToHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage) BackToHome.getScene().getWindow();
        window.setScene((new Scene(root)));
    }

    public void populateTimeSlots(LocalDate date, String stylistName) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        Connection connection = null;
        connection = DriverManager.getConnection(Database.connectionString);


        Connection finalConnection = connection;
        Set<LocalTime> availableTimes = Arrays.stream(stime)
                .map(timeStr -> LocalTime.parse(timeStr.toUpperCase(), formatter))
                .filter(time -> apptIsUnique(stylistName, date, time, finalConnection))
                .collect(Collectors.toCollection(TreeSet::new));

        startFrom.getItems().clear();
        startFrom.getItems().addAll(availableTimes);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize  (URL url, ResourceBundle resourceBundle) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        datePicker.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            String selectedStylist = Stylist.getSelectionModel().getSelectedItem();
            if (selectedDate != null && selectedStylist != null) {
                try {
                    populateTimeSlots(selectedDate, selectedStylist);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Stylist.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            String selectedStylist = Stylist.getSelectionModel().getSelectedItem();
            if (selectedDate != null && selectedStylist != null) {
                try {
                    populateTimeSlots(selectedDate, selectedStylist);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        populateServiceDescriptions();
        populateStylist();

        nextButton.setOnAction(actionEvent -> {
            try {
                checkAppointment();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    //Handles if user misses a field
    private void checkAppointment() throws SQLException, IOException {
        StringBuilder missingFields = new StringBuilder();
        String service = Service.getSelectionModel().selectedItemProperty().getValue();
        String stylist = Stylist.getSelectionModel().selectedItemProperty().getValue();
        LocalTime startTime = startFrom.getSelectionModel().getSelectedItem();


        //LocalTime startTime = String.valueOf(startFrom.getSelectionModel().selectedItemProperty().getValue());
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
        //LocalTime startTime = startFrom.getSelectionModel().getSelectedItem();

        if (startTime == null ) {
            missingFields.append("Time, ");
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
    private void showConfirmAppointment() throws SQLException, IOException {
        String service = Service.getSelectionModel().selectedItemProperty().getValue();
        String stylist = Stylist.getSelectionModel().selectedItemProperty().getValue();
        LocalDate date = datePicker.getValue();
        LocalTime startTime = startFrom.getSelectionModel().selectedItemProperty().getValue();

        //Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "appointment", new ButtonType("Confirm"), ButtonType.CANCEL);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Appointment");
        alert.setHeaderText("Please Confirm your Appointment");
        alert.getDialogPane().setStyle("-fx-background-color:  #826603; -fx-font-size: 18px;");
        alert.setContentText("\nService:  " + service + "\nStylist:  " + stylist + "\nDate of your Appointment:  " + date + "\nTime of your Appointment:  " + startTime );
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.OK);


        if (button == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage window = (Stage) BackToHome.getScene().getWindow();
            window.setScene((new Scene(root)));
            saveAppointment();
        } else {
            System.out.println("canceled");
        }


    }

    // saves Appointment
    private void saveAppointment() throws SQLException {
        LocalDate date = datePicker.getValue();
        LocalTime time = startFrom.getSelectionModel().getSelectedItem();
        String stylistName = Stylist.getSelectionModel().selectedItemProperty().getValue();
        String service = Service.getSelectionModel().selectedItemProperty().getValue();


        int clientId = Client.clientID; // we should find a way to determine the client ID

        Appointment apt = new Appointment(date, time, stylistName, service, clientId);
        Connection connection = null;
        connection = DriverManager.getConnection(Database.connectionString);
        if (apptIsUnique(stylistName, date, time, connection)) {
            apt.insert(connection);

            // Show confirmation message to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment saved successfully!");
            alert.setTitle("Confirmation");
            alert.setHeaderText("Thank You For booking with us!");
            alert.showAndWait();
        } else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "This time slot is already booked. Please choose another time.");
            errorAlert.setTitle("Booking Error");
            errorAlert.setHeaderText("Time Slot Unavailable");
            errorAlert.showAndWait();
        }
        connection.close();
    }

    public boolean apptIsUnique(String stylistName, LocalDate appointmentDate, LocalTime appointmentTime, Connection connection) {
        String sql = "SELECT COUNT(*) FROM appointment WHERE stylist_name = ? AND appointment_date = ? AND appointment_time = ?";
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

