package edu.metrostate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Service {
    // Attributes
    private String name;
    private double price;
    private String description;
    private int lengthOfTime;

    // Constructor
    public Service(String name, double price, String description, int lengthOfTime) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lengthOfTime = lengthOfTime;
    }

    public Service(String name, String description){
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLengthOfTime() {
        return lengthOfTime;
    }
    public void setLengthOfTime(int lengthOfTime) {
        this.lengthOfTime = lengthOfTime;
    }

    public void insert(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = insertServicesStatement(connection);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CloseQuietly.close(statement);
        }
    }

    public PreparedStatement insertServicesStatement(Connection connection) throws SQLException {
        String sql = "INSERT INTO Services (stylist_name, service_offered) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, this.name);
        preparedStatement.setString(2, this.description);
        return preparedStatement;
    }

}
