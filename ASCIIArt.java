/*
 * Lausekielinen ohjelmointi II, harjoitustyö 2.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 10.12.2016.
 *
 * Ohjelma lataa tiedostosta ASCII-grafiikkaa ja tarjoaa käyttäjälle mahdollisuuden
 * tulostaa grafiikan näytölle merkkeinä tai lukuina, tulostaa taulukkoon liittyvää informaatiota,
 * filtteröidä kuvaa halutunkokoisella mediaanisuotimella ja ladata uudestaan alkuperäisen kuvan.
 */


import java.io.*;
import java.util.*;

public class ASCIIArt {
    public static void main(String[] args) {
        final String VIRHEKOMENTO = "Invalid command-line argument!";

        // Esitellään, luodaan ja alustetaan merkkitaulu.
        char[] merkkitaulu = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};

        // Kutsutaan metodia, joka tulostaa tervehdyksen.
        tulostaTervehdys();

        // Esitellään muuttujat.
        char[][] merkit;
        String tiedostonNimi;

        // Jos komentoriviparametreja on enemmän kuin nolla kpl, sijoitetaan tiedostonNimi-muuttujaan
        // komentoriviparametrina saatu tiedoston nimi.
        if (args.length > 0) {
            tiedostonNimi = args[0];
            // Kutsutaan metodia, joka saa parametrina tiedoston nimen ja lataa tiedostosta taulukon sekä
            // sijoittaa sen merkit-muuttujaan.
            merkit = lataaTaulukko(tiedostonNimi);
            // Jos komentoriviparametreja on useampia kuin kaksi tai jos tiedostoa ei löydy,
            // tulostetaan virheilmoitus sekä kutsutaan metodia, joka tulostaa jäähyväiset.
            if (args.length != 1 || merkit == null) {
                System.out.println(VIRHEKOMENTO);
                tulostaHeipat();

            // Jos komentoriviparametrit ja tiedosto ovat kunnossa, jatketaan ohjelman suorittamista.
            } else {
                // Esitellään ja alustetaan muuttuja.
                boolean jatketaan = true;
                // Niin kauan kuin jatketaan, muuttujan arvo on tosi, jatketaan komentojen pyytämistä.
                do {
                    // Kutsutaan metodia, joka tulostaa käytettävissä olevat komennot.
                    tulostaKomennot();
                    // Luetaan käyttäjältä komento.
                    String komento = In.readString();

                    // Jos komento on "printa", kutsutaan metodia,
                    // joka tulostaa parametrinaan saamansa merkit-taulukon.
                    if (komento.equals("printa")) {
                        tulosta(merkit);
                    // Jos komento on "printi", tulostetaan merkit-taulun sisältö lukuina.
                    } else if (komento.equals("printi")) {
                        System.out.println(merkitLukuina(merkkitaulu, merkit));
                    // Jos komento on "info", tulostetaan tietoja merkit-taulukosta.
                    } else if (komento.equals("info")){
                        System.out.println(infoMjonona(merkkitaulu, merkit));
                    // Jos komento alkaa sanalla "filter"...
                    } else if (komento.startsWith("filter")) {
                        // ...pilkotaan komennon osat osat-taulukkoon välilyönnin kohdalta.
                        String[] osat = komento.split("[ ]");
                        // Esitellään filtterin koko-muuttuja ja alustetaan se kolmella, joka
                        // on pienin filtterin koko ja koko, jota käytetään, jos ei käyttäjä määrittele
                        // muuta kokoa kutsussaan.
                        int koko = 3;
                        // Jos osat-taulukossa on enemmän kuin yksi alkiota eli komennossa on määritelty
                        // filtterin koko, sijoitetaan koko-muuttujaan merkkijonosta muutettu luku
                        // taulukon indeksistä yksi. Muuten filtterin koko on siis kolme.
                        if (osat.length > 1) {
                            koko = Integer.parseInt(osat[1]);
                        } else {
                            koko = 3;
                        }
                        // Jos koko on parillinen, tulostetaan virheilmoitus.
                        if (koko % 2 == 0) {
                            System.out.println("Invalid command!");
                        // Jos koko on pariton, kutsutaan metodia, joka filtteröi parametrina saamansa
                        // taulukon ja palauttaa samaan taulukkomuuttujaan filtteröidyn taulukon.
                        // Taulukko tulostetaan tulostusmetodilla.
                        } else {
                            merkit = filtteroi(merkkitaulu, merkit, koko);
                        }
                    // Jos komento on "reset", kutsutaan metodia, joka lataa uudestaan alkuperäisen
                    // taulukon tiedostosta merkit-muuttujaan.
                    } else if (komento.equals("reset")) {
                        merkit = lataaTaulukko(tiedostonNimi);
                    // Jos komento on "quit", tulostetaan heipat metodin avulla ja käännetään
                    // lippumuuttuja, jotta lopetetaan komentojen pyytäminen.
                    } else if (komento.equals("quit")) {
                        tulostaHeipat();
                        jatketaan = false;
                    // Jos komento ei ole validi, tulostetaan virheilmoitus.
                    } else {
                        System.out.println("Invalid command!");
                    }
                } while (jatketaan);
            }
        } else {
            System.out.println(VIRHEKOMENTO);
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

    // Metodi saa parametreina merkkitaulun sekä merkit-taulukon ja muuttaa merkit-taulukon merkit
    // luvuiksi sekä kerää luvut merkkijonoksi, joka saadaan metodista paluuarvona.
    public static String merkitLukuina(char[] merkkitaulu, char[][] merkit) {

        // Tarkistetaan, että taulukoille on varattu muistia.
        if (merkit != null && merkkitaulu != null) {
            // Esitellään ja alustetaan luvut-merkkjono.
            String luvut = "";
            // Verrataan merkit-taulukossa olevaa merkkiä merkkitaulussa oleviin merkkeihin
            // ja jos merkit-taulukossa oleva merkki vastaa jotain merkkitaulussa olevaa merkkiä
            // otetaan sen indeksi ylös merkkitaulusta luvut-merkkijonoon.
            for (int i = 0; i < merkit.length; i++) {
                for (int j = 0; j < merkit[i].length; j++) {
                    // Sijoitetaan merkki-muuttujaan arvo riviltä i, sarakkeesta j.
                    char merkki = merkit[i][j];
                    // Kutsutaan metodia, joka muuttaa merkin numeroksi ja sijoitetaan paluuarvo
                    // luku-muuttujaan.
                    int luku = muutaMerkkiNumeroksi(merkkitaulu, merkki);
                    // Kutsutaan metodia, joka muuttaa luvun merkkijonoksi.
                    String lukuMjonona = muutaLukuMjonoksi(luku);
                    // Lisätään luvut-merkkijonoon merkkijonoksi muutettu luku.
                    luvut = luvut + lukuMjonona;
                    // Lisätään lukujen väliin välilyönti.
                    if (j < merkit[i].length - 1) {
                        luvut = luvut + " ";
                    }
                }
                // Lisätään rivien perään rivinvaihto, paitsi viimeisen rivin perään.
                if (i < merkit.length - 1) {
                    luvut = luvut + "\n";
                }
            }
            // Paluuarvona saadaan luvut-merkkijono.
            return luvut;
        } else {
            return "E";
        }
    }

    // Metodi saa parametreina merkkitaulukon ja merkin ja palauttaa merkkiä vastaavan indeksiarvon.
    public static int muutaMerkkiNumeroksi(char[] merkkitaulukko, char merkki) {
        // Esitellään ja alustetaan muuttuja.
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
    public static char[][] lataaTaulukko(String tiedostonNimi) {
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

    // Metodi saa parametrina merkkijonon, jonka se pilkkoo taulukkoon.
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



    // Info

    // Metodi saa parametrina kaksiulotteisen taulukon ja palauttaa sen korkeuden.
    public static int taulukonKorkeus(char[][] merkit) {
        int korkeus = merkit.length;
        return korkeus;
    }

    // Metodi palauttaa parametrina saamansa taulukon leveyden.
    // Oletetaan, että kaikki rivit ovat yhtä leveitä.
    public static int taulukonLeveys(char[][] merkit) {
        int leveys = merkit[0].length;
        return leveys;
    }

    // Metodi saa parametreina merkit ja kaksiulotteisen taulukon ja laskee merkkien
    // esiintymät kyseisessä taulukossa.
    public static int merkinLkmTaulukossa(char merkki, char[][] merkit) {
        // Merkin esiintymien lkm on nolla.
        int lkm = 0;
        // Käydään merkit-taulukko läpi ja pidetään kirjaa merkin esiintymistä siinä.
        for (int i = 0; i < merkit.length; i++) {
            for (int j = 0; j < merkit[i].length; j++) {
                // Jos paikassa i,j on tutkittava merkki, kasvatetaan lkm-laskuria.
                if (merkit[i][j] == merkki) {
                    lkm++;
                }
            }
        }
        // Palautetaan merkin esiintymistä kirjaa pitävä kokonaisluku.
        return lkm;
    }

    // Metodi saa parametreina yksi- ja kaksiulotteiset taulukot ja kerää tiedot merkkijonoon,
    // jonka palauttaa paluuarvona.
    public static String infoMjonona(char[] merkkitaulu, char[][] merkit) {
        // Esitellään ja alustetaan merkkijonomuuttuja.
        String info = "";

        // Tarkistetaan että taulukoille on varattu muistia.
        if (merkit != null && merkkitaulu != null) {
            // Sijoitetaan muuttujaan luvut, jotka saadaan, kun kutsutaan metodeja, jotka selvittävät
            // taulukon korkeuden ja leveyden. Lisätään myös rivinvaihto.
            info = taulukonKorkeus(merkit) + " x " + taulukonLeveys(merkit) + "\n";
            // Käydään merkkitaulu läpi.
            for (int i = 0; i < merkkitaulu.length; i++) {
                // Sijoitetaan merkki-muuttujaan merkkitaulun käsiteltävänä oleva merkki.
                char merkki = merkkitaulu[i];
                // Kutsutaan metodia, joka saa parametreina merkkitaulusta saadun merkin sekä merkit-taulukon
                // ja palauttaa merkin esiintymien lukumäärän merkit-taulukossa.
                int merkinLkm = merkinLkmTaulukossa(merkki, merkit);
                // Lisätään info-merkkijonoon merkki ja merkkien lukumäärä taulukossa.
                info = info + merkki + " " + merkinLkm;
                // Lisätään myös rivinvaihto, paitsi viimeisen rivin jälkeen.
                if (i < merkkitaulu.length - 1) {
                    info = info + "\n";
                }
            }
            // Paluuarvona saadaan info-merkkijono.
            return info;
        } else {
            return "E";
        }
    }


    // Filtteröinti.

    // Metodi saa parametreina merkkitaulun, merkit-taulukon, filtterin koon, filtteröitävän rivin ja sarakkeen ja
    // antaa paluuarvona viitteen yksiulotteiseen taulukkoon, jossa on filtteröitävän alueen luvut.
    public static int[] muutaFiltteriLuvuiksi(char[] merkkitaulu, char[][] merkit, int koko, int rivi, int sarake) {

        // Tarkistetaan että taulukoille on varattu muistia.
        if (merkkitaulu != null && merkit != null) {
            // Esitellään muuttujat ja alustetaan niihin filtterin sivujen pituus eli "koko".
            int rivienLkm = koko;
            int sarakkeidenLkm = koko;

            // Alustetaan muuttujaan filtterin lukujen kokonaismäärä.
            int filtterinKoko = rivienLkm * sarakkeidenLkm;

            // Esitellään ja alustetaan muuttuja filtterin kokoiseksi.
            int[] luvut = new int[filtterinKoko];

            // Esitellään ja alustetaan muuttuja, jolla siirrytään indeksejä eteenpäin.
            int lukujenInd = 0;

            // Filtterin ensimmäinen rivi löydetään siten, että vähennetään filtteröitävänä olevasta rivistä
            // puolet filtterin sivun koosta.
            int filtterinYlarivi = rivi - koko / 2;
            // Filtterin ensimmäinen sarake löydetään siten, että vähennetään filtteröitävänä olevasta sarakkeesta
            // puolet filtterin sivun koosta.
            int filtterinEkaSarake = sarake - koko / 2;
            // Käydään läpi filtterinkokoinen alue kaksiulotteisesta merkit-taulukosta
            // ja muutetaan merkki sitä vastaavaksi luvuksi.
            // Silmukkaa suoritetaan filtterin sivunmittainen alue kerrallaan.
            for (int i = filtterinYlarivi; i < filtterinYlarivi + koko; i++) {
                for (int j = filtterinEkaSarake; j < filtterinEkaSarake + koko; j++) {
                    // Luetaan muuttujaan merkki taulukosta.
                    char merkki = merkit[i][j];
                    // Kutsutaan metodia, joka muuttaa merkin indeksiä vastaavaksi luvuksi.
                    int merkkiLukuna = muutaMerkkiNumeroksi(merkkitaulu, merkki);
                    // Sijoitetaan uuteen luvut-taulukkoon merkistä muutettu luku.
                    luvut[lukujenInd] = merkkiLukuna;
                    // Kasvatetaan indeksiä.
                    lukujenInd++;
                }
            }
            // Paluuarvona saadaan viite luvut-taulukkoon.
            return luvut;
        } else {
            return null;
        }
    }

    // Metodi saa parametrina merkkitaulun ja luvun ja muuttaa luvun merkkitaulussa olevaksi merkiksi,
    // joka saadaan metodista paluuarvona.
    public static char muutaLukuMerkiksi(char[] merkkitaulu, int luku) {
        // Alustetaan merkki merkillä, jota ei löydy merkkitaulusta.
        char merkki = 'K';
        boolean merkkiLoytyi = false;
        // Käydään läpi merkkitaulukkoa, kunnes löydetään lukua vastaava merkki.
        for (int i = 0; i < merkkitaulu.length && !merkkiLoytyi; i++) {
            // Jos taulukosta löytyy lukua vastaava indeksi, sijoitetaan merkki-muuttujaan merkki
            // taulukosta ja käännetään lippu merkin löytymisen merkiksi.
            if (i == luku) {
                merkki = merkkitaulu[i];
                merkkiLoytyi = true;
            }
        }
        // Paluuarvona saadaan lukua vastaava merkki.
        return merkki;
    }

    // Metodi lajittelee parametrina saamansa taulukon arvot valintalajittelun avulla
    // ja antaa paluuarvona totuusarvon sen mukaan, onko taulukolle varattu muistia vai ei.
    public static boolean lajittele(int[] arvot) {

        // Tarkistetaan että on varattu muistia.
        if (arvot != null) {
            // Suoritetaan silmukkaa niin kauan kuin saadaan taulukko käytyä läpi.
            for (int i = 0; i < arvot.length; i++) {
                // Esitellään ja alustetaan muuttuja sijoittamalla sinne indeksissä oleva arvo.
                int pieninLuku = arvot[i];
                // Esitellään ja alustetaan muuttuja sijoittamalla sinne indeksi.
                int pienimmanIndeksi = i;

                // Alustetaan indeksilaskuri samalla arvolla kuin ulompi silmukka ja suoritetaan
                // silmukkaa niin kauan kuin päästään taulukko loppuun.
                for (int j = i; j < arvot.length; j++) {
                    // Jos indeksissä oleva arvo on pienempi kuin sen hetkinen pienin luku,
                    // muuttuja saa uudeksi arvoksi kyseisessä indeksissä olevan arvon.
                    if (arvot[j] < pieninLuku) {
                        pieninLuku = arvot[j];
                        // Kirjataan ylös myös indeksi, josta pienin arvo löytyi.
                        pienimmanIndeksi = j;
                    }
                }

                // Esitellään ja alustetaan muuttuja indeksistä i löytyvällä arvolla.
                int ekaLajittelematon = arvot[i];
                // Vaihdetaan lukujen paikkaa sijoittamalla pienin luku taulukon indeksiin i
                // ja sijoittamalla arvo ekasta lajittelemattomasta pienimmän indeksiin.
                arvot[i] = pieninLuku;
                arvot[pienimmanIndeksi] = ekaLajittelematon;
            }
            // Paluuarvona saadaan tosi, jos muistia on varattu, muuten epätosi.
            return true;
        } else {
            return false;
        }
    }

    // Metodi etsii mediaanin taulukosta, jonka se saa parametrina ja palauttaa double-tyyppisen
    // luvun.
    public static int mediaani(int[] luvut) {
        // Sijoitetaan muuttujaan taulukossa olevien lukujen lukumäärä.
        int lukujenLkm = luvut.length;
        // Esitellään muuttuja.
        int mediaani;

        // Jos taulukolle on varattu muistia, etsitään mediaani. Muussa tapauksessa
        // palautetaan virheilmoitus.
        if (luvut != null) {
            // Saadaan keskimmainen indeksi jakamalla lukujen määrä kahdella. Tyyppimuunnos
            // pyöristää luvun alaspäin.
            int keskimmainenIndeksi = lukujenLkm / 2;
            // Luetaan mediaanin arvo taulukon keskimmäisestä indeksistä.
            mediaani = luvut[keskimmainenIndeksi];
            // Paluuarvona saadaan double-tyyppinen mediaani (tai virheilmoitus).
            return mediaani;
        } else {
            return -1;
        }
    }

    // Metodi saa parametreina taulukon rivien ja sarakkeiden lukumäärät, filtterin koon, filtteröitävän
    // rivin ja sarakkeen ja palauttaa totuusarvon sen mukaan, voidaanko paikka filtteröidä vai ei eli
    // onko se liian lähellä taulukon reunaa.
    public static boolean paikanVoiFiltteroida(int rivienLkm, int sarakkeidenLkm,
    int filtterinKoko, int rivi, int sarake) {
        boolean voiFiltteroida = false;
        // Saadaan filtterin reunan leveys, kun jaetaan filtterin koko kahdella.
        int filtterinReuna = filtterinKoko / 2;
        // Taulukon yläreunan filtteröimätön alue (indeksit) saadaan, kun vähennetään filtterin leveydestä yksi.
        int ylaReuna = filtterinReuna - 1;
        // Taulukon vasemman reunan filtteröimätön alue (indeksit) saadaan, kun vähennetään filtterin leveydestä yksi.
        int vasenReuna = filtterinReuna - 1;
        // Taulukon oikealla reunalla oleva filtteröimätön alue saadaan, kun vähennetään taulukon sarakkeiden
        // lukumäärästä filtterin reunan leveys.
        int oikeaReuna = sarakkeidenLkm - filtterinReuna;
        // Taulukon alareunassa oleva filtteröimätön alue saadaan, kun vähennetään taulukon rivien
        // lukumäärästä filtterin reunan leveys.
        int alaReuna = rivienLkm - filtterinReuna;
        // Tutkitaan onko filtteröitävä paikka filtteröimättömän reunuksen sisä- vai ulkopuolella.
        // Jos rivi on suurempi kuin ylareunan filtteröimätön alue ja pienempi kuin alareunan filtteröimätön alue
        // ja jos sarake on suurempi kuin vasemman reunan filtteröimätän alue ja vastaavasti pienempi kuin
        // oikean reunan filtteröimätön alue, paikka voidaan filtteröidä ja totuusarvo on tosi, muussa tapauksessa
        // totuusarvo on epätosi.
        if ((rivi > ylaReuna) && (sarake > vasenReuna) && (sarake < oikeaReuna) &&
        (rivi < alaReuna)) {
            voiFiltteroida = true;
        } else {
            voiFiltteroida = false;
        }
        // Paluuarvona saadaan totuusarvo, joka kertoo voidaanko paikka filtteröidä.
        return voiFiltteroida;
    }

    // Metodi saa parametreina merkkitaulun, merkit-taulukon, filtterin koon sekä filtteröitävän rivin ja sarakkeen
    // ja palauttaa filtteröimisen seurauksena saatavan merkin.
    public static char filtteroiPaikka(char[] merkkitaulukko, char[][] merkit, int koko, int rivi, int sarake) {
        // Alustetaan merkki merkkitaulusta löytymättömällä merkillä.
        char palautettavaMerkki = '0';

        // Filtteröitävän taulukon rivien ja sarakkeiden lukumäärät saadaan merkit-taulukosta.
        int rivienLkm = merkit.length;
        // Oletetaan, että kaikki rivit ovat saman mittaiset.
        int sarakkeidenLkm = merkit[0].length;

        // Kutsutaan metodia, joka tutkii voiko paikan filtteröidä ja palauttaa totuusarvon riippuen siitä onko
        // paikka liian lähellä reunaa vai ei.
        boolean voiFiltteroida = paikanVoiFiltteroida(rivienLkm, sarakkeidenLkm, koko, rivi, sarake);
        // Jos koordinaatti on liian lähellä reunaa, palautetaan reunassa oleva merkki.
        if (!voiFiltteroida) {
            palautettavaMerkki = merkit[rivi][sarake];
        // Jos paikan voi filtteröidä eli se ei ole liina lähellä reunaa...
        } else {
            // Kutsutaan metodia, joka muuttaa filtterin alueella olevat merkit luvuiksi ja palauttaa viitteen
            // taulukkoon, johon luvut sijoitetaan.
            int[] merkkejaVastaavatLuvut = muutaFiltteriLuvuiksi(merkkitaulukko, merkit, koko, rivi, sarake);
            // Kutsutaan metodia, joka lajittelee lukutaulukon.
            lajittele(merkkejaVastaavatLuvut);
            // Kutsutaan metodia, joka etsii mediaanin lajitellusta taulukosta, ja palauttaa mediaanin arvon.
            int mediaani = mediaani(merkkejaVastaavatLuvut);
            // Kutsutaan metodia, joka muuttaa mediaanin lukua vastaavaksi merkiksi.
            char lukuaVastaavaMerkki = muutaLukuMerkiksi(merkkitaulukko, mediaani);
            // Filtterin keskipisteessä palautetaan uusi mediaania vastaava merkki.
            palautettavaMerkki = lukuaVastaavaMerkki;
        }
        return palautettavaMerkki;
    }

    // Metodi saa parametreina merkkitaulun, merkit-taulukon ja filtterin koon, filtteröi merkit-taulukon ja
    // palauttaa uuden filtteröidyn taulukon.
    public static char[][] filtteroi(char[] merkkitaulu, char[][] merkit, int filtterinKoko) {

        // Tarkistetaan että taulukoille on varattu muistia.
        if (merkkitaulu != null && merkit != null) {
            // Saadaan rivien ja sarakkeiden lukumäärä merkit-taulukosta, koska uudesta filtteröidystä taulukosta
            // tulee saman kokoinen kuin alkuperäisestä.
            int rivit = merkit.length;
            int sarakkeet = merkit[0].length;
            // Alustetaan uusi taulukko alkuperäisen taulukon kokoiseksi.
            char[][] filtteroidytMerkit = new char[rivit][sarakkeet];
            // Käydään taulukkoa läpi rivi ja sarake kerrallaan.
            for (int i = 0; i < merkit.length; i++) {
                for (int j = 0; j < merkit[i].length; j++) {
                    // Kutsutaan metodia, joka saa parametreina merkkilistan, merkit-taulukon, filtterin koon, rivin
                    // ja sarakkeen ja palauttaa merkin.
                    char filtteroityMerkki = filtteroiPaikka(merkkitaulu, merkit, filtterinKoko, i, j);
                    // Sijoitetaan paluuarvona saatu merkki uuteen taulukkoon koordinaattiin i,j.
                    filtteroidytMerkit[i][j] = filtteroityMerkki;
                }
            }
            // Paluuarvona saadaan uusi taulukko täytettynä.
            return filtteroidytMerkit;
        // Jos ei ole varattu muistia palautetaan null.
        } else {
            return null;
        }
    }
}
