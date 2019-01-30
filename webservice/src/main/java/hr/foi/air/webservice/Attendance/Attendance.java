package hr.foi.air.webservice.Attendance;

public class Attendance {
    private String date;

    /**
     * Deafult constructor.
     */
    public Attendance(){
        // Empty constructor
    }

    /**
     * Parametarized constructor.
     * @param date Attendance date
     */
    public Attendance(String date){
        this.date = date;
    }

    /**
     * Gets Attendance date.
     * @return Attendance date as {@link String}
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets Attendance date.
     * @param date Attendance date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
