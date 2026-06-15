/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.client.controller;

import dance.academy.client.forme.PretraziUpisForma;
import dance.academy.client.forme.model.ModelTabeleUpis;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.kriterijum.KriterijumPretrage;

/**
 *
 * @author Korisnik
 */
public class PretraziUpisController {
    
    private final PretraziUpisForma puf;
    
    public PretraziUpisController(PretraziUpisForma puf) {
        this.puf = puf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        puf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Instruktor i = null;
                Ucesnik u = null;
                Date datum = null;
                
                String datumStr = puf.getjTextFieldDatum().getText().trim();
                
                if (puf.getjComboBoxInstruktor().getSelectedIndex() == -1 &&
                    puf.getjComboBoxUcesnik().getSelectedIndex() == -1 &&
                    datumStr.isEmpty()) {
                    JOptionPane.showMessageDialog(puf, "Izaberite kriterijum pretrage!");
                    return;
                }
                
                if (puf.getjComboBoxInstruktor().getSelectedIndex() != -1) {
                    i = (Instruktor) puf.getjComboBoxInstruktor().getSelectedItem();
                }
                
                if (puf.getjComboBoxUcesnik().getSelectedIndex() != -1) {
                    u = (Ucesnik) puf.getjComboBoxUcesnik().getSelectedItem();
                }
                
                if (!datumStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        datum = sdf.parse(datumStr);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(puf, "Uneli ste datum u pogresnom formatu!");
                    }

                }
                
                KriterijumPretrage kp = new KriterijumPretrage(i, u, datum);
                
                Komunikacija.getInstance().konekcija();
                List<UpisNaProgram> filtrirani = Komunikacija.getInstance().pretraziUpise(kp);
                
                if(filtrirani != null && !filtrirani.isEmpty()){
                    JOptionPane.showMessageDialog(puf, "Sistem je nasao upise na program na osnovu kriterijuma.");
                }else{
                    JOptionPane.showMessageDialog(puf, "Sistem ne moze da nadje upise na program na osnovu kriterijumima.");
                }
                
                ModelTabeleUpis mtp = new ModelTabeleUpis(filtrirani);
                puf.getjTable1().setModel(mtp);
                
            }
        });
        
        puf.ponistiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremiFormu();
                
            }
        });
        
        puf.vratiSeNaGFAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                puf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
                
            }
        });
        
        puf.izmeniAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = puf.getjTable1().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(puf, "Sistem ne moze da nadje upis na program.");
                    return;
                }
                
                JOptionPane.showMessageDialog(puf, "Sistem je nasao upis na program.");
                
                ModelTabeleUpis mtp = (ModelTabeleUpis) puf.getjTable1().getModel();
                List<UpisNaProgram> lista = mtp.getLista();
                UpisNaProgram selektovana = lista.get(red);
                
                Koordinator.getInstance().otvoriPromeniUpisFormu(selektovana);
                
            }
        });
        
        puf.detaljanPrikazAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = puf.getjTable1().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(puf, "Sistem ne moze da nadje upis na program.");
                    return;
                }
                
                JOptionPane.showMessageDialog(puf, "Sistem je nasao upis na program.");
                
                ModelTabeleUpis mtp = (ModelTabeleUpis) puf.getjTable1().getModel();
                List<UpisNaProgram> lista = mtp.getLista();
                UpisNaProgram selektovana = lista.get(red);
                
                Koordinator.getInstance().otvoriIzabranUpisFormu(selektovana);
                
            }
        });
        
    }
    
    public void prikaziFormu() {
        puf.setVisible(true);
    }
    
    public void pripremiFormuZaPretragu() {
        pripremiFormu();
        puf.getjButtonIzmeni().setVisible(false);
    }

    public void pripremiFormuZaIzmenu() {
        pripremiFormu();
        puf.getjButtonIzmeni().setVisible(true);
    }

    private void pripremiFormu() {
        
        //Combo box Instruktori
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            puf.getjComboBoxInstruktor().addItem(i);
        }
        puf.getjComboBoxInstruktor().setSelectedIndex(-1);

        //Combo box Polaznici
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> ucesnici = Komunikacija.getInstance().ucitajUcesnike();
        for (Ucesnik u : ucesnici) {
            puf.getjComboBoxUcesnik().addItem(u);
        }
        puf.getjComboBoxUcesnik().setSelectedIndex(-1);
        
        //Tabela Prijave na kurs
        Komunikacija.getInstance().konekcija();
        List<UpisNaProgram> upisi = Komunikacija.getInstance().ucitajUpise();
        ModelTabeleUpis mtp = new ModelTabeleUpis(upisi);
        puf.getjTable1().setModel(mtp);
        
        puf.getjTextFieldDatum().setText("");
        
    }

    public void osveziTabelu() {
        pripremiFormu();
    }

    
}
