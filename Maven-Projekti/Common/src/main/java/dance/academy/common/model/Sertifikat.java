package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.List;

/**
 * Predstavlja sertifikat koji instruktor poseduje za određeni stil plesa.
 * <p>
 * Sertifikat dokazuje da je instruktor stručno osposobljen da predaje
 * određeni stil plesa i da je tu obuku dobio od konkretne organizacije.
 * Svaki instruktor može imati više sertifikata za različite stilove.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class Sertifikat implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator sertifikata u bazi podataka. */
    private int id;

    /** Stil plesa na koji se sertifikat odnosi. */
    private PlesniStil ples;

    /** Naziv organizacije koja je izdala sertifikat. */
    private String organizacija;

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public Sertifikat() {
    }

    /**
     * Konstruktor koji inicijalizuje sertifikat sa svim podacima.
     * Poziva odgovarajuće setter metode radi validacije vrednosti.
     *
     * @param id           jedinstveni identifikator sertifikata
     * @param naziv        stil plesa na koji se sertifikat odnosi
     * @param organizacija naziv organizacije koja je izdala sertifikat
     */
    public Sertifikat(int id, PlesniStil naziv, String organizacija) {
        setId(id);
        setPles(naziv);
        setOrganizacija(organizacija);
    }

    /**
     * Vraća jedinstveni identifikator sertifikata.
     *
     * @return id sertifikata
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator sertifikata.
     * <p>
     * ID mora biti veći ili jednak nuli, ili -1 kao privremena vrednost
     * pre upisa u bazu podataka.
     * </p>
     *
     * @param id novi identifikator
     * @throws IllegalArgumentException ukoliko je id manji od -1
     */
    public void setId(int id) {
        if (id < -1)
            throw new IllegalArgumentException("ID ne sme biti manji od -1");
        this.id = id;
    }

    /**
     * Vraća stil plesa na koji se sertifikat odnosi.
     *
     * @return stil plesa
     */
    public PlesniStil getPles() {
        return ples;
    }

    /**
     * Postavlja stil plesa na koji se sertifikat odnosi.
     * <p>
     * Stil plesa ne sme biti null.
     * </p>
     *
     * @param ples novi stil plesa
     * @throws NullPointerException ukoliko je ples null
     */
    public void setPles(PlesniStil ples) {
        if (ples == null)
            throw new NullPointerException("Stil plesa ne sme biti null");
        this.ples = ples;
    }

    /**
     * Vraća naziv organizacije koja je izdala sertifikat.
     *
     * @return naziv organizacije
     */
    public String getOrganizacija() {
        return organizacija;
    }

    /**
     * Postavlja naziv organizacije koja je izdala sertifikat.
     * <p>
     * Naziv organizacije ne sme biti null niti prazan string.
     * </p>
     *
     * @param organizacija naziv organizacije
     * @throws NullPointerException     ukoliko je organizacija null
     * @throws IllegalArgumentException ukoliko je organizacija prazan string
     */
    public void setOrganizacija(String organizacija) {
        if (organizacija == null)
            throw new NullPointerException("Organizacija ne sme biti null");
        if (organizacija.isEmpty())
            throw new IllegalArgumentException("Organizacija ne sme biti prazna");
        this.organizacija = organizacija;
    }

    /**
     * Vraća tekstualni prikaz sertifikata sa stilom plesa i organizacijom.
     *
     * @return string reprezentacija sertifikata
     */
    @Override
    public String toString() {
        return "Sertifikat{" + "ples=" + ples + ", organizacija=" + organizacija + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dva sertifikata po jedinstvenom identifikatoru.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako imaju isti id, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Sertifikat other = (Sertifikat) obj;
        return this.id == other.id;
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "sertifikat"
     */
    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    /**
     * Nije podržano za ovu klasu.
     *
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog sertifikata u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ples,organizacija";
    }

    /**
     * Vraća vrednosti atributa sertifikata formatiranih za SQL INSERT upit.
     *
     * @return string sa vrednostima odvojenim zarezima
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + String.valueOf(ples) + "','" + organizacija + "'";
    }

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     *
     * @return string u formatu "sertifikat.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "sertifikat.id=" + id;
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
     * Nije podržano za ovu klasu — sertifikati se ne menjaju, samo dodaju ili brišu.
     *
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}