package edu.metrostate;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private LocalDate dateOfBirth;
    private List<Appointment> pastAppointments;
    private List<Appointment> futureAppointments;

    public Client(String firstName, String lastName, String email, String phoneNumber, String password, LocalDate dateOfBirth){
        super(firstName, lastName, email, phoneNumber, password);
        this.dateOfBirth = dateOfBirth;
        this.pastAppointments = new ArrayList<Appointment>();
        this.futureAppointments =  new ArrayList <Appointment>();
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;

    }
    public List<Appointment> getPastAppointments() {
        return pastAppointments;

    }
    public List<Appointment> getFutureAppointments(){
        return futureAppointments;
    }

    public void  setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth =dateOfBirth;
    }

    public void setPastAppointments(List<Appointment> pastAppointments){
        this.pastAppointments = pastAppointments ;

    }
    public void setFutureAppointments(List<Appointment> futureAppointments){
        this.futureAppointments = futureAppointments;
    }



}





