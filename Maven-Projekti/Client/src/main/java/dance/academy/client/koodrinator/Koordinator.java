package dance.academy.client.koodrinator;

import dance.academy.client.controller.GlavnaFormaController;
import dance.academy.client.controller.IzabraniUpisController;
import dance.academy.client.controller.IzabraniUcesnikController;
import dance.academy.client.controller.KreirajUcesnikaController;
import dance.academy.client.controller.KreirajUpisController;
import dance.academy.client.controller.LoginController;
import dance.academy.client.controller.ObrisiUcesnikaController;
import dance.academy.client.controller.PretraziUcesnikaController;
import dance.academy.client.controller.PretraziUpisController;
import dance.academy.client.controller.PromeniUcesnikaController;
import dance.academy.client.controller.UbaciSertifikatController;
import dance.academy.client.forme.GlavnaForma;
import dance.academy.client.forme.IzabraniUpisForma;
import dance.academy.client.forme.IzabraniUcesnikForma;
import dance.academy.client.forme.KreirajUcesnikaForma;
import dance.academy.client.forme.KreirajUpisForma;
import dance.academy.client.forme.LoginForma;
import dance.academy.client.forme.ObrisiUcesnikaForma;
import dance.academy.client.forme.PretraziUcesnikaForma;
import dance.academy.client.forme.PretraziUpisForma;
import dance.academy.client.forme.PromeniUcesnikaForma;
import dance.academy.client.forme.UbaciSertifikatForma;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;

