/*
 * Lausekielinen ohjelmointi II, harjoitustyö 2.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 29.11.2016.
 *
 *
 */
import java.io.*;
import java.util.*;

public class ASCIIArt {
    public static void main(String[] args) {

        // Kutsutaan metodia, joka tulostaa tervehdyksen.
        tulostaTervehdys();

        if (args.length > 0) {
            String tiedostonNimi = args[0];
            char[][] merkit = luoTaulukko(tiedostonNimi);
            if (args.length != 1 || merkit == null) {
                System.out.println("Invalid command-line argument!");
            }
        } else {
            System.out.println("Invalid command-line argument!");
        }

    }

    // Metodi tulostaa tervehdyksen.
    public static void tulostaTervehdys() {
        // Esitellään ja alustetaan vakiot.
        final char YLAREUNA = '-';
        final char ALAREUNA = '-';
        final String SIVUT = "|";
        final String TEKSTI = "A S C I I A r t";
        final int LEVEYS = 19;

        // Tulostetaan yläreuna.
        for (int i = 0; i < LEVEYS; i++) {
            System.out.print(YLAREUNA);
        }
        // Rivinvaihto.
        System.out.println();

        // Tulostetaan tekstirivi.
        System.out.println(SIVUT + ' ' + TEKSTI + ' ' + SIVUT);

        // Tulostetaan alareuna.
        for (int i = 0; i < LEVEYS; i++) {
            System.out.print(ALAREUNA);
        }
        // Rivinvaihto.
        System.out.println();
    }


    // Metodi saa parametrina tiedoston nimen ja lukee tiedoston merkit kaksiulotteiseen taulukkoon.
    public static char[][] luoTaulukko(String tiedostonNimi) {
        // Kutsutaan metodia, joka saa parametrina tiedoston nimen ja laskee tiedostossa olevien rivien määrän.
        int rivienLkm = laskeRivit(tiedostonNimi);

        // Jos tiedosto on olemassa...
        if (rivienLkm >= 0) {
            // Luodaan taulukko, joka sisältää rivit. Taulukon koko on rivien määrä.
            char[][] rivit = new char[rivienLkm][];

            try {
                // Luodaan tiedosto-olio.
                File tiedosto = new File(tiedostonNimi);
                // Luodaan lukija.
                Scanner lukija = new Scanner(tiedosto);
                // Esitellään ja alustetaan muuttuja, jolla pidetään kirjaa indekseistä.
                int indeksi = 0;
                // Luetaan rivejä niin kauan kuin niitä on saatavilla tiedostosta.
                while (lukija.hasNextLine()) {
                    // Luetaan rivi ja sijoitetaan se muuttujaan.
                    String rivi = lukija.nextLine();
                    // Pilkotaan luettu rivi taulukkoon, joka sisältää rivin merkit.
                    char[] merkit = pilkoMerkkijono(rivi);
                    // Sijoitetaan merkkitaulukko rivitaulukon indeksiin.
                    rivit[indeksi] = merkit;
                    // Kasvatetaan indeksiä.
                    indeksi++;
                }
                // Suljetaan lukija.
                lukija.close();
                // Paluuarvo on null, jos tapahtuu poikkeus.
            } catch (FileNotFoundException e) {
                return null;
            } catch (Exception e) {
                return null;
            }
            // Palautetaan kaksiulotteinen rivitaulukko.
            return rivit;
        // Jos tiedostoa ei ole olemassa, palautetaan null.
        } else {
            return null;
        }
    }

    // Metodi laskee rivien lukumäärän tiedostossa, jonka nimen se saa parametrina.
    public static int laskeRivit(String tiedostonNimi) {
        // Rivien lukumäärä on vielä nolla.
        int rivienLkm = 0;
        try {
            // Luodaan tiedosto-olio.
            File tiedosto = new File(tiedostonNimi);
            // Luodaan lukija.
            Scanner lukija = new Scanner(tiedosto);
            // Luetaan rivejä niin kauan kuin niitä on saatavilla tiedostosta.
            while (lukija.hasNextLine()) {
                // Kutsutaan metodia ja pidetään kirjaa kutsujen määrästä eli rivien määrästä.
                lukija.nextLine();
                rivienLkm++;
            }
        // Suljetaan lukija.
        lukija.close();
        // Paluuarvo on negatiivinen, jos tapahtuu poikkeus.
        } catch (FileNotFoundException e) {
            return -1;
        } catch (Exception e) {
            return -1;
        }
        // Palautetaan lukumäärä.
        return rivienLkm;
    }

    // Metodi saa parametrina merkkijonon, jonka se pilkkoon taulukkoon.
    public static char[] pilkoMerkkijono(String rivi) {
        // Esitellään ja alustetaan yksiulotteinen taulukko, jonka koko on merkkijonon eli rivin pituus.
        char[] merkit = new char[rivi.length()];
        // Sijoitetaan taulukon indeksiin merkkijonon merkit yksitellen, kunnes merkkijono on käyty läpi.
        for (int i = 0; i < rivi.length(); i++) {
            merkit[i] = rivi.charAt(i);
        }
        // Palautetaan merkit-taulukko.
        return merkit;
    }
}
