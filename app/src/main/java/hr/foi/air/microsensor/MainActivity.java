package hr.foi.air.microsensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.webservice.Student;
import hr.foi.air.webservice.StudentDataLoader;
import hr.foi.air.webservice.StudentObservable;

public class MainActivity extends AppCompatActivity implements Observer {
    @BindView(R.id.mInputEmail) EditText inputEmail;
    @BindView(R.id.mInputPassword) EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentObservable.getInstance().addObserver(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mLogin)
    public void onClick(View view)
    {
        String email = inputEmail.getText().toString();
        String lozinka = inputPassword.getText().toString();
        StudentDataLoader controller = new StudentDataLoader();
        controller.start(email, lozinka);
    }

    // U ovoj funkciji pozivati druge funkcije koje rade sa dohvaćenim podacima
    @Override
    public void update(Observable o, Object arg) {
        List<Student> response = (List<Student>) arg;

        Student currentUser = null;
        if(!response.isEmpty())
        {
            currentUser = response.get(0);

            Intent i = new Intent(MainActivity.this, HomepageActivity.class);
            i.putExtra("CURRENT_USER", currentUser.getIme().toString());
            startActivity(i);
        }
        else {
            Toast.makeText(MainActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
