package edu.metrostate;

import edu.metrostate.Client;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Database {

    public static String databaseName = "SalonAppDatabase.db";
    public static String connectionString = "jdbc:sqlite:" + databaseName;

    public static Boolean createTable(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = Client.createClientTable(connection);
            Boolean result = statement.execute();
            if (result) {
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




}