/**
 * Centralna klasa koja koordinira otvaranje i zatvaranje formi klijentske aplikacije.
 * <p>
 * Implementira <b>Singleton</b> obrazac i služi kao jedina tačka za
 * upravljanje navigacijom između formi. Svaka forma se otvara isključivo
 * kroz Koordinator, koji kreira odgovarajući controller i prosleđuje mu
 * formu. Na taj način forme i controlleri ne znaju jedni za druge direktno.
 * </p>
 * <p>
 * Koordinator čuva i referencu na trenutno ulogovanog instruktora koji
 * se koristi u različitim delovima aplikacije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class Koordinator {

    /** Jedina instanca koordinatora — Singleton. */
    private static Koordinator instance;

    /** Trenutno ulogovani instruktor. */
    private Instruktor ulogvani;

    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PretraziUcesnikaController pretraziUcesnikaController;
    private KreirajUcesnikaController kreirajUcesnikaController;
    private UbaciSertifikatController ubaciSertifikatController;
    private ObrisiUcesnikaController obrisiUcesnikaController;
    private PromeniUcesnikaController promeniUcesnikaController;
    private PretraziUpisController pretraziUpisController;
    private KreirajUpisController kreirajUpisController;
    private IzabraniUpisController izabraniUpisController;
    private IzabraniUcesnikController izabraniUcesnikController;

    /**
     * Privatni konstruktor sprečava direktno instanciranje.
     */
    private Koordinator() {
    }

    /**
     * Vraća jedinu instancu koordinatora.
     * Ako instanca još ne postoji, kreira je.
     *
     * @return jedina instanca klase Koordinator
     */
    public static Koordinator getInstance() {
        if (instance == null)
            instance = new Koordinator();
        return instance;
    }

    /**
     * Vraća trenutno ulogovanog instruktora.
     *
     * @return ulogovani instruktor
     */
    public Instruktor getUlogvani() {
        return ulogvani;
    }

    /**
     * Postavlja trenutno ulogovanog instruktora nakon uspešne prijave.
     *
     * @param ulogvani instruktor koji se prijavio na sistem
     */
    public void setUlogvani(Instruktor ulogvani) {
        this.ulogvani = ulogvani;
    }

    /**
     * Otvara login formu i prikazuje je korisniku.
     * Ovo je prva forma koja se prikazuje pri pokretanju aplikacije.
     */
    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.prikaziFormu();
    }

    /**
     * Otvara glavnu formu aplikacije nakon uspešne prijave.
     */
    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.prikaziFormu();
    }

    /**
     * Zatvara glavnu formu aplikacije.
     */
    public void zatvoriGF() {
        glavnaFormaController.zatvoriFormu();
    }

    /**
     * Otvara formu za pretragu učesnika u režimu pretrage.
     */
    public void otvoriPretraziUcesnikaFormu() {
        pretraziUcesnikaController = new PretraziUcesnikaController(new PretraziUcesnikaForma());
        pretraziUcesnikaController.pripremiFormuZaPretragu();
        pretraziUcesnikaController.prikaziFormu();
    }

    /**
     * Otvara formu za kreiranje novog učesnika.
     */
    public void otvoriKreirajUcesnikaFormu() {
        kreirajUcesnikaController = new KreirajUcesnikaController(new KreirajUcesnikaForma());
        kreirajUcesnikaController.prikaziFormu();
    }

    /**
     * Otvara formu za dodavanje sertifikata instruktoru.
     */
    public void otvoriUbaciVrstuSertifikatFormu() {
        ubaciSertifikatController = new UbaciSertifikatController(new UbaciSertifikatForma());
        ubaciSertifikatController.prikaziFormu();
    }

    /**
     * Otvara formu za brisanje učesnika iz sistema.
     */
    public void otvoriObrisiUcesnikaFormu() {
        obrisiUcesnikaController = new ObrisiUcesnikaController(new ObrisiUcesnikaForma());
        obrisiUcesnikaController.prikaziFormu();
    }

    /**
     * Otvara formu za pretragu učesnika u režimu izmene.
     * Za razliku od pretrage, ovde se po odabiru učesnika otvara forma za izmenu.
     */
    public void otvoriPretraziUcesnikaFormuZaIzmenu() {
        pretraziUcesnikaController = new PretraziUcesnikaController(new PretraziUcesnikaForma());
        pretraziUcesnikaController.pripremiFormuZaIzmenu();
        pretraziUcesnikaController.prikaziFormu();
    }

    /**
     * Otvara formu za izmenu podataka selektovanog učesnika.
     *
     * @param selektovani učesnik čiji se podaci menjaju
     */
    public void otvoriPromeniUcesnikaFormu(Ucesnik selektovani) {
        promeniUcesnikaController = new PromeniUcesnikaController(new PromeniUcesnikaForma());
        promeniUcesnikaController.prikaziFormu(selektovani);
    }

    /**
     * Osvežava tabelu učesnika na formi za pretragu.
     * Koristi se nakon izmene ili brisanja učesnika.
     */
    public void osveziTabeluUcesnici() {
        pretraziUcesnikaController.osveziTabelu();
    }

    /**
     * Otvara formu za pretragu upisa u režimu pretrage.
     */
    public void otvoriPretraziUpisFormu() {
        pretraziUpisController = new PretraziUpisController(new PretraziUpisForma());
        pretraziUpisController.pripremiFormuZaPretragu();
        pretraziUpisController.prikaziFormu();
    }

    /**
     * Otvara formu za kreiranje novog upisa na program aktivnosti.
     */
    public void otvoriKreirajUpisFormu() {
        kreirajUpisController = new KreirajUpisController(new KreirajUpisForma());
        kreirajUpisController.pripremiZaKreiranje();
        kreirajUpisController.prikaziFormu();
    }

    /**
     * Otvara formu za pretragu upisa u režimu izmene.
     */
    public void otvoriPretraziUpisFormuZaIzmenu() {
        pretraziUpisController = new PretraziUpisController(new PretraziUpisForma());
        pretraziUpisController.pripremiFormuZaIzmenu();
        pretraziUpisController.prikaziFormu();
    }

    /**
     * Otvara formu za izmenu selektovanog upisa.
     *
     * @param selektovana upis koji se menja
     */
    public void otvoriPromeniUpisFormu(UpisNaProgram selektovana) {
        kreirajUpisController = new KreirajUpisController(new KreirajUpisForma());
        kreirajUpisController.pripremiZaIzmenu(selektovana);
        kreirajUpisController.prikaziFormu();
    }

    /**
     * Otvara formu za detaljan prikaz selektovanog upisa.
     *
     * @param selektovana upis koji se prikazuje
     */
    public void otvoriIzabranUpisFormu(UpisNaProgram selektovana) {
        izabraniUpisController = new IzabraniUpisController(new IzabraniUpisForma());
        izabraniUpisController.prikaziFormu(selektovana);
    }

    /**
     * Otvara formu za detaljan prikaz selektovanog učesnika.
     *
     * @param selektovani učesnik koji se prikazuje
     */
    public void otvoriIzabraniUcesnikFormu(Ucesnik selektovani) {
        izabraniUcesnikController = new IzabraniUcesnikController(new IzabraniUcesnikForma());
        izabraniUcesnikController.prikaziFormu(selektovani);
    }

    /**
     * Osvežava tabelu upisa na formi za pretragu.
     * Koristi se nakon izmene statusa ili podataka upisa.
     */
    public void osveziTabeluUpisi() {
        pretraziUpisController.osveziTabelu();
    }
}