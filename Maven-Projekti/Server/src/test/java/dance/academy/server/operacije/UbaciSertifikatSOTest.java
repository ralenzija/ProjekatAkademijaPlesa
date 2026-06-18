package dance.academy.server.operacije;

import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Sertifikat;
import dance.academy.server.repozitorijum.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za UbaciSertifikatSO.
 */
public class UbaciSertifikatSOTest {

    private UbaciSertifikatSO ubaciSertifikatSO;
    private Repository mockBroker;
    private Sertifikat sertifikat;

    @BeforeEach
    public void setUp() throws Exception {
        mockBroker = Mockito.mock(Repository.class);
        ubaciSertifikatSO = new UbaciSertifikatSO(mockBroker);
        sertifikat = new Sertifikat(1, PlesniStil.SALSA, "World Dance Council");
    }

    @AfterEach
    public void tearDown() {
        ubaciSertifikatSO = null;
        mockBroker = null;
        sertifikat = null;
    }

    @Test
    public void testPredusluviValidanSertifikat() {
        assertDoesNotThrow(() -> ubaciSertifikatSO.izvrsi(sertifikat, null),
                "Ne sme baciti izuzetak za validan sertifikat");
    }

    @Test
    public void testPredusluviNullParam() {
        assertThrows(Exception.class,
                () -> ubaciSertifikatSO.izvrsi(null, null),
                "Mora baciti izuzetak za null parametar");
    }

    @Test
    public void testPredusluviPogresanTip() {
        assertThrows(Exception.class,
                () -> ubaciSertifikatSO.izvrsi("pogresanTip", null),
                "Mora baciti izuzetak za pogresan tip parametra");
    }

    @Test
    public void testUbaciSertifikatPozivaAdd() throws Exception {
        ubaciSertifikatSO.izvrsi(sertifikat, null);
        Mockito.verify(mockBroker, Mockito.times(1)).add(sertifikat);
    }

    @Test
    public void testGetSNakonDodavanja() throws Exception {
        ubaciSertifikatSO.izvrsi(sertifikat, null);
        assertEquals(sertifikat, ubaciSertifikatSO.getS(),
                "Sertifikat treba biti isti nakon dodavanja");
    }
}