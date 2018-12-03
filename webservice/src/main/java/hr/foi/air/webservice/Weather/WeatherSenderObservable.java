package hr.foi.air.webservice.Weather;

import java.util.Observable;

public class WeatherSenderObservable extends Observable {

    private static WeatherSenderObservable instance = null;

    public static WeatherSenderObservable getInstance() {

        if(instance == null) {

            instance = new WeatherSenderObservable();

        }

        return instance;
    }

    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }
}
