package hr.foi.air.webservice.Attendance;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class LectureResponse extends DataResponse {
    List<Lecture> data;

    /**
     * Gets list of the class Lecture.
     * @return List of Lecture as {@link List<Lecture>}
     */
    public List<Lecture> getData() {
        return data;
    }

    /**
     * Sets list of the class Lecture.
     * @param data List of Lecture
     */
    public void setData(List<Lecture> data) {
        this.data = data;
    }
}
