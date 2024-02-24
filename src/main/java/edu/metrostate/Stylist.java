package edu.metrostate;

import javafx.concurrent.Service;

import java.util.ArrayList;
import java.util.List;



public class Stylist extends Person{
    private List<Service> specialization;
    private List<TimeSlot> availability ;

    private list<Appointment> appointment;

    public  Stylist(String firstName, String lastName, String email, String phoneNumber, String password){
        super(firstName,lastName, email, phoneNumber,password);
        this.specialization = new ArrayList<Service>();
        this.availability = new ArrayList<TimeSlot>();
        this.appointment = new ArrayList<Appointment>();


    }

    public List<Service> getSpecialization(){
        return specialization;
    }

    public List<TimeSlot> getAvailability(){
        return availability;

    }
    public list<Appointment> getAppointment(){
        return appointment;
    }
    public void setSpecialization(List<Service> specialization){
        this.specialization =  specialization;
    }
   public void setAvailability(List<TimeSlot> availability){
        this.availability = availability;

   }
   public void setAppointments(List<Appointment> appointments){
        this.appointments =appointments;
   }
}
