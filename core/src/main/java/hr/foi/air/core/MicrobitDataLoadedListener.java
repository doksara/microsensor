package hr.foi.air.core;

public interface MicrobitDataLoadedListener {
    void onDataLoaded(Double temperature, Double brightness, Double humidity);
}
