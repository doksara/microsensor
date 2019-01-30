package hr.foi.air.webservice.Weather;

public class Weather {
    private String dan;
    private double temperatura;
    private double jacina_svjetlosti;
    private double vlaznost_zraka;

    /**
     * Default constructor.
     * @param dan Current day of the week
     * @param temperatura Current temperature
     * @param jacina_svjetlosti Current light intensity
     * @param vlaznost_zraka current air humidity
     */
    public Weather (String dan, double temperatura, double jacina_svjetlosti, double vlaznost_zraka) {
        this.dan = dan;
        this.temperatura = temperatura;
        this.jacina_svjetlosti = jacina_svjetlosti;
        this.vlaznost_zraka = vlaznost_zraka;
    }

    /**
     * Gets measurement light intensity.
     * @return Measurement light intensity as {@link Double}
     */
    public double getJacina_svjetlosti() {
        return jacina_svjetlosti;
    }

    /**
     * Gets measurement temperature.
     * @return Measurement temperature as {@link Double}
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * Gets measurement air humidity.
     * @return Measurement air humidity as {@link }
     * @return Measurement air humidity as {@link Double}
     */
    public double getVlaznost_zraka() {
        return vlaznost_zraka;
    }

    /**
     * Gets measurement day of the week.
     * @return Measurement day of the week
     */
    public String getDan() {
        return dan;
    }

    /**
     * Sets measurement day of thw week.
     * @param dan Measurement day of the week
     */
    public void setDan(String dan) {
        this.dan = dan;
    }

    /**
     * Sets measurement light intensity
     * @param jacina_svjetlosti Measurement light intensity
     */
    public void setJacina_svjetlosti(double jacina_svjetlosti) {
        this.jacina_svjetlosti = jacina_svjetlosti;
    }

    /**
     * Sets measurement temperature.
     * @param temperatura Measurement temperature
     */
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Sets measurement air humidity.
     * @param vlaznost_zraka Measurement air humidity
     */
    public void setVlaznost_zraka(double vlaznost_zraka) {
        this.vlaznost_zraka = vlaznost_zraka;
    }
}
