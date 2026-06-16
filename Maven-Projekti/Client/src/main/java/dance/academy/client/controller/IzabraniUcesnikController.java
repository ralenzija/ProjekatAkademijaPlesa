package dance.academy.client.controller;
import dance.academy.client.forme.IzabraniUcesnikForma;
import java.time.format.DateTimeFormatter;
import dance.academy.common.model.Ucesnik;

/**
 * Controller klasa za formu detaljanog prikaza učesnika.
 * <p>
 * Prikazuje sve podatke o selektovanom učesniku u read-only režimu —
 * sva polja su onemogućena za unos kako bi se sprečila nenamerna izmena.
 * Koristi se kada instruktor želi da pregleda detalje o učesniku
 * bez mogućnosti izmene.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class IzabraniUcesnikController {

    /** Referenca na formu detaljanog prikaza učesnika. */
    private final IzabraniUcesnikForma iuf;

    /**
     * Konstruktor koji prima formu za detaljan prikaz učesnika.
     *
     * @param iuf forma za prikaz detalja učesnika
     */
    public IzabraniUcesnikController(IzabraniUcesnikForma iuf) {
        this.iuf = iuf;
    }

    /**
     * Priprema formu sa podacima učesnika i prikazuje je.
     *
     * @param selektovani učesnik čiji se detalji prikazuju
     */
    public void prikaziFormu(Ucesnik selektovani) {
        pripremiFormu(selektovani);
        iuf.setVisible(true);
    }

    /**
     * Popunjava sva polja forme podacima selektovanog učesnika i
     * onemogućava editovanje svih komponenti.
     * <p>
     * Datum rođenja se formatira u format {@code yyyy.MM.dd} pre prikaza.
     * Nivo i stil plesa se postavljaju u odgovarajuće combo boxove.
     * </p>
     *
     * @param selektovani učesnik čiji se podaci prikazuju
     */
    private void pripremiFormu(Ucesnik selektovani) {
        iuf.getjTextFieldIme().setText(selektovani.getIme());
        iuf.getjTextFieldPrezime().setText(selektovani.getPrezime());
        iuf.getjTextFieldEmail().setText(selektovani.getEmail());
        iuf.getjTextFieldTelefon().setText(selektovani.getTelefon());
        iuf.getjTextFieldNapomena().setText(selektovani.getNapomena());
        iuf.getjTextFieldDatum().setText(
                selektovani.getDatumRodjenja().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        iuf.getjComboBoxNivo().setSelectedItem(selektovani.getNivo().getNivo());
        iuf.getjComboBoxPles().setSelectedItem(selektovani.getNivo().getVrsta());

        iuf.getjTextFieldIme().setEnabled(false);
        iuf.getjTextFieldPrezime().setEnabled(false);
        iuf.getjTextFieldEmail().setEnabled(false);
        iuf.getjTextFieldTelefon().setEditable(false);
        iuf.getjTextFieldNapomena().setEditable(false);
        iuf.getjTextFieldDatum().setEditable(false);
        iuf.getjComboBoxNivo().setEnabled(false);
        iuf.getjComboBoxPles().setEnabled(false);
    }
}