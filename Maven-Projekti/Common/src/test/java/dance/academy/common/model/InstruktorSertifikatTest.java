package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test klasa za klasu InstruktorSertifikat.
 */
public class InstruktorSertifikatTest {

    private InstruktorSertifikat instruktorSertifikat;
    private Instruktor instruktor;
    private Sertifikat sertifikat;
    private Date datum;

    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        sertifikat = new Sertifikat(1, PlesniStil.SALSA, "World Dance Council");
        datum = new Date();
        instruktorSertifikat = new InstruktorSertifikat(instruktor, sertifikat, datum);
    }

    @AfterEach
    public void tearDown() {
        instruktorSertifikat = null;
        instruktor = null;
        sertifikat = null;
        datum = null;
    }

    @Test
    public void testBesparametarskiKonstruktor() {
        InstruktorSertifikat is = new InstruktorSertifikat();
        assertNull(is.getInstruktor(), "Instruktor treba biti null");
        assertNull(is.getSertifikat(), "Sertifikat treba biti null");
        assertNull(is.getDatumSticanja(), "Datum sticanja treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(instruktor, instruktorSertifikat.getInstruktor(), "Instruktor nije ispravan");
        assertEquals(sertifikat, instruktorSertifikat.getSertifikat(), "Sertifikat nije ispravan");
        assertEquals(datum, instruktorSertifikat.getDatumSticanja(), "Datum sticanja nije ispravan");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullInstruktor() {
        assertThrows(NullPointerException.class,
                () -> new InstruktorSertifikat(null, sertifikat, datum),
                "Konstruktor mora baciti NullPointerException za null instruktora");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullSertifikat() {
        assertThrows(NullPointerException.class,
                () -> new InstruktorSertifikat(instruktor, null, datum),
                "Konstruktor mora baciti NullPointerException za null sertifikat");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullDatum() {
        assertThrows(NullPointerException.class,
                () -> new InstruktorSertifikat(instruktor, sertifikat, null),
                "Konstruktor mora baciti NullPointerException za null datum");
    }

    @Test
    public void testSetGetInstruktor() {
        Instruktor noviInstruktor = new Instruktor(2, "Ana", "Anic", "ana@gmail.com", "sifra456");
        instruktorSertifikat.setInstruktor(noviInstruktor);
        assertEquals(noviInstruktor, instruktorSertifikat.getInstruktor(), "Instruktor nije ispravan");
    }

    @Test
    public void testSetInstruktorNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> instruktorSertifikat.setInstruktor(null),
                "Mora baciti NullPointerException za null instruktora");
    }

    @Test
    public void testSetGetSertifikat() {
        Sertifikat noviSertifikat = new Sertifikat(2, PlesniStil.TANGO, "Tango Association");
        instruktorSertifikat.setSertifikat(noviSertifikat);
        assertEquals(noviSertifikat, instruktorSertifikat.getSertifikat(), "Sertifikat nije ispravan");
    }

    @Test
    public void testSetSertifikatNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> instruktorSertifikat.setSertifikat(null),
                "Mora baciti NullPointerException za null sertifikat");
    }

    @Test
    public void testSetGetDatumSticanja() {
        Date noviDatum = new Date(System.currentTimeMillis() + 10000);
        instruktorSertifikat.setDatumSticanja(noviDatum);
        assertEquals(noviDatum, instruktorSertifikat.getDatumSticanja(), "Datum sticanja nije ispravan");
    }

    @Test
    public void testSetDatumSticanjaNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> instruktorSertifikat.setDatumSticanja(null),
                "Mora baciti NullPointerException za null datum");
    }

    @Test
    public void testToStringNijeNull() {
        assertNotNull(instruktorSertifikat.toString(), "toString ne sme vratiti null");
    }

    @Test
    public void testToStringSadrziInstruktora() {
        assertTrue(instruktorSertifikat.toString().contains("instruktor"),
                "toString treba sadrzati 'instruktor'");
    }

    @Test
    public void testEqualsIstObjekat() {
        assertTrue(instruktorSertifikat.equals(instruktorSertifikat),
                "InstruktorSertifikat mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(instruktorSertifikat.equals(null),
                "InstruktorSertifikat ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(instruktorSertifikat.equals("neki string"),
                "InstruktorSertifikat ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        InstruktorSertifikat drugi = new InstruktorSertifikat(instruktor, sertifikat, datum);
        assertTrue(instruktorSertifikat.equals(drugi),
                "InstruktorSertifikati sa istim podacima moraju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "nikola@gmail.com, sifra123, 1, true",
        "druga@gmail.com, sifra123, 1, false",
        "nikola@gmail.com, sifra123, 2, false",
        "druga@gmail.com, drugaSifra, 2, false"
    })
    public void testEqualsParametrizovano(String email, String sifra, int idSertifikata, boolean ocekivano) {
        Instruktor i = new Instruktor(1, "Ime", "Prezime", email, sifra);
        Sertifikat s = new Sertifikat(idSertifikata, PlesniStil.SALSA, "Organizacija");
        InstruktorSertifikat drugi = new InstruktorSertifikat(i, s, datum);
        assertEquals(ocekivano, instruktorSertifikat.equals(drugi),
                "Equals test za email=" + email + " sertifikat id=" + idSertifikata);
    }

    @Test
    public void testEqualsRazlicitiInstruktor() {
        Instruktor drugiInstruktor = new Instruktor(2, "Ana", "Anic", "ana@gmail.com", "sifra456");
        InstruktorSertifikat drugi = new InstruktorSertifikat(drugiInstruktor, sertifikat, datum);
        assertFalse(instruktorSertifikat.equals(drugi),
                "InstruktorSertifikati sa razlicitim instruktorom ne smeju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitiSertifikat() {
        Sertifikat drugiSertifikat = new Sertifikat(2, PlesniStil.TANGO, "Tango Association");
        InstruktorSertifikat drugi = new InstruktorSertifikat(instruktor, drugiSertifikat, datum);
        assertFalse(instruktorSertifikat.equals(drugi),
                "InstruktorSertifikati sa razlicitim sertifikatom ne smeju biti jednaki");
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("instruktor_sertifikat", instruktorSertifikat.vratiNazivTabele(),
                "Naziv tabele treba biti 'instruktor_sertifikat'");
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("instruktor,sertifikat,datumSticanja",
                instruktorSertifikat.vratiKoloneZaUbacivanje(),
                "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = instruktor.getId() + "," + sertifikat.getId() + ",'" + datum + "'";
        assertEquals(expected, instruktorSertifikat.vratiVrednostiZaUbacivanje(),
                "Vrednosti za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "instruktor_sertifikat.instruktor=1 AND instruktor_sertifikat.sertifikat=1";
        assertEquals(expected, instruktorSertifikat.vratiPrimarniKljuc(),
                "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiListuBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> instruktorSertifikat.vratiListu(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> instruktorSertifikat.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaIzmeniBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> instruktorSertifikat.vratiVrednostiZaIzmenu(),
                "Treba baciti UnsupportedOperationException");
    }
}
