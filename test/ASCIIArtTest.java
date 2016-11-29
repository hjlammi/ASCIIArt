import static org.junit.Assert.*;

import org.junit.Test;

public class ASCIIArtTest {

    @Test
    public void yksiRivi() {
        char[] merkkitaulukko = { '#', '@', '&', '$', '%', 'x', '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};
        char[][] merkit = { { '#', '#', '&', 'x' }, { ';', '#' } };
        String tulos = " 0  0  2  5\n10  0";
        assertEquals(tulos, ASCIIArt.merkitLukuina(merkkitaulukko, merkit));
    }


}
