package hospital;

public class Patient {
private int patientId;
private String name;
private String phone;
private int age;
private String gender;

public Patient(int patientId, String name, String phone, int age , String gender) {
this.patientId = patientId;
this.name = name;
this.phone = phone;
this.age = age;
this.gender = gender;
}

public int getPatientId() {
return patientId;
}

public void setPatientId(int patientId) {
this.patientId = patientId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public int getAge() {
return age;
}

public void setAge(int age) {
this.age = age;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;	
}

@Override
public String toString() {
return "Patient ID: " + patientId + " | Name: " + name + " | Phone: " + phone + " | Age: " + age + "| Gender: " + gender ;
}
}