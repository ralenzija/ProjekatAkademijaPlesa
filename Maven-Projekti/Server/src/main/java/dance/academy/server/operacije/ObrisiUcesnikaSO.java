package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za brisanje učesnika iz sistema.
 * <p>
 * Pre brisanja proverava da li učesnik ima aktivnih ili istorijskih upisa. Ako
 * postoje upisi vezani za učesnika, brisanje nije dozvoljeno kako bi se očuvao
 * integritet podataka u bazi. Učesnik se može obrisati samo ako nema nijedan
 * upis u sistemu.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class ObrisiUcesnikaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Učesnik koji se briše, dostupan nakon izvršavanja operacije.
     */
    private Ucesnik u;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public ObrisiUcesnikaSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public ObrisiUcesnikaSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava preduslove pre brisanja učesnika.
     * <p>
     * Pored provere tipa parametra, sistem proverava da li postoje upisi vezani
     * za ovog učesnika. Ako postoje, brisanje se odbija jer bi narušilo
     * referencijalni integritet podataka.
     * </p>
     *
     * @param param objekat koji treba da bude instanca {@link Ucesnik}
     * @throws Exception ukoliko parametar nije validan ili učesnik ima
     * postojeće upise
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ucesnik)) {
            throw new Exception("Sistem ne moze da obrise ucesnika.");
        }
        u = (Ucesnik) param;
        UpisNaProgram unp = new UpisNaProgram();
        unp.setUcesnik(u);
        List<UpisNaProgram> upisi = broker.getAll(unp, " WHERE ucesnik= " + u.getId());
        if (!upisi.isEmpty()) {
            throw new Exception("Sistem ne moze da obrise clana jer postoje njegovi upisi.");
        }
    }

    /**
     * Briše učesnika iz baze podataka.
     *
     * @param param objekat tipa {@link Ucesnik} koji se briše
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        u = (Ucesnik) param;
        try {
            broker.delete(u, null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća učesnika koji je obrisan.
     *
     * @return obrisani učesnik
     */
    public Ucesnik getU() {
        return u;
    }
}
