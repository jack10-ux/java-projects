package hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
private String appointmentId;
private Patient patient;
private Doctor doctor;
private LocalDate appointmentDate;
private LocalTime appointmentTime;

public Appointment(String appointmentId, Patient patient, Doctor doctor, LocalDate appointmentDate, LocalTime appointmentTime) {
this.appointmentId = appointmentId;
this.patient = patient;
this.doctor = doctor;
this.appointmentDate = appointmentDate;
this.appointmentTime = appointmentTime;
}

public String getAppointmentId() {
return appointmentId;
}

public void setAppointmentId(String appointmentId) {
this.appointmentId = appointmentId;
}

public Patient getPatient() {
return patient;
}

public void setPatient(Patient patient) {
this.patient = patient;
}

public Doctor getDoctor() {
return doctor;
}

public void setDoctor(Doctor doctor) {
this.doctor = doctor;
}

public LocalDate getAppointmentDate() {
return appointmentDate;
}

public void setAppointmentDate(LocalDate appointmentDate) {
this.appointmentDate = appointmentDate;
}

public LocalTime getAppointmentTime() {
return appointmentTime;
}

public void setAppointmentTime(LocalTime appointmentTime) {
this.appointmentTime = appointmentTime;
}

@Override
public String toString() {
return "Appointment ID: " + appointmentId +
" | Patient: " + patient.getName() + " (ID: " + patient.getPatientId() + ")" +
" | Doctor: Dr. " + doctor.getName() + " (ID: " + doctor.getDoctorId() + ")" +
" | Date: " + appointmentDate +
" | Time: " + appointmentTime;
}
}