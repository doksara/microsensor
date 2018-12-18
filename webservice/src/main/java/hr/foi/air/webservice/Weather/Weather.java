package hr.foi.air.webservice.Weather;

public class Weather {
    String dan;
    double temperatura;
    double jacina_svjetlosti;
    double vlaznost_zraka;

    public Weather (String dan, double temperatura, double jacina_svjetlosti, double vlaznost_zraka) {
        this.dan = dan;
        this.temperatura = temperatura;
        this.jacina_svjetlosti = jacina_svjetlosti;
        this.vlaznost_zraka = vlaznost_zraka;
    }

    public double getJacina_svjetlosti() {
        return jacina_svjetlosti;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getVlaznost_zraka() {
        return vlaznost_zraka;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public void setJacina_svjetlosti(double jacina_svjetlosti) {
        this.jacina_svjetlosti = jacina_svjetlosti;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setVlaznost_zraka(double vlaznost_zraka) {
        this.vlaznost_zraka = vlaznost_zraka;
    }
}
