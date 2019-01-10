package hr.foi.air.webservice.Attendance;

import java.util.List;

public class tempAttendance {

    private String naziv;
    private List<String> datum;

    public List<String> getDatum() {
        return datum;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setDatum(List<String> datum) {
        this.datum = datum;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
