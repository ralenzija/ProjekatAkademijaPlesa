package dance.academy.client.forme.model;

import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Ucesnik;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za ModelTabeleUcesnik.
 */
public class ModelTabeleUcesnikTest {

    private ModelTabeleUcesnik model;
    private List<Ucesnik> lista;
    private Ucesnik ucesnik;

    @BeforeEach
    public void setUp() {
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
        lista = new ArrayList<>();
        lista.add(ucesnik);
        model = new ModelTabeleUcesnik(lista);
    }

    @AfterEach
    public void tearDown() {
        model = null;
        lista = null;
        ucesnik = null;
    }


    @Test
    public void testGetRowCountJedanUcesnik() {
        assertEquals(1, model.getRowCount(), "Treba biti jedan red");
    }

    @Test
    public void testGetRowCountPraznaLista() {
        ModelTabeleUcesnik prazan = new ModelTabeleUcesnik(new ArrayList<>());
        assertEquals(0, prazan.getRowCount(), "Treba biti nula redova");
    }

    @Test
    public void testGetRowCountViseUcesnika() {
        NivoVestine nivo2 = new NivoVestine(2, PlesniNivo.SREDNJI, PlesniStil.TANGO);
        lista.add(new Ucesnik(2, "Ana", "Anic", "ana@gmail.com", nivo2,
                "069111", LocalDate.of(2000, 1, 1), ""));
        assertEquals(2, model.getRowCount(), "Treba biti dva reda");
    }


    @Test
    public void testGetColumnCount() {
        assertEquals(6, model.getColumnCount(), "Treba biti 6 kolona");
    }


    @Test
    public void testGetColumnNameID() {
        assertEquals("ID", model.getColumnName(0), "Prva kolona treba biti ID");
    }

    @Test
    public void testGetColumnNameIme() {
        assertEquals("Ime i prezime", model.getColumnName(1), "Druga kolona treba biti Ime i prezime");
    }

    @Test
    public void testGetColumnNameEmail() {
        assertEquals("Email", model.getColumnName(2), "Treca kolona treba biti Email");
    }

    @Test
    public void testGetColumnNameNivo() {
        assertEquals("Nivo vestine", model.getColumnName(3), "Cetvrta kolona treba biti Nivo vestine");
    }

    @Test
    public void testGetColumnNameTelefon() {
        assertEquals("Telefon", model.getColumnName(4), "Peta kolona treba biti Telefon");
    }

    @Test
    public void testGetColumnNameDatum() {
        assertEquals("Datum Rodjenja", model.getColumnName(5), "Sesta kolona treba biti Datum Rodjenja");
    }


    @Test
    public void testGetValueAtID() {
        assertEquals(1, model.getValueAt(0, 0), "ID treba biti 1");
    }

    @Test
    public void testGetValueAtIme() {
        assertEquals("Marko Petrovic", model.getValueAt(0, 1), "Ime treba biti Marko Petrovic");
    }

    @Test
    public void testGetValueAtEmail() {
        assertEquals("marko@gmail.com", model.getValueAt(0, 2), "Email nije ispravan");
    }

    @Test
    public void testGetValueAtNivo() {
        assertEquals("POCETNI - SALSA", model.getValueAt(0, 3), "Nivo nije ispravan");
    }

    @Test
    public void testGetValueAtTelefon() {
        assertEquals("0611234567", model.getValueAt(0, 4), "Telefon nije ispravan");
    }

    @Test
    public void testGetValueAtDatum() {
        assertEquals("1995.03.15", model.getValueAt(0, 5), "Datum nije ispravan");
    }

    @Test
    public void testGetValueAtDefaultKolona() {
        assertEquals("NA", model.getValueAt(0, 99), "Default vrednost treba biti NA");
    }


    @Test
    public void testGetLista() {
        assertEquals(lista, model.getLista(), "Lista nije ispravna");
    }

    @Test
    public void testGetListaNijeNull() {
        assertNotNull(model.getLista(), "Lista ne sme biti null");
    }
}