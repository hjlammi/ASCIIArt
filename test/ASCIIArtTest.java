import static org.junit.Assert.*;

import org.junit.Test;

public class ASCIIArtTest {

    @Test
    public void kaksiRivia() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};
        char[][] merkit = { { '#', '#', '&', 'x' }, { ';', '#' } };
        String tulos = " 0  0  2  5\n10  0";
        assertEquals(tulos, ASCIIArt.merkitLukuina(merkkitaulukko, merkit));
    }

    @Test
    public void eiMuistia() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};
        char[][] merkit = null;
        assertEquals("E", ASCIIArt.merkitLukuina(merkkitaulukko, merkit));
    }

    @Test
    public void tahtiKuusi() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};
        assertEquals(6, ASCIIArt.muutaMerkkiNumeroksi(merkkitaulukko, '*'));
    }

    @Test
    public void merkkiaEiLoydy() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};
        assertEquals(-1, ASCIIArt.muutaMerkkiNumeroksi(merkkitaulukko, 'U'));
    }

    @Test
    public void kuusiMjonona() {
        assertEquals(" 6", ASCIIArt.muutaLukuMjonoksi(6));
    }

    @Test
    public void kaksinumeroinenMjonona() {
        assertEquals("15", ASCIIArt.muutaLukuMjonoksi(15));
    }

    @Test
    public void leveysKaksi() {
        char[][] merkit = { { '#', '#' }, { ';', '#' } };
        assertEquals(2, ASCIIArt.taulukonKorkeus(merkit));
    }

}
