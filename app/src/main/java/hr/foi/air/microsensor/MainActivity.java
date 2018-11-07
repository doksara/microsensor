package hr.foi.air.microsensor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import hr.foi.air.webservice.Student;
import hr.foi.air.webservice.StudentDataLoader;
import hr.foi.air.webservice.StudentObservable;

public class MainActivity extends AppCompatActivity implements Observer {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentObservable.getInstance().addObserver(this);
    }

    public void onClick(View view)
    {
        // ove stringove treba napuniti sa pravim podacima iz text view-a
        String email = "dbaricevi@foi.hr";
        String lozinka = "bratic";
        StudentDataLoader controller = new StudentDataLoader();
        controller.start(email,lozinka);
    }

    //Funkcija koja prikazuje dobivene podatke tj. može se obrisati kasnije
    private void setTextView(Student list)
    {
        TextView textView = findViewById(R.id.textView);
        textView.setText(list.getEmail());
        counter++;
    }

    //U ovoj funkciji pozivati druge funkcije koje rade sa dohvaćenim podacima
    @Override
    public void update(Observable o, Object arg) {
        Student response = (Student) arg;
        setTextView(response);
    }
}
