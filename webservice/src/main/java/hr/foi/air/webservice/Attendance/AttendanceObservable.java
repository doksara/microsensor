package hr.foi.air.webservice.Attendance;

import java.util.Observable;

public class AttendanceObservable extends Observable {
    private static AttendanceObservable instance = null;

    public static AttendanceObservable getInstance() {

        if(instance == null) {

            instance = new AttendanceObservable();

        }

        return instance;
    }

    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
