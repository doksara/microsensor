package hr.foi.air.webservice.Student;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class StudentResponse extends DataResponse {
    List<Student> data;

    /**
     * Gets list of the class Student.
     * @return List of Students as {@link List<Student>}
     */
    public List<Student> getData() {
        return data;
    }

    /**
     * Sets list of the class Student.
     * @param data List of Students as {@link List<Student>}
     */
    public void setData(List<Student> data) {
        this.data = data;
    }
}
