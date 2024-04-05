package edu.metrostate;


import edu.metrostate.Database.DBHelper;
import edu.metrostate.Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.DriverManager;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        new DBHelper(DriverManager.getConnection(Database.connectionString));

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