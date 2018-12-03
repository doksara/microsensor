package hr.foi.air.webservice.Weather;

public class Weather {
    String dan;
    int temperatura;
    int jacina_svjetlosti;
    float vlaznost_zraka;

    public int getJacina_svjetlosti() {
        return jacina_svjetlosti;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public float getVlaznost_zraka() {
        return vlaznost_zraka;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public void setJacina_svjetlosti(int jacina_svjetlosti) {
        this.jacina_svjetlosti = jacina_svjetlosti;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public void setVlaznost_zraka(int vlaznost_zraka) {
        this.vlaznost_zraka = vlaznost_zraka;
    }
}
