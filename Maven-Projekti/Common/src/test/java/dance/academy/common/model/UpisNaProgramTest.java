package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za klasu UpisNaProgram.
 */
public class UpisNaProgramTest {

    private UpisNaProgram upis;
    private Instruktor instruktor;
    private Ucesnik ucesnik;
    private List<StavkaUpisa> stavke;
    private Date datum;

    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo, "0611234567",
                java.time.LocalDate.of(1995, 3, 15), "");
        stavke = new ArrayList<>();
        datum = new Date();
        upis = new UpisNaProgram(1, datum, StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik, stavke);
    }

    @AfterEach
    public void tearDown() {
        upis = null;
        instruktor = null;
        ucesnik = null;
        stavke = null;
        datum = null;
    }



    @Test
    public void testBesparametarskiKonstruktor() {
        UpisNaProgram u = new UpisNaProgram();
        assertEquals(0, u.getId(), "ID treba biti 0");
        assertNull(u.getDatum(), "Datum treba biti null");
        assertNull(u.getStatus(), "Status treba biti null");
        assertNull(u.getInstruktor(), "Instruktor treba biti null");
        assertNull(u.getUcesnik(), "Ucesnik treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktorSaStavkama() {
        assertEquals(1, upis.getId(), "ID treba biti 1");
        assertEquals(datum, upis.getDatum(), "Datum nije ispravan");
        assertEquals(StatusUpisa.KREIRAN, upis.getStatus(), "Status treba biti KREIRAN");
        assertEquals(5000.0, upis.getUkupanIznos(), "Ukupan iznos treba biti 5000.0");
        assertEquals(instruktor, upis.getInstruktor(), "Instruktor nije ispravan");
        assertEquals(ucesnik, upis.getUcesnik(), "Ucesnik nije ispravan");
        assertEquals(stavke, upis.getStavke(), "Stavke nisu ispravne");
    }

    @Test
    public void testParametrizovaniKonstruktorBezStavki() {
        UpisNaProgram u = new UpisNaProgram(2, datum, StatusUpisa.POTVRDJEN, 3000.0, instruktor, ucesnik);
        assertEquals(2, u.getId(), "ID treba biti 2");
        assertEquals(StatusUpisa.POTVRDJEN, u.getStatus(), "Status treba biti POTVRDJEN");
        assertEquals(3000.0, u.getUkupanIznos(), "Ukupan iznos treba biti 3000.0");
        assertNotNull(u.getStavke(), "Stavke ne smeju biti null");
    }



    @Test
    public void testSetGetId() {
        upis.setId(99);
        assertEquals(99, upis.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetGetDatum() {
        Date noviDatum = new Date(System.currentTimeMillis() + 1000);
        upis.setDatum(noviDatum);
        assertEquals(noviDatum, upis.getDatum(), "Datum nije ispravan");
    }

    @Test
    public void testSetGetStatus() {
        upis.setStatus(StatusUpisa.POTVRDJEN);
        assertEquals(StatusUpisa.POTVRDJEN, upis.getStatus(), "Status treba biti POTVRDJEN");
    }

    @Test
    public void testSetGetUkupanIznos() {
        upis.setUkupanIznos(9999.99);
        assertEquals(9999.99, upis.getUkupanIznos(), "Ukupan iznos treba biti 9999.99");
    }

    @Test
    public void testSetGetInstruktor() {
        Instruktor noviInstruktor = new Instruktor(2, "Ana", "Anic", "ana@gmail.com", "sifra456");
        upis.setInstruktor(noviInstruktor);
        assertEquals(noviInstruktor, upis.getInstruktor(), "Instruktor nije ispravan");
    }

    @Test
    public void testSetGetUcesnik() {
        NivoVestine nivo = new NivoVestine(2, PlesniNivo.SREDNJI, PlesniStil.TANGO);
        Ucesnik noviUcesnik = new Ucesnik(2, "Ana", "Anic", "ana@gmail.com", nivo, "069111", 
                java.time.LocalDate.of(2000, 1, 1), "");
        upis.setUcesnik(noviUcesnik);
        assertEquals(noviUcesnik, upis.getUcesnik(), "Ucesnik nije ispravan");
    }

    @Test
    public void testSetGetStavke() {
        List<StavkaUpisa> noveStavke = new ArrayList<>();
        StavkaUpisa stavka = new StavkaUpisa();
        noveStavke.add(stavka);
        upis.setStavke(noveStavke);
        assertEquals(noveStavke, upis.getStavke(), "Stavke nisu ispravne");
    }


    @Test
    public void testToStringSadrziStatus() {
        String result = upis.toString();
        assertTrue(result.contains("KREIRAN"), "toString treba sadrzati status KREIRAN");
    }

    @Test
    public void testToStringNijeNull() {
        assertNotNull(upis.toString(), "toString ne sme vratiti null");
    }



    @Test
    public void testEqualsIstObjekat() {
        assertTrue(upis.equals(upis), "Upis mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(upis.equals(null), "Upis ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(upis.equals("neki string"), "Upis ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        UpisNaProgram drugi = new UpisNaProgram(1, new Date(), StatusUpisa.POTVRDJEN, 9999.0, instruktor, ucesnik);
        assertTrue(upis.equals(drugi), "Upisi sa istim ID-jem moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitiID() {
        UpisNaProgram drugi = new UpisNaProgram(2, datum, StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik);
        assertFalse(upis.equals(drugi), "Upisi sa razlicitim ID-jem ne smeju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "1, true",
        "2, false",
        "0, false",
        "99, false"
    })
    public void testEqualsParametrizovano(int id, boolean ocekivano) {
        UpisNaProgram drugi = new UpisNaProgram(id, datum, StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik);
        assertEquals(ocekivano, upis.equals(drugi), "Equals test za id: " + id);
    }


    @ParameterizedTest
    @CsvSource({
        "KREIRAN",
        "POTVRDJEN",
        "ODBIJEN",
        "OTKAZAN",
        "ZAVRSEN"
    })
    public void testSviStatusi(String status) {
        StatusUpisa s = StatusUpisa.valueOf(status);
        upis.setStatus(s);
        assertEquals(s, upis.getStatus(), "Status treba biti " + status);
    }



    @Test
    public void testVratiNazivTabele() {
        assertEquals("upis_na_program", upis.vratiNazivTabele(), "Naziv tabele treba biti 'upis_na_program'");
    }



    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("datum,status,ukupanIznos,instruktor,ucesnik",
                upis.vratiKoloneZaUbacivanje(), "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("upis_na_program.id=1", upis.vratiPrimarniKljuc(), "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> upis.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }
}