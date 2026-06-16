package dance.academy.client.controller;

import dance.academy.client.forme.PretraziUcesnikaForma;
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

/**
 * Controller klasa za formu pretrage učesnika.
 * <p>
 * Koristi se u dva scenarija — pretraga i izmena. U scenariju pretrage
 * dugme "Izmeni" je sakriveno, a u scenariju izmene je vidljivo i
 * otvara formu za izmenu selektovanog učesnika.
 * </p>
 * <p>
 * Pretraga se vrši po imenu i prezimenu ili email adresi. Combo boxevi
 * se pune sortiranim listama učesnika. Dugme "Detaljan prikaz" otvara
 * formu sa svim podacima o selektovanom učesniku u read-only režimu.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class PretraziUcesnikaController {

    /** Referenca na formu za pretragu učesnika. */
    private final PretraziUcesnikaForma puf;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param puf forma za pretragu učesnika
     */
    public PretraziUcesnikaController(PretraziUcesnikaForma puf) {
        this.puf = puf;
        addActionListeners();
    }

    /**
     * Registruje action listenere na sva dugmad forme.
     * <p>
     * Dugme "Pretrazi" filtrira tabelu po odabranom kriterijumu i
     * prikazuje poruku "Sistem je našao učesnike na osnovu kriterijuma."
     * ili "Sistem ne može da nadje učesnike na osnovu kriterijumima."
     * Dugme "Vrati se na GF" zatvara formu i otvara glavnu.
     * Dugme "Izmeni" otvara formu za izmenu selektovanog učesnika i
     * prikazuje poruku "Sistem je nasao učesnika."
     * Dugme "Ponisti pretragu" resetuje tabelu i combo boxeve na početno stanje.
     * Dugme "Detaljan prikaz" otvara formu za prikaz detalja učesnika i
     * prikazuje poruku "Sistem je nasao učesnika."
     * </p>
     */
    private void addActionListeners() {

        puf.pretragaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ucesnik u = new Ucesnik();
                if (puf.getjComboBoxImePrezime().getSelectedIndex() != -1) {
                    String[] imePrezime = String.valueOf(
                            puf.getjComboBoxImePrezime().getSelectedItem()).split(" ");
                    u.setIme(imePrezime[0]);
                    u.setPrezime(imePrezime[1]);
                }
                if (puf.getjComboBoxEmail().getSelectedIndex() != -1) {
                    u.setEmail(String.valueOf(puf.getjComboBoxEmail().getSelectedItem()));
                }
                if (puf.getjComboBoxImePrezime().getSelectedIndex() == -1 &&
                        puf.getjComboBoxEmail().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(puf,
                            "Niste selektovali kriterijum pretrazivanja (Ime i prezime / Email)");
                    return;
                }
                Komunikacija.getInstance().konekcija();
                List<Ucesnik> filtrirani = Komunikacija.getInstance().pretraziUcesnike(u);
                if (filtrirani != null && !filtrirani.isEmpty()) {
                    JOptionPane.showMessageDialog(puf, "Sistem je našao učesnike na osnovu kriterijuma.");
                } else {
                    JOptionPane.showMessageDialog(puf, "Sistem ne može da nadje učesnike na osnovu kriterijumima.");
                }
                ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(filtrirani);
                puf.getjTable1().setModel(mtp);
            }
        });

        puf.vratiSeNaGFAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
            }
        });

        puf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = puf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(puf, "Sistem ne moze da nadje učesnika.");
                    return;
                }
                JOptionPane.showMessageDialog(puf, "Sistem je nasao učesnika.");
                ModelTabeleUcesnik mtp = (ModelTabeleUcesnik) puf.getjTable1().getModel();
                List<Ucesnik> lista = mtp.getLista();
                Ucesnik selektovani = lista.get(red);
                Koordinator.getInstance().otvoriPromeniUcesnikaFormu(selektovani);
            }
        });

        puf.ponistiPretraguAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremi();
            }
        });

        puf.detaljanPrikazAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = puf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(puf, "Sistem ne moze da nadje učesnika.");
                    return;
                }
                JOptionPane.showMessageDialog(puf, "Sistem je nasao učesnika.");
                ModelTabeleUcesnik mtp = (ModelTabeleUcesnik) puf.getjTable1().getModel();
                List<Ucesnik> lista = mtp.getLista();
                Ucesnik selektovani = lista.get(red);
                Koordinator.getInstance().otvoriIzabraniUcesnikFormu(selektovani);
            }
        });
    }

    /**
     * Prikazuje formu korisniku.
     */
    public void prikaziFormu() {
        puf.setVisible(true);
    }

    /**
     * Priprema formu za scenario pretrage — dugme "Izmeni" je sakriveno.
     * Koristi se kada instruktor samo pregledava učesnike bez namere izmene.
     */
    public void pripremiFormuZaPretragu() {
        pripremi();
        puf.getjButtonIzmeni().setVisible(false);
    }

    /**
     * Priprema formu za scenario izmene — dugme "Izmeni" je vidljivo.
     * Koristi se kada instruktor želi da izabere učesnika za izmenu podataka.
     */
    public void pripremiFormuZaIzmenu() {
        pripremi();
        puf.getjButtonIzmeni().setVisible(true);
    }

    /**
     * Učitava sve učesnike sa servera, puni tabelu i combo boxove.
     * <p>
     * Combo box za ime i prezime se puni sortiranom listom po imenu,
     * a combo box za email se puni sortiranom listom po email adresi.
     * Oba combo boxa se resetuju na prazan izbor nakon punjenja.
     * </p>
     */
    private void pripremi() {
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> lista = Komunikacija.getInstance().ucitajUcesnike();
        ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(lista);
        puf.getjTable1().setModel(mtp);

        List<Ucesnik> sortIme = new ArrayList<>(lista);
        sortIme.sort(Comparator.comparing(Ucesnik::getIme, String.CASE_INSENSITIVE_ORDER));

        List<Ucesnik> sortEmail = new ArrayList<>(lista);
        sortEmail.sort(Comparator.comparing(Ucesnik::getEmail, String.CASE_INSENSITIVE_ORDER));

        for (Ucesnik u : sortIme) {
            puf.getjComboBoxImePrezime().addItem(u.getIme() + " " + u.getPrezime());
        }
        for (Ucesnik u : sortEmail) {
            puf.getjComboBoxEmail().addItem(u.getEmail());
        }

        puf.getjComboBoxImePrezime().setSelectedIndex(-1);
        puf.getjComboBoxEmail().setSelectedIndex(-1);
    }

    /**
     * Osvežava tabelu učesnika novim podacima sa servera.
     * Koristi se nakon izmene podataka učesnika kako bi se
     * prikazalo ažurirano stanje.
     */
    public void osveziTabelu() {
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> lista = Komunikacija.getInstance().ucitajUcesnike();
        ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(lista);
        puf.getjTable1().setModel(mtp);
    }
}