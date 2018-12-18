package hr.foi.air.microsensor.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import hr.foi.air.microsensor.Measurement;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Weather.Weather;

public class MeasurementViewHolder extends RecyclerView.ViewHolder {
    private TextView mMeasurementDate, mAvgTemperature, mAvgBrightness, mAvgHumidity;

    public MeasurementViewHolder(View itemView){
        super(itemView);
        mMeasurementDate = itemView.findViewById(R.id.mMeasurementDate);
        mAvgTemperature = itemView.findViewById(R.id.mAvgTemperature);
        mAvgBrightness = itemView.findViewById(R.id.mAvgBrightness);
        mAvgHumidity = itemView.findViewById(R.id.mAvgHumidity);
    }

    public void setDetails(Weather weather) {
        mMeasurementDate.setText(String.format(Locale.US, "%s", weather.getDan()));
        mAvgTemperature.setText(String.format(Locale.US, "%s", weather.getTemperatura()));
        mAvgBrightness.setText(String.format(Locale.US, "%s", weather.getJacina_svjetlosti()));
        mAvgHumidity.setText(String.format(Locale.US, "%s", weather.getVlaznost_zraka()));
    }
}
