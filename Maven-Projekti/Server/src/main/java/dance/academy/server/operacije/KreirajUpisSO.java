package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.StavkaUpisa;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za kreiranje novog upisa na program aktivnosti.
 * <p>
 * Kreira novi upis u bazi podataka zajedno sa svim stavkama koje ga čine. Nakon
 * što se upis upiše u bazu, sistem preuzima automatski generisani ID i
 * dodeljuje ga svakoj stavki pre nego što se i one upišu. Upis mora imati barem
 * jednu stavku da bi bio validan.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class KreirajUpisSO extends ApstraktnaGenerickaOperacija {

    /**
     * Novokreirani upis, dostupan nakon izvršavanja operacije.
     */
    private UpisNaProgram upis;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public KreirajUpisSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public KreirajUpisSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava preduslove pre kreiranja upisa.
     * <p>
     * Upis mora biti validan objekat tipa {@link UpisNaProgram} i mora sadržati
     * barem jednu stavku — upis bez stavki nije dozvoljen.
     * </p>
     *
     * @param param objekat koji treba da bude instanca {@link UpisNaProgram}
     * @throws Exception ukoliko je parametar null, nije ispravan tip, ili nema
     * stavki
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof UpisNaProgram)) {
            throw new Exception("Sistem ne moze da kreira prijavu na kurs.");
        }
        upis = (UpisNaProgram) param;
        if (upis.getStavke() == null || upis.getStavke().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira upis bez stavki.");
        }
    }

    /**
     * Kreira novi upis i sve njegove stavke u bazi podataka.
     * <p>
     * Redosled operacija:
     * <ol>
     * <li>Konverzija datuma u SQL format</li>
     * <li>Unos upisa u bazu i preuzimanje generisanog ID-ja</li>
     * <li>Unos svake stavke upisa sa referencom na novokreirani upis</li>
     * </ol>
     * </p>
     *
     * @param param objekat tipa {@link UpisNaProgram} koji se kreira
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        upis = (UpisNaProgram) param;
        java.sql.Date sqlDatum = new java.sql.Date(upis.getDatum().getTime());
        upis.setDatum(sqlDatum);
        try {
            int noviID = broker.add(upis);
            upis.setId(noviID);
            for (StavkaUpisa su : upis.getStavke()) {
                su.setUpis(upis);
                broker.add(su);
            }
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća novokreirani upis sa dodeljenim ID-jem.
     *
     * @return kreirani upis na program
     */
    public UpisNaProgram getUpis() {
        return upis;
    }
}
