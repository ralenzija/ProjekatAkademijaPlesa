package dance.academy.client.controller;
import dance.academy.client.forme.UbaciSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Sertifikat;

/**
 * Controller klasa za formu dodavanja sertifikata instruktoru.
 * <p>
 * Omogućava dodavanje novog sertifikata koji potvrđuje da instruktor
 * poseduje kvalifikaciju za određeni stil plesa. Instruktor bira stil
 * plesa iz combo boxa i unosi naziv organizacije koja je izdala sertifikat.
 * Nakon uspešnog dodavanja, forma se resetuje za novi unos.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class UbaciSertifikatController {

    /** Referenca na formu za dodavanje sertifikata. */
    private final UbaciSertifikatForma uvpf;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param uvpf forma za dodavanje sertifikata
     */
    public UbaciSertifikatController(UbaciSertifikatForma uvpf) {
        this.uvpf = uvpf;
        addActionListeners();
    }

    /**
     * Registruje action listenere na dugmad forme.
     * <p>
     * Dugme "Dodaj sertifikat" kreira novi {@link Sertifikat} sa odabranim
     * stilom plesa i unetom organizacijom i šalje ga na server. Prikazuje
     * poruku "Sistem je zapamtio sertifikat." pri uspehu, ili
     * "Sistem ne moze da zapamti sertifikat." pri grešci.
     * Nakon dodavanja, combo box i polje organizacije se resetuju.
     * Dugme "Vrati se na GF" zatvara formu i otvara glavnu formu.
     * </p>
     */
    private void addActionListeners() {

        uvpf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlesniStil ples = (PlesniStil) uvpf.getjComboBoxPles().getSelectedItem();
                String organizacija = uvpf.getjTextFieldOrganizacija().getText().trim();
                Sertifikat s = new Sertifikat(-1, ples, organizacija);
                try {
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().dodajSertifikat(s);
                    JOptionPane.showMessageDialog(uvpf, "Sistem je zapamtio sertifikat.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(uvpf, "Sistem ne moze da zapamti sertifikat.");
                }
                uvpf.getjComboBoxPles().setSelectedIndex(-1);
                uvpf.getjTextFieldOrganizacija().setText("");
            }
        });

        uvpf.vratiNaGFAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uvpf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
            }
        });
    }

    /**
     * Resetuje combo box na prazan izbor i prikazuje formu korisniku.
     */
    public void prikaziFormu() {
        uvpf.getjComboBoxPles().setSelectedIndex(-1);
        uvpf.setVisible(true);
    }
}