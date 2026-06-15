/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.client.controller;

import dance.academy.client.forme.IzabraniUcesnikForma;
import java.time.format.DateTimeFormatter;
import dance.academy.common.model.Ucesnik;

/**
 *
 * @author Korisnik
 */
public class IzabraniUcesnikController {
    
    private final IzabraniUcesnikForma iuf;
    
    public IzabraniUcesnikController(IzabraniUcesnikForma iuf) {
        this.iuf = iuf;
    }

    public void prikaziFormu(Ucesnik selektovani) {
        pripremiFormu(selektovani);
        iuf.setVisible(true);
    }

    private void pripremiFormu(Ucesnik selektovani) {
        
        iuf.getjTextFieldIme().setText(selektovani.getIme());
        iuf.getjTextFieldPrezime().setText(selektovani.getPrezime());
        iuf.getjTextFieldEmail().setText(selektovani.getEmail());
        iuf.getjTextFieldTelefon().setText(selektovani.getTelefon());
        iuf.getjTextFieldNapomena().setText(selektovani.getNapomena());
        iuf.getjTextFieldDatum().setText(selektovani.getDatumRodjenja().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        iuf.getjComboBoxNivo().setSelectedItem(selektovani.getNivo().getNivo());
        iuf.getjComboBoxPles().setSelectedItem(selektovani.getNivo().getVrsta());
        
        iuf.getjTextFieldIme().setEnabled(false);
        iuf.getjTextFieldPrezime().setEnabled(false);
        iuf.getjTextFieldEmail().setEnabled(false);
        iuf.getjTextFieldTelefon().setEditable(false);
        iuf.getjTextFieldNapomena().setEditable(false);
        iuf.getjTextFieldDatum().setEditable(false);
        iuf.getjComboBoxNivo().setEnabled(false);
        iuf.getjComboBoxPles().setEnabled(false);
        
    }
    
}
