package dance.academy.server.operacije;

import dance.academy.common.model.Instruktor;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.StatusUpisa;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.server.repozitorijum.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za UcitajUpiseSO.
 */
public class UcitajUpiseSOTest {

    private UcitajUpiseSO ucitajUpiseSO;
    private Repository mockBroker;
    private UpisNaProgram upis;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        ucitajUpiseSO = new UcitajUpiseSO(mockBroker);

        Instruktor instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        Ucesnik ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");

        upis = new UpisNaProgram(1, new Date(), StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik);
    }

    @AfterEach
    public void tearDown() {
        ucitajUpiseSO = null;
        mockBroker = null;
        upis = null;
    }

    @Test
    public void testUcitajUpise() throws Exception {
        List<UpisNaProgram> lista = new ArrayList<>();
        lista.add(upis);
        Mockito.when(mockBroker.getAll(Mockito.any(UpisNaProgram.class), Mockito.anyString()))
                .thenReturn((List) lista);

        ucitajUpiseSO.izvrsi(new UpisNaProgram(), null);

        assertNotNull(ucitajUpiseSO.getUpise(), "Lista ne sme biti null");
        assertEquals(1, ucitajUpiseSO.getUpise().size(), "Lista treba imati jedan upis");
    }

    @Test
    public void testUcitajUpisePraznaLista() throws Exception {
        List<UpisNaProgram> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(UpisNaProgram.class), Mockito.anyString()))
                .thenReturn((List) praznaLista);

        ucitajUpiseSO.izvrsi(new UpisNaProgram(), null);

        assertTrue(ucitajUpiseSO.getUpise().isEmpty(), "Lista treba biti prazna");
    }

    @Test
    public void testUcitajViseUpisa() throws Exception {
        List<UpisNaProgram> lista = new ArrayList<>();
        lista.add(upis);
        lista.add(new UpisNaProgram(2, new Date(), StatusUpisa.POTVRDJEN, 3000.0, null, null));
        Mockito.when(mockBroker.getAll(Mockito.any(UpisNaProgram.class), Mockito.anyString()))
                .thenReturn((List) lista);

        ucitajUpiseSO.izvrsi(new UpisNaProgram(), null);

        assertEquals(2, ucitajUpiseSO.getUpise().size(), "Lista treba imati 2 upisa");
    }

    @Test
    public void testNemaPreduslova() {
        assertDoesNotThrow(() -> ucitajUpiseSO.izvrsi(new UpisNaProgram(), null),
                "Ne sme baciti izuzetak");
    }
}