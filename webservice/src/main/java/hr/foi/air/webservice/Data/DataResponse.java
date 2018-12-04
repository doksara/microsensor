package hr.foi.air.webservice.Data;

import java.util.List;

import hr.foi.air.webservice.Student.Student;

public abstract class DataResponse {
    String message;
    String success;

    public String getMessage() {
        return message;
    }

    public String getSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
