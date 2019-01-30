package hr.foi.air.webservice.Attendance;

import java.util.Observable;

public class AttendanceObservable extends Observable {
    private static AttendanceObservable instance = null;

    /**
     * Gets the instance of the class AttendanceObservable.
     * @return Instance of AttendanceObservable
     */
    public static AttendanceObservable getInstance() {

        if(instance == null) {

            instance = new AttendanceObservable();

        }

        return instance;
    }

    /**
     * Sends the data to all observers of the current instance of AttendanceObservable.
     * @param response Data sent to observers
     */
    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
