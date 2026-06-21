package dance.academy.client.komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.Sertifikat;
import dance.academy.common.model.kriterijum.KriterijumPretrage;
import dance.academy.common.komunikacija.Zahtev;
import dance.academy.common.komunikacija.Odgovor;
import dance.academy.common.komunikacija.Operacija;
import dance.academy.common.komunikacija.Posiljalac;
import dance.academy.common.komunikacija.Primalac;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Klasa koja upravlja komunikacijom između klijenta i servera.
 * <p>
 * Implementira <b>Singleton</b> obrazac i služi kao jedina tačka kontakta
 * klijentske aplikacije sa serverom. Svaka metoda šalje odgovarajući
 * {@link Zahtev} serveru i prima {@link Odgovor}. Za liste objekata
 * koristi se Gson deserijalizacija iz JSON formata, dok se za pojedinačne
 * objekte i boolean vrednosti koristi direktna Java deserijalizacija.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class Komunikacija {

    /** Socket konekcija prema serveru. */
    private Socket socket;

    /** Jedina instanca klase — Singleton. */
    private static Komunikacija instance;

    /** Objekat za slanje zahteva serveru. */
    private Posiljalac posiljalac;

    /** Objekat za primanje odgovora od servera. */
    private Primalac primalac;

    /** Gson instanca sa custom adapterima za LocalDate i LocalTime. */
    private final Gson gson;

    /**
     * Privatni konstruktor sprečava direktno instanciranje.
     * Inicijalizuje Gson sa custom adapterima za Java Time tipove.
     */
    private Komunikacija() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (src, typeOfSrc, context)
                        -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, typeOfT, context)
                        -> LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(LocalTime.class,
                        (JsonSerializer<LocalTime>) (src, typeOfSrc, context)
                        -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalTime.class,
                        (JsonDeserializer<LocalTime>) (json, typeOfT, context)
                        -> LocalTime.parse(json.getAsString()))
                .create();
    }

    /**
     * Vraća jedinu instancu klase Komunikacija.
     *
     * @return jedina instanca klase Komunikacija
     */
    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    /**
     * Uspostavlja socket konekciju sa serverom na adresi localhost:9000.
     */
    public void konekcija() {
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Greska u klasi Komunikacija, fja konekcija");
        }
    }

    /**
     * Šalje zahtev za prijavu instruktora na sistem.
     *
     * @param i instruktor sa email-om i šifrom
     * @return prijavljeni instruktor, ili null ako prijava nije uspela
     */
    public Instruktor login(Instruktor i) {
        Zahtev z = new Zahtev(Operacija.LOGIN, i);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        i = (Instruktor) o.getOdgovor();
        return i;
    }

    /**
     * Traži od servera listu svih učesnika i deserijalizuje je iz JSON-a.
     *
     * @return lista svih učesnika
     */
    public List<Ucesnik> ucitajUcesnike() {
        Zahtev z = new Zahtev(Operacija.UCITAJ_UCESNIKE, null);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<Ucesnik>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

    /**
     * Šalje zahtev za pretragu učesnika i deserijalizuje rezultat iz JSON-a.
     *
     * @param u učesnik sa postavljenim kriterijumima pretrage
     * @return lista učesnika koji odgovaraju kriterijumima
     */
    public List<Ucesnik> pretraziUcesnike(Ucesnik u) {
        Zahtev z = new Zahtev(Operacija.PRETRAZI_UCESNIKE, u);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<Ucesnik>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

    /**
     * Traži nivo veštine koji odgovara zadatom stilu i nivou plesa.
     *
     * @param nv nivo veštine sa postavljenim nivoom i stilom plesa
     * @return odgovarajući nivo veštine iz baze
     */
    public NivoVestine pronadjiNivoVestine(NivoVestine nv) {
        Zahtev z = new Zahtev(Operacija.UCITAJ_NIVOE, nv);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        nv = (NivoVestine) o.getOdgovor();
        return nv;
    }

    /**
     * Šalje zahtev za kreiranje novog učesnika.
     *
     * @param u učesnik koji se kreira
     */
    public void kreirajUcesnika(Ucesnik u) {
        NivoVestine nv = pronadjiNivoVestine(u.getNivo());
        u.setNivo(nv);
        Zahtev z = new Zahtev(Operacija.KREIRAJ_UCESNIKA, u);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Šalje zahtev za dodavanje novog sertifikata instruktora.
     *
     * @param s sertifikat koji se dodaje
     */
    public void dodajSertifikat(Sertifikat s) {
        Zahtev z = new Zahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Šalje zahtev za brisanje učesnika iz sistema.
     *
     * @param selektovani učesnik koji se briše
     */
    public void obrisiUcesnika(Ucesnik selektovani) {
        Zahtev z = new Zahtev(Operacija.OBRISI_UCESNIKA, selektovani);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Šalje zahtev za izmenu podataka učesnika.
     *
     * @param uc učesnik sa izmenjenim podacima
     */
    public void promeniUcesnika(Ucesnik uc) {
        NivoVestine nv = pronadjiNivoVestine(uc.getNivo());
        uc.setNivo(nv);
        Zahtev z = new Zahtev(Operacija.IZMENI_UCESNIKA, uc);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Traži od servera listu svih instruktora i deserijalizuje je iz JSON-a.
     *
     * @return lista svih instruktora
     */
    public List<Instruktor> ucitajInstruktore() {
        Zahtev z = new Zahtev(Operacija.UCITAJ_INSTRUKTORE, null);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<Instruktor>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

    /**
     * Traži od servera listu svih upisa i deserijalizuje je iz JSON-a.
     *
     * @return lista svih upisa
     */
    public List<UpisNaProgram> ucitajUpise() {
        Zahtev z = new Zahtev(Operacija.UCITAJ_UPISE, null);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<UpisNaProgram>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

    /**
     * Šalje zahtev za pretragu upisa i deserijalizuje rezultat iz JSON-a.
     *
     * @param kp kriterijum pretrage
     * @return lista upisa koji odgovaraju kriterijumima
     */
    public List<UpisNaProgram> pretraziUpise(KriterijumPretrage kp) {
        Zahtev z = new Zahtev(Operacija.PRETRAZI_UPISE, kp);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<UpisNaProgram>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

    /**
     * Traži od servera listu svih programa aktivnosti i deserijalizuje je iz JSON-a.
     *
     * @return lista svih programa aktivnosti
     */
    public List<ProgramAktivnosti> ucitajPrograme() {
        Zahtev z = new Zahtev(Operacija.UCITAJ_PROGRAME, null);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<ProgramAktivnosti>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

    /**
     * Šalje zahtev za kreiranje novog upisa.
     *
     * @param unp upis koji se kreira
     */
    public void kreirajUpis(UpisNaProgram unp) {
        Zahtev z = new Zahtev(Operacija.KREIRAJ_UPIS, unp);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Šalje zahtev za izmenu postojećeg upisa.
     *
     * @param zaIzmenu upis sa izmenjenim podacima
     */
    public void izmeniUpis(UpisNaProgram zaIzmenu) {
        Zahtev z = new Zahtev(Operacija.IZMENI_UPIS, zaIzmenu);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Šalje zahtev za promenu statusa upisa.
     *
     * @param up upis sa postavljenim novim statusom
     */
    public void promeniStatusUpisa(UpisNaProgram up) {
        Zahtev z = new Zahtev(Operacija.PROMENI_STATUS_UPISA, up);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }

    /**
     * Traži preporučene programe po stilu plesa i deserijalizuje ih iz JSON-a.
     *
     * @param filter program sa postavljenim stilom plesa kao filterom
     * @return lista preporučenih programa sortiranih po ceni
     */
    public List<ProgramAktivnosti> preporuciProgram(ProgramAktivnosti filter) {
        Zahtev z = new Zahtev(Operacija.PREPORUCI_PROGRAM, filter);
        posiljalac.posalji(z);
        Odgovor o = (Odgovor) primalac.primi();
        Type type = new TypeToken<List<ProgramAktivnosti>>() {}.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }
}