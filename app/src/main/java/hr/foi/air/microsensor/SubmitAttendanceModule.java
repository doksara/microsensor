package hr.foi.air.microsensor;

import android.support.v4.app.Fragment;

public interface SubmitAttendanceModule {



    /**
     * Displays the current module in parent fragment.
     */
    public void displayModule();

    /**
     * Gets the current fragment.
     * @return Current fragment as {@link Fragment}
     */
    public Fragment getFragment();

    /**
     * Gets the unique module ID.
     * @return Unique module ID as {@link String}.
     */
    public String getModuleID();
}
