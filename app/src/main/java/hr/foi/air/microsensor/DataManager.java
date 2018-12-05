package hr.foi.air.microsensor;

import hr.foi.air.core.NavigationItem;

public class DataManager {
    //singleton
    private static DataManager instance;

    private DataManager()
    {

    }

    public static DataManager getInstance()
    {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }

<<<<<<< HEAD
    public void sendData(final NavigationItem module){

=======
    public void sendData(NavigationItem module, String dataToSend){
        module.setData(dataToSend);
>>>>>>> user_interface
    }
}
