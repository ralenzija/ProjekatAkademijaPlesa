package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Ucesnik;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za izmenu podataka o učesniku.
 * <p>
 * Proverava da li je prosleđeni objekat validan {@link Ucesnik}, a zatim
 * ažurira njegove podatke u bazi podataka. Koristi se kada instruktor ili
 * administrator želi da ispravi ili dopuni informacije o postojećem učesniku.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class IzmeniUcesnikaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Učesnik čiji su podaci izmenjeni, dostupan nakon izvršavanja operacije.
     */
    private Ucesnik u;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public IzmeniUcesnikaSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public IzmeniUcesnikaSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava da li je prosleđeni objekat validan učesnik.
     *
     * @param param objekat koji treba da bude instanca {@link Ucesnik}
     * @throws Exception ukoliko je parametar null ili nije instanca klase
     * Ucesnik
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ucesnik)) {
            throw new Exception("Sistem ne moze da izmeni ucesnika.");
        }
    }

    /**
     * Izvršava izmenu podataka učesnika u bazi podataka.
     *
     * @param param objekat tipa {@link Ucesnik} sa novim podacima
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        u = (Ucesnik) param;
        try {
            broker.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća učesnika čiji su podaci izmenjeni.
     *
     * @return izmenjeni učesnik
     */
    public Ucesnik getU() {
        return u;
    }
}
