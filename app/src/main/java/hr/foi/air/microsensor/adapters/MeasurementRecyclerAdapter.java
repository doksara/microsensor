package hr.foi.air.microsensor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Weather.Weather;

public class MeasurementRecyclerAdapter extends RecyclerView.Adapter<MeasurementViewHolder> {
    private Context context;
    private List<Weather> weathers;

    /**
     * Default constructor.
     * @param context The {@link Context} of the View instancing this class
     * @param weathers The {@link List<Weather>} with all items for recycling
     */
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

    /**
     * Sets the details for each item according to its position.
     * @param measurementViewHolder The {@link MeasurementViewHolder} that holds the view of item.
     * @param position The position of each item in list.
     */
    @Override
    public void onBindViewHolder(@NonNull MeasurementViewHolder measurementViewHolder, int position) {
        Weather weather = weathers.get(position);
        measurementViewHolder.bind(weather);
    }

    /**
     * Returns the number of items in the list.
     * @return Size of {@link List<Weather>}.
     */
    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
