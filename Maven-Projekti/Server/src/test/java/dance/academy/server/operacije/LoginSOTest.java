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
 * Test klasa za LoginSO.
 */
public class LoginSOTest {

    private LoginSO loginSO;
    private Repository mockBroker;
    private Instruktor instruktor;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        loginSO = new LoginSO(mockBroker);
        instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
    }

    @AfterEach
    public void tearDown() {
        loginSO = null;
        mockBroker = null;
        instruktor = null;
    }

    @Test
    public void testLoginUspesan() throws Exception {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktor);
        Mockito.when(mockBroker.getAll(Mockito.any(Instruktor.class), Mockito.isNull()))
                .thenReturn((List) lista);

        loginSO.izvrsi(instruktor, null);

        assertEquals(instruktor, loginSO.getI(), "Prijavljeni instruktor treba biti isti");
    }

    @Test
    public void testLoginNeuspesan() throws Exception {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(new Instruktor(2, "Ana", "Anic", "ana@gmail.com", "drugaSifra"));
        Mockito.when(mockBroker.getAll(Mockito.any(Instruktor.class), Mockito.isNull()))
                .thenReturn((List) lista);

        loginSO.izvrsi(instruktor, null);

        assertEquals(-1, loginSO.getI().getId(), "Neuspesan login treba vratiti instruktora sa ID-jem -1");
    }

    @Test
    public void testLoginPraznaLista() throws Exception {
        List<Instruktor> lista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(Instruktor.class), Mockito.isNull()))
                .thenReturn((List) lista);

        loginSO.izvrsi(instruktor, null);

        assertEquals(-1, loginSO.getI().getId(), "Prazna lista treba vratiti instruktora sa ID-jem -1");
    }

    @Test
    public void testPreduslovi() {
        assertDoesNotThrow(() -> loginSO.izvrsi(instruktor, null),
                "Login ne sme baciti izuzetak pri normalnom pozivanju");
    }
}