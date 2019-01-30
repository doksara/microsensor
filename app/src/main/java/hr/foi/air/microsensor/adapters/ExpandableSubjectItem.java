package hr.foi.air.microsensor.adapters;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import java.util.List;
import hr.foi.air.webservice.Attendance.Attendance;
import hr.foi.air.webservice.Attendance.Subject;

public class ExpandableSubjectItem extends Subject implements Parent<Attendance> {
    private List<Attendance> attendanceList;

    /**
     * Default constructor.
     * @param subject The {@link Subject} carrying the childen as {@link List&lt;Attendance&gt;}
     */
    public ExpandableSubjectItem(Subject subject){
        super(subject.getName(), subject.getAttendanceList());
        this.attendanceList = subject.getAttendanceList();
    }

    /**
     * Gets the children of this parent
     * @return {@link List&lt;Attendance&gt;} associated with this instance.
     */
    @Override
    public List<Attendance> getChildList() {
        return attendanceList;
    }

    /**
     * Gets the state of expandable item.
     * @return True if item is expanded, false if item is not expanded.
     */
    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
