package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.StavkaUpisa;
import dance.academy.server.repozitorijum.Repository;


/**
 * Sistemska operacija za izmenu postojećeg upisa na program aktivnosti.
 * <p>
 * Operacija ažurira osnovne podatke upisa i zamenjuje sve stavke upisa novim
 * stavkama. Stare stavke se brišu iz baze, a nove se upisuju, čime se postiže
 * potpuna sinhronizacija stanja upisa sa podacima koje je korisnik uneo.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class IzmeniUpisSO extends ApstraktnaGenerickaOperacija {

    /**
     * Upis koji je izmenjen, dostupan nakon izvršavanja operacije.
     */
    private UpisNaProgram unp;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public IzmeniUpisSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public IzmeniUpisSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava preduslove pre izmene upisa.
     * <p>
     * Upis mora biti validan objekat tipa {@link UpisNaProgram} i mora imati
     * barem jednu stavku — upis bez stavki nema smisla u sistemu.
     * </p>
     *
     * @param param objekat koji treba da bude instanca {@link UpisNaProgram}
     * @throws Exception ukoliko je parametar null, nije ispravan tip, ili nema
     * stavki
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof UpisNaProgram)) {
            throw new Exception("Sistem ne moze da kreira upis na program.");
        }
        unp = (UpisNaProgram) param;
        if (unp.getStavke() == null || unp.getStavke().isEmpty()) {
            throw new Exception("Prijava mora imati barem jednu stavku.");
        }
    }

    /**
     * Izvršava izmenu upisa u bazi podataka.
     * <p>
     * Redosled operacija:
     * <ol>
     * <li>Ažuriranje osnovnih podataka upisa (datum, status, iznos...)</li>
     * <li>Brisanje svih postojećih stavki vezanih za ovaj upis</li>
     * <li>Unos novih stavki iz prosleđenog upisa</li>
     * </ol>
     * Datum se konvertuje u SQL format pre slanja u bazu.
     * </p>
     *
     * @param param objekat tipa {@link UpisNaProgram} sa izmenjenim podacima
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        unp = (UpisNaProgram) param;
        java.sql.Date sqlDatum = new java.sql.Date(unp.getDatum().getTime());
        unp.setDatum(sqlDatum);
        try {
            broker.edit(unp);
            String uslov = " WHERE upis=" + unp.getId();
            broker.delete(new StavkaUpisa(), uslov);
            for (StavkaUpisa su : unp.getStavke()) {
                su.setUpis(unp);
                broker.add(su);
            }
        } catch (Exception ex) {
            Logger.getLogger(IzmeniUpisSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća upis koji je izmenjen.
     *
     * @return izmenjeni upis na program
     */
    public UpisNaProgram getUnp() {
        return unp;
    }
}
