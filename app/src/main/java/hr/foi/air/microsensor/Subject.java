package hr.foi.air.microsensor;

import java.util.List;

public class Subject {
    private String name;
    private List<Attendance> attendanceList;

    public Subject() {
        // Empty constructor
    }

    public Subject(String name, List<Attendance> attendanceList) {
        this.name = name;
        this.attendanceList = attendanceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
