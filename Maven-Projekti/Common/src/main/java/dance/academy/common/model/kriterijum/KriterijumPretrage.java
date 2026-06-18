package dance.academy.common.model.kriterijum;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import dance.academy.common.model.ApstraktniDomenskiObjekat;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;

/**
 * Predstavlja kriterijum za pretragu upisa na programe aktivnosti.
 * <p>
 * Koristi se kao objekat koji nosi parametre pretrage od klijenta ka serveru.
 * Pretraga se može vršiti po instruktoru, učesniku i datumu — sva tri parametra
 * su opciona, a server filtrira upise prema onim parametrima koji nisu null.
 * </p>
 * <p>
 * Ova klasa implementira {@link ApstraktniDomenskiObjekat} isključivo radi
 * prenosa putem socket konekcije (serijalizacija), a metode za rad sa bazom
 * nisu podržane jer klasa ne odgovara tabeli u bazi podataka.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class KriterijumPretrage implements ApstraktniDomenskiObjekat {

    /** Instruktor po kome se filtriraju upisi. Ako je null, ne filtrira se po instruktoru. */
    private Instruktor i;

    /** Učesnik po kome se filtriraju upisi. Ako je null, ne filtrira se po učesniku. */
    private Ucesnik p;

    /** Datum po kome se filtriraju upisi. Ako je null, ne filtrira se po datumu. */
    private Date d;

    /**
     * Konstruktor koji inicijalizuje kriterijum sa svim parametrima pretrage.
     *
     * @param i instruktor kao kriterijum pretrage, može biti null
     * @param p učesnik kao kriterijum pretrage, može biti null
     * @param d datum kao kriterijum pretrage, može biti null
     */
    public KriterijumPretrage(Instruktor i, Ucesnik p, Date d) {
        this.i = i;
        this.p = p;
        this.d = d;
    }

    /**
     * Podrazumevani konstruktor bez argumenata.
     * Svi parametri pretrage su inicijalno null.
     */
    public KriterijumPretrage() {
    }

    /**
     * Vraća datum kao kriterijum pretrage.
     *
     * @return datum pretrage, ili null ako datum nije postavljen
     */
    public Date getD() { return d; }

    /**
     * Vraća instruktora kao kriterijum pretrage.
     *
     * @return instruktor pretrage, ili null ako instruktor nije postavljen
     */
    public Instruktor getI() { return i; }

    /**
     * Vraća učesnika kao kriterijum pretrage.
     *
     * @return učesnik pretrage, ili null ako učesnik nije postavljen
     */
    public Ucesnik getP() { return p; }

    /**
     * Postavlja datum kao kriterijum pretrage.
     *
     * @param d novi datum pretrage
     */
    public void setD(Date d) { this.d = d; }

    /**
     * Postavlja instruktora kao kriterijum pretrage.
     *
     * @param i novi instruktor pretrage
     */
    public void setI(Instruktor i) { this.i = i; }

    /**
     * Postavlja učesnika kao kriterijum pretrage.
     *
     * @param p novi učesnik pretrage
     */
    public void setP(Ucesnik p) { this.p = p; }

    /**
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiNazivTabele() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
     *
     * @param rs rezultat SQL upita
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
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
     * Nije podržano — klasa ne odgovara tabeli u bazi podataka.
     *
     * @return nikad se ne vraća
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}