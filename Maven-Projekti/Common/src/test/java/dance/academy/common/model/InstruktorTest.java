package dance.academy.common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za klasu Instruktor.
 */
public class InstruktorTest {

    private Instruktor instruktor;

    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
    }

    @AfterEach
    public void tearDown() {
        instruktor = null;
    }

    @Test
    public void testBesparametarskiKonstruktor() {
        Instruktor i = new Instruktor();
        assertNull(i.getIme(), "Ime treba biti null");
        assertNull(i.getPrezime(), "Prezime treba biti null");
        assertNull(i.getEmail(), "Email treba biti null");
        assertNull(i.getSifra(), "Sifra treba biti null");
    }

    @Test
    public void testParametrizovaniKonstruktor() {
        assertEquals(1, instruktor.getId(), "ID treba biti 1");
        assertEquals("Nikola", instruktor.getIme(), "Ime treba biti Nikola");
        assertEquals("Nikolic", instruktor.getPrezime(), "Prezime treba biti Nikolic");
        assertEquals("nikola@gmail.com", instruktor.getEmail(), "Email nije ispravan");
        assertEquals("sifra123", instruktor.getSifra(), "Sifra nije ispravna");
    }


    @Test
    public void testSetGetId() {
        instruktor.setId(99);
        assertEquals(99, instruktor.getId(), "ID treba biti 99");
    }

    @Test
    public void testSetGetIme() {
        instruktor.setIme("Petar");
        assertEquals("Petar", instruktor.getIme(), "Ime treba biti Petar");
    }

    @Test
    public void testSetGetPrezime() {
        instruktor.setPrezime("Petrovic");
        assertEquals("Petrovic", instruktor.getPrezime(), "Prezime treba biti Petrovic");
    }

    @Test
    public void testSetGetEmail() {
        instruktor.setEmail("petar@gmail.com");
        assertEquals("petar@gmail.com", instruktor.getEmail(), "Email treba biti petar@gmail.com");
    }

    @Test
    public void testSetGetSifra() {
        instruktor.setSifra("novaSifra");
        assertEquals("novaSifra", instruktor.getSifra(), "Sifra treba biti novaSifra");
    }


    @Test
    public void testToString() {
        assertEquals("Nikola Nikolic", instruktor.toString(), "toString treba vratiti 'Nikola Nikolic'");
    }

    @Test
    public void testToStringNullIme() {
        instruktor.setIme(null);
        assertEquals("null Nikolic", instruktor.toString(), "toString sa null imenom");
    }


    @Test
    public void testEqualsIstObjekat() {
        assertTrue(instruktor.equals(instruktor), "Instruktor mora biti jednak sam sebi");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(instruktor.equals(null), "Instruktor ne sme biti jednak null");
    }

    @Test
    public void testEqualsDrugiTip() {
        assertFalse(instruktor.equals("neki string"), "Instruktor ne sme biti jednak stringu");
    }

    @Test
    public void testEqualsJednaki() {
        Instruktor drugi = new Instruktor(2, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        assertTrue(instruktor.equals(drugi), "Instruktori sa istim emailom i sifrom moraju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitaSifra() {
        Instruktor drugi = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "drugaSifra");
        assertFalse(instruktor.equals(drugi), "Instruktori sa razlicitom sifrom ne smeju biti jednaki");
    }

    @Test
    public void testEqualsRazlicitEmail() {
        Instruktor drugi = new Instruktor(1, "Nikola", "Nikolic", "drugi@gmail.com", "sifra123");
        assertFalse(instruktor.equals(drugi), "Instruktori sa razlicitim emailom ne smeju biti jednaki");
    }

    @ParameterizedTest
    @CsvSource({
        "nikola@gmail.com, sifra123, true",
        "drugi@gmail.com, sifra123, false",
        "nikola@gmail.com, drugaSifra, false",
        "drugi@gmail.com, drugaSifra, false"
    })
    public void testEqualsParametrizovano(String email, String sifra, boolean ocekivano) {
        Instruktor drugi = new Instruktor(2, "Nikola", "Nikolic", email, sifra);
        assertEquals(ocekivano, instruktor.equals(drugi),
                "Equals test za email: " + email + " sifra: " + sifra);
    }


    @Test
    public void testVratiNazivTabele() {
        assertEquals("instruktor", instruktor.vratiNazivTabele(), "Naziv tabele treba biti 'instruktor'");
    }


    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,email,sifra",
                instruktor.vratiKoloneZaUbacivanje(), "Kolone za ubacivanje nisu ispravne");
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("instruktor.id=1", instruktor.vratiPrimarniKljuc(), "Primarni kljuc nije ispravan");
    }

    @Test
    public void testVratiObjekatIzRSBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> instruktor.vratiObjekatIzRS(null),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaUbacivanjeBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> instruktor.vratiVrednostiZaUbacivanje(),
                "Treba baciti UnsupportedOperationException");
    }

    @Test
    public void testVratiVrednostiZaIzmeniBacaIzuzetak() {
        assertThrows(UnsupportedOperationException.class,
                () -> instruktor.vratiVrednostiZaIzmenu(),
                "Treba baciti UnsupportedOperationException");
    }
}