package hr.foi.air.webservice.Attendance;

public class Lecture {
    private String zgrada;
    private String dvorana;
    private int id_raspored;
    private String kolegij;
    private String tip;

    /**
     * Gets lecture ID.
     * @return lecture ID as {@link Integer}
     */
    public int getIdRaspored() {
        return id_raspored;
    }

    /**
     * Gets hall ID.
     * @return hall ID as {@link String}
     */
    public String getDvorana() {
        return dvorana;
    }

    /**
     * Gets class ID.
     * @return class ID as {@link String}
     */
    public String getKolegij() {
        return kolegij;
    }

    /**
     * Gets building ID.
     * @return building ID as {@link String}
     */
    public String getZgrada() {
        return zgrada;
    }

    /**
     * Gets lecture type.
     * @return lecture type as {@link String}
     */
    public String getTip() { return tip; }

    /**
     * Sets hall ID.
     * @param dvorana hall ID
     */
    public void setDvorana(String dvorana) {
        this.dvorana = dvorana;
    }

    /**
     * Sets lecture ID.
     * @param idKolegij lecture ID
     */
    public void setIdRaspored(int idKolegij) {
        this.id_raspored = idKolegij;
    }

    /**
     * Sets class ID.
     * @param kolegij class ID
     */
    public void setKolegij(String kolegij) {
        this.kolegij = kolegij;
    }

    /**
     * Sets building ID.
     * @param zgrada building ID
     */
    public void setZgrada(String zgrada) {
        this.zgrada = zgrada;
    }

    /**
     * Sets lecture type.
     * @param tip lecture type
     */
    public void setTip(String tip) { this.tip = tip; }
}
