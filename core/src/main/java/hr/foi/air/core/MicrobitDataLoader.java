package hr.foi.air.core;

public abstract class MicrobitDataLoader {
    public Double temperature;
    public Double brightness;
    public Double humidity;

    protected MicrobitDataLoadedListener mMicrobitDataLoadedListener;

    public void loadData(MicrobitDataLoadedListener microbitDataLoadedListener){
        this.mMicrobitDataLoadedListener = microbitDataLoadedListener;
    }

    public boolean dataLoaded(){
        if (temperature == null || brightness == null || humidity == null){
            return false;
        }
        else {
            return true;
        }
    }
}
