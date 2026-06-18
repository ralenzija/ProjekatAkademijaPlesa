package dance.academy.common.model;
import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 * Interfejs koji definišu sve domenske klase u sistemu plesne akademije.
 * <p>
 * Svaka klasa koja predstavlja entitet iz baze podataka mora implementirati
 * ovaj interfejs. On propisuje metode potrebne za generički rad sa bazom
 * podataka kroz broker patern — bez pisanja SQL-a van domenskih klasa.
 * Zahvaljujući ovom interfejsu, {@code DBBroker} može da radi sa bilo kojim
 * domenskim objektom na uniforman način.
 * </p>
 * <p>
 * Interfejs proširuje {@link Serializable} kako bi se objekti mogli
 * prenositi između klijenta i servera putem socket konekcije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public interface ApstraktniDomenskiObjekat extends Serializable {

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     * <p>
     * Koristi se u generičkim SQL upitima koje gradi {@code DBBroker}.
     * Na primer, klasa {@code Ucesnik} vraća {@code "ucesnik"}.
     * </p>
     *
     * @return naziv tabele kao string
     */
    public String vratiNazivTabele();

    /**
     * Kreira listu domenskih objekata na osnovu rezultata SQL upita.
     * <p>
     * Svaka klasa sama zna kako da parsira {@link ResultSet} i kreira
     * odgovarajuće objekte. Ovo je centralno mesto za mapiranje
     * podataka iz baze u Java objekte.
     * </p>
     *
     * @param rs rezultat SQL upita sa podacima iz baze
     * @return lista domenskih objekata kreiranih iz rezultata upita
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    /**
     * Vraća nazive kolona koje se koriste pri unosu novog objekta u bazu.
     * <p>
     * Koristi se u SQL INSERT upitu koji gradi {@code DBBroker}.
     * Na primer: {@code "ime,prezime,email"}.
     * </p>
     *
     * @return string sa nazivima kolona odvojenim zarezima
     */
    public String vratiKoloneZaUbacivanje();

    /**
     * Vraća vrednosti atributa objekta formatiranih za SQL INSERT upit.
     * <p>
     * Vrednosti moraju biti u istom redosledu kao kolone iz
     * {@link #vratiKoloneZaUbacivanje()}. String vrednosti se navode
     * pod jednostrukim navodnicima.
     * </p>
     *
     * @return string sa vrednostima odvojenim zarezima
     */
    public String vratiVrednostiZaUbacivanje();

    /**
     * Vraća uslov primarnog ključa za SQL WHERE klauzulu.
     * <p>
     * Koristi se u SQL UPDATE i DELETE upitima kako bi se
     * tačno identifikovao red koji se menja ili briše.
     * Na primer: {@code "ucesnik.id=5"}.
     * </p>
     *
     * @return string sa uslovom primarnog ključa
     */
    public String vratiPrimarniKljuc();

    /**
     * Kreira jedan domenski objekat na osnovu trenutnog reda u ResultSet-u.
     * <p>
     * Za razliku od {@link #vratiListu(ResultSet)} koji prolazi kroz sve
     * redove, ova metoda kreira objekat samo iz trenutnog reda.
     * </p>
     *
     * @param rs rezultat SQL upita pozicioniran na odgovarajući red
     * @return domenski objekat kreiran iz trenutnog reda
     * @throws Exception ukoliko dođe do greške pri čitanju iz ResultSet-a
     */
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;

    /**
     * Vraća vrednosti atributa objekta formatiranih za SQL UPDATE upit.
     * <p>
     * Vrednosti moraju biti u formatu {@code "kolona='vrednost'"} i
     * odvojene zarezima, jer se direktno ubacuju u SET deo UPDATE upita.
     * Na primer: {@code "ime='Marko', prezime='Petrovic'"}.
     * </p>
     *
     * @return string sa vrednostima u formatu za UPDATE upit
     */
    public String vratiVrednostiZaIzmenu();
}