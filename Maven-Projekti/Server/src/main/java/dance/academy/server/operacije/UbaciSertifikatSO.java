package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Sertifikat;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za dodavanje novog sertifikata u sistem.
 * <p>
 * Proverava da li je prosleđeni objekat validan {@link Sertifikat}, a zatim ga
 * upisuje u bazu podataka. Koristi se kada instruktor stekne novu kvalifikaciju
 * za određeni stil plesa i želi da je evidentira u sistemu plesne akademije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UbaciSertifikatSO extends ApstraktnaGenerickaOperacija {

    /**
     * Sertifikat koji je dodat, dostupan nakon izvršavanja operacije.
     */
    private Sertifikat s;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public UbaciSertifikatSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public UbaciSertifikatSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava da li je prosleđeni objekat validan sertifikat.
     *
     * @param param objekat koji treba da bude instanca {@link Sertifikat}
     * @throws Exception ukoliko je parametar null ili nije instanca klase
     * Sertifikat
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Sertifikat)) {
            throw new Exception("Sistem ne moze da zapamti sertifikat.");
        }
    }

    /**
     * Upisuje novi sertifikat u bazu podataka.
     *
     * @param param objekat tipa {@link Sertifikat} koji se dodaje
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        s = (Sertifikat) param;
        try {
            broker.add(s);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća sertifikat koji je dodat u sistem.
     *
     * @return dodati sertifikat
     */
    public Sertifikat getS() {
        return s;
    }
}
