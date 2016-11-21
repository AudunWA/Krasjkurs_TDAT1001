package krasjkurs;

import java.io.Serializable;

public class By implements Serializable {
    private String navn;
    private int innbyggerTall;
    private int maksInnbyggere;
    private String ordførerNavn;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getInnbyggerTall() {
        return innbyggerTall;
    }

    public void setInnbyggerTall(int innbyggerTall) {
        if(innbyggerTall > maksInnbyggere) {
            throw new IllegalArgumentException("innbyggerTall kan ikke være høyere enn maksInnbyggere");
        }
        this.innbyggerTall = innbyggerTall;
    }

    public int getMaksInnbyggere() {
        return maksInnbyggere;
    }

    public void setMaksInnbyggere(int maksInnbyggere) {
        if(innbyggerTall > maksInnbyggere) {
            throw new IllegalArgumentException("kan ikke ha færre maks-innbyggere enn det allerde er i innbyggerTall");
        }
        this.maksInnbyggere = maksInnbyggere;
    }

    public String getOrdførerNavn() {
        return ordførerNavn;
    }

    public void setOrdførerNavn(String ordførerNavn) {
        this.ordførerNavn = ordførerNavn;
    }

    public By(String navn, int innbyggerTall, int maksInnbyggere, String ordførerNavn) {
        if(innbyggerTall > maksInnbyggere) {
            throw new IllegalArgumentException("innbyggerTall kan ikke være høyere enn maksInnbyggere");
        }
        this.navn = navn;
        this.innbyggerTall = innbyggerTall;
        this.maksInnbyggere = maksInnbyggere;
        this.ordførerNavn = ordførerNavn;
    }

    public By() {

    }

    @Override // Ikke nødvendig
    public String toString() {
        return "navn: " + navn + ", innbyggerTall: "
                + innbyggerTall + ", maksInnbyggere: " + maksInnbyggere
                + ", ordførerNavn: " + ordførerNavn;
    }

    @Override // Ikke nødvendig
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(!(obj instanceof By)) return false;
        By objBy = (By)obj;
        // this.navn == objBy.navn
        return this.navn.equals(objBy.navn);
    }

    public boolean harPlassTilFler(int antall) {
        return maksInnbyggere - innbyggerTall > antall;
    }
}
