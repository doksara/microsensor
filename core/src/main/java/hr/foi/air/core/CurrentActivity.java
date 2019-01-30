package hr.foi.air.core;

import android.support.v7.app.AppCompatActivity;

public class CurrentActivity extends AppCompatActivity {
    private static AppCompatActivity activity;

    /**
     * Gets the current activity.
     * @return The {@link AppCompatActivity} currently being viewed
     */
    public static AppCompatActivity getActivity() {
        return activity;
    }

    /**
     * Sets the current activity.
     * @param activity The {@link AppCompatActivity} currently being viewed
     */
    public static void setActivity(AppCompatActivity activity) {
        CurrentActivity.activity = activity;
    }
}
