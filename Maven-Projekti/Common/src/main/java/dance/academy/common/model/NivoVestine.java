package dance.academy.common.model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja nivo veštine učesnika za određeni stil plesa.
 * <p>
 * Kombinacija {@link PlesniNivo} i {@link PlesniStil} definiše precizno
 * koliko je učesnik napredovao u konkretnom stilu. Na primer, učesnik može
 * biti na naprednom nivou u salsi, ali na početnom u tangu. Nivo veštine
 * se čuva u bazi i koristi se pri određivanju u koje programe učesnik
 * može da se upiše.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class NivoVestine implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator nivoa veštine u bazi podataka. */
    private int id;

    /** Nivo znanja učesnika (početni, srednji, napredni, majstorski). */
    private PlesniNivo nivo;

    /** Stil plesa na koji se ovaj nivo veštine odnosi. */
    private PlesniStil vrsta;

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public NivoVestine() {
    }

    /**
     * Konstruktor koji inicijalizuje nivo veštine sa svim podacima.
     *
     * @param id    jedinstveni identifikator
     * @param nivo  nivo znanja učesnika
     * @param vrsta stil plesa
     */
    public NivoVestine(int id, PlesniNivo nivo, PlesniStil vrsta) {
        this.id = id;
        this.nivo = nivo;
        this.vrsta = vrsta;
    }

    /**
     * Vraća jedinstveni identifikator nivoa veštine.
     *
     * @return id nivoa veštine
     */
    public int getId() { return id; }

    /**
     * Postavlja jedinstveni identifikator nivoa veštine.
     *
     * @param id novi identifikator
     */
    public void setId(int id) { this.id = id; }

    /**
     * Vraća nivo znanja učesnika.
     *
     * @return nivo znanja
     */
    public PlesniNivo getNivo() { return nivo; }

    /**
     * Postavlja nivo znanja učesnika.
     *
     * @param nivo novi nivo znanja
     */
    public void setNivo(PlesniNivo nivo) { this.nivo = nivo; }

    /**
     * Vraća stil plesa na koji se nivo veštine odnosi.
     *
     * @return stil plesa
     */
    public PlesniStil getVrsta() { return vrsta; }

    /**
     * Postavlja stil plesa na koji se nivo veštine odnosi.
     *
     * @param vrsta novi stil plesa
     */
    public void setVrsta(PlesniStil vrsta) { this.vrsta = vrsta; }

    /**
     * Vraća tekstualni prikaz nivoa veštine sa nivoom i stilom plesa.
     *
     * @return string reprezentacija nivoa veštine
     */
    @Override
    public String toString() {
        return "NivoVestine{" + "nivo=" + nivo + ", vrsta=" + vrsta + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dva nivoa veštine po jedinstvenom identifikatoru.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako imaju isti id, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final NivoVestine other = (NivoVestine) obj;
        return this.id == other.id;
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "nivo_vestine"
     */
    @Override
    public String vratiNazivTabele() { return "nivo_vestine"; }

    /**
     * Kreira listu nivoa veštine na osnovu rezultata SQL upita.
     * <p>
     * Za svaki red u {@link ResultSet}-u kreira se objekat {@link NivoVestine}
     * sa odgovarajućim nivoom i stilom plesa, konvertovanim iz stringa u enum.
     * </p>
     *
     * @param rs rezultat SQL upita koji sadrži podatke o nivoima veštine
     * @return lista nivoa veštine
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("nivo_vestine.id");
            PlesniNivo nivo = PlesniNivo.valueOf(rs.getString("nivo_vestine.nivo"));
            PlesniStil ples = PlesniStil.valueOf(rs.getString("nivo_vestine.vrsta"));
            NivoVestine nv = new NivoVestine(id, nivo, ples);
            lista.add(nv);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog nivoa veštine u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() { return "nivo,vrsta"; }

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
     * @return string u formatu "nivo_vestine.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() { return "nivo_vestine.id=" + id; }

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