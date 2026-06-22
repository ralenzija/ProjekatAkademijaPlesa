package dance.academy.common.model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja program aktivnosti koji plesna akademija nudi učesnicima.
 * <p>
 * Program aktivnosti je konkretan kurs ili radionica plesa određenog stila
 * koji se odvija u definisanoj sali, u određeno vreme i sa ograničenim brojem
 * mesta. Učesnici se na program upisuju kroz {@link UpisNaProgram}, a svaki
 * program vodi instruktor koji poseduje odgovarajući sertifikat.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class ProgramAktivnosti implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator programa u bazi podataka. */
    private int id;

    /** Naziv programa aktivnosti (npr. "Početni kurs salse"). */
    private String naziv;

    /** Trajanje programa u nedeljama. */
    private int trajanje;

    /** Cena programa u dinarima. */
    private double cena;

    /** Stil plesa koji se uči na ovom programu. */
    private PlesniStil vrsta;

    /** Indikator da li je program trenutno aktivan i dostupan za upis. */
    private boolean aktivan;

    /** Oznaka sale u kojoj se program održava. */
    private String sala;

    /** Datum početka programa. */
    private java.time.LocalDate datumPocetka;

    /** Vreme termina u toku dana kada se program održava. */
    private java.time.LocalTime termin;

    /** Maksimalan broj učesnika koji mogu biti upisani na ovaj program. */
    private int maxUcesnika;

    /** Dodatne napomene o programu (posebni uslovi, oprema i sl.). */
    private String napomena;

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public ProgramAktivnosti() {
    }

    /**
     * Konstruktor koji inicijalizuje program aktivnosti sa svim podacima.
     * Poziva odgovarajuće setter metode radi validacije vrednosti.
     *
     * @param id           jedinstveni identifikator programa
     * @param naziv        naziv programa
     * @param trajanje     trajanje programa u nedeljama
     * @param cena         cena programa
     * @param vrsta        stil plesa koji se uči
     * @param aktivan      da li je program aktivan
     * @param sala         oznaka sale
     * @param datumPocetka datum početka programa
     * @param termin       vreme termina
     * @param maxUcesnika  maksimalan broj učesnika
     * @param napomena     dodatne napomene
     */
    public ProgramAktivnosti(int id, String naziv, int trajanje, double cena, PlesniStil vrsta,
            boolean aktivan, String sala, LocalDate datumPocetka, LocalTime termin,
            int maxUcesnika, String napomena) {
        setId(id);
        setNaziv(naziv);
        setTrajanje(trajanje);
        setCena(cena);
        setVrsta(vrsta);
        setAktivan(aktivan);
        setSala(sala);
        setDatumPocetka(datumPocetka);
        setTermin(termin);
        setMaxUcesnika(maxUcesnika);
        setNapomena(napomena);
    }

    /**
     * Vraća jedinstveni identifikator programa.
     *
     * @return id programa
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator programa.
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
     * Vraća naziv programa aktivnosti.
     *
     * @return naziv programa
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv programa aktivnosti.
     * <p>
     * Naziv ne sme biti null niti prazan string.
     * </p>
     *
     * @param naziv novi naziv
     * @throws NullPointerException     ukoliko je naziv null
     * @throws IllegalArgumentException ukoliko je naziv prazan string
     */
    public void setNaziv(String naziv) {
        if (naziv == null)
            throw new NullPointerException("Naziv ne sme biti null");
        if (naziv.isEmpty())
            throw new IllegalArgumentException("Naziv ne sme biti prazan");
        this.naziv = naziv;
    }

    /**
     * Vraća trajanje programa u nedeljama.
     *
     * @return trajanje u nedeljama
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     * Postavlja trajanje programa u nedeljama.
     * <p>
     * Trajanje mora biti pozitivan broj.
     * </p>
     *
     * @param trajanje novo trajanje
     * @throws IllegalArgumentException ukoliko je trajanje manje ili jednako nuli
     */
    public void setTrajanje(int trajanje) {
        if (trajanje <= 0)
            throw new IllegalArgumentException("Trajanje mora biti pozitivan broj");
        this.trajanje = trajanje;
    }

    /**
     * Vraća cenu programa u dinarima.
     *
     * @return cena programa
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu programa u dinarima.
     * <p>
     * Cena ne sme biti negativna vrednost.
     * </p>
     *
     * @param cena nova cena
     * @throws IllegalArgumentException ukoliko je cena negativna
     */
    public void setCena(double cena) {
        if (cena < 0)
            throw new IllegalArgumentException("Cena ne sme biti negativna");
        this.cena = cena;
    }

    /**
     * Vraća stil plesa koji se uči na ovom programu.
     *
     * @return stil plesa
     */
    public PlesniStil getVrsta() {
        return vrsta;
    }

    /**
     * Postavlja stil plesa koji se uči na ovom programu.
     * <p>
     * Stil plesa ne sme biti null.
     * </p>
     *
     * @param vrsta novi stil plesa
     * @throws NullPointerException ukoliko je vrsta null
     */
    public void setVrsta(PlesniStil vrsta) {
        if (vrsta == null)
            throw new NullPointerException("Stil plesa ne sme biti null");
        this.vrsta = vrsta;
    }

    /**
     * Vraća da li je program trenutno aktivan.
     *
     * @return {@code true} ako je program aktivan, {@code false} inače
     */
    public boolean isAktivan() {
        return aktivan;
    }

    /**
     * Postavlja status aktivnosti programa.
     *
     * @param aktivan {@code true} ako je program aktivan
     */
    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    /**
     * Vraća oznaku sale u kojoj se program održava.
     *
     * @return oznaka sale
     */
    public String getSala() {
        return sala;
    }

    /**
     * Postavlja oznaku sale u kojoj se program održava.
     * <p>
     * Oznaka sale ne sme biti null niti prazan string.
     * </p>
     *
     * @param sala nova oznaka sale
     * @throws NullPointerException     ukoliko je sala null
     * @throws IllegalArgumentException ukoliko je sala prazan string
     */
    public void setSala(String sala) {
        if (sala == null)
            throw new NullPointerException("Sala ne sme biti null");
        if (sala.isEmpty())
            throw new IllegalArgumentException("Sala ne sme biti prazna");
        this.sala = sala;
    }

    /**
     * Vraća datum početka programa.
     *
     * @return datum početka
     */
    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    /**
     * Postavlja datum početka programa.
     * <p>
     * Datum početka ne sme biti null.
     * </p>
     *
     * @param datumPocetka novi datum početka
     * @throws NullPointerException ukoliko je datum null
     */
    public void setDatumPocetka(LocalDate datumPocetka) {
        if (datumPocetka == null)
            throw new NullPointerException("Datum pocetka ne sme biti null");
        this.datumPocetka = datumPocetka;
    }

    /**
     * Vraća vreme termina kada se program održava.
     *
     * @return vreme termina
     */
    public LocalTime getTermin() {
        return termin;
    }

    /**
     * Postavlja vreme termina kada se program održava.
     * <p>
     * Termin ne sme biti null.
     * </p>
     *
     * @param termin novo vreme termina
     * @throws NullPointerException ukoliko je termin null
     */
    public void setTermin(LocalTime termin) {
        if (termin == null)
            throw new NullPointerException("Termin ne sme biti null");
        this.termin = termin;
    }

    /**
     * Vraća maksimalan broj učesnika na programu.
     *
     * @return maksimalan broj učesnika
     */
    public int getMaxUcesnika() {
        return maxUcesnika;
    }

    /**
     * Postavlja maksimalan broj učesnika na programu.
     * <p>
     * Maksimalan broj učesnika mora biti pozitivan broj.
     * </p>
     *
     * @param maxUcesnika novi maksimalan broj
     * @throws IllegalArgumentException ukoliko je broj manji ili jednak nuli
     */
    public void setMaxUcesnika(int maxUcesnika) {
        if (maxUcesnika <= 0)
            throw new IllegalArgumentException("Maksimalan broj ucesnika mora biti pozitivan");
        this.maxUcesnika = maxUcesnika;
    }

    /**
     * Vraća dodatne napomene o programu.
     *
     * @return napomena
     */
    public String getNapomena() {
        return napomena;
    }

    /**
     * Postavlja dodatne napomene o programu.
     * <p>
     * Napomena može biti null ili prazan string ukoliko
     * nema posebnih napomena o programu.
     * </p>
     *
     * @param napomena nova napomena
     */
    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    /**
     * Vraća naziv programa kao tekstualni prikaz.
     *
     * @return naziv programa
     */
    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Poredi dva programa aktivnosti po jedinstvenom identifikatoru.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako imaju isti id, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ProgramAktivnosti other = (ProgramAktivnosti) obj;
        return this.id == other.id;
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "program_aktivnosti"
     */
    @Override
    public String vratiNazivTabele() {
        return "program_aktivnosti";
    }

    /**
     * Kreira listu programa aktivnosti na osnovu rezultata SQL upita.
     *
     * @param rs rezultat SQL upita koji sadrži podatke o programima
     * @return lista programa aktivnosti
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("program_aktivnosti.id");
            String naziv = rs.getString("program_aktivnosti.naziv");
            int trajanje = rs.getInt("program_aktivnosti.trajanje");
            double cena = rs.getDouble("program_aktivnosti.cena");
            PlesniStil ples = PlesniStil.valueOf(rs.getString("program_aktivnosti.vrsta"));
            boolean aktivan = rs.getBoolean("program_aktivnosti.aktivan");
            String sala = rs.getString("program_aktivnosti.sala");
            java.sql.Date d = rs.getDate("program_aktivnosti.datum_pocetka");
            LocalDate datumPocetka = (d != null) ? d.toLocalDate() : null;
            java.sql.Time t = rs.getTime("program_aktivnosti.termin");
            LocalTime termin = (t != null) ? t.toLocalTime() : null;
            int maxUcesnika = rs.getInt("program_aktivnosti.max_ucesnika");
            String napomena = rs.getString("program_aktivnosti.napomena");
            ProgramAktivnosti kp = new ProgramAktivnosti(
                    id, naziv, trajanje, cena, ples,
                    aktivan, sala, datumPocetka, termin, maxUcesnika, napomena);
            lista.add(kp);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog programa u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,trajanje,cena,vrsta,aktivan,sala,datum_pocetka,termin,max_ucesnika,napomena";
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
     * @return string u formatu "program_aktivnosti.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "program_aktivnosti.id=" + id;
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