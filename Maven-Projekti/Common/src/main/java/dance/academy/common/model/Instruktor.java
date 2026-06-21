package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Predstavlja instruktora plesne akademije.
 * <p>
 * Instruktor je zaposleni koji vodi programe aktivnosti i odgovoran je
 * za napredak učesnika. Svaki instruktor se prijavljuje na sistem putem
 * email adrese i šifre, a može posedovati više sertifikata za različite
 * stilove plesa koji su evidentirani kroz {@link InstruktorSertifikat}.
 * </p>
 * <p>
 * Lombok anotacije {@code @Getter}, {@code @Setter}, {@code @NoArgsConstructor}
 * i {@code @AllArgsConstructor} automatski generišu get/set metode i
 * konstruktore u toku kompajliranja, eliminišući boilerplate kod.
 * Metode {@code toString}, {@code equals} i {@code hashCode} su eksplicitno
 * napisane jer sadrže specifičnu poslovnu logiku.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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