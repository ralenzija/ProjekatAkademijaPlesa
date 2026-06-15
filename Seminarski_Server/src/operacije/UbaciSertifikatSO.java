/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sertifikat;

/**
 *
 * @author Korisnik
 */
public class UbaciSertifikatSO extends ApstraktnaGenerickaOperacija {
    
    private Sertifikat s;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Sertifikat)){
            throw new Exception("Sistem ne moze da zapamti sertifikat.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        s = (Sertifikat) param;
        
        try {
            broker.add(s);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Sertifikat getS() {
        return s;
    }
    
}
