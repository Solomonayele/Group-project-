package edu.metrostate;

import edu.metrostate.Database.Database;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static edu.metrostate.Database.DBHelper.fetchAppointmentByClientID;

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
    private Button refreshTable;



    @FXML
    private TableView<Appointment> appointmentTableView;
    @FXML
    private TableColumn<Appointment, Integer> idColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> dateColumn;
    @FXML
    private TableColumn<Appointment, LocalTime> timeColumn;
    @FXML
    private TableColumn<Appointment, String> StylistColumn;
    @FXML
    private TableColumn<Appointment, String> ServiceColumn;
    Appointment appointID;

    ObservableList<Appointment> listApp;

    public void initialize() throws SQLException {

        //Client.getClientID();
        displayAppointment(Client.clientID);

        cancelApptID.setOnMouseClicked((mouseEvent) -> {
            try {
                deleteAppointmentByID();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        refreshTable.setOnAction(actionEvent -> {
            displayAppointment(Client.clientID);
        });
    }

    public void displayAppointment(int clientID){

        idColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("clientId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("datePicker"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Appointment,LocalTime>("timePicker"));
        StylistColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("stylistName"));
        ServiceColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("serviceDesc"));

        listApp = fetchAppointmentByClientID(clientID);
        appointmentTableView.setItems(listApp);
        //appointmentTableView.getColumns().addAll();

    }

    public void deleteAppointmentByID() throws SQLException {
        Connection connection = null;
        try {
            appointID = appointmentTableView.getSelectionModel().getSelectedItem();
            connection = DriverManager.getConnection(Database.connectionString);
            String sql = "DELETE FROM Appointment WHERE client_id = ? AND appointment_date = ? AND appointment_time = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int clientId = appointID.getClientId();
            LocalDate date = appointID.getDatePicker();
            LocalTime time = appointID.getTimePicker();
            preparedStatement.setInt(1, clientId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.setTime(3, java.sql.Time.valueOf(time));
            preparedStatement.executeUpdate();

            displayAppointment(Client.clientID);
        }catch (SQLException e){
            throw new RuntimeException("Error deleting appointment", e);
        }


    }


    public void goToAppointment() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("appointment.fxml"));
        Stage window = (Stage) bookApptID.getScene().getWindow();
        window.setScene((new Scene(root)));
        displayAppointment(Client.clientID);
    }

    public void Logout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        Stage window = (Stage) bookApptID.getScene().getWindow();
        window.setScene((new Scene(root)));
    }



}
