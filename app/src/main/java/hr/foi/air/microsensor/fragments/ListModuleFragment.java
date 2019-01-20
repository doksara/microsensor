package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.StatisticsViewModule;
import hr.foi.air.microsensor.adapters.MeasurementRecyclerAdapter;
import hr.foi.air.webservice.Weather.Weather;

public class ListModuleFragment extends Fragment implements StatisticsViewModule {
    private MeasurementRecyclerAdapter mAdapter;
    private List<Weather> weatherList;
    private RecyclerView mRecycler;

    public ListModuleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_module, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecycler = (RecyclerView) view.findViewById(R.id.mRecyclerViewStatistics);
        displayModule();

        ButterKnife.bind(this, view);
    }

    public void setData(List<Weather> weatherList){
        this.weatherList = weatherList;
    }

    @Override
    public Fragment getFragment(){
        return this;
    }

    public String getModuleID(){
        return "mSelectListModule";
    }

    public void displayModule() {
        if (mRecycler != null){
            mAdapter = new MeasurementRecyclerAdapter(getActivity(), weatherList);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecycler.setLayoutManager(manager);
            mRecycler.setHasFixedSize(true);
            mRecycler.setAdapter(mAdapter);
        }
    }
}
