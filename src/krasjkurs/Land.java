package krasjkurs;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Audun on 21.11.2016.
 */
public class Land implements Serializable {
    private String navn;
    private By[] byer;
    private String statsoverhode;
    private int maksAntallByer;
    private int antallByer;

    public Land(String navn, String statsoverhode, int maksAntallByer) {
        this.navn = navn;
        this.statsoverhode = statsoverhode;
        this.maksAntallByer = maksAntallByer;
        this.byer = new By[maksAntallByer];
    }

    @Override
    public String toString() {
        String output = "navn: " + navn + ", statsoverhode: "
                + statsoverhode + ", byer: \n";
        for(int i = 0; i < maksAntallByer; i++) {
            if(byer[i] != null) {
                output += byer[i].toString() + "\n";
            }
        }
        return output;
    }

    public boolean regBy(By by) {
        if(antallByer >= maksAntallByer) {
            return false;
        }

        if(by == null) {
            return false;
        }

        if(finnes(by)) {
            return false;
        }

        By kopi = new By(by.getNavn(), by.getInnbyggerTall(), by.getMaksInnbyggere(), by.getOrdførerNavn());
        byer[antallByer] = kopi;
        antallByer++;
        // antallByer += 1;
        // antallByer = antallByer + 1;
        return true;
    }

    public boolean finnes(By by) {
        for(int i = 0; i < antallByer; i++) {
            if(byer[i] == null) continue;

            if(byer[i].equals(by)) {
                return true;
            }
        }
        return false;
    }

    public int totaltAntallInnbyggere() {
        int sum = 0;
        for (int i = 0; i < antallByer; i++) {
            if(byer[i] == null) continue;
            sum += byer[i].getInnbyggerTall();
        }

        return sum;
    }

    public By[] ledigPlass(int antallPlasser) {
        By[] resultat = new By[antallByer];
        int teller = 0;
        for (int i = 0; i < antallByer; i++) {
            if(byer[i] == null) continue;

            if(byer[i].harPlassTilFler(antallPlasser)) {
                resultat[teller] =
                new By(byer[i].getNavn(), byer[i].getInnbyggerTall(),
                        byer[i].getMaksInnbyggere(),
                        byer[i].getOrdførerNavn());

                teller++;
            }
        }

        By[] temp = new By[teller];
        for (int i = 0; i < teller; i++) {
            temp[i] = resultat[i];
        }
        return temp;
    }

    public String[] getOrdførere() {
        String[] ordførere = new String[antallByer];
        for (int i = 0; i < antallByer; i++) {
            if(byer[i] == null) continue;

            ordførere[i] = byer[i].getOrdførerNavn();
        }
        Arrays.sort(ordførere);

        return ordførere;
    }
}
