package hr.foi.air.webservice;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Student {

    //Primijeniti prema tome što trebamo dohvatiti(imena varijabli moraju biti
    // ista kao i u JSON-u)
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
