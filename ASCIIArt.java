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
        // Esitellään, luodaan ja alustetaan merkkitaulu.
        char[] merkkitaulu = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};

        // Kutsutaan metodia, joka tulostaa tervehdyksen.
        tulostaTervehdys();

        char[][] merkit;

        if (args.length > 0) {
            String tiedostonNimi = args[0];
            merkit = luoTaulukko(tiedostonNimi);
            if (args.length != 1 || merkit == null) {
                System.out.println("Invalid command-line argument!");
            } else {
                boolean jatketaan = true;
                do {
                    tulostaKomennot();
                    // Luetaan käyttäjältä komento.
                    String komento = In.readString();
                    if (komento.equals("printa")) {
                        tulosta(merkit);
                    } else if (komento.equals("printi")) {
                        System.out.println(merkitLukuina(merkkitaulu, merkit));
                    } else if (komento.equals("quit")) {
                        tulostaHeipat();
                        jatketaan = false;
                    }
                } while (jatketaan);
            }
        } else {
            System.out.println("Invalid command-line argument!");
            // Kutsutaan metodia, joka tulostaa heipat.
            tulostaHeipat();
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

    // Metodi tulostaa komentorivin.
    public static void tulostaKomennot() {
        System.out.println("printa/printi/info/filter [n]/reset/quit?");
    }

    // Metodi tulostaa heipat.
    public static void tulostaHeipat() {
        System.out.println("Bye, see you soon.");
    }

    // Metodi tulostaa parametrina saamansa kaksiulotteisen taulukon.
    public static void tulosta(char[][] merkit){

        // Tulostetaan vain jos taulukolle on varattu muistia.
        if (merkit != null) {
            // Suoritetaan ulommaista silmukkaa niin kauan kuin saadaan kaikki rivit tulostettua.
            for (int i = 0; i < merkit.length; i++){
                // Suoritetaan sisimmäistä silmukkaa niin kauan kuin saadaan sarakkeiden sisällöt
                // tulostettua eli niin kauan kuin sarakkeen arvo on pienempi kuin rivin pituus.
                for (int j = 0; j < merkit[i].length; j++){
                    System.out.print(merkit[i][j]);
                }
                // Sarakkeiden tulostamisen jälkeen tulostetaan aina rivinvaihto että päästään
                // seuraavalle riville.
                System.out.println();
            }
        // Jos taulukolle ei ole varattu muistia kerrotaan siitä käyttäjälle.
        } else {
            System.out.println("Taulukolle ei ole varattu muistia.");
        }
    }

    public static String merkitLukuina(char[] merkkitaulukko, char[][] merkit) {
        // Verrataan merkit-taulukossa olevaa merkkiä merkkitaulukossa oleviin merkkeihin
        // ja jos merkit-taulukossa oleva merkki vastaa jotain taulukossa olevaa merkkiä
        // otetaan sen indeksi ylös luvut-muuttujaan.
        if (merkit != null && merkkitaulukko != null) {
            String luvut = "";
            for (int i = 0; i < merkit.length; i++) {
                for (int j = 0; j < merkit[i].length; j++) {
                    for (int k = 0; k < merkkitaulukko.length; k++) {
                        if (merkit[i][j] == merkkitaulukko[k]) {
                            if (k >= 0 && k <= 9) {
                                luvut = luvut + " " + k;
                            } else {
                                luvut = luvut + k;
                            }
                            if (j < merkit[i].length - 1) {
                                luvut = luvut + " ";
                            }
                        }
                    }
                }
                if (i < merkit.length - 1) {
                    luvut = luvut + "\n";
                }
            }
            return luvut;
        } else {
            return "E";
        }
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
