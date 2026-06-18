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
        this.id = id;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.cena = cena;
        this.vrsta = vrsta;
        this.aktivan = aktivan;
        this.sala = sala;
        this.datumPocetka = datumPocetka;
        this.termin = termin;
        this.maxUcesnika = maxUcesnika;
        this.napomena = napomena;
    }

    /**
     * Vraća jedinstveni identifikator programa.
     *
     * @return id programa
     */
    public int getId() { return id; }

    /**
     * Postavlja jedinstveni identifikator programa.
     *
     * @param id novi identifikator
     */
    public void setId(int id) { this.id = id; }

    /**
     * Vraća naziv programa aktivnosti.
     *
     * @return naziv programa
     */
    public String getNaziv() { return naziv; }

    /**
     * Postavlja naziv programa aktivnosti.
     *
     * @param naziv novi naziv
     */
    public void setNaziv(String naziv) { this.naziv = naziv; }

    /**
     * Vraća trajanje programa u nedeljama.
     *
     * @return trajanje u nedeljama
     */
    public int getTrajanje() { return trajanje; }

    /**
     * Postavlja trajanje programa u nedeljama.
     *
     * @param trajanje novo trajanje
     */
    public void setTrajanje(int trajanje) { this.trajanje = trajanje; }

    /**
     * Vraća cenu programa u dinarima.
     *
     * @return cena programa
     */
    public double getCena() { return cena; }

    /**
     * Postavlja cenu programa u dinarima.
     *
     * @param cena nova cena
     */
    public void setCena(double cena) { this.cena = cena; }

    /**
     * Vraća stil plesa koji se uči na ovom programu.
     *
     * @return stil plesa
     */
    public PlesniStil getVrsta() { return vrsta; }

    /**
     * Postavlja stil plesa koji se uči na ovom programu.
     *
     * @param vrsta novi stil plesa
     */
    public void setVrsta(PlesniStil vrsta) { this.vrsta = vrsta; }

    /**
     * Vraća da li je program trenutno aktivan.
     *
     * @return {@code true} ako je program aktivan, {@code false} inače
     */
    public boolean isAktivan() { return aktivan; }

    /**
     * Postavlja status aktivnosti programa.
     *
     * @param aktivan {@code true} ako je program aktivan
     */
    public void setAktivan(boolean aktivan) { this.aktivan = aktivan; }

    /**
     * Vraća oznaku sale u kojoj se program održava.
     *
     * @return oznaka sale
     */
    public String getSala() { return sala; }

    /**
     * Postavlja oznaku sale u kojoj se program održava.
     *
     * @param sala nova oznaka sale
     */
    public void setSala(String sala) { this.sala = sala; }

    /**
     * Vraća datum početka programa.
     *
     * @return datum početka
     */
    public LocalDate getDatumPocetka() { return datumPocetka; }

    /**
     * Postavlja datum početka programa.
     *
     * @param datumPocetka novi datum početka
     */
    public void setDatumPocetka(LocalDate datumPocetka) { this.datumPocetka = datumPocetka; }

    /**
     * Vraća vreme termina kada se program održava.
     *
     * @return vreme termina
     */
    public LocalTime getTermin() { return termin; }

    /**
     * Postavlja vreme termina kada se program održava.
     *
     * @param termin novo vreme termina
     */
    public void setTermin(LocalTime termin) { this.termin = termin; }

    /**
     * Vraća maksimalan broj učesnika na programu.
     *
     * @return maksimalan broj učesnika
     */
    public int getMaxUcesnika() { return maxUcesnika; }

    /**
     * Postavlja maksimalan broj učesnika na programu.
     *
     * @param maxUcesnika novi maksimalan broj
     */
    public void setMaxUcesnika(int maxUcesnika) { this.maxUcesnika = maxUcesnika; }

    /**
     * Vraća dodatne napomene o programu.
     *
     * @return napomena
     */
    public String getNapomena() { return napomena; }

    /**
     * Postavlja dodatne napomene o programu.
     *
     * @param napomena nova napomena
     */
    public void setNapomena(String napomena) { this.napomena = napomena; }

    /**
     * Vraća naziv programa kao tekstualni prikaz.
     *
     * @return naziv programa
     */
    @Override
    public String toString() { return naziv; }

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
    public String vratiNazivTabele() { return "program_aktivnosti"; }

    /**
     * Kreira listu programa aktivnosti na osnovu rezultata SQL upita.
     * <p>
     * Za svaki red u {@link ResultSet}-u kreira se objekat {@link ProgramAktivnosti}
     * sa svim atributima, uključujući konverziju SQL datuma i vremena u Java tipove.
     * </p>
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
     * @return string u formatu "program_aktivnosti.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() { return "program_aktivnosti.id=" + id; }

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