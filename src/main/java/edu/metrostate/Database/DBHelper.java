package edu.metrostate.Database;

import edu.metrostate.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;




//Creates tables in one method to avoid clutter.
public class DBHelper {

    public static Connection connection = null;
    public DBHelper(Connection connection) throws SQLException {
        this.connection = connection;
        createTables();
        populateTables();

    }

    public void createTables() {
        try{
            connection.setAutoCommit(false);

            PreparedStatement clientTableCreation = createClientTable(connection);
            clientTableCreation.executeUpdate();
            CloseQuietly.close(clientTableCreation);

            PreparedStatement appointmentTableCreation = createAppointmentTable(connection);
            appointmentTableCreation.executeUpdate();
            CloseQuietly.close(appointmentTableCreation);

            PreparedStatement servicesTableCreation = createServiceTable(connection);
            servicesTableCreation.executeUpdate();
            CloseQuietly.close(servicesTableCreation);

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException sqlException){
            try{
                connection.rollback();
            } catch (SQLException rollbackEx){
                rollbackEx.printStackTrace();
            }
        }
    }

    public static PreparedStatement createClientTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Client (\n"
                + "    clientID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    firstName VARCHAR(50),\n"
                + "    lastName VARCHAR(50),\n"
                + "    dateOfBirth DATE,\n"
                + "    phoneNumber VARCHAR(15),\n"
                + "    email VARCHAR(100),\n"
                + "    password VARCHAR(25)\n"
                + ");";


            return connection.prepareStatement(sql);

    }

    public static PreparedStatement createAppointmentTable(Connection connection) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS Appointment ("
                + "appointment_date DATE,"
                + "appointment_time TIME,"
                + "stylist_name VARCHAR(255),"
                + "service VARCHAR(255),"
                + "client_id INT,"
                + "PRIMARY KEY (appointment_date, appointment_time),"
                + "FOREIGN KEY (client_id) REFERENCES Client(client_id)"
                + ")";

        return connection.prepareStatement(sql);

    }

    public static PreparedStatement createServiceTable(Connection connection) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS Services ("
                + "stylist_name VARCHAR(255), "
                + "service_offered VARCHAR(255)"
                + ")";

        return connection.prepareStatement(sql);
    }

    public void populateTables(){
        try {
            if (isTableEmpty(connection, "services")) {
                populateServicesTable();
            }

            if (isTableEmpty(connection, "appointment")) {

                populateAppointmentTable();

            }
            if (isTableEmpty(connection, "client")) {
                populateClientTable();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void populateClientTable() throws SQLException {
        Client client1 = new Client("John", "Doe", "john.doe@example.com", "763-111-1111", "password123", LocalDate.of(1990, 5, 15));
        client1.insert(connection);
    }
    public void populateAppointmentTable() throws SQLException {
        Appointment apt = new Appointment(LocalDate.of(2024, 5, 1), LocalTime.of(12, 40), "Sandra", "trim", 1  );
        apt.insert(connection);

    }

    public void populateServicesTable() throws SQLException {

       List<Service> services = new ArrayList<>();
        services.add(new Service("Naima", "Large Men's Braid, $60"));
        services.add(new Service("Naima", "Ponytail with Curly Extensions, $120"));
        services.add(new Service("Myriam", "Tribal Braids, $110"));
        services.add(new Service("Myriam", "Two Large Lemonade Braids, $140"));
        services.add(new Service("Julia", "Two Jumbo Tribal Braids, $120"));
        services.add(new Service("Julia", "Gana StiCh Braids In Pony Tail, $150"));
        services.add(new Service("Naima", "Men's Cornrows, $80"));
        services.add(new Service("Solman", "Men's Lemonade Braids, $70"));
        services.add(new Service("Logan", "Men's Haircut, $40"));
        services.add(new Service("Logan", "Men's Haircut Fade, $35"));
        services.add(new Service("Solman", "Men's Haircut Afro, $50"));



        for (Service service : services) {
            service.insert(connection);
        }


        }



    public static boolean isTableEmpty(Connection connection, String tableName) {
        String sql = "SELECT COUNT(*) AS count FROM " + tableName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count == 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking if table is empty: " + e.getMessage());
        }
        return true;
    }

    public static List<String> fetchServiceDescriptions() {
        List<String> descriptions = new ArrayList<>();
        String sql = "SELECT DISTINCT service_offered FROM Services";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                descriptions.add(resultSet.getString("service_offered"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching service descriptions: " + e.getMessage());
        }
        return descriptions;
    }

    public static List<String> fetchStylistNames() {
        List<String> stylistnames = new ArrayList<>();
        String sql = "SELECT DISTINCT stylist_name FROM Services";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                stylistnames.add(resultSet.getString("stylist_name"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching service descriptions: " + e.getMessage());

        }
        return stylistnames;
    }







}
