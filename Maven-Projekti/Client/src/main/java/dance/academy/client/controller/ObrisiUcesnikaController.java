package dance.academy.client.controller;

import dance.academy.client.forme.ObrisiUcesnikaForma;
import dance.academy.client.forme.model.ModelTabeleUcesnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;

/**
 * Controller klasa za formu brisanja učesnika iz sistema.
 * <p>
 * Prikazuje tabelu svih učesnika i omogućava pretragu po imenu/prezimenu
 * ili email adresi. Pre brisanja, controller proverava da li selektovani
 * učesnik ima postojeće upise na programe — ako ima, brisanje se odbija
 * kako bi se očuvao integritet podataka.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class ObrisiUcesnikaController {

    /** Referenca na formu za brisanje učesnika. */
    private final ObrisiUcesnikaForma ouf;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param ouf forma za brisanje učesnika
     */
    public ObrisiUcesnikaController(ObrisiUcesnikaForma ouf) {
        this.ouf = ouf;
        addActionListeners();
    }

    /**
     * Registruje action listenere na sva dugmad forme.
     * <p>
     * Dugme "Vrati se na GF" zatvara formu i otvara glavnu.
     * Dugme "Obrisi" proverava postojeće upise i briše učesnika ako ih nema.
     * Dugme "Pretrazi" filtrira tabelu po odabranom kriterijumu.
     * Dugme "Ponisti pretragu" resetuje tabelu na sve učesnike.
     * </p>
     */
    private void addActionListeners() {

        ouf.vratiSeNaGFAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
            }
        });

        ouf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ouf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ouf, "Sistem ne moze da nadje ucesnika.");
                    return;
                }
                JOptionPane.showMessageDialog(ouf, "Sistem je nasao ucesnika.");
                ModelTabeleUcesnik mtp = (ModelTabeleUcesnik) ouf.getjTable1().getModel();
                List<Ucesnik> lista = mtp.getLista();
                Ucesnik selektovani = lista.get(red);
                Komunikacija.getInstance().konekcija();
                List<UpisNaProgram> sviUpisi = Komunikacija.getInstance().ucitajUpise();
                for (UpisNaProgram u : sviUpisi) {
                    if (u.getUcesnik().getId() == selektovani.getId()) {
                        JOptionPane.showMessageDialog(ouf, "Za selektovanog ucesnika u sistemu postoji upis.");
                        return;
                    }
                }
                try {
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().obrisiUcesnika(selektovani);
                    pripremiFormu();
                    JOptionPane.showMessageDialog(ouf, "Sistem je obrisao ucesnika.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ouf, "Sistem ne moze da obrise ucesnika.");
                }
            }
        });

        ouf.pretragaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ucesnik u = new Ucesnik();
                if (ouf.getjComboBoxImePrezime().getSelectedIndex() != -1) {
                    String[] imePrezime = String.valueOf(
                            ouf.getjComboBoxImePrezime().getSelectedItem()).split(" ");
                    u.setIme(imePrezime[0]);
                    u.setPrezime(imePrezime[1]);
                }
                if (ouf.getjComboBoxEmail().getSelectedIndex() != -1) {
                    u.setEmail(String.valueOf(ouf.getjComboBoxEmail().getSelectedItem()));
                }
                if (ouf.getjComboBoxImePrezime().getSelectedIndex() == -1 &&
                        ouf.getjComboBoxEmail().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ouf, "Niste odabrali kriterijum");
                    return;
                }
                Komunikacija.getInstance().konekcija();
                List<Ucesnik> filtrirani = Komunikacija.getInstance().pretraziUcesnike(u);
                if (filtrirani != null && !filtrirani.isEmpty()) {
                    JOptionPane.showMessageDialog(ouf, "Sistem je našao ucesnike na osnovu kriterijumima.");
                } else {
                    JOptionPane.showMessageDialog(ouf, "Sistem ne može da nadje ucesnike na osnovu kriterijumima.");
                }
                ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(filtrirani);
                ouf.getjTable1().setModel(mtp);
            }
        });

        ouf.ponistiPretraguAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouf.getjComboBoxEmail().setSelectedIndex(-1);
                ouf.getjComboBoxImePrezime().setSelectedIndex(-1);
                pripremiFormu();
            }
        });
    }

    /**
     * Priprema formu i prikazuje je korisniku.
     */
    public void prikaziFormu() {
        pripremiFormu();
        ouf.setVisible(true);
    }

    /**
     * Učitava sve učesnike sa servera, puni tabelu i combo boxove.
     * <p>
     * Combo box za ime i prezime se puni sortiranom listom po imenu,
     * a combo box za email se puni sortiranom listom po email adresi.
     * Oba combo boxa se resetuju na prazan izbor nakon punjenja.
     * </p>
     */
    private void pripremiFormu() {
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> lista = Komunikacija.getInstance().ucitajUcesnike();
        ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(lista);
        ouf.getjTable1().setModel(mtp);

        List<Ucesnik> sortIme = new ArrayList<>(lista);
        sortIme.sort(Comparator.comparing(Ucesnik::getIme, String.CASE_INSENSITIVE_ORDER));

        List<Ucesnik> sortEmail = new ArrayList<>(lista);
        sortEmail.sort(Comparator.comparing(Ucesnik::getEmail, String.CASE_INSENSITIVE_ORDER));

        for (Ucesnik u : sortIme) {
            ouf.getjComboBoxImePrezime().addItem(u.getIme() + " " + u.getPrezime());
        }
        for (Ucesnik u : sortEmail) {
            ouf.getjComboBoxEmail().addItem(u.getEmail());
        }

        ouf.getjComboBoxImePrezime().setSelectedIndex(-1);
        ouf.getjComboBoxEmail().setSelectedIndex(-1);
    }
}