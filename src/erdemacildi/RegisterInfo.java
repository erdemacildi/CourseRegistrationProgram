package erdemacildi;

public class RegisterInfo {

    private Course course;
    private String result;
    private int registerID;
    private String registerMessage;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRegisteID() {
        return registerID;
    }

    public void setRegisteID(int registeID) {
        this.registerID = registeID;
    }

    public String getRegisterMessage() {
        return registerMessage;
    }

    public void setRegisterMessage(String registerMessage) {
        this.registerMessage = registerMessage;
    }
}
