package hr.foi.air.microsensor;

import android.support.v4.app.Fragment;

import hr.foi.air.microsensor.fragments.FormAttendanceSubmission;

public interface AttendanceModule {
    public void setData(Integer idUser, Integer idSchedule, FormAttendanceSubmission f);
    public Fragment getFragment();
    public String getModuleID();
}
