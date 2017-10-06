package delhi.nitdelhi.mpaul24.register;

/**
 * Created by Mpaul24 on 10/5/2017.
 */

public class Student {
    String rollNumber;
    String phoneNumber;
    String name;
    String email;
    String hostel;
    String roomNo;
    static Student student;
    public Student(String rollNumber, String phoneNumber, String name, String email, String hostel, String roomNo) {
        this.rollNumber = rollNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.hostel = hostel;
        this.roomNo = roomNo;
    }

    static void makeEntry(String rollNumber, String phoneNumber, String name, String email, String hostel, String roomNo){
        student = new Student(rollNumber,phoneNumber,name,email,hostel,roomNo);
    }

    static void RestoreObject(Student s){
        student =  s;
    }

    static Student getInstance(){
        return student;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHostel() {
        return hostel;
    }

    public String getRoomNo() {
        return roomNo;
    }
}
