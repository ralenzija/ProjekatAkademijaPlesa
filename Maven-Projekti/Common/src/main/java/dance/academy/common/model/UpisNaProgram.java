package dance.academy.common.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Predstavlja upis učesnika na program aktivnosti plesne akademije.
 * <p>
 * Upis povezuje određenog učesnika sa instruktorom i sadrži listu stavki
 * koje opisuju konkretne programe na koje je učesnik upisan. Svaki upis
 * ima datum, status (npr. aktivan, otkazan) i ukupan iznos koji učesnik
 * treba da plati.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UpisNaProgram implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator upisa u bazi podataka. */
    private int id;

    /** Datum kada je upis kreiran. */
    private Date datum;

    /** Trenutni status upisa (npr. AKTIVAN, OTKAZAN, ZAVRSЕН). */
    private StatusUpisa status;

    /** Ukupan iznos koji učesnik treba da plati za sve stavke upisa. */
    private double ukupanIznos;

    /** Instruktor koji vodi programe na koje je učesnik upisan. */
    private Instruktor instruktor;

    /** Učesnik koji je kreirao upis. */
    private Ucesnik ucesnik;

    /** Lista stavki upisa — svaka stavka predstavlja jedan program aktivnosti. */
    private List<StavkaUpisa> stavke = new ArrayList<>();

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public UpisNaProgram() {
    }

    /**
     * Konstruktor koji inicijalizuje upis sa svim podacima uključujući stavke.
     *
     * @param id           jedinstveni identifikator upisa
     * @param datum        datum kreiranja upisa
     * @param status       status upisa
     * @param ukupanIznos  ukupan iznos za plaćanje
     * @param instruktor   instruktor koji vodi programe
     * @param ucesnik      učesnik koji se upisuje
     * @param stavke       lista stavki upisa
     */
    public UpisNaProgram(int id, Date datum, StatusUpisa status, double ukupanIznos,
                         Instruktor instruktor, Ucesnik ucesnik, List<StavkaUpisa> stavke) {
        this.id = id;
        this.datum = datum;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.ucesnik = ucesnik;
        this.stavke = stavke;
    }

    /**
     * Konstruktor koji inicijalizuje upis bez stavki.
     * Stavke se mogu naknadno dodati metodom {@link #setStavke(List)}.
     *
     * @param id           jedinstveni identifikator upisa
     * @param datum        datum kreiranja upisa
     * @param status       status upisa
     * @param ukupanIznos  ukupan iznos za plaćanje
     * @param instruktor   instruktor koji vodi programe
     * @param ucesnik      učesnik koji se upisuje
     */
    public UpisNaProgram(int id, Date datum, StatusUpisa status, double ukupanIznos,
                         Instruktor instruktor, Ucesnik ucesnik) {
        this.id = id;
        this.datum = datum;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.ucesnik = ucesnik;
    }

    /**
     * Vraća listu stavki upisa.
     *
     * @return lista stavki upisa
     */
    public List<StavkaUpisa> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki upisa.
     *
     * @param stavke nova lista stavki
     */
    public void setStavke(List<StavkaUpisa> stavke) {
        this.stavke = stavke;
    }

    /**
     * Vraća jedinstveni identifikator upisa.
     *
     * @return id upisa
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator upisa.
     *
     * @param id novi identifikator
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća datum kreiranja upisa.
     *
     * @return datum upisa
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum kreiranja upisa.
     *
     * @param datum novi datum
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     * Vraća trenutni status upisa.
     *
     * @return status upisa
     */
    public StatusUpisa getStatus() {
        return status;
    }

    /**
     * Postavlja status upisa.
     *
     * @param status novi status
     */
    public void setStatus(StatusUpisa status) {
        this.status = status;
    }

    /**
     * Vraća ukupan iznos koji učesnik treba da plati.
     *
     * @return ukupan iznos
     */
    public double getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Postavlja ukupan iznos koji učesnik treba da plati.
     *
     * @param ukupanIznos novi ukupan iznos
     */
    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    /**
     * Vraća instruktora koji vodi programe u ovom upisu.
     *
     * @return instruktor
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Postavlja instruktora koji vodi programe u ovom upisu.
     *
     * @param instruktor novi instruktor
     */
    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    /**
     * Vraća učesnika koji je kreirao upis.
     *
     * @return učesnik
     */
    public Ucesnik getUcesnik() {
        return ucesnik;
    }

    /**
     * Postavlja učesnika koji je kreirao upis.
     *
     * @param ucesnik novi učesnik
     */
    public void setUcesnik(Ucesnik ucesnik) {
        this.ucesnik = ucesnik;
    }

    /**
     * Vraća tekstualni prikaz upisa sa svim ključnim informacijama.
     *
     * @return string reprezentacija upisa
     */
    @Override
    public String toString() {
        return "UpisNaKurs{" + "datum=" + datum + ", status=" + status +
                ", ukupanIznos=" + ukupanIznos + ", instruktor=" + instruktor +
                ", ucesnik=" + ucesnik + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Poredi dva upisa po njihovom jedinstvenom identifikatoru.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su upisi isti (isti id), {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final UpisNaProgram other = (UpisNaProgram) obj;
        return this.id == other.id;
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "upis_na_program"
     */
    @Override
    public String vratiNazivTabele() {
        return "upis_na_program";
    }

    /**
     * Kreira listu upisa na osnovu rezultata SQL upita.
     * <p>
     * Za svaki red u {@link ResultSet}-u kreira se objekat {@link UpisNaProgram}
     * zajedno sa povezanim instruktorom, učesnikom i stavkama upisa.
     * Stavke se učitavaju dodatnim upitom ka tabeli {@code stavka_upisa}.
     * </p>
     *
     * @param rs rezultat SQL upita koji sadrži podatke o upisima
     * @return lista upisa iz baze podataka
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idIns = rs.getInt("instruktor.id");
            String imeIns = rs.getString("instruktor.ime");
            String prezimeIns = rs.getString("instruktor.prezime");
            String emailIns = rs.getString("instruktor.email");
            String sifraIns = rs.getString("instruktor.sifra");
            Instruktor i = new Instruktor(idIns, imeIns, prezimeIns, emailIns, sifraIns);

            int idU = rs.getInt("ucesnik.id");
            String imeU = rs.getString("ucesnik.ime");
            String prezimeU = rs.getString("ucesnik.prezime");
            String emailU = rs.getString("ucesnik.email");
            String telefonU = rs.getString("ucesnik.telefon");
            java.sql.Date dRodj = rs.getDate("ucesnik.datum_rodjenja");
            java.time.LocalDate datumRodjU = (dRodj != null) ? dRodj.toLocalDate() : null;
            String napomenaU = rs.getString("ucesnik.napomena");
            Ucesnik p = new Ucesnik(idU, imeU, prezimeU, emailU, null, telefonU, datumRodjU, napomenaU);

            int idUp = rs.getInt("upis_na_program.id");
            java.sql.Date datumSQL = rs.getDate("upis_na_program.datum");
            String statusStr = rs.getString("upis_na_program.status");
            double ukupanIznosUp = rs.getDouble("upis_na_program.ukupanIznos");
            StatusUpisa sp = StatusUpisa.valueOf(statusStr);
            java.util.Date datumUp = (datumSQL != null) ? new java.util.Date(datumSQL.getTime()) : null;
            UpisNaProgram prijava = new UpisNaProgram(idUp, datumUp, sp, ukupanIznosUp, i, p, null);

            String upitStavke = "SELECT * FROM stavka_upisa " +
                    "JOIN program_aktivnosti ON stavka_upisa.program = program_aktivnosti.id " +
                    "WHERE upis=" + idUp;
            List<StavkaUpisa> sveStavke = new ArrayList<>();
            try (Statement st = rs.getStatement().getConnection().createStatement();
                 ResultSet rsStavke = st.executeQuery(upitStavke)) {
                while (rsStavke.next()) {
                    int idStavka = rsStavke.getInt("stavka_upisa.id");
                    double iznos = rsStavke.getDouble("stavka_upisa.iznos");
                    int pid = rsStavke.getInt("program_aktivnosti.id");
                    String pnaziv = rsStavke.getString("program_aktivnosti.naziv");
                    int ptrajanje = rsStavke.getInt("program_aktivnosti.trajanje");
                    double pcena = rsStavke.getDouble("program_aktivnosti.cena");
                    String pvrsta = rsStavke.getString("program_aktivnosti.vrsta");
                    boolean paktivan = rsStavke.getBoolean("program_aktivnosti.aktivan");
                    String psala = rsStavke.getString("program_aktivnosti.sala");
                    java.sql.Date d = rsStavke.getDate("program_aktivnosti.datum_pocetka");
                    java.time.LocalDate pDatumPocetka = (d != null) ? d.toLocalDate() : null;
                    java.sql.Time t = rsStavke.getTime("program_aktivnosti.termin");
                    java.time.LocalTime pTermin = (t != null) ? t.toLocalTime() : null;
                    int pMax = rsStavke.getInt("program_aktivnosti.max_ucesnika");
                    String pNapomena = rsStavke.getString("program_aktivnosti.napomena");
                    ProgramAktivnosti kurs = new ProgramAktivnosti(
                            pid, pnaziv, ptrajanje, pcena, PlesniStil.valueOf(pvrsta),
                            paktivan, psala, pDatumPocetka, pTermin, pMax, pNapomena);
                    StavkaUpisa stavka = new StavkaUpisa(idStavka, iznos, prijava, kurs);
                    sveStavke.add(stavka);
                }
            }
            prijava.setStavke(sveStavke);
            lista.add(prijava);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog upisa u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,status,ukupanIznos,instruktor,ucesnik";
    }

    /**
     * Vraća vrednosti atributa upisa formatiranih za SQL INSERT upit.
     *
     * @return string sa vrednostima odvojenim zarezima
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datum + "','" + status + "'," + ukupanIznos + "," +
                instruktor.getId() + "," + ucesnik.getId();
    }

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     *
     * @return string u formatu "upis_na_program.id=X"
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "upis_na_program.id=" + id;
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
     * Vraća vrednosti atributa upisa formatiranih za SQL UPDATE upit.
     *
     * @return string sa vrednostima u formatu "kolona=vrednost"
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + datum + "', status='" + status + "', ukupanIznos=" + ukupanIznos +
                ", instruktor=" + instruktor.getId() + ", ucesnik=" + ucesnik.getId();
    }
}