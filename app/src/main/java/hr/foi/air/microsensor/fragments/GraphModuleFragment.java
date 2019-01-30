package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.StatisticsViewModule;
import hr.foi.air.webservice.Weather.Weather;

public class GraphModuleFragment extends Fragment implements StatisticsViewModule {
    AnyChartView lineChart;
    private List<Weather> weatherList;
    private final static String textColor = "#FFFFFF";
    private final static String position = "right";

    /**
     * Empty public constructor.
     */
    public GraphModuleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph_module, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lineChart = view.findViewById(R.id.mLineChart);
        displayModule();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setData(List<Weather> weatherList){
        this.weatherList = weatherList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment getFragment(){
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModuleID(){
        return "mSelectGraphModule";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayModule(){
        Cartesian cartesian = AnyChart.line();
        cartesian.background().fill("#455A64");
        cartesian.animation(true);

        cartesian.padding(0,0,0,0);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Measurements by date");
        cartesian.title().fontColor(textColor);

        cartesian.yAxis(0).title("°C / RH / lm");
        cartesian.yAxis(0).title().fontColor(textColor);
        cartesian.yAxis(0).labels().fontColor(textColor);
        cartesian.xAxis(0).labels().padding(0,0,0,0);
        cartesian.xAxis(0).labels().fontColor(textColor);

        List<DataEntry> seriesData = new ArrayList<>();
        if (weatherList != null){
            for (Weather weather : weatherList){
                String measurementDate = weather.getDan();
                Double temperature = weather.getTemperatura();
                Double humidity = weather.getVlaznost_zraka();
                Double brightness = weather.getJacina_svjetlosti();
                seriesData.add(new CustomDataEntry(measurementDate,temperature,humidity,brightness));
            }
        }

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping temperatureSeriesMapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping humiditySeriesMapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping lightLevelSeriesMapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line temperatureSeries = cartesian.line(temperatureSeriesMapping);
        temperatureSeries.name("Temperature");
        temperatureSeries.hovered().markers().enabled(true);
        temperatureSeries.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(1d);
        temperatureSeries.tooltip()
                .position(position)
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line humiditySeries = cartesian.line(humiditySeriesMapping);
        humiditySeries.name("Humidity");
        humiditySeries.hovered().markers().enabled(true);
        humiditySeries.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(1d);
        humiditySeries.tooltip()
                .position(position)
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line lightLevelSeries = cartesian.line(lightLevelSeriesMapping);
        lightLevelSeries.name("Light level");
        lightLevelSeries.hovered().markers().enabled(true);
        lightLevelSeries.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(1d);
        lightLevelSeries.tooltip()
                .position(position)
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontColor(textColor);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        this.lineChart.setBackgroundColor("#455A64");
        this.lineChart.setChart(cartesian);
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
    }
}
