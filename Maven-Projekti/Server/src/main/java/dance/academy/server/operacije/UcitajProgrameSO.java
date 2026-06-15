/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.server.operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.ProgramAktivnosti;

/**
 *
 * @author Korisnik
 */
public class UcitajProgrameSO extends ApstraktnaGenerickaOperacija {

    private List<ProgramAktivnosti> programi;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
         try {
            programi = broker.getAll((ProgramAktivnosti)param, null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<ProgramAktivnosti> getProgrami() {
        return programi;
    }
    
    
    
}
