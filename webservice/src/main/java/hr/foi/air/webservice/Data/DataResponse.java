package hr.foi.air.webservice.Data;

import java.util.List;

import hr.foi.air.webservice.Student.Student;

public abstract class DataResponse {
    String message;
    String success;

    /**
     * Gets response message.
     * @return Message of the response as {@link String}
     */
    public String getMessage() {
        return message;
    }
    /**
     * Gets response success string.
     * @return Success string of the response as {@link String}
     */
    public String getSuccess() {
        return success;
    }

    /**
     * Sets response message.
     * @param message Message of the response
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets response success string.
     * @param success Success string of the response
     */
    public void setSuccess(String success) {
        this.success = success;
    }
}
