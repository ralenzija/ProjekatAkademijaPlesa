package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Instruktor;
import dance.academy.server.repozitorijum.Repository;

/**
 * Sistemska operacija za prijavu instruktora na sistem.
 * <p>
 * Učitava sve instruktore iz baze i proverava da li postoji instruktor čiji se
 * email i šifra poklapaju sa prosleđenim podacima. Poređenje se vrši kroz
 * {@link Instruktor#equals(Object)} metodu koja poredi email i šifru. Ako
 * instruktor nije pronađen, vraća se objekat sa ID-jem -1 kao signal neuspešne
 * prijave.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class LoginSO extends ApstraktnaGenerickaOperacija {

    /**
     * Instruktor koji se prijavljuje, postavlja se nakon izvršavanja operacije.
     */
    private Instruktor instruktor;

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom. Koristi
     * se u produkcijskom kodu.
     */
    public LoginSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     * <p>
     * Koristi se isključivo u JUnit testovima kako bi se izbegla stvarna
     * konekcija sa bazom podataka i zamenila mock objektom.
     * </p>
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public LoginSO(Repository broker) {
        super(broker);
    }

    /**
     * Nema posebnih preduslova za login operaciju.
     *
     * @param param prosleđeni objekat
     * @throws Exception nikad ne baca izuzetak u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Proverava kredencijale instruktora poređenjem sa podacima iz baze.
     * <p>
     * Učitava sve instruktore iz baze i traži poklapanje sa prosleđenim
     * objektom. Ako je instruktor pronađen, postavlja se kao rezultat. Ako
     * nije, rezultat je objekat sa ID-jem -1 koji signalizira neuspešnu prijavu
     * na klijentskoj strani.
     * </p>
     *
     * @param param objekat tipa {@link Instruktor} sa email-om i šifrom
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        try {
            List<Instruktor> sviInstruktori = broker.getAll((Instruktor) param, null);
            System.out.println("Klasa LoginSO: " + sviInstruktori);
            instruktor = new Instruktor(-1, null, null, null, null);
            for (Instruktor i : sviInstruktori) {
                if (i.equals((Instruktor) param)) {
                    instruktor = i;
                    return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća instruktora koji se prijavio na sistem. Ako prijava nije uspešna,
     * vraća objekat sa ID-jem -1.
     *
     * @return prijavljeni instruktor, ili objekat sa ID-jem -1 ako prijava nije
     * uspela
     */
    public Instruktor getI() {
        return instruktor;
    }
}
