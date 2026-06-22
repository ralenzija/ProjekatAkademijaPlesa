package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
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
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "Nema napomene");
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
    public void testKonstruktorBacaIzuzetakZaNullIme() {
        assertThrows(NullPointerException.class,
                () -> new Ucesnik(1, null, "Petrovic", "marko@gmail.com", nivo,
                        "0611234567", LocalDate.of(1995, 3, 15), ""),
                "Konstruktor mora baciti NullPointerException za null ime");
    }

    @Test
    public void testKonstruktorBacaIzuzetakZaNullEmail() {
        assertThrows(NullPointerException.class,
                () -> new Ucesnik(1, "Marko", "Petrovic", null, nivo,
                        "0611234567", LocalDate.of(1995, 3, 15), ""),
                "Konstruktor mora baciti NullPointerException za null email");
    }


    @Test
    public void testSetGetId() {
        ucesnik.setId(99);
        assertEquals(99, ucesnik.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetIdNegativanBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> ucesnik.setId(-5),
                "Mora baciti izuzetak za id manji od -1");
    }

    @Test
    public void testSetGetIme() {
        ucesnik.setIme("Ana");
        assertEquals("Ana", ucesnik.getIme(), "Ime treba biti Ana");
    }

    @Test
    public void testSetImeNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> ucesnik.setIme(null),
                "Mora baciti NullPointerException za null ime");
    }

    @Test
    public void testSetImePrazanStringBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> ucesnik.setIme(""),
                "Mora baciti IllegalArgumentException za prazno ime");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ana", "Marko", "Petar", "Jelena"})
    public void testSetImeValidneVrednosti(String ime) {
        assertDoesNotThrow(() -> ucesnik.setIme(ime),
                "Ne sme baciti izuzetak za validno ime: " + ime);
    }

    @Test
    public void testSetGetPrezime() {
        ucesnik.setPrezime("Jovanovic");
        assertEquals("Jovanovic", ucesnik.getPrezime(), "Prezime treba biti Jovanovic");
    }

    @Test
    public void testSetPrezimeNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> ucesnik.setPrezime(null),
                "Mora baciti NullPointerException za null prezime");
    }

    @Test
    public void testSetPrezimePrazanStringBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> ucesnik.setPrezime(""),
                "Mora baciti IllegalArgumentException za prazno prezime");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jovanovic", "Petrovic", "Nikolic"})
    public void testSetPrezimeValidneVrednosti(String prezime) {
        assertDoesNotThrow(() -> ucesnik.setPrezime(prezime),
                "Ne sme baciti izuzetak za validno prezime: " + prezime);
    }

    @Test
    public void testSetGetEmail() {
        ucesnik.setEmail("ana@gmail.com");
        assertEquals("ana@gmail.com", ucesnik.getEmail(), "Email treba biti ana@gmail.com");
    }

    @Test
    public void testSetEmailNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> ucesnik.setEmail(null),
                "Mora baciti NullPointerException za null email");
    }

    @Test
    public void testSetEmailPrazanStringBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> ucesnik.setEmail(""),
                "Mora baciti IllegalArgumentException za prazan email");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ana@gmail.com", "marko@example.com", "test@test.com"})
    public void testSetEmailValidneVrednosti(String email) {
        assertDoesNotThrow(() -> ucesnik.setEmail(email),
                "Ne sme baciti izuzetak za validan email: " + email);
    }

    @Test
    public void testSetGetNivo() {
        NivoVestine noviNivo = new NivoVestine(2, PlesniNivo.SREDNJI, PlesniStil.TANGO);
        ucesnik.setNivo(noviNivo);
        assertEquals(noviNivo, ucesnik.getNivo(), "Nivo treba biti noviNivo");
    }

    @Test
    public void testSetNivoNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> ucesnik.setNivo(null),
                "Mora baciti NullPointerException za null nivo");
    }

    @Test
    public void testSetGetTelefon() {
        ucesnik.setTelefon("0691234567");
        assertEquals("0691234567", ucesnik.getTelefon(), "Telefon treba biti 0691234567");
    }

    @Test
    public void testSetTelefonNullDozvoljeno() {
        assertDoesNotThrow(() -> ucesnik.setTelefon(null),
                "Null telefon treba biti dozvoljen");
    }

    @Test
    public void testSetTelefonPrazanStringDozvoljeno() {
        assertDoesNotThrow(() -> ucesnik.setTelefon(""),
                "Prazan string za telefon treba biti dozvoljen");
    }

    @Test
    public void testSetTelefonNeispravanFormatBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> ucesnik.setTelefon("abc123"),
                "Mora baciti izuzetak za telefon sa slovima");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0611234567", "0641234567", "+381611234567"})
    public void testSetTelefonValidneVrednosti(String telefon) {
        assertDoesNotThrow(() -> ucesnik.setTelefon(telefon),
                "Ne sme baciti izuzetak za validan telefon: " + telefon);
    }

    @Test
    public void testSetGetDatumRodjenja() {
        LocalDate noviDatum = LocalDate.of(2000, 1, 1);
        ucesnik.setDatumRodjenja(noviDatum);
        assertEquals(noviDatum, ucesnik.getDatumRodjenja(), "Datum rodjenja nije ispravan");
    }

    @Test
    public void testSetDatumRodjenjaNullBacaIzuzetak() {
        assertThrows(NullPointerException.class,
                () -> ucesnik.setDatumRodjenja(null),
                "Mora baciti NullPointerException za null datum");
    }

    @Test
    public void testSetDatumRodjenjaUBuducnostiBacaIzuzetak() {
        assertThrows(IllegalArgumentException.class,
                () -> ucesnik.setDatumRodjenja(LocalDate.now().plusDays(1)),
                "Mora baciti izuzetak za datum u buducnosti");
    }

    @ParameterizedTest
    @CsvSource({
        "1990, 1, 1",
        "2000, 6, 15",
        "1985, 12, 31"
    })
    public void testSetDatumRodjenjaValidneVrednosti(int godina, int mesec, int dan) {
        assertDoesNotThrow(() -> ucesnik.setDatumRodjenja(LocalDate.of(godina, mesec, dan)),
                "Ne sme baciti izuzetak za validan datum");
    }

    @Test
    public void testSetGetNapomena() {
        ucesnik.setNapomena("Nova napomena");
        assertEquals("Nova napomena", ucesnik.getNapomena(), "Napomena treba biti Nova napomena");
    }

    @Test
    public void testSetNapomenaNullDozvoljeno() {
        assertDoesNotThrow(() -> ucesnik.setNapomena(null),
                "Null napomena treba biti dozvoljena");
    }

    @Test
    public void testToString() {
        assertEquals("Marko Petrovic", ucesnik.toString(), "toString treba vratiti 'Marko Petrovic'");
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
        Ucesnik drugi = new Ucesnik(2, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611111111", LocalDate.of(1990, 1, 1), "");
        assertTrue(ucesnik.equals(drugi), "Ucesnici sa istim imenom, prezimenom i emailom moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitEmail() {
        Ucesnik drugi = new Ucesnik(1, "Marko", "Petrovic", "drugi@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
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
        Ucesnik drugi = new Ucesnik(2, ime, prezime, email, nivo,
                "0611111111", LocalDate.of(1990, 1, 1), "");
        assertEquals(ocekivano, ucesnik.equals(drugi),
                "Equals test za: " + ime + " " + prezime + " " + email);
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