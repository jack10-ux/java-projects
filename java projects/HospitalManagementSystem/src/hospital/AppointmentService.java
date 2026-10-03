package hospital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppointmentService {

private Map<Integer, Patient> patients;
private Map<Integer, Doctor> doctors;
private Map<String, Appointment> appointments;
private List<LocalTime> standardSlots;

public AppointmentService() {
this.patients = new HashMap<>();
this.doctors = new HashMap<>();
this.appointments = new HashMap<>();

// Initialize standard daily appointment time slots
this.standardSlots = new ArrayList<>();
standardSlots.add(LocalTime.of(9, 0));
standardSlots.add(LocalTime.of(10, 0));
standardSlots.add(LocalTime.of(11, 0));
standardSlots.add(LocalTime.of(14, 0));
standardSlots.add(LocalTime.of(15, 0));
standardSlots.add(LocalTime.of(16, 0));

// Seed initial sample patients
patients.put(1, new Patient(1, "Naidu", "8897294014", 22 , "M"));
patients.put(2, new Patient(2, "Srinu", "6304441992", 23 , "M"));


doctors.put(101, new Doctor(101, "Aravind", "Cardiology"));
doctors.put(102, new Doctor(102, "Harsha", "Neurology"));
doctors.put(103, new Doctor(103, "Sandeep", "Pediatrics"));
}

public void registerPatient(int patientId, String name, String phone, int age , String gender) throws AppointmentException {
if (patientId <= 0) {
throw new AppointmentException("Patient ID must be a positive integer.");
}
if (name == null || name.trim().isEmpty()) {
throw new AppointmentException("Patient name cannot be empty.");
}
if (patients.containsKey(patientId)) {
throw new AppointmentException("Patient ID " + patientId + " already exists.");
}

Patient patient = new Patient(patientId, name, phone, age, gender);
patients.put(patientId, patient);
System.out.println("SUCCESS: Patient registered successfully: " + patient);
}

public void addDoctor(int doctorId, String name, String specialization) throws AppointmentException {
if (doctorId <= 0) {
throw new AppointmentException("Doctor ID must be a positive integer.");
}
if (name == null || name.trim().isEmpty()) {
throw new AppointmentException("Doctor name cannot be empty.");
}
if (doctors.containsKey(doctorId)) {
throw new AppointmentException("Doctor ID " + doctorId + " already exists.");
}

Doctor doctor = new Doctor(doctorId, name, specialization);
doctors.put(doctorId, doctor);
System.out.println("SUCCESS: Doctor added successfully: " + doctor);
}


public void bookAppointment(int patientId, int doctorId, LocalDate date, LocalTime time) throws AppointmentException {
if (!patients.containsKey(patientId)) {
throw new AppointmentException("Patient ID " + patientId + " does not exist! Please register patient first.");
}
if (!doctors.containsKey(doctorId)) {
throw new AppointmentException("Doctor ID " + doctorId + " does not exist!");
}

String slotKey = generateSlotKey(doctorId, date, time);
if (appointments.containsKey(slotKey)) {
Doctor d = doctors.get(doctorId);
throw new AppointmentException("Doctor Dr. " + d.getName() + " is already booked for date " + date + " at time " + time + "!");
}

Patient patient = patients.get(patientId);
Doctor doctor = doctors.get(doctorId);
String appointmentId = "APT-" + doctorId + "-" + (appointments.size() + 1);

Appointment appointment = new Appointment(appointmentId, patient, doctor, date, time);
appointments.put(slotKey, appointment);

System.out.println("SUCCESS: Appointment booked successfully!");
System.out.println(appointment);
}


public void cancelAppointment(int doctorId, LocalDate date, LocalTime time) throws AppointmentException {
if (!doctors.containsKey(doctorId)) {
throw new AppointmentException("Doctor ID " + doctorId + " does not exist!");
}

String slotKey = generateSlotKey(doctorId, date, time);
if (!appointments.containsKey(slotKey)) {
throw new AppointmentException("No appointment found for Doctor ID " + doctorId + " on " + date + " at " + time + ".");
}

Appointment removed = appointments.remove(slotKey);
System.out.println("SUCCESS: Appointment cancelled successfully: " + removed.getAppointmentId());
}


public List<LocalTime> getAvailableSlots(int doctorId, LocalDate date) throws AppointmentException {
if (!doctors.containsKey(doctorId)) {
throw new AppointmentException("Doctor ID " + doctorId + " does not exist!");
}

List<LocalTime> availableSlots = new ArrayList<>();
for (LocalTime slot : standardSlots) {
String slotKey = generateSlotKey(doctorId, date, slot);
if (!appointments.containsKey(slotKey)) {
availableSlots.add(slot);
}
}
return availableSlots;
}


public List<Appointment> getDoctorSchedule(int doctorId) throws AppointmentException {
if (!doctors.containsKey(doctorId)) {
throw new AppointmentException("Doctor ID " + doctorId + " does not exist!");
}

List<Appointment> schedule = new ArrayList<>();
for (Appointment appt : appointments.values()) {
if (appt.getDoctor().getDoctorId() == doctorId) {
schedule.add(appt);
}
}
return schedule;
}


public List<Appointment> getPatientHistory(int patientId) throws AppointmentException {
if (!patients.containsKey(patientId)) {
throw new AppointmentException("Patient ID " + patientId + " does not exist!");
}

List<Appointment> history = new ArrayList<>();
for (Appointment appt : appointments.values()) {
if (appt.getPatient().getPatientId() == patientId) {
history.add(appt);
}
}
return history;
}

public void displayAllPatients() {
System.out.println("\n--- REGISTERED PATIENTS ---");
if (patients.isEmpty()) {
System.out.println("No registered patients found.");
return;
}
for (Patient p : patients.values()) {
System.out.println(p);
}
}

public void displayAllDoctors() {
System.out.println("\n--- LIST OF DOCTORS ---");
if (doctors.isEmpty()) {
System.out.println("No doctors available.");
return;
}
for (Doctor d : doctors.values()) {
System.out.println(d);
}
}

public Doctor getDoctor(int doctorId) {
return doctors.get(doctorId);
}

public Patient getPatient(int patientId) {
return patients.get(patientId);
}

private String generateSlotKey(int doctorId, LocalDate date, LocalTime time) {
return doctorId + "_" + date.toString() + "_" + time.toString();
}
}
