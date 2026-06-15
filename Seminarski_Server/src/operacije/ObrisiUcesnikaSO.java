/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ucesnik;
import model.UpisNaProgram;

/**
 *
 * @author Korisnik
 */
public class ObrisiUcesnikaSO extends ApstraktnaGenerickaOperacija {
    
    private Ucesnik u;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Ucesnik)){
            throw new Exception("Sistem ne moze da obrise ucesnika.");
        }
        
        u = (Ucesnik) param;
        UpisNaProgram unp = new UpisNaProgram();
        unp.setUcesnik(u);

        List<UpisNaProgram> upisi = broker.getAll(unp, " WHERE ucesnik= " + u.getId());

        if (!upisi.isEmpty()) {
            throw new Exception("Sistem ne moze da obrise clana jer postoje njegovi upisi.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        u = (Ucesnik) param;
        
        try {
            broker.delete(u,null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Ucesnik getU() {
        return u;
    }

    
    
}
