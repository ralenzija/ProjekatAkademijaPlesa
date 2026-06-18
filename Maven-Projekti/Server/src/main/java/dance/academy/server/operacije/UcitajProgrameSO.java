package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za učitavanje svih programa aktivnosti iz baze podataka.
 * <p>
 * Koristi se kada klijent treba da prikaže listu dostupnih programa, npr. pri
 * kreiranju upisa gde učesnik bira programe koje želi da pohađa. Vraća
 * kompletnu listu svih programa bez filtriranja.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UcitajProgrameSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista programa aktivnosti učitanih iz baze, dostupna nakon izvršavanja
     * operacije.
     */
    private List<ProgramAktivnosti> programi;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public UcitajProgrameSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public UcitajProgrameSO(Repository broker) {
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
     * Učitava sve programe aktivnosti iz baze podataka.
     *
     * @param param objekat tipa {@link ProgramAktivnosti} koji služi kao šablon
     * za upit
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        try {
            programi = broker.getAll((ProgramAktivnosti) param, null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća listu svih programa aktivnosti iz baze podataka.
     *
     * @return lista programa aktivnosti
     */
    public List<ProgramAktivnosti> getProgrami() {
        return programi;
    }
}
