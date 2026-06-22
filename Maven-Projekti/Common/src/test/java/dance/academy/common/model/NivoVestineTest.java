package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za klasu NivoVestine.
 */
public class NivoVestineTest {

    private NivoVestine nivoVestine;

    @BeforeEach
    public void setUp() {
        nivoVestine = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
    }

    @AfterEach
    public void tearDown() {
        nivoVestine = null;
    }


    @Test
    public void testBesparametarskiKonstruktor() {
        NivoVestine nv = new NivoVestine();
        assertEquals(0, nv.getId(), "ID treba biti 0");
        assertNull(nv.getNivo(), "Nivo treba biti null");
        assertNull(nv.getVrsta(), "Vrsta treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(1, nivoVestine.getId(), "ID treba biti 1");
        assertEquals(PlesniNivo.POCETNI, nivoVestine.getNivo(), "Nivo treba biti POCETNI");
        assertEquals(PlesniStil.SALSA, nivoVestine.getVrsta(), "Vrsta treba biti SALSA");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullNivo() {
        assertThrows(NullPointerException.class,
                () -> new NivoVestine(1, null, PlesniStil.SALSA),
                "Konstruktor mora baciti NullPointerException za null nivo");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullVrsta() {
        assertThrows(NullPointerException.class,
                () -> new NivoVestine(1, PlesniNivo.POCETNI, null),
                "Konstruktor mora baciti NullPointerException za null vrstu");
    }


    @Test
    public void testSetGetId() {
        nivoVestine.setId(99);
        assertEquals(99, nivoVestine.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetIdNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> nivoVestine.setId(-5),
                "Mora baciti izuzetak za id manji od -1");
    }

    @Test
    public void testSetGetNivo() {
        nivoVestine.setNivo(PlesniNivo.NAPREDNI);
        assertEquals(PlesniNivo.NAPREDNI, nivoVestine.getNivo(), "Nivo treba biti NAPREDNI");
    }

    @Test
    public void testSetNivoNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> nivoVestine.setNivo(null),
                "Mora baciti NullPointerException za null nivo");
    }

    @ParameterizedTest
    @EnumSource(PlesniNivo.class)
    public void testSetNivoSveVrednosti(PlesniNivo nivo) {
        assertDoesNotThrow(() -> nivoVestine.setNivo(nivo),
                "Ne sme baciti izuzetak za validan nivo: " + nivo);
    }

    @Test
    public void testSetGetVrsta() {
        nivoVestine.setVrsta(PlesniStil.TANGO);
        assertEquals(PlesniStil.TANGO, nivoVestine.getVrsta(), "Vrsta treba biti TANGO");
    }

    @Test
    public void testSetVrstaNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> nivoVestine.setVrsta(null),
                "Mora baciti NullPointerException za null vrstu");
    }

    @ParameterizedTest
    @EnumSource(PlesniStil.class)
    public void testSetVrstaSveVrednosti(PlesniStil stil) {
        assertDoesNotThrow(() -> nivoVestine.setVrsta(stil),
                "Ne sme baciti izuzetak za validan stil: " + stil);
    }


    @Test
    public void testToString() {
        String expected = "NivoVestine{nivo=POCETNI, vrsta=SALSA}";
        assertEquals(expected, nivoVestine.toString(), "toString nije ispravan");
    }


    @Test
    public void testEqualsIstObjekat() {
        assertTrue(nivoVestine.equals(nivoVestine), "NivoVestine mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(nivoVestine.equals(null), "NivoVestine ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(nivoVestine.equals("neki string"), "NivoVestine ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        NivoVestine drugi = new NivoVestine(1, PlesniNivo.SREDNJI, PlesniStil.TANGO);
        assertTrue(nivoVestine.equals(drugi), "NivoVestine sa istim ID-jem moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitiID() {
        NivoVestine drugi = new NivoVestine(2, PlesniNivo.POCETNI, PlesniStil.SALSA);
        assertFalse(nivoVestine.equals(drugi), "NivoVestine sa razlicitim ID-jem ne smeju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "1, true",
        "2, false",
        "0, false",
        "99, false"
    })
    public void testEqualsParametrizovano(int id, boolean ocekivano) {
        NivoVestine drugi = new NivoVestine(id, PlesniNivo.NAPREDNI, PlesniStil.BACHATA);
        assertEquals(ocekivano, nivoVestine.equals(drugi), "Equals test za id: " + id);
    }


    @Test
    public void testVratiNazivTabele() {
        assertEquals("nivo_vestine", nivoVestine.vratiNazivTabele(),
                "Naziv tabele treba biti 'nivo_vestine'");
    }


    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("nivo,vrsta", nivoVestine.vratiKoloneZaUbacivanje(),
                "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("nivo_vestine.id=1", nivoVestine.vratiPrimarniKljuc(),
                "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiVrednostiZaUbacivanjeBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> nivoVestine.vratiVrednostiZaUbacivanje(),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> nivoVestine.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaIzmeniBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> nivoVestine.vratiVrednostiZaIzmenu(),
                "Treba baciti UnsupportedOperationException");
    }
}