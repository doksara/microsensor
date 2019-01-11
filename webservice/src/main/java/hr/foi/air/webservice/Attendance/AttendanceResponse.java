package hr.foi.air.webservice.Attendance;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class AttendanceResponse extends DataResponse {

    List<tempAttendance> data;

    public List<tempAttendance> getData() {
        return data;
    }

    public void setData(List<tempAttendance> data) {
        this.data = data;
    }
}
