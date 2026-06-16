package dance.academy.client.controller;

import dance.academy.client.forme.KreirajUcesnikaForma;
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
 * Controller klasa za formu kreiranja novog učesnika.
 * <p>
 * Upravlja unosom i validacijom podataka o novom učesniku pre slanja
 * na server. Validacija obuhvata format imena i prezimena (početno
 * veliko slovo, max 50 karaktera), format email adrese (isključivo
 * Gmail adrese malim slovima), format datuma rođenja, ispravnost
 * broja telefona i obaveznost izbora nivoa i stila plesa.
 * Nakon uspešnog kreiranja, polja se resetuju za novi unos.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class KreirajUcesnikaController {

    /** Referenca na formu za kreiranje učesnika. */
    private final KreirajUcesnikaForma kuf;

    /** Datum rođenja učesnika parsiran iz tekstualnog polja. */
    LocalDate datumRodjenja;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param kuf forma za kreiranje učesnika
     */
    public KreirajUcesnikaController(KreirajUcesnikaForma kuf) {
        this.kuf = kuf;
        addActionListeners();
    }

    /**
     * Registruje action listenere na dugmad forme.
     * <p>
     * Dugme "Kreiraj" pokreće validaciju i slanje podataka na server.
     * Dugme "Vrati se na glavnu formu" zatvara ovu formu i otvara glavnu.
     * </p>
     */
    private void addActionListeners() {

        kuf.kreiranjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ime = kuf.getjTextFieldIme().getText().trim();
                String prezime = kuf.getjTextFieldPrezime().getText().trim();
                String email = kuf.getjTextFieldEmail().getText().trim();
                String telefon = kuf.getjTextFieldTelefon().getText().trim();
                String napomena = kuf.getjTextFieldNapomena().getText().trim();
                String datum = kuf.getjTextFieldDatum().getText().trim();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                try {
                    datumRodjenja = LocalDate.parse(datum, fmt);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(kuf, "Neispravan format datuma rođenja. Koristi: yyyy.MM.dd");
                    return;
                }
                PlesniNivo nivo = (PlesniNivo) kuf.getjComboBoxNivo().getSelectedItem();
                PlesniStil ples = (PlesniStil) kuf.getjComboBoxPles().getSelectedItem();

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(kuf, "Morate upisati ime!");
                    return;
                }
                if (ime.length() > 50) {
                    JOptionPane.showMessageDialog(kuf, "Ime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(ime.charAt(0))) {
                    JOptionPane.showMessageDialog(kuf, "Ime uvek počinje velikim slovom!");
                    return;
                }
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(kuf, "Morate upisati prezime!");
                    return;
                }
                if (prezime.length() > 50) {
                    JOptionPane.showMessageDialog(kuf, "Prezime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(prezime.charAt(0))) {
                    JOptionPane.showMessageDialog(kuf, "Prezime uvek počinje velikim slovom!");
                    return;
                }
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(kuf, "Morate upisati e-mail!");
                    return;
                }
                if (!email.equals(email.toLowerCase())) {
                    JOptionPane.showMessageDialog(kuf, "E-mail je uvek malim slovima!");
                    return;
                }
                if (!email.matches("[a-z0-9._%+-]+@gmail\\.com")) {
                    JOptionPane.showMessageDialog(kuf, "Loš format e-maila!");
                    return;
                }
                if (kuf.getjComboBoxNivo().getSelectedIndex() == -1 && kuf.getjComboBoxPles().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(kuf, "Morate izabrati nivo!");
                    return;
                }
                if (kuf.getjComboBoxPles().getSelectedIndex() == -1 && kuf.getjComboBoxNivo().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(kuf, "Morate izabrati ples!");
                    return;
                }
                if (kuf.getjComboBoxNivo().getSelectedIndex() == -1 && kuf.getjComboBoxPles().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kuf, "Morate izabrati nivo i ples!");
                    return;
                }
                if (!telefon.isEmpty() && !telefon.matches("\\d{5,20}")) {
                    JOptionPane.showMessageDialog(kuf, "Telefon mora sadržati isključivo cifre (5–20).");
                    return;
                }
                if (datumRodjenja.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(kuf, "Datum rođenja ne može biti u budućnosti.");
                    return;
                }

                NivoVestine nv = new NivoVestine(-1, nivo, ples);
                Ucesnik u = new Ucesnik(-1, ime, prezime, email, nv, telefon, datumRodjenja, napomena);

                try {
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().kreirajUcesnika(u);
                    JOptionPane.showMessageDialog(kuf, "Sistem je zapamtio ucesnika.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kuf, "Sistem ne moze da zapamti ucesnika.");
                }

                kuf.getjTextFieldIme().setText("");
                kuf.getjTextFieldPrezime().setText("");
                kuf.getjTextFieldEmail().setText("");
                kuf.getjTextFieldDatum().setText("");
                kuf.getjTextFieldNapomena().setText("");
                kuf.getjTextFieldTelefon().setText("");
                kuf.getjComboBoxNivo().setSelectedIndex(-1);
                kuf.getjComboBoxPles().setSelectedIndex(-1);
            }
        });

        kuf.vratiNaGFAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kuf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
            }
        });
    }

    /**
     * Priprema formu i prikazuje je korisniku.
     * Combo boxovi se resetuju na početni prazan izbor.
     */
    public void prikaziFormu() {
        pripremiFormu();
        kuf.setVisible(true);
    }

    /**
     * Resetuje combo boxove na početni prazan izbor pre prikaza forme.
     */
    private void pripremiFormu() {
        kuf.getjComboBoxNivo().setSelectedIndex(-1);
        kuf.getjComboBoxPles().setSelectedIndex(-1);
    }
}