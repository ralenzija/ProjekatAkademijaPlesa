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
 * Test klasa za PretraziUcesnikeSO.
 */
public class PretraziUcesnikeSOTest {

    private PretraziUcesnikeSO pretraziUcesnikeSO;
    private Repository mockBroker;
    private Ucesnik ucesnik;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        pretraziUcesnikeSO = new PretraziUcesnikeSO(mockBroker);
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
    }

    @AfterEach
    public void tearDown() {
        pretraziUcesnikeSO = null;
        mockBroker = null;
        ucesnik = null;
    }

    @Test
    public void testPretraziPoImenu() throws Exception {
        List<Ucesnik> lista = new ArrayList<>();
        lista.add(ucesnik);
        Mockito.when(mockBroker.getAll(Mockito.any(Ucesnik.class), Mockito.anyString()))
                .thenReturn((List) lista);

        ucesnik.setEmail(null);
        pretraziUcesnikeSO.izvrsi(ucesnik, null);

        assertEquals(1, pretraziUcesnikeSO.getUcesnici().size(),
                "Treba vratiti jednog ucesnika");
    }

    @Test
    public void testPretraziPoEmailu() throws Exception {
        List<Ucesnik> lista = new ArrayList<>();
        lista.add(ucesnik);
        Mockito.when(mockBroker.getAll(Mockito.any(Ucesnik.class), Mockito.anyString()))
                .thenReturn((List) lista);

        ucesnik.setIme(null);
        pretraziUcesnikeSO.izvrsi(ucesnik, null);

        assertEquals(1, pretraziUcesnikeSO.getUcesnici().size(),
                "Treba vratiti jednog ucesnika po emailu");
    }

    @Test
    public void testPretraziPraznaLista() throws Exception {
        List<Ucesnik> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(Ucesnik.class), Mockito.anyString()))
                .thenReturn((List) praznaLista);

        pretraziUcesnikeSO.izvrsi(ucesnik, null);

        assertTrue(pretraziUcesnikeSO.getUcesnici().isEmpty(),
                "Lista treba biti prazna");
    }

    @Test
    public void testNemaPreduslova() {
        assertDoesNotThrow(() -> pretraziUcesnikeSO.izvrsi(ucesnik, null),
                "Ne sme baciti izuzetak");
    }
}