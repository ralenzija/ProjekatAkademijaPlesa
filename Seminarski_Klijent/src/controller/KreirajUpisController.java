/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajUpisForma;
import forme.model.ModelTabeleStavkaUpisa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Instruktor;
import model.ProgramAktivnosti;
import model.Ucesnik;
import model.UpisNaProgram;
import model.StatusUpisa;
import model.StavkaUpisa;

/**
 *
 * @author Korisnik
 */
public class KreirajUpisController {
  
    private final KreirajUpisForma kuf;
    private List<StavkaUpisa> stavke = new ArrayList<>();
    private int id = 1;
    private double ukupanIznos = 0;
    private UpisNaProgram zaIzmenu;

    public KreirajUpisController(KreirajUpisForma kuf) {
        this.kuf = kuf;
        addActionListeners();
    }

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
                
                kuf.getjTextFieldUkupanIznos().setText(ukupanIznos+"");
                
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
                kuf.getjTextFieldUkupanIznos().setText(ukupanIznos+"");
                
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
                System.out.println(stavke);
                
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
    
    public void prikaziFormu() {
        
        kuf.setVisible(true);
        
    }

    private void pripremiFormu() {
        
        kuf.getjTextFieldUkupanIznos().setEnabled(false);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String danasnjiDatum = sdf.format(new Date());
        kuf.getjTextFieldDatum().setText(danasnjiDatum);
        
        //Combo box Instruktori
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            kuf.getjComboBoxInstruktor().addItem(i);
        }

        //Combo box Polaznici
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> ucesnici = Komunikacija.getInstance().ucitajUcesnike();
        if (ucesnici == null) {
            JOptionPane.showMessageDialog(null, "Greška: sistem nije uspeo da učita učesnike!");
            return;
        }
        for (Ucesnik u : ucesnici) {
            kuf.getjComboBoxUcesnik().addItem(u);
        }
        
        //Combo box KursPlesa
        Komunikacija.getInstance().konekcija();
        List<ProgramAktivnosti> programi = Komunikacija.getInstance().ucitajPrograme();
        for (ProgramAktivnosti pa : programi) {
            kuf.getjComboBoxProgram().addItem(pa);
        }
        kuf.getjComboBoxProgram().setSelectedIndex(-1);
        
    }

    public void pripremiZaKreiranje() {
        
        pripremiFormu();
        kuf.getjButtonKreirajUpis().setVisible(true);
        kuf.getjButtonPotvrdiIzmene().setVisible(false);
        
        kuf.getjComboBoxInstruktor().setSelectedIndex(-1);
        kuf.getjComboBoxUcesnik().setSelectedIndex(-1);
        kuf.getjComboBoxStatus().setSelectedIndex(-1);
        
        kuf.getjTextFieldDatum().setEnabled(false);
        
    }

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
        
        kuf.getjTextFieldUkupanIznos().setText(selektovana.getUkupanIznos()+"");
        setUkupanIznos(selektovana.getUkupanIznos());
        
        //Popunjavanje tabele stavkama
        List<StavkaUpisa> sveStavke = selektovana.getStavke();
        setStavke(sveStavke);
        ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(sveStavke);
        kuf.getjTable1().setModel(mtsp);

    }

    public void setStavke(List<StavkaUpisa> stavke) {
        this.stavke = stavke;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public void setZaIzmenu(UpisNaProgram zaIzmenu) {
        this.zaIzmenu = zaIzmenu;
    }
    
    
}
