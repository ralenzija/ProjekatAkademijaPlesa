package dance.academy.server.controller;

import java.util.List;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.Sertifikat;
import dance.academy.common.model.kriterijum.KriterijumPretrage;
import dance.academy.server.operacije.KreirajUcesnikaSO;
import dance.academy.server.operacije.KreirajUpisSO;
import dance.academy.server.operacije.LoginSO;
import dance.academy.server.operacije.ObrisiUcesnikaSO;
import dance.academy.server.operacije.PretraziUcesnikeSO;
import dance.academy.server.operacije.PretraziUpiseSO;
import dance.academy.server.operacije.IzmeniUcesnikaSO;
import dance.academy.server.operacije.IzmeniUpisSO;
import dance.academy.server.operacije.PreporuciProgramSO;
import dance.academy.server.operacije.PromeniStatusUpisaSO;
import dance.academy.server.operacije.UcitajNivoeSO;
import dance.academy.server.operacije.UbaciSertifikatSO;
import dance.academy.server.operacije.UcitajInstruktoreSO;
import dance.academy.server.operacije.UcitajProgrameSO;
import dance.academy.server.operacije.UcitajUcesnikeSO;
import dance.academy.server.operacije.UcitajUpiseSO;

/**
 * Centralna kontroler klasa na serverskoj strani aplikacije.
 * <p>
 * Implementira <b>Singleton</b> obrazac i služi kao posrednik između
 * {@code ObradaKlijentskihZahteva} i sistemskih operacija. Za svaki
 * zahtev koji stigne od klijenta, kontroler kreira odgovarajuću SO klasu,
 * pokreće je i vraća rezultat. Na taj način sloj komunikacije ne zna ništa
 * o poslovnoj logici, a SO klase ne znaju ništa o mreži.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class Controller {

    /** Jedina instanca kontrolera — Singleton. */
    private static Controller instance;

    /**
     * Privatni konstruktor sprečava direktno instanciranje.
     */
    private Controller() {
    }

    /**
     * Vraća jedinu instancu kontrolera.
     * Ako instanca još ne postoji, kreira je.
     *
     * @return jedina instanca klase Controller
     */
    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * Proverava kredencijale instruktora i prijavljuje ga na sistem.
     *
     * @param i instruktor sa email-om i šifrom za proveru
     * @return prijavljeni instruktor, ili objekat sa ID-jem -1 ako prijava nije uspela
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public Instruktor login(Instruktor i) throws Exception {
        LoginSO operacija = new LoginSO();
        operacija.izvrsi(i, null);
        System.out.println("Klasa Controller: " + operacija.getI());
        return operacija.getI();
    }

    /**
     * Učitava sve učesnike iz baze podataka.
     *
     * @return lista svih učesnika sa nivoima veštine
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<Ucesnik> ucitajUcesnike() throws Exception {
        UcitajUcesnikeSO operacija = new UcitajUcesnikeSO();
        operacija.izvrsi(new Ucesnik(), null);
        System.out.println("Klasa Controller: " + operacija.getUcesnici());
        return operacija.getUcesnici();
    }

    /**
     * Pretražuje učesnike prema zadatim kriterijumima.
     *
     * @param u učesnik sa postavljenim kriterijumima pretrage (ime, prezime, email)
     * @return lista učesnika koji odgovaraju kriterijumima
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<Ucesnik> pretraziUcesnike(Ucesnik u) throws Exception {
        PretraziUcesnikeSO operacija = new PretraziUcesnikeSO();
        operacija.izvrsi(u, null);
        System.out.println("Klasa Controller: " + operacija.getUcesnici());
        return operacija.getUcesnici();
    }

    /**
     * Pronalazi nivo veštine koji odgovara zadatom stilu i nivou plesa.
     *
     * @param nv nivo veštine sa postavljenim nivoom i stilom plesa
     * @return odgovarajući nivo veštine iz baze
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public NivoVestine ucitajNivoe(NivoVestine nv) throws Exception {
        UcitajNivoeSO operacija = new UcitajNivoeSO();
        operacija.izvrsi(nv, null);
        System.out.println("Klasa Controller: " + operacija.getNivo());
        return operacija.getNivo();
    }

    /**
     * Kreira novog učesnika u sistemu.
     *
     * @param u učesnik koji se dodaje
     * @return {@code true} ako je kreiranje uspešno, {@code false} u slučaju greške
     */
    public boolean kreirajUcesnika(Ucesnik u) {
        try {
            KreirajUcesnikaSO operacija = new KreirajUcesnikaSO();
            operacija.izvrsi(u, null);
            System.out.println("Klasa Controller: " + operacija.getUcesnik());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Dodaje novi sertifikat u sistem.
     *
     * @param s sertifikat koji se dodaje
     * @return {@code true} ako je dodavanje uspešno, {@code false} u slučaju greške
     */
    public boolean dodajSertifikat(Sertifikat s) {
        try {
            UbaciSertifikatSO operacija = new UbaciSertifikatSO();
            operacija.izvrsi(s, null);
            System.out.println("Klasa Controller: " + operacija.getS());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Briše učesnika iz sistema.
     * Brisanje nije moguće ako učesnik ima postojeće upise.
     *
     * @param u2 učesnik koji se briše
     * @return {@code true} ako je brisanje uspešno, {@code false} u slučaju greške
     */
    public boolean obrisiUcesnika(Ucesnik u2) {
        try {
            ObrisiUcesnikaSO operacija = new ObrisiUcesnikaSO();
            operacija.izvrsi(u2, null);
            System.out.println("Klasa Controller: " + operacija.getU());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Ažurira podatke postojećeg učesnika u sistemu.
     *
     * @param u3 učesnik sa izmenjenim podacima
     * @return {@code true} ako je izmena uspešna, {@code false} u slučaju greške
     */
    public boolean izmeniUcesnika(Ucesnik u3) {
        try {
            IzmeniUcesnikaSO operacija = new IzmeniUcesnikaSO();
            operacija.izvrsi(u3, null);
            System.out.println("Klasa Controller: " + operacija.getU());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Učitava sve instruktore iz baze podataka.
     *
     * @return lista svih instruktora
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<Instruktor> ucitajInstruktore() throws Exception {
        UcitajInstruktoreSO operacija = new UcitajInstruktoreSO();
        operacija.izvrsi(new Instruktor(), null);
        System.out.println("Klasa Controller: " + operacija.getInstruktori());
        return operacija.getInstruktori();
    }

    /**
     * Učitava sve upise na programe aktivnosti iz baze podataka.
     *
     * @return lista svih upisa sa podacima o instruktorima i učesnicima
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<UpisNaProgram> ucitajUpise() throws Exception {
        UcitajUpiseSO operacija = new UcitajUpiseSO();
        operacija.izvrsi(new UpisNaProgram(), null);
        System.out.println("Klasa Controller: " + operacija.getUpise());
        return operacija.getUpise();
    }

    /**
     * Pretražuje upise prema zadatim kriterijumima.
     *
     * @param kp kriterijum pretrage sa kombinacijom instruktora, učesnika i datuma
     * @return lista upisa koji odgovaraju kriterijumima
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<UpisNaProgram> pretraziUpise(KriterijumPretrage kp) throws Exception {
        PretraziUpiseSO operacija = new PretraziUpiseSO();
        operacija.izvrsi(new UpisNaProgram(), kp);
        System.out.println("Klasa Controller: " + operacija.getUpise());
        return operacija.getUpise();
    }

    /**
     * Učitava sve programe aktivnosti iz baze podataka.
     *
     * @return lista svih programa aktivnosti
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<ProgramAktivnosti> ucitajPrograme() throws Exception {
        UcitajProgrameSO operacija = new UcitajProgrameSO();
        operacija.izvrsi(new ProgramAktivnosti(), null);
        System.out.println("Klasa Controller: " + operacija.getProgrami());
        return operacija.getProgrami();
    }

    /**
     * Kreira novi upis na program aktivnosti zajedno sa stavkama.
     *
     * @param unp upis koji se kreira sa listom stavki
     * @return {@code true} ako je kreiranje uspešno, {@code false} u slučaju greške
     */
    public boolean kreirajUpis(UpisNaProgram unp) {
        try {
            KreirajUpisSO operacija = new KreirajUpisSO();
            operacija.izvrsi(unp, null);
            System.out.println("Klasa Controller: " + operacija.getUpis());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Ažurira postojeći upis i zamenjuje sve stavke novim.
     *
     * @param zaIzmenu upis sa izmenjenim podacima i novim stavkama
     * @return {@code true} ako je izmena uspešna, {@code false} u slučaju greške
     */
    public boolean izmeniUpis(UpisNaProgram zaIzmenu) {
        try {
            IzmeniUpisSO operacija = new IzmeniUpisSO();
            operacija.izvrsi(zaIzmenu, null);
            System.out.println("Klasa Controller: " + operacija.getUnp());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Menja status postojećeg upisa na program aktivnosti.
     *
     * @param upisSaNovimStatusom upis sa postavljenim novim statusom
     * @return {@code true} ako je promena uspešna, {@code false} u slučaju greške
     */
    public boolean promeniStatusUpisa(UpisNaProgram upisSaNovimStatusom) {
        try {
            PromeniStatusUpisaSO operacija = new PromeniStatusUpisaSO();
            operacija.izvrsi(upisSaNovimStatusom, null);
            System.out.println("Klasa Controller (promeniStatusUpisa): " + operacija.getUpis());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Preporučuje programe aktivnosti na osnovu zadatog stila plesa.
     * Rezultati su sortirani po ceni uzlazno.
     *
     * @param program program sa postavljenim stilom plesa kao filterom
     * @return lista preporučenih programa sortiranih po ceni
     * @throws Exception ukoliko dođe do greške pri komunikaciji sa bazom
     */
    public List<ProgramAktivnosti> preporuciProgram(ProgramAktivnosti program) throws Exception {
        PreporuciProgramSO operacija = new PreporuciProgramSO();
        operacija.izvrsi(program, null);
        System.out.println("Klasa Controller (preporuciProgram): " + operacija.getPrograme());
        return operacija.getPrograme();
    }
}