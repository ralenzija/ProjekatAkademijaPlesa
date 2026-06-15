/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UpisNaProgram;

/**
 *
 * @author Korisnik
 */
public class UcitajUpiseSO extends ApstraktnaGenerickaOperacija {
    
    private List<UpisNaProgram> upisi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        String uslov = " JOIN instruktor ON upis_na_program.instruktor=instruktor.id JOIN ucesnik ON upis_na_program.ucesnik=ucesnik.id";
        
        try {
            upisi = broker.getAll((UpisNaProgram)param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<UpisNaProgram> getUpise() {
        return upisi;
    }
    
    
    
}
