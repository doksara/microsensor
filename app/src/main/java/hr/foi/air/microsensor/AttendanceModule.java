package hr.foi.air.microsensor;

import android.support.v4.app.Fragment;

import hr.foi.air.microsensor.fragments.FormAttendanceSubmission;

public interface AttendanceModule {

    /**
     * Sets the data for the fragment.
     * @param f reference on FormAttendanceSubmission fragment.
     */
    public void setData(FormAttendanceSubmission f);

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
