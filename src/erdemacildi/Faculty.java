package erdemacildi;

import java.util.ArrayList;

public class Faculty {

    private int ID;
    private String name;
    private String surname;
    private String departmentName;
    private ArrayList<AssignInfo> assignInfoList = new ArrayList<>();

    public Faculty(int ID, String name, String surname, String departmentName) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.departmentName = departmentName;
    }

    public boolean withdrawAssignInfo (AssignInfo assignInfo){
        boolean check = false;
        int e = 0;
        for (AssignInfo a : assignInfoList){
            if (a.getCourse().getName().equals(assignInfo.getCourse().getName())){
                assignInfoList.remove(e);
            }
            e++;
        }
        return check;
    }

    public void addAssignInfo(AssignInfo assignInfo){
        assignInfoList.add(assignInfo);
    }

    public void printCourseList(){
        int e = 0;
        System.out.println("-------------------------------");
        System.out.println("printCourseList METHOD OF STUDENT ->");
        for (AssignInfo a : assignInfoList){
            for (Student b : a.getCourse().getStudentList()){
                System.out.print("STUDENT : " + b.getName() + " " + b.getSurname());
                System.out.println("MAJOR : " + b.getMajor());
            }
            System.out.println("COURSE : " + a.getCourse().getName() + " STATUS : REQUEST " + a.getAssignResult());
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ArrayList<AssignInfo> getAssignInfoList() {
        return assignInfoList;
    }

    public void setAssignInfoList(ArrayList<AssignInfo> assignInfoList) {
        this.assignInfoList = assignInfoList;
    }
}
