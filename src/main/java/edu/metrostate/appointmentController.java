package edu.metrostate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class appointmentController implements Initializable {

    @FXML
    private ComboBox<String> Category;


    @FXML
    private ComboBox<String> Service;

    @FXML
    private ComboBox<String> Stylist;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button next;

    @FXML
    private ChoiceBox<String>  startFrom;

    @FXML
    private ChoiceBox<String>  FinishBy;


    String[] categoryName = {"Hair","Makeup","massage"};
    String[] serviceName ={"hair cut", "Hair braid", "makeup"};
    String[] stylistName= {"Neimo","Myriam", "Solmon","Julia", "Logan"};
    String[] stime = {"9:00 am", "10:00 am","11:00 am", "12:00 pm", "1:00 pm" ,"2:00 pm" , "3:00 pm" ,"4:00 pm" , "2:00 pm" ,"3:00 pm" ,"4:00 pm"};






    @FXML
    void selectDate(ActionEvent event) {
        datePicker.getValue().toString();
    }

    @FXML
    void startTime(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stylist.getItems().addAll(stylistName);
        Category.getItems().addAll(categoryName);
        Service.getItems().addAll(serviceName);

        startFrom.getItems().addAll(stime);
        FinishBy.getItems().addAll(stime);


    }
}
