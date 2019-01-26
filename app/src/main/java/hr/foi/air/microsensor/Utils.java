package hr.foi.air.microsensor;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

public class Utils {

    public static Dialog LoadingSpinner(Context mContext){
        Dialog pd = new Dialog(mContext, android.R.style.Theme_Black);
        View view = LayoutInflater.from(mContext).inflate(R.layout.progress_spinner, null);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().setBackgroundDrawableResource(R.color.transparent);
        pd.setContentView(view);
        return pd;
    }
}
