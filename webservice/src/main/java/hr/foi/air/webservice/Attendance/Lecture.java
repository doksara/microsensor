package hr.foi.air.webservice.Attendance;

public class Lecture {
    private String zgrada;
    private String dvorana;
    private int id_raspored;
    private String kolegij;
    private String tip;

    public int getIdRaspored() {
        return id_raspored;
    }

    public String getDvorana() {
        return dvorana;
    }

    public String getKolegij() {
        return kolegij;
    }

    public String getZgrada() {
        return zgrada;
    }

    public String getTip() { return tip; }

    public void setDvorana(String dvorana) {
        this.dvorana = dvorana;
    }

    public void setIdRaspored(int idKolegij) {
        this.id_raspored = idKolegij;
    }

    public void setKolegij(String kolegij) {
        this.kolegij = kolegij;
    }

    public void setZgrada(String zgrada) {
        this.zgrada = zgrada;
    }

    public void setTip(String tip) { this.tip = tip; }
}
