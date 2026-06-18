package dance.academy.server.operacije;

import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Ucesnik;
import dance.academy.server.repozitorijum.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za UcitajUcesnikeSO.
 */
public class UcitajUcesnikeSOTest {

    private UcitajUcesnikeSO ucitajUcesnikeSO;
    private Repository mockBroker;
    private Ucesnik ucesnik;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        ucitajUcesnikeSO = new UcitajUcesnikeSO(mockBroker);
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
    }

    @AfterEach
    public void tearDown() {
        ucitajUcesnikeSO = null;
        mockBroker = null;
        ucesnik = null;
    }

    @Test
    public void testUcitajUcesnike() throws Exception {
        List<Ucesnik> lista = new ArrayList<>();
        lista.add(ucesnik);
        Mockito.when(mockBroker.getAll(Mockito.any(Ucesnik.class), Mockito.anyString()))
                .thenReturn((List) lista);

        ucitajUcesnikeSO.izvrsi(new Ucesnik(), null);

        assertNotNull(ucitajUcesnikeSO.getUcesnici(), "Lista ne sme biti null");
    }

    @Test
    public void testUcitajUcesnikePraznaLista() throws Exception {
        List<Ucesnik> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(Ucesnik.class), Mockito.anyString()))
                .thenReturn((List) praznaLista);

        ucitajUcesnikeSO.izvrsi(new Ucesnik(), null);

        assertTrue(ucitajUcesnikeSO.getUcesnici().isEmpty(),
                "Lista treba biti prazna");
    }

    @Test
    public void testUcitajViseUcesnika() throws Exception {
        List<Ucesnik> lista = new ArrayList<>();
        lista.add(ucesnik);
        NivoVestine nivo2 = new NivoVestine(2, PlesniNivo.SREDNJI, PlesniStil.TANGO);
        lista.add(new Ucesnik(2, "Ana", "Anic", "ana@gmail.com", nivo2,
                "069111", LocalDate.of(2000, 1, 1), ""));
        Mockito.when(mockBroker.getAll(Mockito.any(Ucesnik.class), Mockito.anyString()))
                .thenReturn((List) lista);

        ucitajUcesnikeSO.izvrsi(new Ucesnik(), null);

        assertEquals(2, ucitajUcesnikeSO.getUcesnici().size(),
                "Lista treba imati 2 ucesnika");
    }

    @Test
    public void testNemaPreduslova() {
        assertDoesNotThrow(() -> ucitajUcesnikeSO.izvrsi(new Ucesnik(), null),
                "Ne sme baciti izuzetak");
    }
}