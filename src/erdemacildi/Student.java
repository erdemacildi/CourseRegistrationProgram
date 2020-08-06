package erdemacildi;

import java.util.ArrayList;

public class Student {
    private String name;
    private String surname;
    private String major;
    private int ID;
    private int year;
    private ArrayList<RegisterInfo> registerList;

    public Student(String name, String surname, String major, int ID, int year) {
        this.name = name;
        this.surname = surname;
        this.major = major;
        this.ID = ID;
        this.year = year;
    }

    public void addRegisterInfo (RegisterInfo registerInfo ){
        registerList.add(registerInfo);
    }

    public boolean removeRegisterInfo(Course course){
        boolean check = false;
        int e = 0;
        for (RegisterInfo a : registerList){
            if (a.getCourse().equals(course)){
                registerList.remove(e);
            }
            e++;
        }
        return check ;
    }

    public void printCourseList(){
        System.out.println("printCourseList METHOD OF STUDENT ->");
        System.out.print("STUDENT : " + this.name + " " + this.surname);
        System.out.println("MAJOR : " + this.major);
        for (RegisterInfo a : registerList)
            System.out.println("\tCOURSE : " + a.getCourse().getName() + " STATUS : " + a.getResult());

        int totalCredits = getTotalCredit();
        System.out.println("TOTAL CREDITS : " + totalCredits);

    }

    public int getTotalCredit(){
        int totalCredit = 0;
        for ( RegisterInfo a : registerList)
            totalCredit += a.getCourse().getCredits();
        return totalCredit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
