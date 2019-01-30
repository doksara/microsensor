package hr.foi.air.webservice.Data;

import java.util.Observable;

public class DataObservable extends Observable {
    private static DataObservable instance = null;

    /**
     * Gets the instance of the class DataObservable.
     * @return Instance of DataObservable
     */
    public static DataObservable getInstance() {

        if(instance == null) {

            instance = new DataObservable();

        }

        return instance;
    }

    /**
     * Sends the data to all observers of the current instance of DataObservable.
     * @param response Data sent to observers
     */
    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
