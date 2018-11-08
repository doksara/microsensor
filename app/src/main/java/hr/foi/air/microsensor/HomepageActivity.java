package hr.foi.air.microsensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomepageActivity extends AppCompatActivity {
    @BindView(R.id.userMessage) TextView userMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        // Dohvaćamo ime korisnika koji se logirao
        Intent intent = getIntent();
        String message = intent.getStringExtra("CURRENT_USER");
        userMessage.setText("Pozdrav " + message);
    }

}
