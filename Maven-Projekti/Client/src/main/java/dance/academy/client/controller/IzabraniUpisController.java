package dance.academy.client.controller;
import dance.academy.client.forme.IzabraniUpisForma;
import dance.academy.client.forme.model.ModelTabeleStavkaUpisa;
import java.text.SimpleDateFormat;
import java.util.List;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.StavkaUpisa;

/**
 * Controller klasa za formu detaljanog prikaza upisa na program aktivnosti.
 * <p>
 * Prikazuje sve podatke o selektovanom upisu u read-only režimu,
 * uključujući instruktora, učesnika, datum, status, ukupan iznos
 * i tabelu sa stavkama upisa. Sva polja su onemogućena za unos.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class IzabraniUpisController {

    /** Referenca na formu detaljanog prikaza upisa. */
    private final IzabraniUpisForma iuf;

    /**
     * Konstruktor koji prima formu za detaljan prikaz upisa.
     *
     * @param iuf forma za prikaz detalja upisa
     */
    public IzabraniUpisController(IzabraniUpisForma iuf) {
        this.iuf = iuf;
    }

    /**
     * Priprema formu sa podacima selektovanog upisa i prikazuje je.
     *
     * @param selektovana upis čiji se detalji prikazuju
     */
    public void prikaziFormu(UpisNaProgram selektovana) {
        pripremiFormu(selektovana);
        iuf.setVisible(true);
    }

    /**
     * Popunjava sva polja forme podacima selektovanog upisa i
     * onemogućava editovanje svih komponenti.
     * <p>
     * Metoda uspostavlja konekciju sa serverom kako bi učitala
     * listu instruktora i učesnika za combo boxove, a zatim
     * selektuje odgovarajuće vrednosti. Stavke upisa se prikazuju
     * u tabeli kroz {@link ModelTabeleStavkaUpisa}.
     * Datum se formatira u format {@code dd.MM.yyyy} pre prikaza.
     * </p>
     *
     * @param selektovana upis čiji se podaci prikazuju
     */
    private void pripremiFormu(UpisNaProgram selektovana) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datum = sdf.format(selektovana.getDatum());

        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            iuf.getjComboBoxInstruktor().addItem(i);
        }

        Komunikacija.getInstance().konekcija();
        List<Ucesnik> ucesnici = Komunikacija.getInstance().ucitajUcesnike();
        for (Ucesnik u : ucesnici) {
            iuf.getjComboBoxUcesnik().addItem(u);
        }

        iuf.getjTextFieldDatum().setText(datum);
        iuf.getjComboBoxInstruktor().setSelectedItem(selektovana.getInstruktor());
        iuf.getjComboBoxUcesnik().setSelectedItem(selektovana.getUcesnik());
        iuf.getjComboBoxStatus().setSelectedItem(selektovana.getStatus());
        iuf.getjTextFieldUkupanIznos().setText(selektovana.getUkupanIznos() + "");

        List<StavkaUpisa> sveStavke = selektovana.getStavke();
        ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(sveStavke);
        iuf.getjTable1().setModel(mtsp);

        iuf.getjTextFieldUkupanIznos().setEnabled(false);
        iuf.getjTextFieldDatum().setEnabled(false);
        iuf.getjComboBoxInstruktor().setEnabled(false);
        iuf.getjComboBoxUcesnik().setEnabled(false);
        iuf.getjComboBoxStatus().setEnabled(false);
    }
}