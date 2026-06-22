package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja jednu stavku unutar upisa na program aktivnosti.
 * <p>
 * Svaka stavka upisa vezana je za konkretan {@link ProgramAktivnosti} i
 * sadrži iznos koji učesnik plaća za taj program. Stavke zajedno čine
 * sadržaj jednog {@link UpisNaProgram}, a njihovim iznosima se formira
 * ukupan iznos upisa.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class StavkaUpisa implements ApstraktniDomenskiObjekat {

    /** Redni broj stavke unutar upisa, ujedno i primarni ključ u bazi. */
    private int rb;

    /** Iznos koji učesnik plaća za ovaj program aktivnosti. */
    private double iznos;

    /** Upis na program kome ova stavka pripada. */
    private UpisNaProgram upis;

    /** Program aktivnosti na koji se ova stavka odnosi. */
    private ProgramAktivnosti program;

    /**
     * Podrazumevani konstruktor bez argumenata.
     */
    public StavkaUpisa() {
    }

    /**
     * Konstruktor koji inicijalizuje stavku upisa sa svim potrebnim podacima.
     * Poziva odgovarajuće setter metode radi validacije vrednosti.
     *
     * @param rb      redni broj stavke
     * @param iznos   iznos za plaćanje za ovaj program
     * @param upis    upis na program kome stavka pripada
     * @param program program aktivnosti na koji se stavka odnosi
     */
    public StavkaUpisa(int rb, double iznos, UpisNaProgram upis, ProgramAktivnosti program) {
        setRb(rb);
        setIznos(iznos);
        this.upis = upis;
        setProgram(program);
    }

    /**
     * Vraća redni broj stavke upisa.
     *
     * @return redni broj stavke
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke upisa.
     * <p>
     * Redni broj mora biti pozitivan broj.
     * </p>
     *
     * @param rb novi redni broj
     * @throws IllegalArgumentException ukoliko je redni broj manji ili jednak nuli
     */
    public void setRb(int rb) {
        if (rb <= 0)
            throw new IllegalArgumentException("Redni broj mora biti pozitivan");
        this.rb = rb;
    }

    /**
     * Vraća iznos koji učesnik plaća za ovaj program.
     *
     * @return iznos stavke
     */
    public double getIznos() {
        return iznos;
    }

    /**
     * Postavlja iznos koji učesnik plaća za ovaj program.
     * <p>
     * Iznos ne sme biti negativan.
     * </p>
     *
     * @param iznos novi iznos
     * @throws IllegalArgumentException ukoliko je iznos negativan
     */
    public void setIznos(double iznos) {
        if (iznos < 0)
            throw new IllegalArgumentException("Iznos ne sme biti negativan");
        this.iznos = iznos;
    }

    /**
     * Vraća upis na program kome ova stavka pripada.
     *
     * @return upis na program
     */
    public UpisNaProgram getUpis() {
        return upis;
    }

    /**
     * Postavlja upis na program kome ova stavka pripada.
     * <p>
     * Upis može biti null — stavka se može kreirati pre nego što je
     * pridružena konkretnom upisu.
     * </p>
     *
     * @param upis novi upis
     */
    public void setUpis(UpisNaProgram upis) {
        this.upis = upis;
    }

    /**
     * Vraća program aktivnosti na koji se ova stavka odnosi.
     *
     * @return program aktivnosti
     */
    public ProgramAktivnosti getProgram() {
        return program;
    }

    /**
     * Postavlja program aktivnosti na koji se ova stavka odnosi.
     * <p>
     * Program aktivnosti ne sme biti null.
     * </p>
     *
     * @param program novi program aktivnosti
     * @throws NullPointerException ukoliko je program null
     */
    public void setProgram(ProgramAktivnosti program) {
        if (program == null)
            throw new NullPointerException("Program aktivnosti ne sme biti null");
        this.program = program;
    }

    /**
     * Vraća tekstualni prikaz stavke upisa.
     *
     * @return string reprezentacija stavke
     */
    @Override
    public String toString() {
        return "StavkaPrijave{" + "prijava=" + upis + ", program=" + program + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Poredi dve stavke upisa po programu aktivnosti.
     * Dve stavke su jednake ako se odnose na isti program.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su stavke jednake, {@code false} inače
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final StavkaUpisa other = (StavkaUpisa) obj;
        return Objects.equals(this.program, other.program);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "stavka_upisa"
     */
    @Override
    public String vratiNazivTabele() {
        return "stavka_upisa";
    }

    /**
     * Kreira listu stavki upisa na osnovu rezultata SQL upita.
     *
     * @param rs rezultat SQL upita koji sadrži podatke o stavkama
     * @return lista stavki upisa
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idS = rs.getInt("stavka_upisa.id");
            double iznosS = rs.getDouble("stavka_upisa.iznos");
            int idUpis = rs.getInt("upis_na_program.id");
            int idProgram = rs.getInt("program_aktivnosti.id");
            UpisNaProgram pnk = new UpisNaProgram();
            pnk.setId(idUpis);
            ProgramAktivnosti pa = new ProgramAktivnosti();
            pa.setId(idProgram);
            StavkaUpisa su = new StavkaUpisa(idS, iznosS, pnk, pa);
            lista.add(su);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje se koriste pri unosu nove stavke u bazu.
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "upis,iznos,program";
    }

    /**
     * Vraća vrednosti atributa stavke formatiranih za SQL INSERT upit.
     *
     * @return string sa vrednostima odvojenim zarezima
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return upis.getId() + ", " + iznos + ", " + program.getId();
    }

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     *
     * @return string u formatu "stavka_upisa.id=X AND stavka_upisa.upis=Y"
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_upisa.id=" + rb + " AND stavka_upisa.upis=" + upis.getId();
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
     * Nije podržano za ovu klasu — stavke upisa se ne menjaju, samo brišu i ponovo dodaju.
     *
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}