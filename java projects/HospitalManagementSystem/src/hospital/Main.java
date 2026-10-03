package hospital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
	static final String RESET = "\u001B[0m";
	static final String RED = "\u001B[31m";
	static final String GREEN = "\u001B[32m";
	static final String YELLOW = "\u001B[33m";
	static final String BLUE = "\u001B[34m";
	static final String CYAN = "\u001B[36m";
	
public static void main(String[] args) {
AppointmentService service = new AppointmentService();
Scanner scanner = new Scanner(System.in);
boolean running = true;

System.out.println(CYAN +"=================================================" + RESET);
System.out.println(CYAN +" WELCOME TO HOSPITAL APPOINTMENT MANAGEMENT SYSTEM " + RESET);
System.out.println(CYAN +"=================================================" + RESET);

while (running) {
System.out.println(YELLOW+"\n------------------- MAIN MENU -------------------"+RESET);
System.out.println(BLUE+"1. Register Patient"+RESET);
System.out.println(BLUE+"2. Add Doctor"+RESET);
System.out.println(BLUE+"3. Book Appointment"+RESET);
System.out.println(BLUE+"4. Cancel Appointment"+RESET);
System.out.println(BLUE+"5. View Available Slots"+RESET);
System.out.println(BLUE+"6. View Doctor Schedule"+RESET);
System.out.println(BLUE+"7. View Patient History"+RESET);
System.out.println(BLUE+"8. Exit"+RESET);
System.out.print(GREEN+"Enter your choice (1-8): "+RESET);

String choice = scanner.nextLine().trim();

switch (choice) {
case "1":
handleRegisterPatient(service, scanner);
break;
case "2":
handleAddDoctor(service, scanner);
break;
case "3":
handleBookAppointment(service, scanner);
break;
case "4":
handleCancelAppointment(service, scanner);
break;
case "5":
handleViewAvailableSlots(service, scanner);
break;
case "6":
handleViewDoctorSchedule(service, scanner);
break;
case "7":
handleViewPatientHistory(service, scanner);
break;
case "8":
System.out.println(GREEN+"\nThank you for using Hospital Appointment Management System. Takecare!" +RESET);
running = false;
break;
default:
System.out.println(RED+"Invalid choice! Please enter a number between 1 and 8."+RESET);
break;
}
}

scanner.close();
}

private static void handleRegisterPatient(AppointmentService service, Scanner scanner) {
System.out.println(YELLOW+"\n--- REGISTER PATIENT ---"+RESET);
try {
System.out.print(BLUE+"Enter Patient ID (integer): "+RESET);
int id = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Patient Name: "+RESET);
String name = scanner.nextLine().trim();

System.out.print(BLUE+"Enter Phone Number: "+RESET);
String phone = scanner.nextLine().trim();

System.out.print(BLUE+"Enter Age: "+RESET);
int age = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Gender: "+RESET);
String gender = scanner.nextLine().trim();		


service.registerPatient(id, name, phone, age , gender);
} catch (NumberFormatException e) {
System.out.println(RED+"ERROR: Patient ID and Age must be numeric values!"+RESET);
} catch (AppointmentException e) {
System.out.println(RED+"ERROR: " + e.getMessage()+RESET);
}
}

private static void handleAddDoctor(AppointmentService service, Scanner scanner) {
System.out.println(YELLOW+"\n--- ADD DOCTOR ---"+RESET);
try {
System.out.print(BLUE+"Enter Doctor ID (integer): "+RESET);
int id = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Doctor Name: "+RESET);
String name = scanner.nextLine().trim();

System.out.print(BLUE+"Enter Specialization: "+RESET);
String spec = scanner.nextLine().trim();

service.addDoctor(id, name, spec);
} catch (NumberFormatException e) {
System.out.println(RED+"ERROR: Doctor ID must be a numeric value!"+RESET);
} catch (AppointmentException e) {
System.out.println(RED+"ERROR: " + e.getMessage()+RESET);
}
}

private static void handleBookAppointment(AppointmentService service, Scanner scanner) {
System.out.println(YELLOW+"\n--- BOOK APPOINTMENT ---"+RESET);
service.displayAllPatients();
service.displayAllDoctors();

try {
System.out.print(BLUE+"\nEnter Patient ID: "+RESET);
int patientId = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Doctor ID: "+RESET);
int doctorId = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Appointment Date (YYYY-MM-DD): "+RESET);
String dateStr = scanner.nextLine().trim();
LocalDate date = LocalDate.parse(dateStr);

System.out.print(BLUE+"Enter Appointment Time (HH:MM, e.g. 10:00): "+RESET);
String timeStr = scanner.nextLine().trim();
LocalTime time = LocalTime.parse(timeStr);

service.bookAppointment(patientId, doctorId, date, time);
} catch (NumberFormatException e) {
System.out.println(RED+"ERROR: Patient ID and Doctor ID must be numbers!"+RESET);
} catch (DateTimeParseException e) {
System.out.println(RED+"ERROR: Invalid Date/Time format! Please use YYYY-MM-DD for date (e.g. 2026-10-05) and HH:MM for time (e.g. 10:00)."+RESET);
} catch (AppointmentException e) {
System.out.println(RED+"ERROR: " + e.getMessage()+RESET);
}
}

