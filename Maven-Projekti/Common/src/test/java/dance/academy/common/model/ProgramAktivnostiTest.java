package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za klasu ProgramAktivnosti.
 */
public class ProgramAktivnostiTest {

    private ProgramAktivnosti program;

    @BeforeEach
    public void setUp() {
        program = new ProgramAktivnosti(1, "Salsa pocetni", 8, 5000.0, PlesniStil.SALSA,
                true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, "Nema napomene");
    }

    @AfterEach
    public void tearDown() {
        program = null;
    }


    @Test
    public void testBesparametarskiKonstruktor() {
        ProgramAktivnosti p = new ProgramAktivnosti();
        assertEquals(0, p.getId(), "ID treba biti 0");
        assertNull(p.getNaziv(), "Naziv treba biti null");
        assertNull(p.getVrsta(), "Vrsta treba biti null");
        assertNull(p.getSala(), "Sala treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(1, program.getId(), "ID treba biti 1");
        assertEquals("Salsa pocetni", program.getNaziv(), "Naziv treba biti Salsa pocetni");
        assertEquals(8, program.getTrajanje(), "Trajanje treba biti 8");
        assertEquals(5000.0, program.getCena(), "Cena treba biti 5000.0");
        assertEquals(PlesniStil.SALSA, program.getVrsta(), "Vrsta treba biti SALSA");
        assertTrue(program.isAktivan(), "Program treba biti aktivan");
        assertEquals("Sala A", program.getSala(), "Sala treba biti Sala A");
        assertEquals(LocalDate.of(2026, 1, 1), program.getDatumPocetka(), "Datum pocetka nije ispravan");
        assertEquals(LocalTime.of(18, 0), program.getTermin(), "Termin nije ispravan");
        assertEquals(20, program.getMaxUcesnika(), "Max ucesnika treba biti 20");
        assertEquals("Nema napomene", program.getNapomena(), "Napomena nije ispravna");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullNaziv() {
        assertThrows(NullPointerException.class,
                () -> new ProgramAktivnosti(1, null, 8, 5000.0, PlesniStil.SALSA,
                        true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, ""),
                "Konstruktor mora baciti NullPointerException za null naziv");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullVrsta() {
        assertThrows(NullPointerException.class,
                () -> new ProgramAktivnosti(1, "Salsa", 8, 5000.0, null,
                        true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, ""),
                "Konstruktor mora baciti NullPointerException za null vrstu");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNegativnuCenu() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProgramAktivnosti(1, "Salsa", 8, -100.0, PlesniStil.SALSA,
                        true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, ""),
                "Konstruktor mora baciti IllegalArgumentException za negativnu cenu");
    }


    @Test
    public void testSetGetId() {
        program.setId(99);
        assertEquals(99, program.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetIdNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setId(-5),
                "Mora baciti izuzetak za id manji od -1");
    }

    @Test
    public void testSetGetNaziv() {
        program.setNaziv("Tango napredni");
        assertEquals("Tango napredni", program.getNaziv(), "Naziv treba biti Tango napredni");
    }

    @Test
    public void testSetNazivNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> program.setNaziv(null),
                "Mora baciti NullPointerException za null naziv");
    }

