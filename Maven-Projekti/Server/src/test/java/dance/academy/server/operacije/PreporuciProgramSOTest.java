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
 * Test klasa za PreporuciProgramSO.
 */
public class PreporuciProgramSOTest {

    private PreporuciProgramSO preporuciProgramSO;
    private Repository mockBroker;
    private ProgramAktivnosti program;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        preporuciProgramSO = new PreporuciProgramSO(mockBroker);
        program = new ProgramAktivnosti(1, "Salsa pocetni", 8, 5000.0, PlesniStil.SALSA,
                true, "Sala A", LocalDate.of(2026, 1, 1), LocalTime.of(18, 0), 20, "");
    }

    @AfterEach
    public void tearDown() {
        preporuciProgramSO = null;
        mockBroker = null;
        program = null;
    }

    @Test
    public void testPredusluviNullParam() {
        assertThrows(Exception.class,
                () -> preporuciProgramSO.izvrsi(null, null),
                "Mora baciti izuzetak za null parametar");
    }

    @Test
    public void testPredusluviPogresanTip() {
        assertThrows(Exception.class,
                () -> preporuciProgramSO.izvrsi("pogresanTip", null),
                "Mora baciti izuzetak za pogresan tip");
    }

    @Test
    public void testPredusluviNullVrsta() {
        ProgramAktivnosti p = new ProgramAktivnosti();
        assertThrows(Exception.class,
                () -> preporuciProgramSO.izvrsi(p, null),
                "Mora baciti izuzetak ako vrsta plesa nije postavljena");
    }

    @Test
    public void testPreporuciValidanProgram() throws Exception {
        List<ProgramAktivnosti> lista = new ArrayList<>();
        lista.add(program);
        Mockito.when(mockBroker.getAll(Mockito.any(ProgramAktivnosti.class), Mockito.anyString()))
                .thenReturn((List) lista);

        preporuciProgramSO.izvrsi(program, null);

        assertNotNull(preporuciProgramSO.getPrograme(), "Lista ne sme biti null");
        assertEquals(1, preporuciProgramSO.getPrograme().size(),
                "Lista treba imati jedan program");
    }

    @Test
    public void testPreporuciPraznaLista() throws Exception {
        List<ProgramAktivnosti> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(ProgramAktivnosti.class), Mockito.anyString()))
                .thenReturn((List) praznaLista);

        preporuciProgramSO.izvrsi(program, null);

        assertTrue(preporuciProgramSO.getPrograme().isEmpty(),
                "Lista treba biti prazna");
    }

    @Test
    public void testPreporuciSortiranoPoCeni() throws Exception {
        List<ProgramAktivnosti> lista = new ArrayList<>();
        ProgramAktivnosti jeftiniji = new ProgramAktivnosti(2, "Salsa osnove", 4, 2000.0,
                PlesniStil.SALSA, true, "Sala B", LocalDate.of(2026, 3, 1),
                LocalTime.of(17, 0), 15, "");
        lista.add(jeftiniji);
        lista.add(program);
        Mockito.when(mockBroker.getAll(Mockito.any(ProgramAktivnosti.class), Mockito.anyString()))
                .thenReturn((List) lista);

        preporuciProgramSO.izvrsi(program, null);

        assertEquals(2, preporuciProgramSO.getPrograme().size(),
                "Lista treba imati 2 programa");
    }
}