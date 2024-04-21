package edu.metrostate;



import java.util.ArrayList;
import java.util.List;



public class Stylist extends Person{
    private List<Service> specialization;

    private List<Appointment> appointment;

    public  Stylist(String firstName, String lastName, String email, String phoneNumber, String password) {
        super(firstName,lastName, email, phoneNumber,password);
        this.specialization = new ArrayList<>();

    }

    public List<Service> getSpecialization(){
        return specialization;
    }


    public List<Appointment> getAppointment(){
        return appointment;
    }
    public void setSpecialization(List<Service> specialization){
        this.specialization =  specialization;
    }

   public void setAppointments(List<Appointment> appointment){
        this.appointment = appointment;
   }
}
