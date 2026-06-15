/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.client.controller;

import dance.academy.client.forme.GlavnaForma;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.Instruktor;

/**
 *
 * @author Korisnik
 */
public class GlavnaFormaController {
    
    private final GlavnaForma gf;
    
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
        
    }
    
    public void prikaziFormu() {
        
        gf.setVisible(true);
        Instruktor ulogovani = Koordinator.getInstance().getUlogvani();
        gf.getjLabelUlogovani().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
        
    }

    public void zatvoriFormu() {
        gf.dispose();
    }
    
}
