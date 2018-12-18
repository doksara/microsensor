package hr.foi.air.webservice.Attendance;

public class Lecture {
    String zgrada;
    String dvorana;
    int id_kolegij;
    String kolegij;

    public int getIdKolegij() {
        return id_kolegij;
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

    public void setDvorana(String dvorana) {
        this.dvorana = dvorana;
    }

    public void setIdKolegij(int idKolegij) {
        this.id_kolegij = idKolegij;
    }

    public void setKolegij(String kolegij) {
        this.kolegij = kolegij;
    }

    public void setZgrada(String zgrada) {
        this.zgrada = zgrada;
    }
}
