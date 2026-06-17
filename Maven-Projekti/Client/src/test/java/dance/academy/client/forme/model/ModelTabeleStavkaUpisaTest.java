package dance.academy.client.forme.model;

import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.StavkaUpisa;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za ModelTabeleStavkaUpisa.
 */
public class ModelTabeleStavkaUpisaTest {

    private ModelTabeleStavkaUpisa model;
    private List<StavkaUpisa> lista;
    private StavkaUpisa stavka;
    private ProgramAktivnosti program;

    @BeforeEach
    public void setUp() {
        program = new ProgramAktivnosti(1, "Salsa pocetni", 8, 5000.0, PlesniStil.SALSA,
                true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, "");
        stavka = new StavkaUpisa(1, 5000.0, null, program);
        lista = new ArrayList<>();
        lista.add(stavka);
        model = new ModelTabeleStavkaUpisa(lista);
    }

    @AfterEach
    public void tearDown() {
        model = null;
        lista = null;
        stavka = null;
        program = null;
    }

    // ===================== BROJ REDOVA =====================

    @Test
    public void testGetRowCountJednaStavka() {
        assertEquals(1, model.getRowCount(), "Treba biti jedan red");
    }

    @Test
    public void testGetRowCountPraznaLista() {
        ModelTabeleStavkaUpisa prazan = new ModelTabeleStavkaUpisa(new ArrayList<>());
        assertEquals(0, prazan.getRowCount(), "Treba biti nula redova");
    }

    @Test
    public void testGetRowCountViseStavki() {
        ProgramAktivnosti program2 = new ProgramAktivnosti(2, "Tango srednji", 6, 4000.0,
                PlesniStil.TANGO, true, "Sala B", LocalDate.of(2026, 2, 1),
                LocalTime.of(19, 0), 15, "");
        lista.add(new StavkaUpisa(2, 4000.0, null, program2));
        assertEquals(2, model.getRowCount(), "Treba biti dva reda");
    }

    // ===================== BROJ KOLONA =====================

    @Test
    public void testGetColumnCount() {
        assertEquals(3, model.getColumnCount(), "Treba biti 3 kolone");
    }

    // ===================== NAZIVI KOLONA =====================

    @Test
    public void testGetColumnNameRedniBroj() {
        assertEquals("Redni broj", model.getColumnName(0), "Prva kolona treba biti Redni broj");
    }

    @Test
    public void testGetColumnNameNazivPrograma() {
        assertEquals("Naziv programa", model.getColumnName(1), "Druga kolona treba biti Naziv programa");
    }

    @Test
    public void testGetColumnNameIznos() {
        assertEquals("Iznos", model.getColumnName(2), "Treca kolona treba biti Iznos");
    }

    // ===================== VREDNOSTI CELIJA =====================

    @Test
    public void testGetValueAtRedniBroj() {
        assertEquals(1, model.getValueAt(0, 0), "Redni broj treba biti 1");
    }

    @Test
    public void testGetValueAtNazivPrograma() {
        assertEquals("Salsa pocetni", model.getValueAt(0, 1), "Naziv programa treba biti Salsa pocetni");
    }

    @Test
    public void testGetValueAtIznos() {
        assertEquals(5000.0, model.getValueAt(0, 2), "Iznos treba biti 5000.0");
    }

    @Test
    public void testGetValueAtDefaultKolona() {
        assertEquals("N/A", model.getValueAt(0, 99), "Default vrednost treba biti N/A");
    }

    // ===================== VISE STAVKI =====================

    @Test
    public void testGetValueAtDrugaStavka() {
        ProgramAktivnosti program2 = new ProgramAktivnosti(2, "Tango srednji", 6, 4000.0,
                PlesniStil.TANGO, true, "Sala B", LocalDate.of(2026, 2, 1),
                LocalTime.of(19, 0), 15, "");
        lista.add(new StavkaUpisa(2, 4000.0, null, program2));
        assertEquals("Tango srednji", model.getValueAt(1, 1), "Naziv drugog programa treba biti Tango srednji");
    }

    @Test
    public void testGetValueAtIznosDrugeStavke() {
        ProgramAktivnosti program2 = new ProgramAktivnosti(2, "Tango srednji", 6, 4000.0,
                PlesniStil.TANGO, true, "Sala B", LocalDate.of(2026, 2, 1),
                LocalTime.of(19, 0), 15, "");
        lista.add(new StavkaUpisa(2, 4000.0, null, program2));
        assertEquals(4000.0, model.getValueAt(1, 2), "Iznos druge stavke treba biti 4000.0");
    }

    // ===================== LISTA =====================

    @Test
    public void testGetLista() {
        assertNotNull(model.getLista(), "Lista ne sme biti null");
    }

    @Test
    public void testGetListaSadrziStavku() {
        assertTrue(model.getLista().contains(stavka), "Lista treba sadrzati stavku");
    }
}