package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.StatusUpisa;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za promenu statusa upisa na program aktivnosti.
 * <p>
 * Koristi se kada instruktor ili administrator želi da promeni status upisa,
 * npr. da potvrdi, odbije ili otkaže upis. Upis mora imati validan ID i novi
 * status mora biti postavljen pre poziva operacije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class PromeniStatusUpisaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Upis čiji je status promenjen, dostupan nakon izvršavanja operacije.
     */
    private UpisNaProgram upis;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public PromeniStatusUpisaSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public PromeniStatusUpisaSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava preduslove pre promene statusa upisa.
     * <p>
     * Upis mora biti validan objekat sa ispravnim ID-jem (veći od 0) i novi
     * status mora biti eksplicitno postavljen.
     * </p>
     *
     * @param param objekat tipa {@link UpisNaProgram} sa ID-jem i novim
     * statusom
     * @throws Exception ukoliko parametar nije validan, ID nije ispravan, ili
     * status nije postavljen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof UpisNaProgram up) || up.getId() <= 0 || up.getStatus() == null) {
            throw new Exception("Nedovoljan parametar za promenu statusa upisa.");
        }
    }

    /**
     * Ažurira podatke upisa u bazi podataka.
     * <p>
     * Poziva generičku edit metodu brokera koja ažurira sve kolone upisa.
     * Ukoliko je potrebno menjati isključivo status, moguće je proširiti
     * implementaciju da koristi specifičniji SQL upit.
     * </p>
     *
     * @param param objekat tipa {@link UpisNaProgram} sa novim statusom
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        UpisNaProgram up = (UpisNaProgram) param;
        try {
            broker.edit(up);
            upis = up;
        } catch (Exception ex) {
            Logger.getLogger(PromeniStatusUpisaSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća upis čiji je status promenjen.
     *
     * @return upis sa ažuriranim statusom
     */
    public UpisNaProgram getUpis() {
        return upis;
    }
}
