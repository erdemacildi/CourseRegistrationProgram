package erdemacildi;

import java.util.ArrayList;

public class Course {

    private String department;
    private String name;
    private int credits;
    private int prereqYear;
    private int maxEnrollment;
    private int reservedSeats;
    private ArrayList<Student> studentList;
    private ArrayList<Student> replacementList;
    private Faculty instructor;

    public Course(String department, String name, int credits, int prereqYear, int maxEnrollment, int reservedSeats) {
        this.department = department;
        this.name = name;
        this.credits = credits;
        this.prereqYear = prereqYear;
        this.maxEnrollment = maxEnrollment;
        this.reservedSeats = reservedSeats;
    }

    public RegisterInfo registerCourse(Student std){
        RegisterInfo registerInfo=null;
        if (std.getYear()>= this.prereqYear && this.studentList.size()<this.maxEnrollment){
            if (std.getMajor().equals(this.department)){
                this.studentList.add(std);
            }else {
                if (this.studentList.size()<((this.maxEnrollment)*70)/100){

                }else {

                }
            }
            //this.studentList.add(std);
        }else{

        }
        return registerInfo;
    }

    public AssignInfo assignInstructor (Faculty instructor, boolean force){
        AssignInfo assignInstructor=null;
        return assignInstructor;
    }

    public void registerReplacementList(){

    }

    public void printStudentList(){

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getPrereqYear() {
        return prereqYear;
    }

    public void setPrereqYear(int prereqYear) {
        this.prereqYear = prereqYear;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Student[] getStudentList() {
        return studentList;
    }

    public void setStudentList(Student[] studentList) {
        this.studentList = studentList;
    }

    public Student[] getReplacementList() {
        return replacementList;
    }

    public void setReplacementList(Student[] replacementList) {
        this.replacementList = replacementList;
    }

    public Faculty getInstructor() {
        return instructor;
    }

    public void setInstructor(Faculty instructor) {
        this.instructor = instructor;
    }
}
