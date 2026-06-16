package dance.academy.client.controller;

import dance.academy.client.forme.PromeniUcesnikaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.PlesniNivo;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.Ucesnik;

/**
 * Controller klasa za formu izmene podataka učesnika.
 * <p>
 * Forma se otvara sa već popunjenim podacima selektovanog učesnika.
 * Polje ID-ja je onemogućeno jer se ne može menjati. Validacija podataka
 * je ista kao pri kreiranju — ime i prezime moraju počinjati velikim slovom,
 * email mora biti Gmail adresa malim slovima, telefon mora sadržati samo
 * cifre, a datum rođenja ne sme biti u budućnosti.
 * </p>
 * <p>
 * Nakon uspešne izmene, tabela učesnika na formi pretrage se automatski
 * osvežava kroz {@link Koordinator}.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class PromeniUcesnikaController {

    /** Referenca na formu za izmenu učesnika. */
    private final PromeniUcesnikaForma puf;

    /** Datum rođenja učesnika parsiran iz tekstualnog polja. */
    LocalDate datumRodjenja;

    /** ID učesnika koji se menja, čuva se radi kreiranja izmenjenog objekta. */
    private int id;

    /** Referenca na originalnog učesnika pre izmene. */
    private Ucesnik u;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param puf forma za izmenu učesnika
     */
    public PromeniUcesnikaController(PromeniUcesnikaForma puf) {
        this.puf = puf;
        addActionListeners();
    }

    /**
     * Registruje action listener na dugme za čuvanje izmena.
     * <p>
     * Dugme "Sacuvaj izmene" pokreće validaciju unetih podataka i šalje
     * izmenjenog učesnika na server. Validacije su:
     * ime i prezime moraju počinjati velikim slovom i imati max 50 karaktera,
     * email mora biti Gmail adresa malim slovima,
     * telefon mora sadržati isključivo cifre (5-20),
     * datum rođenja ne sme biti u budućnosti,
     * nivo i stil plesa moraju oba biti izabrana.
     * </p>
     * <p>
     * Nakon uspešne izmene prikazuje poruku "Sistem je zapamtio učesnika."
     * i osvežava tabelu učesnika. U slučaju greške prikazuje poruku
     * "Sistem ne moze da zapamti učesnika."
     * </p>
     */
    private void addActionListeners() {

        puf.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = puf.getjTextFieldIme().getText().trim();
                String prezime = puf.getjTextFieldPrezime().getText().trim();
                String email = puf.getjTextFieldEmail().getText().trim();
                String telefon = puf.getjTextFieldTelefon().getText().trim();
                String napomena = puf.getjTextFieldNapomena().getText().trim();
                String datum = puf.getjTextFieldDatum().getText().trim();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                try {
                    datumRodjenja = LocalDate.parse(datum, fmt);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(puf, "Neispravan format datuma rođenja. Koristi: yyyy.MM.dd");
                    return;
                }
                PlesniNivo nivo = (PlesniNivo) puf.getjComboBoxNivo().getSelectedItem();
                PlesniStil ples = (PlesniStil) puf.getjComboBoxPles().getSelectedItem();

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(puf, "Morate upisati ime!");
                    return;
                }
                if (ime.length() > 50) {
                    JOptionPane.showMessageDialog(puf, "Ime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(ime.charAt(0))) {
                    JOptionPane.showMessageDialog(puf, "Ime uvek počinje velikim slovom!");
                    return;
                }
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(puf, "Morate upisati prezime!");
                    return;
                }
                if (prezime.length() > 50) {
                    JOptionPane.showMessageDialog(puf, "Prezime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(prezime.charAt(0))) {
                    JOptionPane.showMessageDialog(puf, "Prezime uvek počinje velikim slovom!");
                    return;
                }
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(puf, "Morate upisati e-mail!");
                    return;
                }
                if (!email.equals(email.toLowerCase())) {
                    JOptionPane.showMessageDialog(puf, "E-mail je uvek malim slovima!");
                    return;
                }
                if (!email.matches("[a-z0-9._%+-]+@gmail\\.com")) {
                    JOptionPane.showMessageDialog(puf, "Loš format e-maila!");
                    return;
                }
                if (puf.getjComboBoxNivo().getSelectedIndex() == -1 &&
                        puf.getjComboBoxPles().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(puf, "Morate izabrati nivo!");
                    return;
                }
                if (puf.getjComboBoxPles().getSelectedIndex() == -1 &&
                        puf.getjComboBoxNivo().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(puf, "Morate izabrati ples!");
                    return;
                }
                if (puf.getjComboBoxNivo().getSelectedIndex() == -1 &&
                        puf.getjComboBoxPles().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(puf, "Morate izabrati nivo i ples!");
                    return;
                }
                if (!telefon.isEmpty() && !telefon.matches("\\d{5,20}")) {
                    JOptionPane.showMessageDialog(puf, "Telefon mora sadržati isključivo cifre (5–20).");
                    return;
                }
                if (datumRodjenja.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(puf, "Datum rođenja ne može biti u budućnosti.");
                    return;
                }

                NivoVestine nv = new NivoVestine(-1, nivo, ples);
                Ucesnik u = new Ucesnik(id, ime, prezime, email, nv, telefon, datumRodjenja, napomena);

                try {
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().promeniUcesnika(u);
                    JOptionPane.showMessageDialog(puf, "Sistem je zapamtio učesnika.");
                    Koordinator.getInstance().osveziTabeluUcesnici();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(puf, "Sistem ne moze da zapamti učesnika.");
                }
            }
        });
    }

    /**
     * Popunjava formu podacima selektovanog učesnika i prikazuje je.
     * <p>
     * Polje ID-ja se popunjava ali se onemogućava za unos.
     * Datum se formatira u format {@code yyyy.MM.dd} pre prikaza.
     * Nivo i stil plesa se postavljaju u odgovarajuće combo boxove.
     * </p>
     *
     * @param selektovani učesnik čiji se podaci menjaju
     */
    public void prikaziFormu(Ucesnik selektovani) {
        puf.getjTextFieldId().setText(selektovani.getId() + "");
        puf.getjTextFieldId().setEnabled(false);
        puf.getjTextFieldIme().setText(selektovani.getIme());
        puf.getjTextFieldPrezime().setText(selektovani.getPrezime());
        puf.getjTextFieldEmail().setText(selektovani.getEmail());
        puf.getjTextFieldTelefon().setText(selektovani.getTelefon());
        puf.getjTextFieldNapomena().setText(selektovani.getNapomena());
        puf.getjTextFieldDatum().setText(
                selektovani.getDatumRodjenja().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        puf.getjComboBoxNivo().setSelectedItem(selektovani.getNivo().getNivo());
        puf.getjComboBoxPles().setSelectedItem(selektovani.getNivo().getVrsta());
        puf.setVisible(true);
        id = selektovani.getId();
        u = selektovani;
    }

    /**
     * Vraća ID učesnika koji se menja.
     *
     * @return ID učesnika
     */
    public int getId() {
        return id;
    }
}