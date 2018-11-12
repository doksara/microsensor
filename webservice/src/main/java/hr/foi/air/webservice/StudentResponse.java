package hr.foi.air.webservice;

import java.util.List;

public class StudentResponse {
    List<Student> data;
    String message;
    String success;

    public List<Student> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getSuccess() {
        return success;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
