package hr.foi.air.webservice.Attendance;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class AttendanceResponse extends DataResponse {

    List<tempAttendance> data;

    /**
     * Gets list of the class tempAttendance.
     * @return List of Weather as {@link List<tempAttendance>}
     */
    public List<tempAttendance> getData() {
        return data;
    }

    /**
     * Sets list of the class tempAttendance.
     * @param data List of tempAttendance
     */
    public void setData(List<tempAttendance> data) {
        this.data = data;
    }
}
