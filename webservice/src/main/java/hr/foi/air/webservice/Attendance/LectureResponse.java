package hr.foi.air.webservice.Attendance;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class LectureResponse extends DataResponse {
    List<Lecture> data;

    public List<Lecture> getData() {
        return data;
    }

    public void setData(List<Lecture> data) {
        this.data = data;
    }
}
