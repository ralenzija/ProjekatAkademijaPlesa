package dance.academy.client.forme.model;

import dance.academy.common.model.Instruktor;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.StatusUpisa;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za ModelTabeleUpis.
 */
public class ModelTabeleUpisTest {

    private ModelTabeleUpis model;
    private List<UpisNaProgram> lista;
    private UpisNaProgram upis;
    private Instruktor instruktor;
    private Ucesnik ucesnik;
    private Date datum;

    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
        datum = new Date();
        upis = new UpisNaProgram(1, datum, StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik);
        lista = new ArrayList<>();
        lista.add(upis);
        model = new ModelTabeleUpis(lista);
    }

    @AfterEach
    public void tearDown() {
        model = null;
        lista = null;
        upis = null;
        instruktor = null;
        ucesnik = null;
        datum = null;
    }


    @Test
    public void testGetRowCountJedanUpis() {
        assertEquals(1, model.getRowCount(), "Treba biti jedan red");
    }

    @Test
    public void testGetRowCountPraznaLista() {
        ModelTabeleUpis prazan = new ModelTabeleUpis(new ArrayList<>());
        assertEquals(0, prazan.getRowCount(), "Treba biti nula redova");
    }

    @Test
    public void testGetRowCountViseUpisa() {
        lista.add(new UpisNaProgram(2, datum, StatusUpisa.POTVRDJEN, 3000.0, instruktor, ucesnik));
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
    public void testGetColumnNameDatum() {
        assertEquals("Datum", model.getColumnName(1), "Druga kolona treba biti Datum");
    }

    @Test
    public void testGetColumnNameStatus() {
        assertEquals("Status", model.getColumnName(2), "Treca kolona treba biti Status");
    }

    @Test
    public void testGetColumnNameUkupanIznos() {
        assertEquals("Ukupan iznos", model.getColumnName(3), "Cetvrta kolona treba biti Ukupan iznos");
    }

    @Test
    public void testGetColumnNameInstruktor() {
        assertEquals("Instruktor", model.getColumnName(4), "Peta kolona treba biti Instruktor");
    }

    @Test
    public void testGetColumnNameUcesnik() {
        assertEquals("Ucesnik", model.getColumnName(5), "Sesta kolona treba biti Ucesnik");
    }


    @Test
    public void testGetValueAtID() {
        assertEquals(1, model.getValueAt(0, 0), "ID treba biti 1");
    }

    @Test
    public void testGetValueAtDatum() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String ocekivani = sdf.format(datum);
        assertEquals(ocekivani, model.getValueAt(0, 1), "Datum nije ispravan");
    }

    @Test
    public void testGetValueAtStatus() {
        assertEquals(StatusUpisa.KREIRAN, model.getValueAt(0, 2), "Status treba biti KREIRAN");
    }

    @Test
    public void testGetValueAtUkupanIznos() {
        assertEquals(5000.0, model.getValueAt(0, 3), "Ukupan iznos treba biti 5000.0");
    }

    @Test
    public void testGetValueAtInstruktor() {
        assertEquals("Nikola Nikolic", model.getValueAt(0, 4), "Instruktor treba biti Nikola Nikolic");
    }

    @Test
    public void testGetValueAtUcesnik() {
        assertEquals("Marko Petrovic", model.getValueAt(0, 5), "Ucesnik treba biti Marko Petrovic");
    }

    @Test
    public void testGetValueAtDefaultKolona() {
        assertEquals("N/A", model.getValueAt(0, 99), "Default vrednost treba biti N/A");
    }


    @Test
    public void testSortiranjePoID() {
        UpisNaProgram upis2 = new UpisNaProgram(2, datum, StatusUpisa.POTVRDJEN, 3000.0, instruktor, ucesnik);
        UpisNaProgram upis3 = new UpisNaProgram(3, datum, StatusUpisa.ODBIJEN, 2000.0, instruktor, ucesnik);
        List<UpisNaProgram> nesortirana = new ArrayList<>();
        nesortirana.add(upis3);
        nesortirana.add(upis2);
        nesortirana.add(upis);
        ModelTabeleUpis m = new ModelTabeleUpis(nesortirana);
        assertEquals(1, m.getValueAt(0, 0), "Prvi red treba biti upis sa ID 1");
        assertEquals(2, m.getValueAt(1, 0), "Drugi red treba biti upis sa ID 2");
        assertEquals(3, m.getValueAt(2, 0), "Treci red treba biti upis sa ID 3");
    }


    @Test
    public void testGetLista() {
        assertNotNull(model.getLista(), "Lista ne sme biti null");
    }

    @Test
    public void testGetListaSadrziUpis() {
        assertTrue(model.getLista().contains(upis), "Lista treba sadrzati upis");
    }
}