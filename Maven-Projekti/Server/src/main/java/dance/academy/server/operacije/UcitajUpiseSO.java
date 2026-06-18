package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za učitavanje svih upisa na programe aktivnosti.
 * <p>
 * Učitava kompletnu listu upisa zajedno sa podacima o instruktorima i
 * učesnicima kroz JOIN sa odgovarajućim tabelama. Koristi se pri prikazu liste
 * upisa na formi za pregled i upravljanje upisima.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UcitajUpiseSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista upisa učitanih iz baze, dostupna nakon izvršavanja operacije.
     */
    private List<UpisNaProgram> upisi;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public UcitajUpiseSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public UcitajUpiseSO(Repository broker) {
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
     * Učitava sve upise iz baze podataka zajedno sa podacima o instruktorima i
     * učesnicima.
     * <p>
     * Koristi JOIN sa tabelama {@code instruktor} i {@code ucesnik} kako bi se
     * uz svaki upis dobili i kompletni podaci o instruktoru koji vodi program i
     * učesniku koji je upisan.
     * </p>
     *
     * @param param objekat tipa {@link UpisNaProgram} koji služi kao šablon za
     * upit
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        String uslov = " JOIN instruktor ON upis_na_program.instruktor=instruktor.id"
                + " JOIN ucesnik ON upis_na_program.ucesnik=ucesnik.id";
        try {
            upisi = broker.getAll((UpisNaProgram) param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća listu svih upisa iz baze podataka.
     *
     * @return lista upisa sa podacima o instruktorima i učesnicima
     */
    public List<UpisNaProgram> getUpise() {
        return upisi;
    }
}
