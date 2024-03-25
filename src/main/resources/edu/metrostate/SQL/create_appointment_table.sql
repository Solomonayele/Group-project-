CREATE TABLE Appointment (
    appointment_date DATE,
    appointment_time TIME,
    stylist_name VARCHAR(255),
    service VARCHAR(255),
    client_id INT,
    PRIMARY KEY (appointment_date, appointment_time, client_id)
    FOREIGN KEY (client_id)
);