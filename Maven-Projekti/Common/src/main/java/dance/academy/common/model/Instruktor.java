package dance.academy.common.model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja instruktora plesne akademije.
 * <p>
 * Instruktor je zaposleni koji vodi programe aktivnosti i odgovoran je
 * za napredak učesnika. Svaki instruktor se prijavljuje na sistem putem
 * email adrese i šifre, a može posedovati više sertifikata za različite
 * stilove plesa koji su evidentirani kroz {@link InstruktorSertifikat}.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class Instruktor implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator instruktora u bazi podataka. */
    private int id;

    /** Ime instruktora. */
    private String ime;

    /** Prezime instruktora. */
    private String prezime;

    /** Email adresa instruktora, koristi se za prijavu na sistem. */
    private String email;

    /** Šifra instruktora za prijavu na sistem. */
    private String sifra;

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public Instruktor() {
    }

    /**
     * Konstruktor koji inicijalizuje instruktora sa svim podacima.
     *
     * @param id      jedinstveni identifikator instruktora
     * @param ime     ime instruktora
     * @param prezime prezime instruktora
     * @param email   email adresa za prijavu
     * @param sifra   šifra za prijavu
     */
    public Instruktor(int id, String ime, String prezime, String email, String sifra) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
    }

    /**
     * Vraća jedinstveni identifikator instruktora.
     *
     * @return id instruktora
     */
    public int getId() { return id; }

    /**
     * Postavlja jedinstveni identifikator instruktora.
     *
     * @param id novi identifikator
     */
    public void setId(int id) { this.id = id; }

    /**
     * Vraća ime instruktora.
     *
     * @return ime instruktora
     */
    public String getIme() { return ime; }

    /**
     * Postavlja ime instruktora.
     *
     * @param ime novo ime
     */
    public void setIme(String ime) { this.ime = ime; }

    /**
     * Vraća prezime instruktora.
     *
     * @return prezime instruktora
     */
    public String getPrezime() { return prezime; }

    /**
     * Postavlja prezime instruktora.
     *
     * @param prezime novo prezime
     */
    public void setPrezime(String prezime) { this.prezime = prezime; }

    /**
     * Vraća email adresu instruktora.
     *
     * @return email adresa
     */
    public String getEmail() { return email; }

    /**
     * Postavlja email adresu instruktora.
     *
     * @param email nova email adresa
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Vraća šifru instruktora.
     *
     * @return šifra
     */
    public String getSifra() { return sifra; }

    /**
     * Postavlja šifru instruktora.
     *
     * @param sifra nova šifra
     */
    public void setSifra(String sifra) { this.sifra = sifra; }

    /**
     * Vraća tekstualni prikaz instruktora u formatu "Ime Prezime".
     *
     * @return ime i prezime instruktora
     */
    @Override
    public String toString() { return ime + " " + prezime; }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Poredi dva instruktora po email adresi i šifri.
     * Koristi se pri proveri da li su isti nalog.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su email i šifra jednaki, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Instruktor other = (Instruktor) obj;
        if (!Objects.equals(this.email, other.email)) return false;
        return Objects.equals(this.sifra, other.sifra);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "instruktor"
     */
    @Override
    public String vratiNazivTabele() { return "instruktor"; }

    /**
     * Kreira listu instruktora na osnovu rezultata SQL upita.
     * <p>
     * Za svaki red u {@link ResultSet}-u kreira se objekat {@link Instruktor}
     * sa svim podacima iz tabele instruktor.
     * </p>
     *
     * @param rs rezultat SQL upita koji sadrži podatke o instruktorima
     * @return lista instruktora
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("instruktor.id");
            String ime = rs.getString("instruktor.ime");
            String prezime = rs.getString("instruktor.prezime");
            String email = rs.getString("instruktor.email");
            String sifra = rs.getString("instruktor.sifra");
            Instruktor i = new Instruktor(id, ime, prezime, email, sifra);
            lista.add(i);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog instruktora u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() { return "ime,prezime,email,sifra"; }

    /**
     * Nije podržano za ovu klasu.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     *
     * @return string u formatu "instruktor.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() { return "instruktor.id=" + id; }

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
     * Nije podržano za ovu klasu.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}