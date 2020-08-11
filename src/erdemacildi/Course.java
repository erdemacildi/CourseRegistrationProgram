package erdemacildi;

import java.util.ArrayList;

public class Course {

    private String department;
    private String name;
    private int credits;
    private int prereqYear;
    private int maxEnrollment;
    private int reservedSeats;
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Student> replacementList = new ArrayList<>();
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
        RegisterInfo registerApproved = new RegisterInfo();
        registerApproved.setResult("APPROVED");
        registerApproved.setRegisterMessage("REQUEST APPROVED");
        registerApproved.setCourse(this);

        RegisterInfo registerRejectedQ = new RegisterInfo();
        registerRejectedQ.setResult("REJECTED");
        registerRejectedQ.setRegisterMessage("REQUEST REJECTED - QUATO");
        registerRejectedQ.setCourse(this);

        RegisterInfo registerRejectedP = new RegisterInfo();
        registerRejectedP.setResult("REJECTED");
        registerRejectedP.setRegisterMessage("REQUEST REJECTED - PREREQUISITE");
        registerRejectedP.setCourse(this);

        RegisterInfo registerWaiting = new RegisterInfo();
        registerWaiting.setResult("WAITING");
        registerWaiting.setRegisterMessage("REQUEST WAITING - REPLACEMENT LIST");
        registerWaiting.setCourse(this);

