/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instruktor;

/**
 *
 * @author Korisnik
 */
public class UcitajInstruktoreSO extends ApstraktnaGenerickaOperacija {

    private List<Instruktor> instruktori;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        try {
            instruktori = broker.getAll((Instruktor)param, null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Instruktor> getInstruktori() {
        return instruktori;
    }
    
}
