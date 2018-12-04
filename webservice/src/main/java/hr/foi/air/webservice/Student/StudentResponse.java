package hr.foi.air.webservice.Student;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class StudentResponse extends DataResponse {
    List<Student> data;

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }
}