    @Test
    public void testSetNazivPrazanStringBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setNaziv(""),
                "Mora baciti IllegalArgumentException za prazan naziv");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Salsa pocetni", "Tango napredni", "Bachata osnove"})
    public void testSetNazivValidneVrednosti(String naziv) {
        assertDoesNotThrow(() -> program.setNaziv(naziv),
                "Ne sme baciti izuzetak za validan naziv: " + naziv);
    }

    @Test
    public void testSetGetTrajanje() {
        program.setTrajanje(12);
        assertEquals(12, program.getTrajanje(), "Trajanje treba biti 12");
    }

    @Test
    public void testSetTrajanjeManjOdNuleBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setTrajanje(-1),
                "Mora baciti izuzetak za negativno trajanje");
    }

    @Test
    public void testSetTrajanjeSNulomBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setTrajanje(0),
                "Mora baciti izuzetak za trajanje jednako nuli");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 8, 12, 24})
    public void testSetTrajanjeValidneVrednosti(int trajanje) {
        assertDoesNotThrow(() -> program.setTrajanje(trajanje),
                "Ne sme baciti izuzetak za validno trajanje: " + trajanje);
    }

    @Test
    public void testSetGetCena() {
        program.setCena(7500.0);
        assertEquals(7500.0, program.getCena(), "Cena treba biti 7500.0");
    }

    @Test
    public void testSetCenaNegativnaBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setCena(-100.0),
                "Mora baciti izuzetak za negativnu cenu");
    }

    @Test
    public void testSetCenaNulaDozvoljena() {
        assertDoesNotThrow(() -> program.setCena(0.0),
                "Cena nula treba biti dozvoljena");
    }

    @ParameterizedTest
    @CsvSource({"1000.0", "2500.5", "5000.0", "10000.0"})
    public void testSetCenaValidneVrednosti(double cena) {
        assertDoesNotThrow(() -> program.setCena(cena),
                "Ne sme baciti izuzetak za validnu cenu: " + cena);
    }

    @Test
    public void testSetGetVrsta() {
        program.setVrsta(PlesniStil.TANGO);
        assertEquals(PlesniStil.TANGO, program.getVrsta(), "Vrsta treba biti TANGO");
    }

    @Test
    public void testSetVrstaNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> program.setVrsta(null),
                "Mora baciti NullPointerException za null vrstu");
    }

    @ParameterizedTest
    @EnumSource(PlesniStil.class)
    public void testSetVrstaSveVrednosti(PlesniStil stil) {
        assertDoesNotThrow(() -> program.setVrsta(stil),
                "Ne sme baciti izuzetak za validan stil: " + stil);
    }

    @Test
    public void testSetGetAktivan() {
        program.setAktivan(false);
        assertFalse(program.isAktivan(), "Program treba biti neaktivan");
    }

    @Test
    public void testSetGetSala() {
        program.setSala("Sala B");
        assertEquals("Sala B", program.getSala(), "Sala treba biti Sala B");
    }

    @Test
    public void testSetSalaNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> program.setSala(null),
                "Mora baciti NullPointerException za null salu");
    }

    @Test
    public void testSetSalaPrazanStringBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setSala(""),
                "Mora baciti IllegalArgumentException za praznu salu");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sala A", "Sala B", "Sala C"})
    public void testSetSalaValidneVrednosti(String sala) {
        assertDoesNotThrow(() -> program.setSala(sala),
                "Ne sme baciti izuzetak za validnu salu: " + sala);
    }

    @Test
    public void testSetGetDatumPocetka() {
        LocalDate noviDatum = LocalDate.of(2027, 6, 1);
        program.setDatumPocetka(noviDatum);
        assertEquals(noviDatum, program.getDatumPocetka(), "Datum pocetka nije ispravan");
    }

    @Test
    public void testSetDatumPocetkaNull() {
        assertThrows(NullPointerException.class,
                () -> program.setDatumPocetka(null),
                "Mora baciti NullPointerException za null datum");
    }

    @Test
    public void testSetGetTermin() {
        LocalTime noviTermin = LocalTime.of(20, 0);
        program.setTermin(noviTermin);
        assertEquals(noviTermin, program.getTermin(), "Termin treba biti 20:00");
    }

    @Test
    public void testSetTerminNull() {
        assertThrows(NullPointerException.class,
                () -> program.setTermin(null),
                "Mora baciti NullPointerException za null termin");
    }

    @Test
    public void testSetGetMaxUcesnika() {
        program.setMaxUcesnika(30);
        assertEquals(30, program.getMaxUcesnika(), "Max ucesnika treba biti 30");
    }

    @Test
    public void testSetMaxUcesnikaNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setMaxUcesnika(-1),
                "Mora baciti izuzetak za negativan broj ucesnika");
    }

    @Test
    public void testSetMaxUcesnikaNulaBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> program.setMaxUcesnika(0),
                "Mora baciti izuzetak za nula ucesnika");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 50})
    public void testSetMaxUcesnikaValidneVrednosti(int max) {
        assertDoesNotThrow(() -> program.setMaxUcesnika(max),
                "Ne sme baciti izuzetak za validan broj ucesnika: " + max);
    }

    @Test
    public void testSetGetNapomena() {
        program.setNapomena("Nova napomena");
        assertEquals("Nova napomena", program.getNapomena(), "Napomena treba biti Nova napomena");
    }

    @Test
    public void testSetNapomenaNullDozvoljeno() {
        assertDoesNotThrow(() -> program.setNapomena(null),
                "Null napomena treba biti dozvoljena");
    }

    @Test
    public void testToString() {
        assertEquals("Salsa pocetni", program.toString(), "toString treba vratiti naziv programa");
    }


    @Test
    public void testEqualsIstObjekat() {
        assertTrue(program.equals(program), "Program mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(program.equals(null), "Program ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(program.equals("neki string"), "Program ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        ProgramAktivnosti drugi = new ProgramAktivnosti(1, "Drugi naziv", 4, 1000.0,
                PlesniStil.TANGO, false, "Sala B", LocalDate.of(2025, 1, 1), LocalTime.of(10, 0), 10, "");
        assertTrue(program.equals(drugi), "Programi sa istim ID-jem moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitiID() {
        ProgramAktivnosti drugi = new ProgramAktivnosti(2, "Salsa pocetni", 8, 5000.0,
                PlesniStil.SALSA, true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, "");
        assertFalse(program.equals(drugi), "Programi sa razlicitim ID-jem ne smeju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "1, true",
        "2, false",
        "0, false",
        "99, false"
    })
    public void testEqualsParametrizovano(int id, boolean ocekivano) {
        ProgramAktivnosti drugi = new ProgramAktivnosti(id, "Neki naziv", 4, 1000.0,
                PlesniStil.BACHATA, false, "Sala C", LocalDate.of(2025, 1, 1), LocalTime.of(10, 0), 10, "");
        assertEquals(ocekivano, program.equals(drugi), "Equals test za id: " + id);
    }


    @Test
    public void testVratiNazivTabele() {
        assertEquals("program_aktivnosti", program.vratiNazivTabele(),
                "Naziv tabele treba biti 'program_aktivnosti'");
    }


    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv,trajanje,cena,vrsta,aktivan,sala,datum_pocetka,termin,max_ucesnika,napomena",
                program.vratiKoloneZaUbacivanje(), "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("program_aktivnosti.id=1", program.vratiPrimarniKljuc(),
                "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiVrednostiZaUbacivanjeBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> program.vratiVrednostiZaUbacivanje(),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> program.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaIzmeniBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> program.vratiVrednostiZaIzmenu(),
                "Treba baciti UnsupportedOperationException");
    }
}