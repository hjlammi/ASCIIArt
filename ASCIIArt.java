/*
 * Lausekielinen ohjelmointi II, harjoitustyö 2.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 29.11.2016.
 *
 *
 */

public class ASCIIArt {
    public static void main(String[] args) {

        // Kutsutaan metodia, joka tulostaa tervehdyksen.
        tulostaTervehdys();

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
}
