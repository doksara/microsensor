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

public class MeasurementRecyclerAdapter extends RecyclerView.Adapter<MeasurementViewHolder> {
    private Context context;
    private List<Measurement> measurements;

    public MeasurementRecyclerAdapter(Context context, List<Measurement> measurements) {
        this.context = context;
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public MeasurementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View measurementView = LayoutInflater.from(context).inflate(R.layout.measurement_list_item, parent, false);
        return new MeasurementViewHolder(measurementView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementViewHolder measurementViewHolder, int position) {
        Measurement measurement = measurements.get(position);
        measurementViewHolder.setDetails(measurement);
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }
}
