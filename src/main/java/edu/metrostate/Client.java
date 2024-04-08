package edu.metrostate;


import javafx.scene.control.DatePicker;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Client extends Person {
    private LocalDate dateOfBirth;
    private List<Appointment> pastAppointments;
    private List<Appointment> futureAppointments;
    public static Integer clientID;


    public Client(String firstName, String lastName, String email, String phoneNumber, String password, LocalDate dateOfBirth){
        super(firstName, lastName, email, phoneNumber, password);
        this.dateOfBirth = dateOfBirth;
        clientID = null;
        this.pastAppointments = new ArrayList<Appointment>();
        this.futureAppointments =  new ArrayList <Appointment>();
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;

    }
    public List<Appointment> getPastAppointments() {
        return pastAppointments;

    }
    public List<Appointment> getFutureAppointments(){
        return futureAppointments;
    }

    public void  setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setPastAppointments(List<Appointment> pastAppointments){
        this.pastAppointments = pastAppointments ;

    }
    public void setFutureAppointments(List<Appointment> futureAppointments){
        this.futureAppointments = futureAppointments;
    }

    public Integer getClientID(){
        return clientID;
    }

    public static void setClientID(Integer id){
        clientID = id;
    }


    public Boolean insert(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = insertClientStatement(connection);
            Boolean result = statement.execute();
            if (result) {
                ResultSet resultSet = statement.getResultSet();
                Integer id = resultSet.getInt("clientID");
                setClientID(id);
                CloseQuietly.close(resultSet);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            CloseQuietly.close(statement);
        }
    }

    public PreparedStatement insertClientStatement(Connection connection) throws SQLException {
        String sqlQuery = "INSERT INTO Client (firstName, lastName, dateOfBirth, phoneNumber, email, password) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING clientID";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, this.getFirstName());
        statement.setString(2, this.getLastName());
        LocalDate date = this.getDateOfBirth();
        statement.setDate(3, java.sql.Date.valueOf(date));
        statement.setString(4, this.getPhoneNumber());
        statement.setString(5, this.getEmail());
        statement.setString(6, this.getPassword());
        return statement;
    }

    public static Integer retrieveClientIdByEmail(Connection connection, String email) {
        String sql = "SELECT clientID FROM Client WHERE email = ?";
        int clientId = -1; // Default value if client ID is not found

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    clientId = resultSet.getInt("clientID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }



}





