package hr.foi.air.microsensor;

import hr.foi.air.core.NavigationItem;

public class DataManager {
    //singleton
    private static DataManager instance;

    /**
     * Empty private Singleton constructor.
     */
    private DataManager()
    {
        // Singleton
    }

    /**
     * Returns the Singleton instance.
     * @return instance of {@link DataManager}
     */
    public static DataManager getInstance()
    {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }

    /**
     * Passes the data to fragment.
     * @param module Module which will receive data
     * @param dataToSend Data to be passed to module as {@link String}
     */
    public void sendData(NavigationItem module, String dataToSend){
        module.setData(dataToSend);
    }
}
