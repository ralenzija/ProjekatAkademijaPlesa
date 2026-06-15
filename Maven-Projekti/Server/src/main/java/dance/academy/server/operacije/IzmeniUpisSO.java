/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.StavkaUpisa;

/**
 *
 * @author Korisnik
 */
public class IzmeniUpisSO extends ApstraktnaGenerickaOperacija {
    
    private UpisNaProgram unp;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof UpisNaProgram)){
            throw new Exception("Sistem ne moze da kreira upis na program.");
        }
        
        unp = (UpisNaProgram) param;
        if (unp.getStavke()== null || unp.getStavke().isEmpty()) {
            throw new Exception("Prijava mora imati barem jednu stavku.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        unp = (UpisNaProgram) param;
        
        java.sql.Date sqlDatum = new java.sql.Date(unp.getDatum().getTime());
        unp.setDatum(sqlDatum);
        
        try {
            
            broker.edit(unp);
            
            String uslov = " WHERE upis=" + unp.getId();
            broker.delete(new StavkaUpisa(), uslov);
            
            for (StavkaUpisa su : unp.getStavke()) {
                su.setUpis(unp);
                broker.add(su);
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(IzmeniUpisSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public UpisNaProgram getUnp() {
        return unp;
    }
     
    
}
