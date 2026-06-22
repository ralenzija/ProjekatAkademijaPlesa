package dance.academy.common.model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja učesnika plesne akademije.
 * <p>
 * Učesnik je osoba koja se može upisati na kurseve plesa koje vode instruktori.
 * Svaki učesnik ima osnovne lične podatke, kontakt informacije, datum rođenja,
 * kao i nivo veštine u plesu koji se dodeljuje nakon procene.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class Ucesnik implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator učesnika u bazi podataka. */
    private int id;

    /** Ime učesnika. */
    private String ime;

    /** Prezime učesnika. */
    private String prezime;

    /** Email adresa učesnika, koristi se i za identifikaciju. */
    private String email;

    /** Nivo veštine učesnika u plesu, određuje u koje kurseve može da se upiše. */
    private NivoVestine nivo;

    /** Kontakt telefon učesnika. */
    private String telefon;

    /** Datum rođenja učesnika. */
    private java.time.LocalDate datumRodjenja;

    /** Dodatne napomene o učesniku (zdravstvena stanja, posebni zahtevi i sl.). */
    private String napomena;

    /**
     * Podrazumevani konstruktor bez argumenata.
     * Koristi se pri kreiranju praznog objekta pre popunjavanja podataka.
     */
    public Ucesnik() {
    }

    /**
     * Konstruktor koji inicijalizuje učesnika sa svim potrebnim podacima.
     * Poziva odgovarajuće setter metode radi validacije vrednosti.
     *
     * @param id             jedinstveni identifikator učesnika
     * @param ime            ime učesnika
     * @param prezime        prezime učesnika
     * @param email          email adresa učesnika
     * @param nivo           nivo veštine učesnika u plesu
     * @param telefon        kontakt telefon učesnika
     * @param datumRodjenja  datum rođenja učesnika
     * @param napomena       dodatne napomene o učesniku
     */
    public Ucesnik(int id, String ime, String prezime, String email, NivoVestine nivo,
            String telefon, LocalDate datumRodjenja, String napomena) {
        setId(id);
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
        setNivo(nivo);
        setTelefon(telefon);
        setDatumRodjenja(datumRodjenja);
        setNapomena(napomena);
    }

    /**
     * Vraća jedinstveni identifikator učesnika.
     *
     * @return id učesnika
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator učesnika.
     * <p>
     * ID mora biti veći ili jednak nuli, ili -1 kao privremena vrednost
     * pre upisa u bazu podataka.
     * </p>
     *
     * @param id novi identifikator učesnika
     * @throws IllegalArgumentException ukoliko je id manji od -1
     */
    public void setId(int id) {
        if (id < -1)
            throw new IllegalArgumentException("ID ne sme biti manji od -1");
        this.id = id;
    }

    /**
     * Vraća ime učesnika.
     *
     * @return ime učesnika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime učesnika.
     * <p>
     * Ime ne sme biti null niti prazan string.
     * </p>
     *
     * @param ime novo ime učesnika
     * @throws NullPointerException     ukoliko je ime null
     * @throws IllegalArgumentException ukoliko je ime prazan string
     */
    public void setIme(String ime) {
        if (ime == null)
            throw new NullPointerException("Ime ne sme biti null");
        if (ime.isEmpty())
            throw new IllegalArgumentException("Ime ne sme biti prazno");
        this.ime = ime;
    }

    /**
     * Vraća prezime učesnika.
     *
     * @return prezime učesnika
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime učesnika.
     * <p>
     * Prezime ne sme biti null niti prazan string.
     * </p>
     *
     * @param prezime novo prezime učesnika
     * @throws NullPointerException     ukoliko je prezime null
     * @throws IllegalArgumentException ukoliko je prezime prazan string
     */
    public void setPrezime(String prezime) {
        if (prezime == null)
            throw new NullPointerException("Prezime ne sme biti null");
        if (prezime.isEmpty())
            throw new IllegalArgumentException("Prezime ne sme biti prazno");
        this.prezime = prezime;
    }

    /**
     * Vraća email adresu učesnika.
     *
     * @return email učesnika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu učesnika.
     * <p>
     * Email ne sme biti null niti prazan string.
     * </p>
     *
     * @param email nova email adresa učesnika
     * @throws NullPointerException     ukoliko je email null
     * @throws IllegalArgumentException ukoliko je email prazan string
     */
    public void setEmail(String email) {
        if (email == null)
            throw new NullPointerException("Email ne sme biti null");
        if (email.isEmpty())
            throw new IllegalArgumentException("Email ne sme biti prazan");
        this.email = email;
    }

    /**
     * Vraća nivo veštine učesnika u plesu.
     *
     * @return nivo veštine učesnika
     */
    public NivoVestine getNivo() {
        return nivo;
    }

    /**
     * Postavlja nivo veštine učesnika u plesu.
     * <p>
     * Nivo veštine ne sme biti null.
     * </p>
     *
     * @param nivo novi nivo veštine
     * @throws NullPointerException ukoliko je nivo null
     */
    public void setNivo(NivoVestine nivo) {
        if (nivo == null)
            throw new NullPointerException("Nivo vestine ne sme biti null");
        this.nivo = nivo;
    }

    /**
     * Vraća kontakt telefon učesnika.
     *
     * @return telefon učesnika
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Postavlja kontakt telefon učesnika.
     * <p>
     * Telefon može biti null ili prazan string ukoliko učesnik
     * nije uneo broj telefona. Ako je unet, mora sadržati
     * isključivo cifre.
     * </p>
     *
     * @param telefon novi kontakt telefon
     * @throws IllegalArgumentException ukoliko telefon sadrži znakove koji nisu cifre
     */
    public void setTelefon(String telefon) {
        if (telefon != null && !telefon.isEmpty() && !telefon.matches("\\+?\\d+"))
            throw new IllegalArgumentException("Telefon sme sadrzati samo cifre");
        this.telefon = telefon;
    }

    /**
     * Vraća datum rođenja učesnika.
     *
     * @return datum rođenja
     */
    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Postavlja datum rođenja učesnika.
     * <p>
     * Datum rođenja ne sme biti null niti u budućnosti.
     * </p>
     *
     * @param datumRodjenja novi datum rođenja
     * @throws NullPointerException     ukoliko je datum null
     * @throws IllegalArgumentException ukoliko je datum u budućnosti
     */
    public void setDatumRodjenja(LocalDate datumRodjenja) {
        if (datumRodjenja == null)
            throw new NullPointerException("Datum rodjenja ne sme biti null");
        if (datumRodjenja.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Datum rodjenja ne moze biti u buducnosti");
        this.datumRodjenja = datumRodjenja;
    }

    /**
     * Vraća napomenu o učesniku.
     *
     * @return napomena
     */
    public String getNapomena() {
        return napomena;
    }

    /**
     * Postavlja napomenu o učesniku.
     * <p>
     * Napomena može biti null ili prazan string ukoliko
     * nema posebnih napomena o učesniku.
     * </p>
     *
     * @param napomena nova napomena
     */
    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    /**
     * Vraća tekstualni prikaz učesnika u formatu "Ime Prezime".
     *
     * @return ime i prezime učesnika
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Poredi dva učesnika po imenu, prezimenu i email adresi.
     * Dva učesnika su jednaka ako im se poklapaju sva tri polja.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su učesnici jednaki, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Ucesnik other = (Ucesnik) obj;
        if (!Objects.equals(this.ime, other.ime)) return false;
        if (!Objects.equals(this.prezime, other.prezime)) return false;
        return Objects.equals(this.email, other.email);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "ucesnik"
     */
    @Override
    public String vratiNazivTabele() {
        return "ucesnik";
    }

    /**
     * Kreira listu učesnika na osnovu rezultata SQL upita.
     *
     * @param rs rezultat SQL upita koji sadrži podatke o učesnicima
     * @return lista učesnika iz baze podataka
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("telefon");
            String napomena = rs.getString("napomena");
            java.sql.Date datumRodjenjaSQL = rs.getDate("datum_rodjenja");
            java.time.LocalDate datumRodjenja = (datumRodjenjaSQL != null) ? datumRodjenjaSQL.toLocalDate() : null;
            int nivoId = rs.getInt("nivo");
            NivoVestine nivo = new NivoVestine();
            nivo.setId(nivoId);
            Ucesnik u = new Ucesnik();
            u.setId(id);
            u.setIme(ime);
            u.setPrezime(prezime);
            u.setEmail(email);
            u.setNivo(nivo);
            u.setTelefon(telefon);
            u.setDatumRodjenja(datumRodjenja);
            u.setNapomena(napomena);
            lista.add(u);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog učesnika u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,telefon,datum_rodjenja,napomena,nivo";
    }

    /**
     * Vraća vrednosti atributa učesnika formatiranih za SQL INSERT upit.
     *
     * @return string sa vrednostima odvojenim zarezima, spreman za SQL upit
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + telefon + "','"
                + java.sql.Date.valueOf(datumRodjenja) + "','" + napomena + "'," + nivo.getId();
    }

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     *
     * @return string u formatu "ucesnik.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "ucesnik.id=" + id;
    }

    /**
     * Nije podržano za ovu klasu.
     *
     * @param rs rezultat SQL upita
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Vraća vrednosti atributa učesnika formatiranih za SQL UPDATE upit.
     *
     * @return string sa vrednostima u formatu "kolona=vrednost"
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "',prezime='" + prezime + "',email='" + email + "',telefon='" + telefon + "',datum_rodjenja='"
                + java.sql.Date.valueOf(datumRodjenja) + "',napomena='" + napomena + "',nivo=" + nivo.getId();
    }
}