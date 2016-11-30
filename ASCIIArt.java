/*
 * Lausekielinen ohjelmointi II, harjoitustyö 2.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 30.11.2016.
 * Käytetty aika: 5 +
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
                    } else if (komento.equals("info")){
                        System.out.println(infoMjonona(merkkitaulu, merkit));
                    } else if (komento.equals("quit")) {
                        tulostaHeipat();
                        jatketaan = false;
                    } else {
                        System.out.println("Invalid command!");
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
                    char merkki = merkit[i][j];
                    int luku = muutaMerkkiNumeroksi(merkkitaulukko, merkki);
                    String lukuMjonona = muutaLukuMjonoksi(luku);
                    luvut = luvut + lukuMjonona;
                    if (j < merkit[i].length - 1) {
                        luvut = luvut + " ";
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

    // Metodi saa parametreina merkkitaulukon ja merkin ja palauttaa merkkiä vastaavan indeksiarvon.
    public static int muutaMerkkiNumeroksi(char[] merkkitaulukko, char merkki) {
        boolean merkkiLoytyi = false;
        // Esitellään ja alustetaan luku luvulla, jota ei löydy merkkitaulukon indekseistä.
        int luku = -1;
        // Käydään läpi merkkitaulukkoa ja verrataan siihen merkkiä niin kauan kuin merkkitaulukkoa on jäljellä
        // tai merkki on löytynyt.
        for (int k = 0; k < merkkitaulukko.length && !merkkiLoytyi; k++) {
            if (merkki == merkkitaulukko[k]) {
                // Sijoitetaan muuttujaan merkkiä vastaava indeksiarvo.
                luku = k;
                // Käännetään lippu löytymisen merkiksi.
                merkkiLoytyi = true;
            }
        }
        // Paluuarvona saadaan luku.
        return luku;
    }

    // Metodi saa parametrina luvun, jonka se muuttaa merkkijonoksi.
    public static String muutaLukuMjonoksi(int luku) {
        // Merkkijono on aluksi tyhjä.
        String lukuMjonona = "";
        // Jos luku on välillä 0 - 9, lisätään luvun eteen välilyönti.
        if (luku >= 0 && luku <= 9) {
            lukuMjonona = " " + luku;
        // Muiden lukujen kohdalla tyyppimuunnetaan luku merkkijonoksi lisäämällä
        // se tyhjään merkkijonoon.
        } else {
            lukuMjonona = "" + luku;
        }
        // Paluuarvona saadaan luku merkkijonona.
        return lukuMjonona;
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

    public static int taulukonKorkeus(char[][] merkit) {
        int korkeus = merkit.length;
        return korkeus;
    }

    // Metodi palauttaa parametrina saamansa taulukon leveyden.
    // Oletetaan, että kaikki rivit  ovat yhtä leveitä.
    public static int taulukonLeveys(char[][] merkit) {
        int leveys = merkit[0].length;
        return leveys;
    }

    public static int merkinLkmTaulukossa(char merkki, char[][] merkit) {
        int lkm = 0;
        // Käydään merkit-taulukko läpi ja pidetään kirjaa merkkien esiintymistä siinä.
        for (int i = 0; i < merkit.length; i++) {
            for (int j = 0; j < merkit[i].length; j++) {
                if (merkit[i][j] == merkki) {
                    lkm++;
                }
            }
        }
        return lkm;
    }

    public static String infoMjonona(char[] merkkitaulukko, char[][] merkit) {
        String info = "";
        info = info + taulukonKorkeus(merkit) + " x " + taulukonLeveys(merkit) + "\n";
        for (int i = 0; i < merkkitaulukko.length; i++) {
            char merkki = merkkitaulukko[i];
            int merkinLkm = merkinLkmTaulukossa(merkki, merkit);
            info = info + merkki + " " + merkinLkm;
            if (i < merkkitaulukko.length - 1) {
                info = info + "\n";
            }
        }
        return info;
    }
}
