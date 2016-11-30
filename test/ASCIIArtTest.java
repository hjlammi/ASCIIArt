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
    public void korkeusKaksi() {
        char[][] merkit = { { '#', '#' }, { ';', '#' } };
        assertEquals(2, ASCIIArt.taulukonKorkeus(merkit));
    }

    @Test
    public void leveysKaksi() {
        char[][] merkit = { { '#', '#' }, { ';', '#' } };
        assertEquals(2, ASCIIArt.taulukonLeveys(merkit));
    }

    @Test
    public void kolmeRisuaitaa() {
        char[][] merkit = { { '#', '#' }, { ';', '#' } };
        assertEquals(3, ASCIIArt.merkinLkmTaulukossa('#', merkit));
    }

    @Test
    public void nollaTahtea() {
        char[][] merkit = { { '#', '#' }, { ';', '#' } };
        assertEquals(0, ASCIIArt.merkinLkmTaulukossa('*', merkit));
    }

    @Test
    public void infoKokoJaMerkit() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%' };
        char[][] merkit = { { '#', '#' }, { '&', '#' } };
        String tulos = "2 x 2\n# 3\n@ 0\n& 1\n$ 0\n% 0";
        assertEquals(tulos, ASCIIArt.infoMjonona(merkkitaulukko, merkit));
    }

    @Test
    public void filter3x3() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%' };
        char[][] merkit = { { '#', '#', '&' },
                            { '&', '#', '%' },
                            { '#', '#', '&' } };
        int[] luvut = { 0, 0, 2, 2, 0, 4, 0, 0, 2 };
        assertArrayEquals(luvut, ASCIIArt.muutaFiltteriLuvuiksi(merkkitaulukko, merkit));
    }

    @Test
    public void tyhjaTaulukko() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%' };
        char[][] merkit = {};
        int[] luvut = {};
        assertArrayEquals(luvut, ASCIIArt.muutaFiltteriLuvuiksi(merkkitaulukko, merkit));
    }

    @Test
    public void lajittelu() {
        int[] luvut = { 0, 0, 2, 2, 0, 4, 0, 0 };
        ASCIIArt.lajittele(luvut);
        assertArrayEquals(new int[]{ 0, 0, 0, 0, 0, 2, 2, 4 }, luvut);
    }

    @Test
    public void mediaaniOnNolla() {
        int[] luvut = { 0, 0, 0, 0, 0, 2, 2, 4 };
        assertEquals(0, ASCIIArt.mediaani(luvut));
    }

    @Test
    public void merkkiOnAt() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%' };
        assertEquals('@', ASCIIArt.muutaLukuMerkiksi(merkkitaulukko, 1));
    }

}
