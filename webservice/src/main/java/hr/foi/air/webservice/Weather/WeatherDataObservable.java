package hr.foi.air.webservice.Weather;

import java.util.Observable;

public class WeatherDataObservable extends Observable {
    private static WeatherDataObservable instance = null;

    public static WeatherDataObservable getInstance() {

        if(instance == null) {

            instance = new WeatherDataObservable();

        }

        return instance;
    }

    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
