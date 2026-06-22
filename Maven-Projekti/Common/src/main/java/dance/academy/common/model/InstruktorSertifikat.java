package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja vezu između instruktora i sertifikata koji poseduje.
 * <p>
 * Ova klasa modeluje asocijativnu tabelu koja beleži koji instruktor
 * poseduje koji sertifikat i kada ga je stekao. Jedan instruktor može
 * imati više sertifikata za različite stilove plesa, a datum sticanja
 * služi kao dokaz o vremenu sticanja kvalifikacije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class InstruktorSertifikat implements ApstraktniDomenskiObjekat {

    /** Instruktor koji poseduje sertifikat. */
    private Instruktor instruktor;

    /** Sertifikat koji instruktor poseduje. */
    private Sertifikat sertifikat;

    /** Datum kada je instruktor stekao sertifikat. */
    private Date datumSticanja;

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public InstruktorSertifikat() {
    }

    /**
     * Konstruktor koji inicijalizuje vezu instruktora i sertifikata.
     * Poziva odgovarajuće setter metode radi validacije vrednosti.
     *
     * @param instruktor    instruktor koji poseduje sertifikat
     * @param sertifikat    sertifikat koji instruktor poseduje
     * @param datumSticanja datum kada je sertifikat stečen
     */
    public InstruktorSertifikat(Instruktor instruktor, Sertifikat sertifikat, Date datumSticanja) {
        setInstruktor(instruktor);
        setSertifikat(sertifikat);
        setDatumSticanja(datumSticanja);
    }

    /**
     * Vraća instruktora koji poseduje sertifikat.
     *
     * @return instruktor
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Postavlja instruktora koji poseduje sertifikat.
     * <p>
     * Instruktor ne sme biti null.
     * </p>
     *
     * @param instruktor novi instruktor
     * @throws NullPointerException ukoliko je instruktor null
     */
    public void setInstruktor(Instruktor instruktor) {
        if (instruktor == null)
            throw new NullPointerException("Instruktor ne sme biti null");
        this.instruktor = instruktor;
    }

    /**
     * Vraća sertifikat koji instruktor poseduje.
     *
     * @return sertifikat
     */
    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    /**
     * Postavlja sertifikat koji instruktor poseduje.
     * <p>
     * Sertifikat ne sme biti null.
     * </p>
     *
     * @param sertifikat novi sertifikat
     * @throws NullPointerException ukoliko je sertifikat null
     */
    public void setSertifikat(Sertifikat sertifikat) {
        if (sertifikat == null)
            throw new NullPointerException("Sertifikat ne sme biti null");
        this.sertifikat = sertifikat;
    }

    /**
     * Vraća datum kada je instruktor stekao sertifikat.
     *
     * @return datum sticanja sertifikata
     */
    public Date getDatumSticanja() {
        return datumSticanja;
    }

    /**
     * Postavlja datum kada je instruktor stekao sertifikat.
     * <p>
     * Datum sticanja ne sme biti null.
     * </p>
     *
     * @param datumSticanja novi datum sticanja
     * @throws NullPointerException ukoliko je datum null
     */
    public void setDatumSticanja(Date datumSticanja) {
        if (datumSticanja == null)
            throw new NullPointerException("Datum sticanja ne sme biti null");
        this.datumSticanja = datumSticanja;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Vraća tekstualni prikaz veze instruktora i sertifikata.
     *
     * @return string reprezentacija
     */
    @Override
    public String toString() {
        return "InstruktorSertifikat{" + "instruktor=" + instruktor +
                ", sertifikat=" + sertifikat + ", datumSticanja=" + datumSticanja + '}';
    }

    /**
     * Poredi dve veze instruktor-sertifikat po instruktoru, sertifikatu i datumu sticanja.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su veze jednake, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final InstruktorSertifikat other = (InstruktorSertifikat) obj;
        if (!Objects.equals(this.instruktor, other.instruktor)) return false;
        if (!Objects.equals(this.sertifikat, other.sertifikat)) return false;
        return Objects.equals(this.datumSticanja, other.datumSticanja);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "instruktor_sertifikat"
     */
    @Override
    public String vratiNazivTabele() {
        return "instruktor_sertifikat";
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
     * Vraća nazive kolona koje se koriste pri unosu nove veze u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "instruktor,sertifikat,datumSticanja";
    }

    /**
     * Vraća vrednosti atributa formatiranih za SQL INSERT upit.
     *
     * @return string sa vrednostima odvojenim zarezima
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return instruktor.getId() + "," + sertifikat.getId() + ",'" + datumSticanja + "'";
    }

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     *
     * @return string u formatu "instruktor_sertifikat.instruktor=X AND instruktor_sertifikat.sertifikat=Y"
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "instruktor_sertifikat.instruktor=" + instruktor.getId() +
                " AND instruktor_sertifikat.sertifikat=" + sertifikat.getId();
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