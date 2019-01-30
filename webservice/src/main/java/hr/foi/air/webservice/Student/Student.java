package hr.foi.air.webservice.Student;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Student {

    int id_korisnik;
    String ime;
    String prezime;
    String email;

    /**
     * Gets student ID.
     * @return Student ID as {@link Integer}
     */
    public int getId_korisnik() {
        return id_korisnik;
    }

    /**
     * Gets Student Email.
     * @return Student Email as {@link String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets Student name.
     * @return Student name as {@link String}
     */
    public String getIme() {
        return ime;
    }

    /**
     * Gets Student surname.
     * @return Student surname as {@link String}
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets Student Email.
     * @param email Email of the Student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets Student ID.
     * @param id_korisnik ID of the student
     */
    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    /**
     * Sets Student name.
     * @param ime Name of the Student
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Sets Student surname.
     * @param prezime Surname of the Student
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