        if (!this.department.equals(std.getMajor())){
            if (std.getYear()>= this.prereqYear){
                if (this.studentList.size()+this.reservedSeats<((this.maxEnrollment)*70)/100) {
                    this.studentList.add(std);
                    std.getRegisterList().add(registerApproved);
                    return registerApproved;
                }else {
                    if (isReserved(std, registerRejectedQ, registerWaiting)) return registerWaiting;
                }
                return registerRejectedQ;
            }else
                std.getRegisterList().add(registerRejectedP);
                return registerRejectedP;
        }else {
            if (std.getYear()>= this.prereqYear){
                if (this.studentList.size()+this.reservedSeats<this.maxEnrollment) {
                    this.studentList.add(std);
                    std.getRegisterList().add(registerApproved);
                    return registerApproved;
                }else if (isReserved(std, registerRejectedQ, registerWaiting)) return registerWaiting;
                return registerRejectedQ;
            }else
                std.getRegisterList().add(registerRejectedP);
                return registerRejectedP;
        }
    }

    private boolean isReserved(Student std, RegisterInfo registerRejectedQ, RegisterInfo registerWaiting) {
        if (reservedSeats > 0){
            this.replacementList.add(std);
            std.getRegisterList().add(registerWaiting);
            return true;
        }else
            std.getRegisterList().add(registerRejectedQ);
        return false;
    }

    public AssignInfo AssignInstructor (Faculty instructor, boolean force){
        AssignInfo assignInstructorApproved = new AssignInfo();
        assignInstructorApproved.setAssignResult("APPROVED");
        assignInstructorApproved.setAssignMessage("INSTRUCTOR " + instructor.getName() + " " + instructor.getSurname() + " ASSIGNED");
        assignInstructorApproved.setCourse(this);

        AssignInfo assignInstructorRejectedD = new AssignInfo();
        assignInstructorRejectedD.setAssignResult("REJECTED");
        assignInstructorRejectedD.setAssignMessage("DEPARTMENT MISMATCH");
        assignInstructorRejectedD.setCourse(this);

        AssignInfo assignInstructorRejectedA = new AssignInfo();
        assignInstructorRejectedA.setAssignResult("REJECTED");
        assignInstructorRejectedA.setAssignMessage("ANOTHER INSTRUCTOR HAS ALREADY ASSIGNED");
        assignInstructorRejectedA.setCourse(this);

        ArrayList <AssignInfo> instructorArray = new ArrayList<>();

        if (this.department.equals(instructor.getDepartmentName())){
            if (force){
                if (this.instructor == null){
                    this.instructor=instructor;
                    instructorArray=instructor.getAssignInfoList();
                    instructorArray.add(assignInstructorApproved);
                    instructor.setAssignInfoList(instructorArray);
                    return assignInstructorApproved;
                }else{
                    ArrayList <AssignInfo> oldInstructor;
                    oldInstructor = this.instructor.getAssignInfoList();
                    oldInstructor.remove(oldInstructor.size()-1);
                    this.instructor.setAssignInfoList(oldInstructor);
                    this.instructor=instructor;
                    return assignInstructorApproved;
                }
            }else {
                if (this.instructor == null){
                    this.instructor = instructor;
                    instructorArray=instructor.getAssignInfoList();
                    instructorArray.add(assignInstructorApproved);
                    instructor.setAssignInfoList(instructorArray);
                    return assignInstructorApproved;
                }else {
                    AssignInfo instructorAllreadyAssigned = new AssignInfo();
                    instructorAllreadyAssigned.setAssignResult("REJECTED");
                    instructorAllreadyAssigned.setAssignMessage("THE COURSE HAS ALREADY AN INSTRUCTOR ASSIGNED");
                    instructorAllreadyAssigned.setCourse(this);
                    return assignInstructorRejectedA;
                }
            }
        }else
            return assignInstructorRejectedD;
    }

    public void RegisterReplacementList(){
        RegisterInfo registerApproved = new RegisterInfo();
        registerApproved.setResult("APPROVED");
        registerApproved.setRegisterMessage("REQUEST APPROVED");
        registerApproved.setCourse(this);

        RegisterInfo registerRejectedQ = new RegisterInfo();
        registerRejectedQ.setResult("REJECTED");
        registerRejectedQ.setRegisterMessage("REQUEST REJECTED - QUATO");
        registerRejectedQ.setCourse(this);

        int e = 0 ;
        for (Student a : this.replacementList){
            if (this.reservedSeats >0) {
                if (this.replacementList.get(e).getMajor().equals(this.department)){
                    for (RegisterInfo b : this.replacementList.get(e).getRegisterList()){
                        int r = 0;
                        if (b.getCourse().equals(this)){
                            this.replacementList.get(e).getRegisterList().remove(r);
                            this.replacementList.get(e).getRegisterList().add(registerApproved);
                        }
                        r++;
                    }
                    this.studentList.add(this.replacementList.get(e));
                    this.reservedSeats--;
                }
            }
            e++;
            if (reservedSeats==0) {
                break;
            }
        }
        if(replacementList.size()>0)
            this.replacementList.remove(e-1);
        e = 0;
        for (Student a : replacementList){
            if (this.reservedSeats >0) {
                for (RegisterInfo b : this.replacementList.get(e).getRegisterList()){
                    int r = 0;
                    if (b.equals(this)){
                        this.replacementList.get(e).getRegisterList().remove(r);
                        this.replacementList.get(e).getRegisterList().add(registerApproved);
                    }
                    r++;
                }
                this.studentList.add(this.replacementList.get(e));
                this.replacementList.remove(e);
                reservedSeats--;
            }
            e++;
            if (reservedSeats==0)
                break;
        }
    }

    public void printStudentList(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("printStrudentList METHOD OF COURSE -->");
        System.out.println("Course : " + this.name + " Department : " + this.department);
        System.out.println("Registered Student List");
        for (Student a : studentList){
            System.out.println("Student ID : " + a.getID() + " Name : " + a.getName() + " " + a.getSurname());
        }
        if (studentList.size()==maxEnrollment){
            System.out.println("No Student in Replacement List");
        }
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

    public Faculty getInstructor() {
        return instructor;
    }

    public void setInstructor(Faculty instructor) {
        this.instructor = instructor;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Student> getReplacementList() {
        return replacementList;
    }

    public void setReplacementList(ArrayList<Student> replacementList) {
        this.replacementList = replacementList;
    }
}
