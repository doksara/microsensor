package hr.foi.air.microsensor.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Weather.Weather;

public class MeasurementViewHolder extends RecyclerView.ViewHolder {
    private TextView mMeasurementDate, mAvgTemperature, mAvgBrightness, mAvgHumidity;

    /**
     * Default constructor.
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public MeasurementViewHolder(View itemView){
        super(itemView);
        mMeasurementDate = itemView.findViewById(R.id.mMeasurementDate);
        mAvgTemperature = itemView.findViewById(R.id.mAvgTemperature);
        mAvgBrightness = itemView.findViewById(R.id.mAvgBrightness);
        mAvgHumidity = itemView.findViewById(R.id.mAvgHumidity);
    }

    /**
     * Binds the {@link View} with data provided in {@link Weather}.
     * @param weather Instance of {@link Weather} which the view will be bind with
     */
    public void bind(Weather weather) {
        mMeasurementDate.setText(String.format(Locale.US, "%s", weather.getDan()));
        mAvgTemperature.setText(String.format(Locale.US, "%s", weather.getTemperatura()));
        mAvgBrightness.setText(String.format(Locale.US, "%s", weather.getJacina_svjetlosti()));
        mAvgHumidity.setText(String.format(Locale.US, "%s", weather.getVlaznost_zraka()));
    }
}
