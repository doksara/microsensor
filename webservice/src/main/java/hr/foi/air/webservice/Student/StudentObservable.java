package hr.foi.air.webservice.Student;

import java.util.Observable;

public class StudentObservable extends Observable {
    private static StudentObservable instance = null;

    public static StudentObservable getInstance() {

        if(instance == null) {

            instance = new StudentObservable();

        }

        return instance;
    }

    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
