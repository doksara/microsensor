package hr.foi.air.webservice.Attendance;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class AttendanceResponse extends DataResponse {
    //Todo: Klasa trenutno ne odgovara JSON responseu sa web servisa i trebalo bi je u narednim commitovima ispraviti.

    List<tempAttendance> data;

    public List<tempAttendance> getData() {
        return data;
    }

    public void setData(List<tempAttendance> data) {
        this.data = data;
    }
}
