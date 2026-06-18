package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Instruktor;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za učitavanje svih instruktora iz baze podataka.
 * <p>
 * Koristi se kada klijent treba da prikaže listu instruktora, npr. pri
 * kreiranju ili izmeni upisa gde se bira instruktor koji vodi program. Vraća
 * kompletnu listu svih instruktora bez filtriranja.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UcitajInstruktoreSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista instruktora učitanih iz baze, dostupna nakon izvršavanja operacije.
     */
    private List<Instruktor> instruktori;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public UcitajInstruktoreSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public UcitajInstruktoreSO(Repository broker) {
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
     * Učitava sve instruktore iz baze podataka.
     *
     * @param param objekat tipa {@link Instruktor} koji služi kao šablon za
     * upit
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        try {
            instruktori = broker.getAll((Instruktor) param, null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća listu svih instruktora iz baze podataka.
     *
     * @return lista instruktora
     */
    public List<Instruktor> getInstruktori() {
        return instruktori;
    }
}
