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
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za PromeniStatusUpisaSO.
 */
public class PromeniStatusUpisaSOTest {

    private PromeniStatusUpisaSO promeniStatusUpisaSO;
    private Repository mockBroker;
    private UpisNaProgram upis;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        promeniStatusUpisaSO = new PromeniStatusUpisaSO(mockBroker);

        Instruktor instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        Ucesnik ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");

        upis = new UpisNaProgram(1, new Date(), StatusUpisa.KREIRAN, 5000.0, instruktor, ucesnik);
    }

    @AfterEach
    public void tearDown() {
        promeniStatusUpisaSO = null;
        mockBroker = null;
        upis = null;
    }

    @Test
    public void testPredusluviNullParam() {
        assertThrows(Exception.class,
                () -> promeniStatusUpisaSO.izvrsi(null, null),
                "Mora baciti izuzetak za null parametar");
    }

    @Test
    public void testPredusluviPogresanTip() {
        assertThrows(Exception.class,
                () -> promeniStatusUpisaSO.izvrsi("pogresanTip", null),
                "Mora baciti izuzetak za pogresan tip parametra");
    }

    @Test
    public void testPredusluviIDNula() {
        upis.setId(0);
        assertThrows(Exception.class,
                () -> promeniStatusUpisaSO.izvrsi(upis, null),
                "Mora baciti izuzetak za ID <= 0");
    }

    @Test
    public void testPredusluviNegativanID() {
        upis.setId(-1);
        assertThrows(Exception.class,
                () -> promeniStatusUpisaSO.izvrsi(upis, null),
                "Mora baciti izuzetak za negativan ID");
    }

    @Test
    public void testPredusluviNullStatus() {
        upis.setStatus(null);
        assertThrows(Exception.class,
                () -> promeniStatusUpisaSO.izvrsi(upis, null),
                "Mora baciti izuzetak za null status");
    }

    @Test
    public void testPromeniStatusValidanUpis() {
        assertDoesNotThrow(() -> promeniStatusUpisaSO.izvrsi(upis, null),
                "Ne sme baciti izuzetak za validan upis");
    }

    @Test
    public void testPromeniStatusPozivaEdit() throws Exception {
        promeniStatusUpisaSO.izvrsi(upis, null);
        Mockito.verify(mockBroker, Mockito.times(1)).edit(upis);
    }

    @Test
    public void testGetUpisNakonPromene() throws Exception {
        promeniStatusUpisaSO.izvrsi(upis, null);
        assertNotNull(promeniStatusUpisaSO.getUpis(), "Upis ne sme biti null nakon promene");
    }
}