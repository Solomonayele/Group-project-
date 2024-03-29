package edu.metrostate;


import  java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

public class Appointment {
    private LocalDate datePicker;
    private LocalTime timePicker;
    private List<Stylist> stylist;
    private List<Service> service;
    private List<Client> client;

    public Appointment(LocalDate datePicker, LocalTime timePicker, List<Stylist> stylist, List<Service> service, List<Client> client){
        this.client = client;
        this.service = service;
        this.stylist = stylist;
        this.datePicker = datePicker;
        this.timePicker = timePicker;
    }

    public LocalTime getTimePicker() {
        return timePicker;
    }
    public LocalDate getDatePicker(){
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
}

