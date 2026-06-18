package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za klasu Ucesnik.
 */
public class UcesnikTest {

    private Ucesnik ucesnik;
    private NivoVestine nivo;

    @BeforeEach
    public void setUp() {
        nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo, "0611234567", LocalDate.of(1995, 3, 15), "Nema napomene");
    }

    @AfterEach
    public void tearDown() {
        ucesnik = null;
        nivo = null;
    }


    @Test
    public void testBesparametarskiKonstruktor() {
        Ucesnik u = new Ucesnik();
        assertNull(u.getIme(), "Ime treba biti null");
        assertNull(u.getPrezime(), "Prezime treba biti null");
        assertNull(u.getEmail(), "Email treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(1, ucesnik.getId(), "ID treba biti 1");
        assertEquals("Marko", ucesnik.getIme(), "Ime treba biti Marko");
        assertEquals("Petrovic", ucesnik.getPrezime(), "Prezime treba biti Petrovic");
        assertEquals("marko@gmail.com", ucesnik.getEmail(), "Email nije ispravan");
        assertEquals(nivo, ucesnik.getNivo(), "Nivo nije ispravan");
        assertEquals("0611234567", ucesnik.getTelefon(), "Telefon nije ispravan");
        assertEquals(LocalDate.of(1995, 3, 15), ucesnik.getDatumRodjenja(), "Datum rodjenja nije ispravan");
        assertEquals("Nema napomene", ucesnik.getNapomena(), "Napomena nije ispravna");
    }


    @Test
    public void testSetGetId() {
        ucesnik.setId(99);
        assertEquals(99, ucesnik.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetGetIme() {
        ucesnik.setIme("Ana");
        assertEquals("Ana", ucesnik.getIme(), "Ime treba biti Ana");
    }

    @Test
    public void testSetGetPrezime() {
        ucesnik.setPrezime("Jovanovic");
        assertEquals("Jovanovic", ucesnik.getPrezime(), "Prezime treba biti Jovanovic");
    }

    @Test
    public void testSetGetEmail() {
        ucesnik.setEmail("ana@gmail.com");
        assertEquals("ana@gmail.com", ucesnik.getEmail(), "Email treba biti ana@gmail.com");
    }

    @Test
    public void testSetGetNivo() {
        NivoVestine noviNivo = new NivoVestine(2, PlesniNivo.SREDNJI, PlesniStil.TANGO);
        ucesnik.setNivo(noviNivo);
        assertEquals(noviNivo, ucesnik.getNivo(), "Nivo treba biti noviNivo");
    }

    @Test
    public void testSetGetTelefon() {
        ucesnik.setTelefon("0691234567");
        assertEquals("0691234567", ucesnik.getTelefon(), "Telefon treba biti 0691234567");
    }

    @Test
    public void testSetGetDatumRodjenja() {
        LocalDate noviDatum = LocalDate.of(2000, 1, 1);
        ucesnik.setDatumRodjenja(noviDatum);
        assertEquals(noviDatum, ucesnik.getDatumRodjenja(), "Datum rodjenja nije ispravan");
    }

    @Test
    public void testSetGetNapomena() {
        ucesnik.setNapomena("Nova napomena");
        assertEquals("Nova napomena", ucesnik.getNapomena(), "Napomena treba biti Nova napomena");
    }


    @Test
    public void testToString() {
        assertEquals("Marko Petrovic", ucesnik.toString(), "toString treba vratiti 'Marko Petrovic'");
    }

    @Test
    public void testToStringNullIme() {
        ucesnik.setIme(null);
        assertEquals("null Petrovic", ucesnik.toString(), "toString sa null imenom");
    }


    @Test
    public void testEqualsIstObjekat() {
        assertTrue(ucesnik.equals(ucesnik), "Ucesnik mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(ucesnik.equals(null), "Ucesnik ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(ucesnik.equals("neki string"), "Ucesnik ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        Ucesnik drugi = new Ucesnik(2, "Marko", "Petrovic", "marko@gmail.com", nivo, "0611111111", LocalDate.of(1990, 1, 1), "");
        assertTrue(ucesnik.equals(drugi), "Ucesnici sa istim imenom, prezimenom i emailom moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitEmail() {
        Ucesnik drugi = new Ucesnik(1, "Marko", "Petrovic", "drugi@gmail.com", nivo, "0611234567", LocalDate.of(1995, 3, 15), "");
        assertFalse(ucesnik.equals(drugi), "Ucesnici sa razlicitim emailom ne smeju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "Marko, Petrovic, marko@gmail.com, true",
        "Ana, Petrovic, marko@gmail.com, false",
        "Marko, Jovanovic, marko@gmail.com, false",
        "Marko, Petrovic, drugi@gmail.com, false"
    })
    public void testEqualsParametrizovano(String ime, String prezime, String email, boolean ocekivano) {
        Ucesnik drugi = new Ucesnik(2, ime, prezime, email, nivo, "0611111111", LocalDate.of(1990, 1, 1), "");
        assertEquals(ocekivano, ucesnik.equals(drugi), "Equals test za: " + ime + " " + prezime + " " + email);
    }


    @Test
    public void testVratiNazivTabele() {
        assertEquals("ucesnik", ucesnik.vratiNazivTabele(), "Naziv tabele treba biti 'ucesnik'");
    }


    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,email,telefon,datum_rodjenja,napomena,nivo",
                ucesnik.vratiKoloneZaUbacivanje(), "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("ucesnik.id=1", ucesnik.vratiPrimarniKljuc(), "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> ucesnik.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }
}