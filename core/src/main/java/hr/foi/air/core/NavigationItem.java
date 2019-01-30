package hr.foi.air.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

public interface NavigationItem {

    /**
     * Gets the current fragment.
     * @return Current fragment as {@link Fragment}
     */
    public Fragment getFragment();

    /**
     * Gets the name of the navigation item.
     * @param context The {@link Context} of the Activity.
     * @return Name of the navigation item as
     */
    public String getName(Context context);

    /**
     * Gets the icon of the navigation item.
     * @param context The {@link Context} of the Activity.
     * @return Icon of the navigation item as {@link Drawable}
     */
    public Drawable getIcon(Context context);

    /**
     * Sets the data for the fragment.
     * @param optionalData Data to be passed to the fragment as {@link String}
     */
    public void setData(String optionalData);

    /**
     * Sets the state of the beacon (active / inactive).
     * @param state The state of Beacon - true for active, false for inactive.
     */
    public void setBeaconState(boolean state);
}