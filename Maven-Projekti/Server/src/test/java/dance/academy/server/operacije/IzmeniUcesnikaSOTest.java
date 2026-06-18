package dance.academy.server.operacije;

import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Ucesnik;
import dance.academy.server.repozitorijum.Repository;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za IzmeniUcesnikaSO.
 */
public class IzmeniUcesnikaSOTest {

    private IzmeniUcesnikaSO izmeniUcesnikaSO;
    private Repository mockBroker;
    private Ucesnik ucesnik;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        izmeniUcesnikaSO = new IzmeniUcesnikaSO(mockBroker);
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
    }

    @AfterEach
    public void tearDown() {
        izmeniUcesnikaSO = null;
        mockBroker = null;
        ucesnik = null;
    }

    @Test
    public void testPredusluviValidanUcesnik() {
        assertDoesNotThrow(() -> izmeniUcesnikaSO.izvrsi(ucesnik, null),
                "Ne sme baciti izuzetak za validan ucesnik");
    }

    @Test
    public void testPredusluviNullParam() {
        assertThrows(Exception.class,
                () -> izmeniUcesnikaSO.izvrsi(null, null),
                "Mora baciti izuzetak za null parametar");
    }

    @Test
    public void testPredusluviPogresanTip() {
        assertThrows(Exception.class,
                () -> izmeniUcesnikaSO.izvrsi("pogresanTip", null),
                "Mora baciti izuzetak za pogresan tip parametra");
    }

    @Test
    public void testIzmeniPozivaEdit() throws Exception {
        izmeniUcesnikaSO.izvrsi(ucesnik, null);
        Mockito.verify(mockBroker, Mockito.times(1)).edit(ucesnik);
    }

    @Test
    public void testGetUNakonIzmene() throws Exception {
        izmeniUcesnikaSO.izvrsi(ucesnik, null);
        assertEquals(ucesnik, izmeniUcesnikaSO.getU(),
                "Ucesnik treba biti isti nakon izmene");
    }
}