package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Ucesnik;
import dance.academy.server.repozitorijum.Repository;
import java.util.stream.Collectors;


/**
 * Sistemska operacija za pretragu učesnika po zadatim kriterijumima.
 * <p>
 * Podržava tri načina pretrage:
 * <ul>
 * <li>Po imenu i prezimenu — kada je email null</li>
 * <li>Po email adresi — kada su ime i prezime null</li>
 * <li>Po svim poljima — kada su ime, prezime i email postavljeni</li>
 * </ul>
 * Rezultati uključuju i podatke o nivou veštine učesnika kroz JOIN sa tabelom
 * {@code nivo_vestine}.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class PretraziUcesnikeSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista pronađenih učesnika, dostupna nakon izvršavanja operacije.
     */
    private List<Ucesnik> ucesnici;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public PretraziUcesnikeSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public PretraziUcesnikeSO(Repository broker) {
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
     * Pretražuje učesnike u bazi prema postavljenim kriterijumima.
     * <p>
     * Na osnovu toga koja polja su popunjena u prosleđenom objektu, dinamički
     * gradi WHERE uslov SQL upita. Uvek se radi JOIN sa tabelom nivoa veštine
     * kako bi se dobili kompletni podaci o učesniku.
     * </p>
     *
     * @param param objekat tipa {@link Ucesnik} sa postavljenim kriterijumima
     * pretrage
     * @param kljuc nije korišćen u ovoj operaciji
     */
   @Override
protected void izvrsiOperaciju(Object param, Object kljuc) {
    Ucesnik u = (Ucesnik) param;
    try {
        List<Ucesnik> svi = broker.getAll(new Ucesnik(),
                " JOIN nivo_vestine ON ucesnik.nivo=nivo_vestine.id");

        ucesnici = svi.stream()
                .filter(uc -> u.getIme() == null || uc.getIme().equalsIgnoreCase(u.getIme()))
                .filter(uc -> u.getPrezime() == null || uc.getPrezime().equalsIgnoreCase(u.getPrezime()))
                .filter(uc -> u.getEmail() == null || uc.getEmail().equalsIgnoreCase(u.getEmail()))
                .collect(Collectors.toList());

    } catch (Exception ex) {
        Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    /**
     * Vraća listu učesnika koji odgovaraju zadatim kriterijumima pretrage.
     *
     * @return lista pronađenih učesnika
     */
    public List<Ucesnik> getUcesnici() {
        return ucesnici;
    }
}
