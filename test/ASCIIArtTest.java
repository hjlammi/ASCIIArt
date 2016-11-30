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

}
