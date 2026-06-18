package dance.academy.server.operacije;

import dance.academy.common.model.Instruktor;
import dance.academy.server.repozitorijum.Repository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za UcitajInstruktoreSO.
 */
public class UcitajInstruktoreSOTest {

    private UcitajInstruktoreSO ucitajInstruktoreSO;
    private Repository mockBroker;
    private Instruktor instruktor;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        ucitajInstruktoreSO = new UcitajInstruktoreSO(mockBroker);
        instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
    }

    @AfterEach
    public void tearDown() {
        ucitajInstruktoreSO = null;
        mockBroker = null;
        instruktor = null;
    }

    @Test
    public void testUcitajInstruktore() throws Exception {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktor);
        Mockito.when(mockBroker.getAll(Mockito.any(Instruktor.class), Mockito.isNull()))
                .thenReturn((List) lista);

        ucitajInstruktoreSO.izvrsi(new Instruktor(), null);

        assertNotNull(ucitajInstruktoreSO.getInstruktori(), "Lista ne sme biti null");
        assertEquals(1, ucitajInstruktoreSO.getInstruktori().size(),
                "Lista treba imati jednog instruktora");
    }

    @Test
    public void testUcitajInstruktorePraznaLista() throws Exception {
        List<Instruktor> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(Instruktor.class), Mockito.isNull()))
                .thenReturn((List) praznaLista);

        ucitajInstruktoreSO.izvrsi(new Instruktor(), null);

        assertTrue(ucitajInstruktoreSO.getInstruktori().isEmpty(),
                "Lista treba biti prazna");
    }

    @Test
    public void testUcitajViseInstruktora() throws Exception {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktor);
        lista.add(new Instruktor(2, "Ana", "Anic", "ana@gmail.com", "sifra456"));
        Mockito.when(mockBroker.getAll(Mockito.any(Instruktor.class), Mockito.isNull()))
                .thenReturn((List) lista);

        ucitajInstruktoreSO.izvrsi(new Instruktor(), null);

        assertEquals(2, ucitajInstruktoreSO.getInstruktori().size(),
                "Lista treba imati 2 instruktora");
    }

    @Test
    public void testNemaPreduslova() {
        assertDoesNotThrow(() -> ucitajInstruktoreSO.izvrsi(new Instruktor(), null),
                "Ne sme baciti izuzetak");
    }
}