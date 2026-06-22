package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Predstavlja instruktora plesne akademije.
 * <p>
 * Instruktor je zaposleni koji vodi programe aktivnosti i odgovoran je
 * za napredak učesnika. Svaki instruktor se prijavljuje na sistem putem
 * email adrese i šifre, a može posedovati više sertifikata za različite
 * stilove plesa koji su evidentirani kroz {@link InstruktorSertifikat}.
 * </p>
 * <p>
 * Lombok anotacija {@code @Getter} automatski generiše get metode u toku
 * kompajliranja. Setter metode su eksplicitno napisane jer sadrže validaciju
 * vrednosti atributa. Metode {@code toString}, {@code equals} i {@code hashCode}
 * su eksplicitno napisane jer sadrže specifičnu poslovnu logiku.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
@Getter
@NoArgsConstructor
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
     * Konstruktor koji inicijalizuje instruktora sa svim podacima.
     * Poziva odgovarajuće setter metode radi validacije vrednosti.
     *
     * @param id      jedinstveni identifikator instruktora
     * @param ime     ime instruktora
     * @param prezime prezime instruktora
     * @param email   email adresa za prijavu
     * @param sifra   šifra za prijavu
     */
    public Instruktor(int id, String ime, String prezime, String email, String sifra) {
        setId(id);
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
        setSifra(sifra);
    }

    /**
     * Postavlja jedinstveni identifikator instruktora.
     * <p>
     * ID mora biti veći ili jednak nuli, ili -1 kao privremena vrednost
     * pre upisa u bazu podataka.
     * </p>
     *
     * @param id novi identifikator instruktora
     * @throws IllegalArgumentException ukoliko je id manji od -1
     */
    public void setId(int id) {
        if (id < -1)
            throw new IllegalArgumentException("ID ne sme biti manji od -1");
        this.id = id;
    }

    /**
     * Postavlja ime instruktora.
     * <p>
     * Ime ne sme biti null niti prazan string.
     * </p>
     *
     * @param ime novo ime instruktora
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
     * Postavlja prezime instruktora.
     * <p>
     * Prezime ne sme biti null niti prazan string.
     * </p>
     *
     * @param prezime novo prezime instruktora
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
     * Postavlja email adresu instruktora.
     * <p>
     * Email ne sme biti null niti prazan string.
     * </p>
     *
     * @param email nova email adresa instruktora
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
     * Postavlja šifru instruktora.
     * <p>
     * Šifra ne sme biti null niti prazan string.
     * </p>
     *
     * @param sifra nova šifra instruktora
     * @throws NullPointerException     ukoliko je šifra null
     * @throws IllegalArgumentException ukoliko je šifra prazan string
     */
    public void setSifra(String sifra) {
        if (sifra == null)
            throw new NullPointerException("Sifra ne sme biti null");
        if (sifra.isEmpty())
            throw new IllegalArgumentException("Sifra ne sme biti prazna");
        this.sifra = sifra;
    }

    /**
     * Vraća tekstualni prikaz instruktora u formatu "Ime Prezime".
     *
     * @return ime i prezime instruktora
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Poredi dva instruktora po email adresi i šifri.
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
    public String vratiNazivTabele() {
        return "instruktor";
    }

    /**
     * Kreira listu instruktora na osnovu rezultata SQL upita.
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
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,sifra";
    }

    /**
     * Nije podržano za ovu klasu.
     *
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
    public String vratiPrimarniKljuc() {
        return "instruktor.id=" + id;
    }

    /**
     * Nije podržano za ovu klasu.
     *
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Nije podržano za ovu klasu.
     *
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}