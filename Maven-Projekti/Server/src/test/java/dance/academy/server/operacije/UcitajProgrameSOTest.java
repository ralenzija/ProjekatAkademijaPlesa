package dance.academy.server.operacije;

import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.server.repozitorijum.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za UcitajProgrameSO.
 */
public class UcitajProgrameSOTest {

    private UcitajProgrameSO ucitajProgrameSO;
    private Repository mockBroker;
    private ProgramAktivnosti program;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        ucitajProgrameSO = new UcitajProgrameSO(mockBroker);
        program = new ProgramAktivnosti(1, "Salsa pocetni", 8, 5000.0, PlesniStil.SALSA,
                true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, "");
    }

    @AfterEach
    public void tearDown() {
        ucitajProgrameSO = null;
        mockBroker = null;
        program = null;
    }

    @Test
    public void testUcitajPrograme() throws Exception {
        List<ProgramAktivnosti> lista = new ArrayList<>();
        lista.add(program);
        Mockito.when(mockBroker.getAll(Mockito.any(ProgramAktivnosti.class), Mockito.isNull()))
                .thenReturn((List) lista);

        ucitajProgrameSO.izvrsi(new ProgramAktivnosti(), null);

        assertNotNull(ucitajProgrameSO.getProgrami(), "Lista ne sme biti null");
        assertEquals(1, ucitajProgrameSO.getProgrami().size(),
                "Lista treba imati jedan program");
    }

    @Test
    public void testUcitajProgramePraznaLista() throws Exception {
        List<ProgramAktivnosti> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(ProgramAktivnosti.class), Mockito.isNull()))
                .thenReturn((List) praznaLista);

        ucitajProgrameSO.izvrsi(new ProgramAktivnosti(), null);

        assertTrue(ucitajProgrameSO.getProgrami().isEmpty(), "Lista treba biti prazna");
    }

    @Test
    public void testUcitajVisePrograma() throws Exception {
        List<ProgramAktivnosti> lista = new ArrayList<>();
        lista.add(program);
        lista.add(new ProgramAktivnosti(2, "Tango srednji", 6, 4000.0, PlesniStil.TANGO,
                true, "Sala B", LocalDate.of(2026, 2, 1), LocalTime.of(19, 0), 15, ""));
        Mockito.when(mockBroker.getAll(Mockito.any(ProgramAktivnosti.class), Mockito.isNull()))
                .thenReturn((List) lista);

        ucitajProgrameSO.izvrsi(new ProgramAktivnosti(), null);

        assertEquals(2, ucitajProgrameSO.getProgrami().size(),
                "Lista treba imati 2 programa");
    }

    @Test
    public void testNemaPreduslova() {
        assertDoesNotThrow(() -> ucitajProgrameSO.izvrsi(new ProgramAktivnosti(), null),
                "Ne sme baciti izuzetak");
    }
}