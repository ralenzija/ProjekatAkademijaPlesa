/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ucesnik;

/**
 *
 * @author Korisnik
 */
public class KreirajUcesnikaSO extends ApstraktnaGenerickaOperacija {
    
    private Ucesnik ucesnik;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Ucesnik)){
            throw new Exception("Sistem ne moze da kreira ucesnika.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        ucesnik = (Ucesnik) param;
        
        try {
            broker.add(ucesnik);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Ucesnik getUcesnik() {
        return ucesnik;
    }
    
    
    
}
