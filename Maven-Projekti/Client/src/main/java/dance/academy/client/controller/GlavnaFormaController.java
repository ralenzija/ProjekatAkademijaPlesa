package dance.academy.client.controller;
import dance.academy.client.forme.GlavnaForma;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.Instruktor;

/**
 * Controller klasa za glavnu formu aplikacije.
 * <p>
 * Upravlja prikazom i ponašanjem {@link GlavnaForma} koja se otvara
 * nakon uspešne prijave instruktora. Na glavnoj formi se prikazuje
 * ime i prezime ulogovanog instruktora, a odavde se pristupa svim
 * ostalim funkcionalnostima aplikacije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class GlavnaFormaController {

    /** Referenca na glavnu formu kojom ovaj controller upravlja. */
    private final GlavnaForma gf;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param gf glavna forma aplikacije
     */
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    /**
     * Registruje action listenere na dugmad i ostale komponente forme.
     */
    private void addActionListeners() {
    }

    /**
     * Prikazuje glavnu formu i ispisuje ime ulogovanog instruktora.
     * Ime i prezime se preuzimaju iz {@link Koordinator}-a.
     */
    public void prikaziFormu() {
        gf.setVisible(true);
        Instruktor ulogovani = Koordinator.getInstance().getUlogvani();
        gf.getjLabelUlogovani().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
    }

    /**
     * Zatvara i uklanja glavnu formu iz memorije.
     */
    public void zatvoriFormu() {
        gf.dispose();
    }
}