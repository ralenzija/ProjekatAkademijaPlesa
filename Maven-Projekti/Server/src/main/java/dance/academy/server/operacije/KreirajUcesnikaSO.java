package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Ucesnik;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za kreiranje novog učesnika u sistemu.
 * <p>
 * Proverava da li je prosleđeni objekat validan {@link Ucesnik}, a zatim ga
 * upisuje u bazu podataka. Koristi se kada se novi učesnik prijavljuje na
 * plesnu akademiju.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class KreirajUcesnikaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Novokreirani učesnik, dostupan nakon izvršavanja operacije.
     */
    private Ucesnik ucesnik;

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public KreirajUcesnikaSO(Repository broker) {
        super(broker);
    }

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public KreirajUcesnikaSO() {
        super();
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
            throw new Exception("Sistem ne moze da kreira ucesnika.");
        }
    }

    /**
     * Upisuje novog učesnika u bazu podataka.
     *
     * @param param objekat tipa {@link Ucesnik} koji se upisuje
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        ucesnik = (Ucesnik) param;
        try {
            broker.add(ucesnik);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća novokreiranog učesnika.
     *
     * @return kreirani učesnik
     */
    public Ucesnik getUcesnik() {
        return ucesnik;
    }
}
