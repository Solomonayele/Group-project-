package edu.metrostate;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(Database.connectionString);

            Database.createTable(connection);

            Client client1 = new Client("John", "Doe", "john.doe@example.com","763-111-1111", "password123", LocalDate.of(1990, 5, 15));
            client1.insert(connection);
            System.out.print("client ID = " + client1.getClientID());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            CloseQuietly.close(connection);
        }



        Parent root = FXMLLoader.load(getClass().getResource("frontPage.fxml"));
        Scene scene = new Scene(root);
        loadStylesheetIntoScene(scene);
        stage.setScene(scene);
        stage.show();



    }

    private void loadStylesheetIntoScene(Scene scene) {
        URL stylesheetURL = getClass().getResource("style.css");
        if (stylesheetURL == null) {
            return;
        }
        String urlString = stylesheetURL.toExternalForm();
        if (urlString == null) {
            return;
        }
        scene.getStylesheets().add(urlString);
    }
}