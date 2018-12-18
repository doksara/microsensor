package hr.foi.air.microsensor;

public class Attendance {
    private String date;

    public Attendance(){
        // Empty constructor
    }

    public Attendance(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
