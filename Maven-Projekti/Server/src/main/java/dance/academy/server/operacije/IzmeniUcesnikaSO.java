/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Ucesnik;

/**
 *
 * @author Korisnik
 */
public class IzmeniUcesnikaSO extends ApstraktnaGenerickaOperacija {

    private Ucesnik u;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ucesnik)) {
            throw new Exception("Sistem ne moze da izmeni ucesnika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        u = (Ucesnik) param;
        
        try {
            broker.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Ucesnik getU() {
        return u;
    }
    
}
