package dance.academy.server.operacije;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.kriterijum.KriterijumPretrage;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za pretragu upisa na programe aktivnosti.
 * <p>
 * Pretraga se vrši na osnovu {@link KriterijumPretrage} koji može sadržati
 * kombinaciju instruktora, učesnika i datuma. Sva tri parametra su opciona, a
 * sistem dinamički gradi SQL upit na osnovu toga koji su postavljeni. Podržano
 * je svih 7 kombinacija kriterijuma pretrage.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class PretraziUpiseSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista pronađenih upisa, dostupna nakon izvršavanja operacije.
     */
    private List<UpisNaProgram> upisi;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public PretraziUpiseSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public PretraziUpiseSO(Repository broker) {
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
     * Pretražuje upise u bazi prema prosleđenom kriterijumu pretrage.
     * <p>
     * Dinamički gradi WHERE uslov SQL upita na osnovu kombinacije postavljenih
     * parametara u {@link KriterijumPretrage}. Uvek se radi JOIN sa tabelama
     * instruktora i učesnika kako bi se dobili kompletni podaci. Datum se
     * konvertuje iz Java u SQL format pre korišćenja u upitu.
     * </p>
     *
     * @param param objekat tipa {@link UpisNaProgram} koji služi kao šablon za
     * upit
     * @param kljuc objekat tipa {@link KriterijumPretrage} sa parametrima
     * pretrage
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        KriterijumPretrage kp = (KriterijumPretrage) kljuc;
        Instruktor i = kp.getI();
        Ucesnik u = kp.getP();
        Date d = kp.getD();
        java.sql.Date sqlDate = null;
        if (d != null) {
            sqlDate = new java.sql.Date(d.getTime());
        }
        String uslov = " JOIN instruktor ON upis_na_program.instruktor=instruktor.id"
                + " JOIN ucesnik ON upis_na_program.ucesnik=ucesnik.id";
        if (i != null && u == null && d == null) {
            uslov += " WHERE instruktor.id=" + i.getId();
        }
        if (i == null && u != null && d == null) {
            uslov += " WHERE ucesnik.id=" + u.getId();
        }
        if (i == null && u == null && d != null) {
            uslov += " WHERE upis_na_program.datum='" + sqlDate + "'";
        }
        if (i != null && u != null && d == null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND ucesnik.id=" + u.getId();
        }
        if (i != null && u == null && d != null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND upis_na_program.datum='" + sqlDate + "'";
        }
        if (i == null && u != null && d != null) {
            uslov += " WHERE ucesnik.id=" + u.getId() + " AND upis_na_program.datum='" + sqlDate + "'";
        }
        if (i != null && u != null && d != null) {
            uslov += " WHERE instruktor.id=" + i.getId()
                    + " AND ucesnik.id=" + u.getId()
                    + " AND upis_na_program.datum='" + sqlDate + "'";
        }
        try {
            upisi = broker.getAll((UpisNaProgram) param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(PretraziUpiseSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća listu upisa koji odgovaraju zadatim kriterijumima pretrage.
     *
     * @return lista pronađenih upisa na programe aktivnosti
     */
    public List<UpisNaProgram> getUpise() {
        return upisi;
    }
}
