/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ucesnik;

/**
 *
 * @author Korisnik
 */
public class UcitajUcesnikeSO extends ApstraktnaGenerickaOperacija {
    
    private List<Ucesnik> ucesnici;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        String uslov = " JOIN nivo_vestine ON ucesnik.nivo=nivo_vestine.id";
        
        try {
            ucesnici = broker.getAll((Ucesnik)param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Ucesnik> getUcesnici() {
        return ucesnici;
    }
    
    
    
}
