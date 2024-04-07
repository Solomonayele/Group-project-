package edu.metrostate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

public class Appointment {
    private static LocalDate datePicker;
    private LocalTime timePicker;
    private List<Stylist> stylist;
    private List<Service> service;
    private List<Client> client;
    private int clientId;
    private String stylistName;
    private String serviceDesc;

    public Appointment(LocalDate datePicker, LocalTime timePicker, List<Stylist> stylist, List<Service> service, List<Client> client){
        this.client = client;
        this.service = service;
        this.stylist = stylist;
        this.datePicker = datePicker;
        this.timePicker = timePicker;
    }
    //Possible constructor
    public Appointment(LocalDate datePicker, LocalTime timePicker, String stylistName, String serviceDesc, int clientId){
        this.clientId = clientId;
        this.serviceDesc = serviceDesc;
        this.stylistName = stylistName;
        this.datePicker = datePicker;
        this.timePicker = timePicker;
    }

    public LocalTime getTimePicker() {
        return timePicker;
    }
    public static LocalDate getDatePicker(){
        return datePicker;
    }
    public List<Stylist> getStylist(){
        return stylist;
    }
    public List<Service> getService(){
        return service;
    }
    public List<Client> getClient(){
        return client;
    }

    public void setDatePicker(LocalDate datePicker) {
        this.datePicker = datePicker;
    }

    public void setTimePicker(LocalTime timePicker) {
        this.timePicker = timePicker;
    }
    public void setStylist(List<Stylist> stylist){
        this.stylist = stylist;
    }
    public void setService(List<Service> service){
        this.service = service;
    }
    public void setClient(List<Client> client){
        this.client = client;
    }

    public void insert(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = insertAppointmentStatement(connection);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CloseQuietly.close(statement);
        }
    }

    public PreparedStatement insertAppointmentStatement(Connection connection) throws SQLException {
        String sql = "INSERT INTO Appointment (appointment_date, appointment_time, stylist_name, service, client_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, java.sql.Date.valueOf(datePicker));
        preparedStatement.setTime(2, java.sql.Time.valueOf(timePicker));
        preparedStatement.setString(3, this.stylistName);
        preparedStatement.setString(4, this.serviceDesc);
        preparedStatement.setInt(5, this.clientId);
        return preparedStatement;
    }

}

