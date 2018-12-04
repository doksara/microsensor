package hr.foi.air.webservice.Data;

import java.util.Observable;

public class DataObservable extends Observable {
    private static DataObservable instance = null;

    public static DataObservable getInstance() {

        if(instance == null) {

            instance = new DataObservable();

        }

        return instance;
    }

    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
