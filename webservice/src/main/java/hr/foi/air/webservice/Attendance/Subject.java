package hr.foi.air.webservice.Attendance;

import java.util.List;

public class Subject {
    private String name;
    private List<Attendance> attendanceList;

    /**
     * Empty constructor.
     */
    public Subject() {
        // Empty constructor
    }

    /**
     * Default constructor.
     * @param name Subject name
     * @param attendanceList Subject list of Attendances
     */
    public Subject(String name, List<Attendance> attendanceList) {
        this.name = name;
        this.attendanceList = attendanceList;
    }

    /**
     * Gets subject name.
     * @return subject name as {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets subject name.
     * @param name subject name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets subject list of attendances.
     * @return subject list of attendances as {@link List<Attendance>}
     */
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    /**
     * Sets subject list of attendances.
     * @param attendanceList subject list of attendances
     */
    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
