package hr.foi.air.webservice.Attendance;

import java.util.List;

public class tempAttendance {

    private String naziv;
    private List<String> datum;

    /**
     * Gets tempAttendance list of dates.
     * @return tempAttendance list of dates as {@link List<String>}
     */
    public List<String> getDatum() {
        return datum;
    }

    /**
     * Gets tempAttendance subject name.
     * @return tempAttendance subject name as {@link String}
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets tempAttendance list of dates.
     * @param datum tempAttendance list of dates
     */
    public void setDatum(List<String> datum) {
        this.datum = datum;
    }

    /**
     * Sets tempAttendance subject name.
     * @param naziv tempAttendance subject name
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
