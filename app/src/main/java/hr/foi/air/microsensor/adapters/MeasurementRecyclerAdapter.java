package hr.foi.air.microsensor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.air.microsensor.Measurement;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Weather.Weather;

public class MeasurementRecyclerAdapter extends RecyclerView.Adapter<MeasurementViewHolder> {
    private Context context;
    private List<Weather> weathers;

    public MeasurementRecyclerAdapter(Context context, List<Weather> weathers) {
        this.context = context;
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public MeasurementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View measurementView = LayoutInflater.from(context).inflate(R.layout.measurement_list_item, parent, false);
        return new MeasurementViewHolder(measurementView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementViewHolder measurementViewHolder, int position) {
        Weather weather = weathers.get(position);
        measurementViewHolder.setDetails(weather);
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
