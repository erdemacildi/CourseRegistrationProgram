package erdemacildi;

public class Faculty {

    private int ID;
    private String name;
    private String surname;
    private String departmentName;
    private AssignInfo[] assignInfoList;

    public Faculty(int ID, String name, String surname, String departmentName) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.departmentName = departmentName;
    }

    public boolean withdrawAssignInfo (AssignInfo assignInfo){
        boolean check = false;
        return check;
    }

    public void addAssignInfo(AssignInfo assignInfo){

    }

    public void printCourseList(){

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

    public AssignInfo[] getAssignInfoList() {
        return assignInfoList;
    }

    public void setAssignInfoList(AssignInfo[] assignInfoList) {
        this.assignInfoList = assignInfoList;
    }
}
