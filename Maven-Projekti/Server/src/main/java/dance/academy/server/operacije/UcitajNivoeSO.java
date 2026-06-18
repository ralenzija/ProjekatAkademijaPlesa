package dance.academy.server.operacije;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.NivoVestine;

/**
 * Sistemska operacija za pronalaženje nivoa veštine po stilu i nivou plesa.
 * <p>
 * Pretražuje bazu podataka i vraća tačno jedan {@link NivoVestine} koji
 * odgovara prosleđenoj kombinaciji nivoa i stila plesa. Koristi se pri
 * kreiranju ili izmeni učesnika kada sistem treba da pronađe odgovarajući
 * nivo veštine iz baze pre nego što ga dodeli učesniku.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UcitajNivoeSO extends ApstraktnaGenerickaOperacija {

    /** Pronađeni nivo veštine, dostupan nakon izvršavanja operacije. */
    private NivoVestine nivo;

    /**
     * Nema posebnih preduslova za ovu operaciju.
     *
     * @param param prosleđeni objekat
     * @throws Exception nikad ne baca izuzetak u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Pretražuje bazu i vraća nivo veštine koji odgovara zadatom stilu i nivou plesa.
     * <p>
     * Gradi WHERE uslov na osnovu nivoa i vrste plesa iz prosleđenog objekta,
     * a zatim uzima prvi (i jedini očekivani) rezultat iz liste.
     * </p>
     *
     * @param param  objekat tipa {@link NivoVestine} sa postavljenim nivoom i vrstom plesa
     * @param kljuc  nije korišćen u ovoj operaciji
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        NivoVestine nv = (NivoVestine) param;
        String n = String.valueOf(nv.getNivo());
        String v = String.valueOf(nv.getVrsta());
        String uslov = " WHERE nivo_vestine.nivo='" + n + "' AND nivo_vestine.vrsta='" + v + "'";
        List<NivoVestine> nivoi = new ArrayList<>();
        try {
            nivoi = broker.getAll((NivoVestine) param, uslov);
            nivo = nivoi.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća pronađeni nivo veštine koji odgovara zadatim kriterijumima.
     *
     * @return nivo veštine iz baze podataka
     */
    public NivoVestine getNivo() {
        return nivo;
    }
}