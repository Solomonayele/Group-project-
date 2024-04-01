package edu.metrostate;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.*;

//signing up a user, logging them in, changing the scene to the correct one
public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile){
        Parent root = null;

        try{
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void registerUser(ActionEvent event, String firstName, String lastName, DatePicker dateOfBirth, String email, String phoneNumber, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection(Database.connectionString); //connection url?
            psCheckUserExists = connection.prepareStatement("SELECT * FROM Client WHERE email = ?");
            psCheckUserExists.setString(6, email);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Email is already registered.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Cannot use email. Already under a registered account.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO Client (firstName, lastName, dateOfBirth, phoneNumber, email, password VALUES (?, ?, ?, ?, ?, ?)");
                psInsert.setString(2, firstName);
                psInsert.setString(3, lastName);
                psInsert.setString(4, String.valueOf(dateOfBirth));
                psInsert.setString(5, phoneNumber);
                psInsert.setString(6, email);
                psInsert.setString(7, password);
                psInsert.executeUpdate();

                changeScene(event, ""); //signed in scene
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
               try{
                   psCheckUserExists.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
            }
            if(psInsert != null){
                try{
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void signInUser(ActionEvent event, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(Database.connectionString);
            preparedStatement = connection.prepareStatement("SElECT password FROM Client WHERE email = ?");
            preparedStatement.setString(6, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User is not found.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect.");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "");
                    } else {
                        System.out.println("Incorrect password.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    }}
