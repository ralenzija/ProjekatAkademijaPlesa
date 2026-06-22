package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test klasa za klasu StavkaUpisa.
 */
public class StavkaUpisaTest {

    private StavkaUpisa stavka;
    private UpisNaProgram upis;
    private ProgramAktivnosti program;

    @BeforeEach
    public void setUp() {
        Instruktor instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        Ucesnik ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
        upis = new UpisNaProgram(1, new Date(), StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik);
        program = new ProgramAktivnosti(1, "Salsa pocetni", 8, 5000.0, PlesniStil.SALSA,
                true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, "");
        stavka = new StavkaUpisa(1, 5000.0, upis, program);
    }

    @AfterEach
    public void tearDown() {
        stavka = null;
        upis = null;
        program = null;
    }

    @Test
    public void testBesparametarskiKonstruktor() {
        StavkaUpisa s = new StavkaUpisa();
        assertEquals(0, s.getRb(), "RB treba biti 0");
        assertEquals(0.0, s.getIznos(), "Iznos treba biti 0.0");
        assertNull(s.getUpis(), "Upis treba biti null");
        assertNull(s.getProgram(), "Program treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(1, stavka.getRb(), "RB treba biti 1");
        assertEquals(5000.0, stavka.getIznos(), "Iznos treba biti 5000.0");
        assertEquals(upis, stavka.getUpis(), "Upis nije ispravan");
        assertEquals(program, stavka.getProgram(), "Program nije ispravan");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullProgram() {
        assertThrows(NullPointerException.class,
                () -> new StavkaUpisa(1, 5000.0, upis, null),
                "Konstruktor mora baciti NullPointerException za null program");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNegativanIznos() {
        assertThrows(IllegalArgumentException.class,
                () -> new StavkaUpisa(1, -100.0, upis, program),
                "Konstruktor mora baciti IllegalArgumentException za negativan iznos");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNegativanRb() {
        assertThrows(IllegalArgumentException.class,
                () -> new StavkaUpisa(-1, 5000.0, upis, program),
                "Konstruktor mora baciti IllegalArgumentException za negativan rb");
    }

    @Test
    public void testSetGetRb() {
        stavka.setRb(99);
        assertEquals(99, stavka.getRb(), "RB treba biti 99");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10, 99})
    public void testSetRbValidneVrednosti(int rb) {
        assertDoesNotThrow(() -> stavka.setRb(rb),
                "Ne sme baciti izuzetak za validan rb: " + rb);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 5000.0, true",
        "2, 9999.0, false",
        "3, 1000.0, false"
    })
    public void testEqualsParametrizovanoRazlicitiProgram(int idPrograma, double cena, boolean ocekivano) {
        ProgramAktivnosti p = new ProgramAktivnosti(idPrograma, "Naziv", 4, cena,
                PlesniStil.SALSA, true, "Sala A", LocalDate.of(2026, 1, 1),
                LocalTime.of(18, 0), 20, "");
        StavkaUpisa druga = new StavkaUpisa(1, cena, upis, p);
        assertEquals(ocekivano, stavka.equals(druga),
                "Equals test za program id=" + idPrograma);
    }

    @Test
    public void testSetRbNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> stavka.setRb(-1),
                "Mora baciti izuzetak za negativan rb");
    }

    @Test
    public void testSetRbNulaBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> stavka.setRb(0),
                "Mora baciti izuzetak za rb jednak nuli");
    }

    @Test
    public void testSetGetIznos() {
        stavka.setIznos(9999.99);
        assertEquals(9999.99, stavka.getIznos(), "Iznos treba biti 9999.99");
    }

    @Test
    public void testSetIznosNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> stavka.setIznos(-100.0),
                "Mora baciti izuzetak za negativan iznos");
    }

    @Test
    public void testSetIznosNulaDozvoljena() {
        assertDoesNotThrow(() -> stavka.setIznos(0.0),
                "Iznos nula treba biti dozvoljen");
    }

    @ParameterizedTest
    @ValueSource(doubles = {1000.0, 2500.5, 0.0, 99999.99})
    public void testSetIznosValidneVrednosti(double iznos) {
        assertDoesNotThrow(() -> stavka.setIznos(iznos),
                "Ne sme baciti izuzetak za validan iznos: " + iznos);
    }

    @Test
    public void testSetGetUpis() {
        UpisNaProgram noviUpis = new UpisNaProgram();
        noviUpis.setId(2);
        stavka.setUpis(noviUpis);
        assertEquals(noviUpis, stavka.getUpis(), "Upis nije ispravan");
    }

    @Test
    public void testSetUpisNullDozvoljeno() {
        assertDoesNotThrow(() -> stavka.setUpis(null),
                "Null upis treba biti dozvoljen");
    }

    @Test
    public void testSetGetProgram() {
        ProgramAktivnosti noviProgram = new ProgramAktivnosti(2, "Tango", 4, 3000.0,
                PlesniStil.TANGO, true, "Sala B", LocalDate.of(2026, 2, 1),
                LocalTime.of(19, 0), 15, "");
        stavka.setProgram(noviProgram);
        assertEquals(noviProgram, stavka.getProgram(), "Program nije ispravan");
    }

    @Test
    public void testSetProgramNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> stavka.setProgram(null),
                "Mora baciti NullPointerException za null program");
    }

    @Test
    public void testToStringNijeNull() {
        assertNotNull(stavka.toString(), "toString ne sme vratiti null");
    }

    @Test
    public void testToStringSadrziProgram() {
        assertTrue(stavka.toString().contains("program"), "toString treba sadrzati 'program'");
    }

    @Test
    public void testEqualsIstObjekat() {
        assertTrue(stavka.equals(stavka), "StavkaUpisa mora biti jednaka sama sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(stavka.equals(null), "StavkaUpisa ne sme biti jednaka null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(stavka.equals("neki string"), "StavkaUpisa ne sme biti jednaka stringu");
    }

    @Test
    public void testEqualsJednaki() {
        StavkaUpisa druga = new StavkaUpisa(2, 9999.0, upis, program);
        assertTrue(stavka.equals(druga), "Stavke sa istim programom moraju biti jednake");
    }

    @Test
    public void testEqualsRazlicitiProgram() {
        ProgramAktivnosti drugiProgram = new ProgramAktivnosti(2, "Tango srednji", 8, 4000.0,
                PlesniStil.TANGO, true, "Sala B", LocalDate.of(2026, 2, 1),
                LocalTime.of(19, 0), 15, "");
        StavkaUpisa druga = new StavkaUpisa(1, 5000.0, upis, drugiProgram);
        assertFalse(stavka.equals(druga), "Stavke sa razlicitim programom ne smeju biti jednake");
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("stavka_upisa", stavka.vratiNazivTabele(),
                "Naziv tabele treba biti 'stavka_upisa'");
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("upis,iznos,program", stavka.vratiKoloneZaUbacivanje(),
                "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = upis.getId() + ", " + 5000.0 + ", " + program.getId();
        assertEquals(expected, stavka.vratiVrednostiZaUbacivanje(),
                "Vrednosti za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "stavka_upisa.id=1 AND stavka_upisa.upis=1";
        assertEquals(expected, stavka.vratiPrimarniKljuc(), "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> stavka.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaIzmeniBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> stavka.vratiVrednostiZaIzmenu(),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiListuNijeNull() {
        assertNotNull(stavka.vratiNazivTabele(), "Naziv tabele ne sme biti null");
    }
}
