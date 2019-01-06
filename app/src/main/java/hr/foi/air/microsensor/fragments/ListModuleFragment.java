package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.adapters.MeasurementRecyclerAdapter;
import hr.foi.air.webservice.Weather.Weather;

public class ListModuleFragment extends Fragment {
    MeasurementRecyclerAdapter mAdapter;
    LinearLayoutManager manager;
    private List<Weather> weatherList;
    private RecyclerView mRecycler;
    private View currentView = null;
    private Context context;

    public ListModuleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_module, container, false);
        this.context = container.getContext();
        setView(view);
        return view;
    }

    public void setData(List<Weather> weatherList){
        this.weatherList = weatherList;
        displayListModule();
    }

    public void setView(View view){
        this.currentView = view;
    }

    public View getView(){
        return this.currentView;
    }

    public void displayListModule() {
        View view = this.getView();
        if (view != null){
            RecyclerView mRecycler = (RecyclerView) view.findViewById(R.id.mRecycleViewStatistics);
            mAdapter = new MeasurementRecyclerAdapter(this.context, weatherList);
            LinearLayoutManager manager = new LinearLayoutManager(this.context);
            mRecycler.setLayoutManager(manager);
            mRecycler.setHasFixedSize(true);
            mRecycler.setAdapter(mAdapter);
        }
    }
}
