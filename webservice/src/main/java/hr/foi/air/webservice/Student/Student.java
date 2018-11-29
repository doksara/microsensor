package hr.foi.air.webservice.Student;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Student {

    //Primijeniti prema tome što trebamo dohvatiti(imena varijabli moraju biti
    // ista kao i u JSON-u)
    int id_korisnik;
    String ime;
    String prezime;
    String email;

    public int getId_korisnik() {
        return id_korisnik;
    }

    public String getEmail() {
        return email;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
