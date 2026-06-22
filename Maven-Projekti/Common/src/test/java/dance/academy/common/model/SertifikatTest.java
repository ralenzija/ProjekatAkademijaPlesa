package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za klasu Sertifikat.
 */
public class SertifikatTest {

    private Sertifikat sertifikat;

    @BeforeEach
    public void setUp() {
        sertifikat = new Sertifikat(1, PlesniStil.SALSA, "World Dance Council");
    }

    @AfterEach
    public void tearDown() {
        sertifikat = null;
    }


    @Test
    public void testBesparametarskiKonstruktor() {
        Sertifikat s = new Sertifikat();
        assertEquals(0, s.getId(), "ID treba biti 0");
        assertNull(s.getPles(), "Ples treba biti null");
        assertNull(s.getOrganizacija(), "Organizacija treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(1, sertifikat.getId(), "ID treba biti 1");
        assertEquals(PlesniStil.SALSA, sertifikat.getPles(), "Ples treba biti SALSA");
        assertEquals("World Dance Council", sertifikat.getOrganizacija(), "Organizacija nije ispravna");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullPles() {
        assertThrows(NullPointerException.class,
                () -> new Sertifikat(1, null, "World Dance Council"),
                "Konstruktor mora baciti NullPointerException za null ples");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullOrganizacija() {
        assertThrows(NullPointerException.class,
                () -> new Sertifikat(1, PlesniStil.SALSA, null),
                "Konstruktor mora baciti NullPointerException za null organizaciju");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaPraznaOrganizacija() {
        assertThrows(IllegalArgumentException.class,
                () -> new Sertifikat(1, PlesniStil.SALSA, ""),
                "Konstruktor mora baciti IllegalArgumentException za praznu organizaciju");
    }


    @Test
    public void testSetGetId() {
        sertifikat.setId(99);
        assertEquals(99, sertifikat.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetIdNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> sertifikat.setId(-5),
                "Mora baciti izuzetak za id manji od -1");
    }

    @Test
    public void testSetGetPles() {
        sertifikat.setPles(PlesniStil.TANGO);
        assertEquals(PlesniStil.TANGO, sertifikat.getPles(), "Ples treba biti TANGO");
    }

    @Test
    public void testSetPlesNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> sertifikat.setPles(null),
                "Mora baciti NullPointerException za null ples");
    }

    @ParameterizedTest
    @EnumSource(PlesniStil.class)
    public void testSetPlesSveVrednosti(PlesniStil stil) {
        assertDoesNotThrow(() -> sertifikat.setPles(stil),
                "Ne sme baciti izuzetak za validan stil: " + stil);
    }

    @Test
    public void testSetGetOrganizacija() {
        sertifikat.setOrganizacija("Nova organizacija");
        assertEquals("Nova organizacija", sertifikat.getOrganizacija(),
                "Organizacija treba biti Nova organizacija");
    }

    @Test
    public void testSetOrganizacijaNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> sertifikat.setOrganizacija(null),
                "Mora baciti NullPointerException za null organizaciju");
    }

    @Test
    public void testSetOrganizacijaPraznaStringBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> sertifikat.setOrganizacija(""),
                "Mora baciti IllegalArgumentException za praznu organizaciju");
    }

    @ParameterizedTest
    @ValueSource(strings = {"World Dance Council", "Tango Federation", "Dance Academy"})
    public void testSetOrganizacijaValidneVrednosti(String organizacija) {
        assertDoesNotThrow(() -> sertifikat.setOrganizacija(organizacija),
                "Ne sme baciti izuzetak za validnu organizaciju: " + organizacija);
    }


    @Test
    public void testToString() {
        String expected = "Sertifikat{ples=SALSA, organizacija=World Dance Council}";
        assertEquals(expected, sertifikat.toString(), "toString nije ispravan");
    }


    @Test
    public void testEqualsIstObjekat() {
        assertTrue(sertifikat.equals(sertifikat), "Sertifikat mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(sertifikat.equals(null), "Sertifikat ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(sertifikat.equals("neki string"), "Sertifikat ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        Sertifikat drugi = new Sertifikat(1, PlesniStil.TANGO, "Druga organizacija");
        assertTrue(sertifikat.equals(drugi), "Sertifikati sa istim ID-jem moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitiID() {
        Sertifikat drugi = new Sertifikat(2, PlesniStil.SALSA, "World Dance Council");
        assertFalse(sertifikat.equals(drugi), "Sertifikati sa razlicitim ID-jem ne smeju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "1, true",
        "2, false",
        "0, false",
        "99, false"
    })
    public void testEqualsParametrizovano(int id, boolean ocekivano) {
        Sertifikat drugi = new Sertifikat(id, PlesniStil.BACHATA, "Neka organizacija");
        assertEquals(ocekivano, sertifikat.equals(drugi), "Equals test za id: " + id);
    }


    @Test
    public void testVratiNazivTabele() {
        assertEquals("sertifikat", sertifikat.vratiNazivTabele(),
                "Naziv tabele treba biti 'sertifikat'");
    }


    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("ples,organizacija", sertifikat.vratiKoloneZaUbacivanje(),
                "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'SALSA','World Dance Council'";
        assertEquals(expected, sertifikat.vratiVrednostiZaUbacivanje(),
                "Vrednosti za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("sertifikat.id=1", sertifikat.vratiPrimarniKljuc(),
                "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> sertifikat.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiListuBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> sertifikat.vratiListu(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaIzmeniBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> sertifikat.vratiVrednostiZaIzmenu(),
                "Treba baciti UnsupportedOperationException");
    }
}