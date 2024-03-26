package edu.metrostate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appointment.fxml"));
        VBox root = loader.load();

        Scene scene = new Scene(root);

        loadStylesheetIntoScene(scene);

        stage.setTitle("Salon App");
        stage.setScene(scene);
        stage.show();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(Database.connectionString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CloseQuietly.close(connection);
            }

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