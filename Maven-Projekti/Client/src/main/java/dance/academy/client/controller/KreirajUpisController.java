package dance.academy.client.controller;

import dance.academy.client.forme.KreirajUpisForma;
import dance.academy.client.forme.model.ModelTabeleStavkaUpisa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.StatusUpisa;
import dance.academy.common.model.StavkaUpisa;

/**
 * Controller klasa za formu kreiranja i izmene upisa na program aktivnosti.
 * <p>
 * Jedna forma se koristi za dva scenarija — kreiranje novog upisa i izmenu
 * postojećeg. Vidljivost dugmadi se prilagođava scenariju:
 * pri kreiranju se prikazuje dugme "Kreiraj upis", a pri izmeni "Potvrdi izmene".
 * </p>
 * <p>
 * Controller upravlja listom stavki upisa — stavke se dodaju i uklanjaju
 * dinamički, a ukupan iznos se automatski ažurira kao zbir iznosa svih stavki.
 * Iznos svake stavke se računa kao cena programa pomnožena trajanjem.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class KreirajUpisController {

    /** Referenca na formu za kreiranje/izmenu upisa. */
    private final KreirajUpisForma kuf;

    /** Lista stavki koje čine trenutni upis. */
    private List<StavkaUpisa> stavke = new ArrayList<>();

    /** Redni broj sledeće stavke koja će biti dodata. */
    private int id = 1;

    /** Ukupan iznos svih stavki u trenutnom upisu. */
    private double ukupanIznos = 0;

    /** Upis koji se menja, koristi se isključivo u scenariju izmene. */
    private UpisNaProgram zaIzmenu;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param kuf forma za kreiranje ili izmenu upisa
     */
    public KreirajUpisController(KreirajUpisForma kuf) {
        this.kuf = kuf;
        addActionListeners();
    }

    /**
     * Registruje action listenere na sva dugmad forme.
     * <p>
     * Dugme "Dodaj stavku" dodaje selektovani program u listu stavki.
     * Dugme "Ukloni stavku" uklanja selektovanu stavku iz tabele.
     * Dugme "Kreiraj upis" šalje novi upis na server.
     * Dugme "Potvrdi izmene" šalje izmenjeni upis na server.
     * Dugme "Vrati se na glavnu formu" zatvara formu i otvara glavnu.
     * </p>
     */
    private void addActionListeners() {

        kuf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kuf.getjComboBoxProgram().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kuf, "Niste odabrali program!");
                    return;
                }
                ProgramAktivnosti pa = (ProgramAktivnosti) kuf.getjComboBoxProgram().getSelectedItem();
                StavkaUpisa stavka = new StavkaUpisa();
                double iznos = pa.getCena() * pa.getTrajanje();
                stavka.setProgram(pa);
                stavka.setIznos(iznos);
                stavka.setRb(id);
                if (!stavke.contains(stavka)) {
                    stavke.add(stavka);
                    id++;
                    ukupanIznos += stavka.getIznos();
                } else {
                    JOptionPane.showMessageDialog(kuf, "Odabrani program je vec dodat!");
                    return;
                }
                ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(stavke);
                kuf.getjTable1().setModel(mtsp);
                kuf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
            }
        });

        kuf.ukloniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kuf.getjTable1().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(kuf, "Odaberite stavku koju zelite da uklonite!");
                    return;
                }
                StavkaUpisa selektovana = stavke.get(kuf.getjTable1().getSelectedRow());
                stavke.remove(selektovana);
                id--;
                ukupanIznos = ukupanIznos - selektovana.getIznos();
                ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(stavke);
                kuf.getjTable1().setModel(mtsp);
                kuf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
            }
        });

        kuf.kreirajPrijavuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kuf.getjComboBoxInstruktor().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kuf, "Niste odabrali instruktora!");
                    return;
                }
                if (kuf.getjComboBoxUcesnik().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kuf, "Niste odabrali ucesnika!");
                    return;
                }
                if (kuf.getjComboBoxStatus().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kuf, "Niste oznacili status upisa!");
                    return;
                }
                Instruktor i = (Instruktor) kuf.getjComboBoxInstruktor().getSelectedItem();
                Ucesnik u = (Ucesnik) kuf.getjComboBoxUcesnik().getSelectedItem();
                StatusUpisa su = (StatusUpisa) kuf.getjComboBoxStatus().getSelectedItem();
                UpisNaProgram unp = new UpisNaProgram(0, new Date(), su, ukupanIznos, i, u, stavke);
                if (stavke == null || stavke.isEmpty()) {
                    JOptionPane.showMessageDialog(kuf, "Sistem ne moze da zapamti upis na program.");
                    return;
                }
                try {
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().kreirajUpis(unp);
                    JOptionPane.showMessageDialog(kuf, "Sistem je zapamtio upis na program.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kuf, "Sistem ne moze da zapamti upis na program.");
                }
                kuf.getjComboBoxInstruktor().setSelectedIndex(-1);
                kuf.getjComboBoxUcesnik().setSelectedIndex(-1);
                kuf.getjComboBoxStatus().setSelectedIndex(-1);
                ukupanIznos = 0;
                kuf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
                kuf.getjComboBoxProgram().setSelectedIndex(-1);
                setStavke(new ArrayList<>());
                ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(stavke);
                kuf.getjTable1().setModel(mtsp);
            }
        });

        kuf.izmeniPrijavuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instruktor i = (Instruktor) kuf.getjComboBoxInstruktor().getSelectedItem();
                Ucesnik u = (Ucesnik) kuf.getjComboBoxUcesnik().getSelectedItem();
                StatusUpisa su = (StatusUpisa) kuf.getjComboBoxStatus().getSelectedItem();
                Date datum = null;
                String datumStr = kuf.getjTextFieldDatum().getText().trim();
                if (datumStr.isEmpty()) {
                    JOptionPane.showMessageDialog(kuf, "Morate uneti datum!");
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    datum = sdf.parse(datumStr);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kuf, "Uneli ste datum u pogresnom formatu!");
                }
                zaIzmenu.setDatum(datum);
                zaIzmenu.setInstruktor(i);
                zaIzmenu.setUcesnik(u);
                zaIzmenu.setStatus(su);
                zaIzmenu.setStavke(stavke);
                zaIzmenu.setUkupanIznos(ukupanIznos);
                if (stavke == null || stavke.isEmpty()) {
                    JOptionPane.showMessageDialog(kuf, "Sistem ne moze da zapamti upis na program.");
                    return;
                }
                try {
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().izmeniUpis(zaIzmenu);
                    Koordinator.getInstance().osveziTabeluUpisi();
                    JOptionPane.showMessageDialog(kuf, "Sistem je zapamtio upis na program.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kuf, "Sistem ne moze da zapamti upis na program.");
                }
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
     * Prikazuje formu korisniku.
     */
    public void prikaziFormu() {
        kuf.setVisible(true);
    }

    /**
     * Popunjava combo boxove sa podacima sa servera i postavlja početne vrednosti.
     * <p>
     * Učitava instruktore, učesnike i programe aktivnosti sa servera i
     * puni odgovarajuće combo boxove. Polje ukupnog iznosa se onemogućava
     * jer se računa automatski, a datum se postavlja na današnji datum.
     * </p>
     */
    private void pripremiFormu() {
        kuf.getjTextFieldUkupanIznos().setEnabled(false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String danasnjiDatum = sdf.format(new Date());
        kuf.getjTextFieldDatum().setText(danasnjiDatum);
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            kuf.getjComboBoxInstruktor().addItem(i);
        }
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> ucesnici = Komunikacija.getInstance().ucitajUcesnike();
        if (ucesnici == null) {
            JOptionPane.showMessageDialog(null, "Greška: sistem nije uspeo da učita učesnike!");
            return;
        }
        for (Ucesnik u : ucesnici) {
            kuf.getjComboBoxUcesnik().addItem(u);
        }
        Komunikacija.getInstance().konekcija();
        List<ProgramAktivnosti> programi = Komunikacija.getInstance().ucitajPrograme();
        for (ProgramAktivnosti pa : programi) {
            kuf.getjComboBoxProgram().addItem(pa);
        }
        kuf.getjComboBoxProgram().setSelectedIndex(-1);
    }

    /**
     * Priprema formu za kreiranje novog upisa.
     * <p>
     * Prikazuje dugme "Kreiraj upis" i sakriva "Potvrdi izmene".
     * Sve selekcije se resetuju na prazan izbor.
     * </p>
     */
    public void pripremiZaKreiranje() {
        pripremiFormu();
        kuf.getjButtonKreirajUpis().setVisible(true);
        kuf.getjButtonPotvrdiIzmene().setVisible(false);
        kuf.getjComboBoxInstruktor().setSelectedIndex(-1);
        kuf.getjComboBoxUcesnik().setSelectedIndex(-1);
        kuf.getjComboBoxStatus().setSelectedIndex(-1);
        kuf.getjTextFieldDatum().setEnabled(false);
    }

    /**
     * Priprema formu za izmenu postojećeg upisa.
     * <p>
     * Prikazuje dugme "Potvrdi izmene" i sakriva "Kreiraj upis" i "Vrati se na GF".
     * Popunjava sva polja podacima selektovanog upisa uključujući tabelu stavki.
     * </p>
     *
     * @param selektovana upis koji se menja
     */
    public void pripremiZaIzmenu(UpisNaProgram selektovana) {
        setZaIzmenu(selektovana);
        kuf.getjButtonVratiSeNaGF().setVisible(false);
        pripremiFormu();
        kuf.getjButtonKreirajUpis().setVisible(false);
        kuf.getjButtonPotvrdiIzmene().setVisible(true);
        kuf.getjComboBoxInstruktor().setSelectedItem(selektovana.getInstruktor());
        kuf.getjComboBoxStatus().setSelectedItem(selektovana.getStatus());
        kuf.getjComboBoxUcesnik().setSelectedItem(selektovana.getUcesnik());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datum = sdf.format(selektovana.getDatum());
        kuf.getjTextFieldDatum().setText(datum);
        kuf.getjTextFieldUkupanIznos().setText(selektovana.getUkupanIznos() + "");
        setUkupanIznos(selektovana.getUkupanIznos());
        List<StavkaUpisa> sveStavke = selektovana.getStavke();
        setStavke(sveStavke);
        ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(sveStavke);
        kuf.getjTable1().setModel(mtsp);
    }

    /**
     * Postavlja listu stavki upisa.
     *
     * @param stavke nova lista stavki
     */
    public void setStavke(List<StavkaUpisa> stavke) {
        this.stavke = stavke;
    }

    /**
     * Postavlja ukupan iznos upisa.
     *
     * @param ukupanIznos novi ukupan iznos
     */
    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    /**
     * Postavlja upis koji se menja u scenariju izmene.
     *
     * @param zaIzmenu upis koji se menja
     */
    public void setZaIzmenu(UpisNaProgram zaIzmenu) {
        this.zaIzmenu = zaIzmenu;
    }
}