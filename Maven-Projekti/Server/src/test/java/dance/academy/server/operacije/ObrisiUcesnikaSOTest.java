package dance.academy.server.operacije;

import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
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
 * Test klasa za ObrisiUcesnikaSO.
 */
public class ObrisiUcesnikaSOTest {

    private ObrisiUcesnikaSO obrisiUcesnikaSO;
    private Repository mockBroker;
    private Ucesnik ucesnik;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        obrisiUcesnikaSO = new ObrisiUcesnikaSO(mockBroker);
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");
    }

    @AfterEach
    public void tearDown() {
        obrisiUcesnikaSO = null;
        mockBroker = null;
        ucesnik = null;
    }

    @Test
    public void testPredusluviNullParam() {
        assertThrows(Exception.class,
                () -> obrisiUcesnikaSO.izvrsi(null, null),
                "Mora baciti izuzetak za null parametar");
    }

    @Test
    public void testPredusluviPogresanTip() {
        assertThrows(Exception.class,
                () -> obrisiUcesnikaSO.izvrsi("pogresanTip", null),
                "Mora baciti izuzetak za pogresan tip parametra");
    }

    @Test
    public void testPredusluviUcesnikSaUpisima() throws Exception {
        List<UpisNaProgram> upisi = new ArrayList<>();
        upisi.add(new UpisNaProgram());
        Mockito.when(mockBroker.getAll(Mockito.any(UpisNaProgram.class), Mockito.anyString()))
                .thenReturn((List) upisi);

        assertThrows(Exception.class,
                () -> obrisiUcesnikaSO.izvrsi(ucesnik, null),
                "Mora baciti izuzetak ako ucesnik ima upise");
    }

    @Test
    public void testObrisiUcesnikaBezUpisa() throws Exception {
        List<UpisNaProgram> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(UpisNaProgram.class), Mockito.anyString()))
                .thenReturn((List) praznaLista);

        assertDoesNotThrow(() -> obrisiUcesnikaSO.izvrsi(ucesnik, null),
                "Ne sme baciti izuzetak za ucesnika bez upisa");
    }

    @Test
    public void testObrisiPozivaDelete() throws Exception {
        List<UpisNaProgram> praznaLista = new ArrayList<>();
        Mockito.when(mockBroker.getAll(Mockito.any(UpisNaProgram.class), Mockito.anyString()))
                .thenReturn((List) praznaLista);

        obrisiUcesnikaSO.izvrsi(ucesnik, null);
        Mockito.verify(mockBroker, Mockito.times(1)).delete(ucesnik, null);
    }
}