package hr.foi.air.microsensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.webservice.Student.StudentObservable;
import hr.foi.air.webservice.Weather.Weather;
import hr.foi.air.webservice.Weather.WeatherDataObservable;
import hr.foi.air.webservice.Weather.WeatherLoader;
import hr.foi.air.webservice.Weather.WeatherSender;
import hr.foi.air.webservice.Weather.WeatherSenderObservable;

public class HomepageActivity extends AppCompatActivity implements Observer {
    @BindView(R.id.userMessage) TextView userMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        WeatherSenderObservable.getInstance().addObserver(this);
        //WeatherDataObservable.getInstance().addObserver(this);
        ButterKnife.bind(this);

        // Dohvaćamo ime korisnika koji se logirao
        Intent intent = getIntent();
        String message = intent.getStringExtra("CURRENT_USER");
        userMessage.setText("Pozdrav " + message);
    }

    @OnClick(R.id.button2)
    public void onClick(View view)
    {
        WeatherSender controller = new WeatherSender();
        controller.sendWeather(controller.create(), 2, 20, 55, 25);
        //WeatherLoader controller = new WeatherLoader();
        //controller.loadWeather(controller.create(), "FOI1", "D101");
    }

    // U ovoj funkciji pozivati druge funkcije koje rade sa dohvaćenim podacima
    @Override
    public void update(Observable o, Object arg) {
        String response = (String) arg;
        Toast.makeText(HomepageActivity.this, response, Toast.LENGTH_SHORT).show();
        /*List<Object> list = (List<Object>) arg;
        String message = (String) list.get(1);
        List<Weather> weatherList = (List<Weather>) list.get(0);
        if(!weatherList.isEmpty())
        {
            TextView text1 = findViewById(R.id.textView);
            text1.setText(Integer.toString(weatherList.get(0).getTemperatura()));
            TextView text2 = findViewById(R.id.textView2);
            text2.setText(Float.toString(weatherList.get(1).getVlaznost_zraka()));
        }
        else {
            Toast.makeText(HomepageActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        */
    }
}
