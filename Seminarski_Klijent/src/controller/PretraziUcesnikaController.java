/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziUcesnikaForma;
import forme.model.ModelTabeleUcesnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Ucesnik;

/**
 *
 * @author Korisnik
 */
public class PretraziUcesnikaController {
    
    private final PretraziUcesnikaForma puf;
    
    public PretraziUcesnikaController(PretraziUcesnikaForma puf) {
        this.puf = puf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        puf.pretragaAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Ucesnik u = new Ucesnik();
                
                if (puf.getjComboBoxImePrezime().getSelectedIndex() != -1) {
                    
                    String imePrezime[] = String.valueOf(puf.getjComboBoxImePrezime().getSelectedItem()).split(" ");
                    String ime = imePrezime[0];
                    String prezime = imePrezime[1];
                    
                    u.setIme(ime);
                    u.setPrezime(prezime);
                    
                }
                
                if (puf.getjComboBoxEmail().getSelectedIndex() != -1) {
                    
                    String email = String.valueOf(puf.getjComboBoxEmail().getSelectedItem());
                    
                    u.setEmail(email);
                    
                }
                
                if (puf.getjComboBoxImePrezime().getSelectedIndex() == -1 && puf.getjComboBoxEmail().getSelectedIndex() == -1) {
                    
                    JOptionPane.showMessageDialog(puf, "Niste selektovali kriterijum pretrazivanja (Ime i prezime / Email)");
                    return;
                    
                }
                
                Komunikacija.getInstance().konekcija();
                List<Ucesnik> filtrirani = Komunikacija.getInstance().pretraziUcesnike(u);
                
                if(filtrirani != null && !filtrirani.isEmpty()){
                    JOptionPane.showMessageDialog(puf, "Sistem je našao učesnike na osnovu kriterijuma.");
                }else{
                    JOptionPane.showMessageDialog(puf, "Sistem ne može da nadje učesnike na osnovu kriterijumima.");
                }
                
                ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(filtrirani);
                puf.getjTable1().setModel(mtp);
                
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
        
        puf.ponistiPretraguAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremi();
                
            }
        });
        
        puf.detaljanPrikazAddActionListener (new ActionListener() {
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
    
    public void prikaziFormu() {
        
        puf.setVisible(true);
        
    }

    public void pripremiFormuZaPretragu() {
        
        pripremi();
        puf.getjButtonIzmeni().setVisible(false);
        
    }

    public void pripremiFormuZaIzmenu() {
        
        pripremi();
        puf.getjButtonIzmeni().setVisible(true);
        
    }

    private void pripremi() {
        
        //tabela
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> lista = Komunikacija.getInstance().ucitajUcesnike();
        ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(lista);
        puf.getjTable1().setModel(mtp);
        
        //combo boxevi
        
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

    public void osveziTabelu() {
        
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> lista = Komunikacija.getInstance().ucitajUcesnike();
        ModelTabeleUcesnik mtp = new ModelTabeleUcesnik(lista);
        puf.getjTable1().setModel(mtp);
        
    }
    
    
    
}
