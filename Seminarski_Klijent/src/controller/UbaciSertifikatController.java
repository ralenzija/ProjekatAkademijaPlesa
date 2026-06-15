/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.UbaciSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.PlesniStil;
import model.Sertifikat;

/**
 *
 * @author Korisnik
 */
public class UbaciSertifikatController {
    
    private final UbaciSertifikatForma uvpf;
    
    public UbaciSertifikatController(UbaciSertifikatForma uvpf) {
        this.uvpf = uvpf;
        addActionListeners();
    }

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
    
    public void prikaziFormu() {
        
        uvpf.getjComboBoxPles().setSelectedIndex(-1);
        uvpf.setVisible(true);
        
    }
    
}
