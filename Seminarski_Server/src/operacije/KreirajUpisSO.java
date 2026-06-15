/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.UpisNaProgram;
import model.StavkaUpisa;

/**
 *
 * @author Korisnik
 */
public class KreirajUpisSO extends ApstraktnaGenerickaOperacija {
    
    private UpisNaProgram upis;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof UpisNaProgram)){
            throw new Exception("Sistem ne moze da kreira prijavu na kurs.");
        }
        
        upis = (UpisNaProgram) param;
        if (upis.getStavke()== null || upis.getStavke().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira upis bez stavki.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        upis = (UpisNaProgram) param;
        
        java.sql.Date sqlDatum = new java.sql.Date(upis.getDatum().getTime());
        upis.setDatum(sqlDatum);
        
        try {
            
            int noviID = broker.add(upis);
            upis.setId(noviID);
            
            for (StavkaUpisa su : upis.getStavke()) {
                
                su.setUpis(upis);
                broker.add(su);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public UpisNaProgram getUpis() {
        return upis;
    }
    
}
