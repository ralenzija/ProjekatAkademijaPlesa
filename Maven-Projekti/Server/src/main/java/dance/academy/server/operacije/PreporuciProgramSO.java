package dance.academy.server.operacije;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.server.repozitorijum.Repository;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Sistemska operacija za preporuku programa aktivnosti učesniku.
 * <p>
 * Na osnovu željenog stila plesa filtrira programe iz baze i vraća ih sortirane
 * po ceni od najjeftinije ka najskupljoj. Opciono se može filtrirati samo
 * aktivne programe, što je korisno kada učesnik traži program na koji može
 * odmah da se upiše.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class PreporuciProgramSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista preporučenih programa, dostupna nakon izvršavanja operacije.
     */
    private List<ProgramAktivnosti> programi = new ArrayList<>();

    /**
     * Podrazumevani konstruktor koji koristi DBBroker za rad sa bazom.
     */
    public PreporuciProgramSO() {
        super();
    }

    /**
     * Konstruktor za testiranje koji prima mock broker kao parametar.
     *
     * @param broker mock repozitorijum koji simulira rad sa bazom
     */
    public PreporuciProgramSO(Repository broker) {
        super(broker);
    }

    /**
     * Proverava da li je prosleđen validan program sa definisanim stilom plesa.
     * <p>
     * Stil plesa je obavezan parametar jer se na osnovu njega vrši preporuka.
     * Bez stila plesa sistem ne može da zna koje programe da preporuči.
     * </p>
     *
     * @param param objekat tipa {@link ProgramAktivnosti} sa postavljenom
     * vrstom plesa
     * @throws Exception ukoliko parametar nije validan ili vrsta plesa nije
     * postavljena
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof ProgramAktivnosti pa) || pa.getVrsta() == null) {
            throw new Exception("Nedovoljan parametar za preporuku programa (vrsta je obavezna).");
        }
    }

    /**
     * Pretražuje i vraća programe koji odgovaraju zadatom stilu plesa.
     * <p>
     * Gradi SQL uslov na osnovu stila plesa i opcionalnog filtera aktivnosti, a
     * rezultate sortira po ceni uzlazno kako bi učesnik najpre video
     * najpovoljnije opcije.
     * </p>
     *
     * @param param objekat tipa {@link ProgramAktivnosti} sa stilom plesa i
     * filterom aktivnosti
     * @param kljuc nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        ProgramAktivnosti pa = (ProgramAktivnosti) param;
        PlesniStil trazeniStil = pa.getVrsta();

        try {
            List<ProgramAktivnosti> svi = broker.getAll(new ProgramAktivnosti(), null);

            programi = svi.stream()
                    .filter(p -> p.getVrsta() == trazeniStil)
                    .filter(p -> !pa.isAktivan() || p.isAktivan())
                    .sorted(Comparator.comparingDouble(ProgramAktivnosti::getCena))
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            Logger.getLogger(PreporuciProgramSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća listu preporučenih programa sortiranih po ceni.
     *
     * @return lista programa aktivnosti koji odgovaraju traženom stilu plesa
     */
    public List<ProgramAktivnosti> getPrograme() {
        return programi;
    }
}
