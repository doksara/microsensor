package hr.foi.air.microsensor;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.webservice.Student.Student;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Student.StudentLoader;
import hr.foi.air.webservice.Weather.WeatherResponse;

public class MainActivity extends AppCompatActivity implements Observer {
    @BindView(R.id.mInputEmail) EditText inputEmail;
    @BindView(R.id.mInputPassword) EditText inputPassword;
    private Dialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mLogin)
    public void onClick(View view)
    {
        this.progressBar = Utils.LoadingSpinner(this);
        progressBar.show();
        DataObservable.getInstance().addObserver(this);
        String email = inputEmail.getText().toString();
        String lozinka = inputPassword.getText().toString();
        StudentLoader controller = new StudentLoader();
        controller.loadStudent(controller.create(), email, lozinka);
    }

    /**
     * On received HTTP response, updates the fragment with data.
     * If login is successful it will return data associated with account. If not, will return error message.
     * @param o Observer that is subscribed to subject
     * @param arg Object that needs to be casted to {@link List&lt;Student&gt;}
     */
    @Override
    public void update(Observable o, Object arg) {
        List<Student> response = (List<Student>) arg;

        if(!response.isEmpty())
        {
            Intent i = new Intent(this, HomepageActivity.class);
            i.putExtra("currentUser", String.valueOf(response.get(0).getId_korisnik()));
            this.progressBar.dismiss();
            startActivity(i);
        }
        else {
            Toast.makeText(MainActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
        }
        DataObservable.getInstance().deleteObserver(this);
    }
}
