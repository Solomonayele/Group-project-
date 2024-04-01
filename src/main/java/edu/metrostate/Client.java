package edu.metrostate;

import edu.metrostate.CloseQuietly;

import java.nio.channels.ClosedChannelException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Client extends Person {
    private LocalDate dateOfBirth;
    private List<Appointment> pastAppointments;
    private List<Appointment> futureAppointments;
    private Integer clientID;

    public Client(String firstName, String lastName, String email, String phoneNumber, String password, LocalDate dateOfBirth){
        super(firstName, lastName, email, phoneNumber, password);
        this.dateOfBirth = dateOfBirth;
        this.clientID = null;
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
        this.dateOfBirth =dateOfBirth;
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

    public void setClientID(Integer id){
        this.clientID = id;
    }
    public static PreparedStatement createClientTable(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS Client (\n"
                + "    clientID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    firstName VARCHAR(50),\n"
                + "    lastName VARCHAR(50),\n"
                + "    dateOfBirth DATE,\n"
                + "    phoneNumber VARCHAR(15),\n"
                + "    email VARCHAR(100),\n"
                + "    password VARCHAR(25)\n"
                + ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return preparedStatement;
        } catch (SQLException e) {
            return null;
        }
    }

    public Boolean insert(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = insertStatement(connection);
            Boolean result = statement.execute();
            if (result) {
                ResultSet resultSet = statement.getResultSet();
                Integer id = resultSet.getInt("clientID");
                this.setClientID(id);
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

    public PreparedStatement insertStatement(Connection connection) throws SQLException {
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



}





