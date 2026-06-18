package dance.academy.server.operacije;

import dance.academy.common.model.Instruktor;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.StatusUpisa;
import dance.academy.common.model.StavkaUpisa;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.server.repozitorijum.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za IzmeniUpisSO.
 */
public class IzmeniUpisSOTest {

    private IzmeniUpisSO izmeniUpisSO;
    private Repository mockBroker;
    private UpisNaProgram upis;
    private List<StavkaUpisa> stavke;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        izmeniUpisSO = new IzmeniUpisSO(mockBroker);

        Instruktor instruktor = new Instruktor(1, "Nikola", "Nikolic", "nikola@gmail.com", "sifra123");
        NivoVestine nivo = new NivoVestine(1, PlesniNivo.POCETNI, PlesniStil.SALSA);
        Ucesnik ucesnik = new Ucesnik(1, "Marko", "Petrovic", "marko@gmail.com", nivo,
                "0611234567", LocalDate.of(1995, 3, 15), "");

        ProgramAktivnosti program = new ProgramAktivnosti(1, "Salsa pocetni", 8, 5000.0,
                PlesniStil.SALSA, true, "Sala A", LocalDate.of(2026, 1, 1),
                LocalTime.of(18, 0), 20, "");

        stavke = new ArrayList<>();
        StavkaUpisa stavka = new StavkaUpisa();
        stavka.setProgram(program);
        stavka.setIznos(5000.0);
        stavke.add(stavka);

        upis = new UpisNaProgram(1, new Date(), StatusUpisa.KREIRAN, 5000.0,
                instruktor, ucesnik, stavke);
    }

    @AfterEach
    public void tearDown() {
        izmeniUpisSO = null;
        mockBroker = null;
        upis = null;
        stavke = null;
    }

    @Test
    public void testPredusluviNullParam() {
        assertThrows(Exception.class,
                () -> izmeniUpisSO.izvrsi(null, null),
                "Mora baciti izuzetak za null parametar");
    }

    @Test
    public void testPredusluviPogresanTip() {
        assertThrows(Exception.class,
                () -> izmeniUpisSO.izvrsi("pogresanTip", null),
                "Mora baciti izuzetak za pogresan tip parametra");
    }

    @Test
    public void testPredusluviUpisBezStavki() {
        upis.setStavke(new ArrayList<>());
        assertThrows(Exception.class,
                () -> izmeniUpisSO.izvrsi(upis, null),
                "Mora baciti izuzetak za upis bez stavki");
    }

    @Test
    public void testPredusluviUpisNullStavke() {
        upis.setStavke(null);
        assertThrows(Exception.class,
                () -> izmeniUpisSO.izvrsi(upis, null),
                "Mora baciti izuzetak za upis sa null stavkama");
    }

    @Test
    public void testIzmeniValidanUpisPozivaEdit() throws Exception {
        izmeniUpisSO.izvrsi(upis, null);
        Mockito.verify(mockBroker, Mockito.times(1)).edit(Mockito.any(UpisNaProgram.class));
    }

    @Test
    public void testIzmeniValidanUpisPozivaDelete() throws Exception {
        izmeniUpisSO.izvrsi(upis, null);
        Mockito.verify(mockBroker, Mockito.times(1))
                .delete(Mockito.any(StavkaUpisa.class), Mockito.anyString());
    }

    @Test
    public void testGetUnpNakonIzmene() throws Exception {
        izmeniUpisSO.izvrsi(upis, null);
        assertNotNull(izmeniUpisSO.getUnp(), "Upis ne sme biti null nakon izmene");
    }
}