private static void handleCancelAppointment(AppointmentService service, Scanner scanner) {
System.out.println(RED+"\n--- CANCEL APPOINTMENT ---"+RESET);
try {
System.out.print(BLUE+"Enter Doctor ID: "+RESET);
int doctorId = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Appointment Date (YYYY-MM-DD): "+RESET);
LocalDate date = LocalDate.parse(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Appointment Time (HH:MM, e.g. 10:00): "+RESET);
LocalTime time = LocalTime.parse(scanner.nextLine().trim());

service.cancelAppointment(doctorId, date, time);
} catch (NumberFormatException e) {
System.out.println(RED+"ERROR: Doctor ID must be a number!"+RESET);
} catch (DateTimeParseException e) {
System.out.println(RED+"ERROR: Invalid Date/Time format!"+RESET);
} catch (AppointmentException e) {
System.out.println(RED+"ERROR: " + e.getMessage()+RESET);
}
}

private static void handleViewAvailableSlots(AppointmentService service, Scanner scanner) {
System.out.println(YELLOW+"\n--- VIEW AVAILABLE SLOTS ---"+RESET);
service.displayAllDoctors();

try {
System.out.print(BLUE+"\nEnter Doctor ID: "+RESET);
int doctorId = Integer.parseInt(scanner.nextLine().trim());

System.out.print(BLUE+"Enter Date (YYYY-MM-DD): "+RESET);
LocalDate date = LocalDate.parse(scanner.nextLine().trim());

Doctor doctor = service.getDoctor(doctorId);
List<LocalTime> slots = service.getAvailableSlots(doctorId, date);

System.out.println("\nAvailable slots for Dr. " + doctor.getName() + " on " + date + ":");
if (slots.isEmpty()) {
System.out.println("No available slots for this date.");
} else {
for (LocalTime slot : slots) {
System.out.println(" - " + slot);
}
}
} catch (NumberFormatException e) {
System.out.println("ERROR: Doctor ID must be a number!");
} catch (DateTimeParseException e) {
System.out.println("ERROR: Invalid Date format! Use YYYY-MM-DD.");
} catch (AppointmentException e) {
System.out.println("ERROR: " + e.getMessage());
}
}

private static void handleViewDoctorSchedule(AppointmentService service, Scanner scanner) {
System.out.println(YELLOW+"\n--- VIEW DOCTOR SCHEDULE ---"+RESET);
try {
System.out.print(BLUE+"Enter Doctor ID: "+RESET);
int doctorId = Integer.parseInt(scanner.nextLine().trim());

Doctor doctor = service.getDoctor(doctorId);
List<Appointment> schedule = service.getDoctorSchedule(doctorId);

System.out.println("\nScheduled Appointments for Dr. " + doctor.getName() + ":");
if (schedule.isEmpty()) {
System.out.println(RED+"No appointments scheduled."+RESET);
} else {
for (Appointment appt : schedule) {
System.out.println(appt);
}
}
} catch (NumberFormatException e) {
System.out.println("ERROR: Doctor ID must be a number!");
} catch (AppointmentException e) {
System.out.println("ERROR: " + e.getMessage());
}
}

private static void handleViewPatientHistory(AppointmentService service, Scanner scanner) {
System.out.println(YELLOW+"\n--- VIEW PATIENT HISTORY ---"+RESET);
try {
System.out.print(BLUE+"Enter Patient ID: "+RESET);
int patientId = Integer.parseInt(scanner.nextLine().trim());

Patient patient = service.getPatient(patientId);
List<Appointment> history = service.getPatientHistory(patientId);

System.out.println("\nAppointment History for Patient " + patient.getName() + ":");
if (history.isEmpty()) {
System.out.println(RED+"No appointment history found."+RESET);
} else {
for (Appointment appt : history) {
System.out.println(appt);
}
}
} catch (NumberFormatException e) {
System.out.println(RED+"ERROR: Patient ID must be a number!"+RESET);
} catch (AppointmentException e) {
System.out.println(RED+"ERROR: " +RESET + e.getMessage());
}
}
}