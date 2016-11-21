package krasjkurs;

import javax.swing.*;
import java.io.*;
import static javax.swing.JOptionPane.*;

/**
 * Created by Audun on 21.11.2016.
 */
public class Klient {
    public static boolean skrivTilFil(Land land, String filnavn) {
        if (land != null) {
            FileOutputStream utstrom;
            ObjectOutputStream ut;
            try {
                utstrom = new FileOutputStream(filnavn);
                ut = new ObjectOutputStream(utstrom);
                ut.writeObject(land);
                ut.close(); // Burde egentlig gjøres i finally-blokk, men er slik i løsningsforslaget
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("fil ikke funnet!");
            } catch (Exception e) {
                System.out.println("noe gikk galt (skriving til fil)");
            }
        }
        return false;
    }

    public static Land lesFraFil(String filnavn) {
        try (FileInputStream innstrom = new FileInputStream(filnavn);
             ObjectInputStream inn = new ObjectInputStream(innstrom)) {
            return (Land) inn.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String[] muligheter = {"Nytt land", "Registrer by", "List ordførere", "List alle byer", "Avslutt"};
        final int NYTT_LAND = 0;
        final int REG_BY = 1;
        final int LIST_ORDFØRERE = 2;
        final int LIST_ALLE_BYER = 3;
        final int AVSLUTT = 4;
        int valg = showOptionDialog(null, "Velg operasjon", "Eksamen vår 2016", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
        Land land = null;
        final String filnavn = "landdata.ser";
        // les eventuelle data fra fil
        try {
            land = lesFraFil(filnavn);
            if (land == null) { // legger inn noen testdata, her kunne en valgt å ta inn brukerinput istd
                land = new Land("Norge", "Kong Harald V", 10);
                land.regBy(new By("Trondheim", 170000, 200000, "Otervik, Rita"));
                land.regBy(new By("Oslo", 650000, 700000, "Borgen, Marianne"));
                land.regBy(new By("Stjørdal", 23308, 23400, "Vigdenes, Ivar"));
                land.regBy(new By("Verdal", 14855, 15000, "Iversen, Bjørn"));
            }
        } catch (Exception e) {
            System.out.println("noe gikk galt ved lesing fra fil" + e.toString());
        }
        while (valg != AVSLUTT) {
            switch (valg) {
                case NYTT_LAND:
                    String navn = showInputDialog("Navn: ");
                    String statsoverhode = showInputDialog("Statsoverhode: ");
                    int antallByer = lesInt("Maks antallbyer: ");
                    int sikker = showConfirmDialog(null, "Sikker? - Du vil slette alle tidligere registrerte data");
                    if (sikker == YES_OPTION) land = new Land(navn, statsoverhode, antallByer);
                    else showMessageDialog(null, "Avbryter");
                    break;
                case REG_BY:
                    if (land != null) {
                        String bynavn = showInputDialog("Navn: ");
                        String ordfører = showInputDialog("Ordfører(Etternavn, Fornavn): ");
                        int antallInnbyggere = lesInt("Antall innbyggere: ");
                        int maksAntallInnbyggere = lesInt("Maks antall innbyggere: ");
                        try {
                            boolean ok = land.regBy(new By(bynavn, antallInnbyggere, maksAntallInnbyggere, ordfører));
                            if (ok) showMessageDialog(null, "By registrert");
                            else showMessageDialog(null, "By er registrert fra før/ ikke plass til by/ feil inndata");
                        } catch (Exception e) {
                            showMessageDialog(null, "Antall innbyggere kan ikke være høyere enn maksAntallInnbyggere" + e.toString());
                        }
                    } else showMessageDialog(null, "Du må registrere ett land først");
                    break;
                case LIST_ORDFØRERE:
                    if (land != null) {
                        String[] oListe = land.getOrdførere();
                        if (oListe != null) {
                            String res = "";
                            for (String s : oListe) {
                                res += s + "\n";
                            }
                            showMessageDialog(null, "Ordførere:\n----------------\n" + res);
                        }
                    } else showMessageDialog(null, "Du må registrere ett land først");
                    break;
                case LIST_ALLE_BYER:
                    if (land != null) {
                        showMessageDialog(null, land);
                    } else showMessageDialog(null, "Du må registrere ett land først");

                default:
                    break;
            }
            valg = showOptionDialog(null, "Velg operasjon", "Eksamen vår 2016", DEFAULT_OPTION, PLAIN_MESSAGE, null, muligheter, muligheter[0]);
        }

        // skriv til fil ved avslutt
        try {
            if (land != null) skrivTilFil(land, filnavn);
            System.out.println("Skriv til fil; " + land);
        } catch (Exception e) {
            System.out.println("Noe gikk galt ved skriving til fil. " + e.toString());
        }
    }

    private static int lesInt(String melding) {
        while(true) {
            try {
                return Integer.parseInt(showInputDialog(melding));
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Skriv inn et gyldig heltall!");
            }
        }
    }
}
