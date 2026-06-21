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

    /**
     * Jedinstveni identifikator učesnika u bazi podataka.
     */
    private int id;

    /**
     * Ime učesnika.
     */
    private String ime;

    /**
     * Prezime učesnika.
     */
    private String prezime;

    /**
     * Email adresa učesnika, koristi se i za identifikaciju.
     */
    private String email;

    /**
     * Nivo veštine učesnika u plesu, određuje u koje kurseve može da se upiše.
     */
    private NivoVestine nivo;

    /**
     * Kontakt telefon učesnika.
     */
    private String telefon;

    /**
     * Datum rođenja učesnika.
     */
    private java.time.LocalDate datumRodjenja;

    /**
     * Dodatne napomene o učesniku (zdravstvena stanja, posebni zahtevi i sl.).
     */
    private String napomena;

    /**
     * Podrazumevani konstruktor bez argumenata. Koristi se pri kreiranju
     * praznog objekta pre popunjavanja podataka.
     */
    public Ucesnik() {
    }

    /**
     * Konstruktor koji inicijalizuje učesnika sa svim potrebnim podacima.
     *
     * @param id jedinstveni identifikator učesnika
     * @param ime ime učesnika
     * @param prezime prezime učesnika
     * @param email email adresa učesnika
     * @param nivo nivo veštine učesnika u plesu
     * @param telefon kontakt telefon učesnika
     * @param datumRodjenja datum rođenja učesnika
     * @param napomena dodatne napomene o učesniku
     */
    public Ucesnik(int id, String ime, String prezime, String email, NivoVestine nivo,
            String telefon, LocalDate datumRodjenja, String napomena) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivo = nivo;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.napomena = napomena;
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
     *
     * @param id novi identifikator učesnika
     */
    public void setId(int id) {
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
     *
     * @param ime novo ime učesnika
     */
    public void setIme(String ime) {
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
     *
     * @param prezime novo prezime učesnika
     */
    public void setPrezime(String prezime) {
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
     *
     * @param email nova email adresa učesnika
     */
    public void setEmail(String email) {
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
     *
     * @param nivo novi nivo veštine
     */
    public void setNivo(NivoVestine nivo) {
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
     *
     * @param telefon novi kontakt telefon
     */
    public void setTelefon(String telefon) {
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
     *
     * @param datumRodjenja novi datum rođenja
     */
    public void setDatumRodjenja(LocalDate datumRodjenja) {
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
     * Poredi dva učesnika po imenu, prezimenu i email adresi. Dva učesnika su
     * jednaka ako im se poklapaju sva tri polja.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su učesnici jednaki, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ucesnik other = (Ucesnik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
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
     * <p>
     * Za svaki red u {@link ResultSet}-u kreira se novi objekat {@link Ucesnik}
     * i dodaje u listu. Datum rođenja se konvertuje iz SQL u Java tip.
     * </p>
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
            Ucesnik u = new Ucesnik(id, ime, prezime, email, nivo, telefon, datumRodjenja, napomena);
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
     * @return string sa vrednostima odvojenim zarezima, spreman za SQL upit
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "',prezime='" + prezime + "',email='" + email + "',telefon='" + telefon + "',datum_rodjenja='"
                + java.sql.Date.valueOf(datumRodjenja) + "',napomena='" + napomena + "',nivo=" + nivo.getId();
    }
}
