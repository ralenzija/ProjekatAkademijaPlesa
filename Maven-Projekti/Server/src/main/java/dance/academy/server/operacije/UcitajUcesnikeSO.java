package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Ucesnik;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za učitavanje svih učesnika iz baze podataka.
 * <p>
 * Učitava kompletnu listu učesnika zajedno sa njihovim nivoima veštine kroz
 * JOIN sa tabelom {@code nivo_vestine}. Koristi se pri prikazu liste učesnika
 * na glavnoj formi aplikacije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UcitajUcesnikeSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista učesnika učitanih iz baze, dostupna nakon izvršavanja operacije.
     */
    private List<Ucesnik> ucesnici;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public UcitajUcesnikeSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public UcitajUcesnikeSO(Repository broker) {
        super(broker);
    }

    /**
     * Nema posebnih preduslova za ovu operaciju.
     *
     * @param param prosleđeni objekat
     * @throws Exception nikad ne baca izuzetak u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sve učesnike iz baze podataka zajedno sa njihovim nivoima
     * veštine.
     * <p>
     * Koristi JOIN sa tabelom {@code nivo_vestine} kako bi se uz svakog
     * učesnika dobili i podaci o njegovom nivou i stilu plesa.
     * </p>
     *
     * @param param objekat tipa {@link Ucesnik} koji služi kao šablon za upit
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        String uslov = " JOIN nivo_vestine ON ucesnik.nivo=nivo_vestine.id";
        try {
            ucesnici = broker.getAll((Ucesnik) param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća listu svih učesnika iz baze podataka.
     *
     * @return lista učesnika sa podacima o nivoima veštine
     */
    public List<Ucesnik> getUcesnici() {
        return ucesnici;
    }
}
