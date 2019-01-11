package hr.foi.air.microsensor.adapters;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import java.util.List;
import hr.foi.air.webservice.Attendance.Attendance;
import hr.foi.air.webservice.Attendance.Subject;

public class ExpandableSubjectItem extends Subject implements Parent<Attendance> {
    private List<Attendance> attendanceList;

    public ExpandableSubjectItem(Subject subject){
        super(subject.getName(), subject.getAttendanceList());
        this.attendanceList = subject.getAttendanceList();
    }

    @Override
    public List<Attendance> getChildList() {
        return attendanceList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
