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
public class LoginSO extends ApstraktnaGenerickaOperacija {
    
    private Instruktor instruktor;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        try {
            
            List<Instruktor> sviInstruktori = broker.getAll((Instruktor)param, null);
            System.out.println("Klasa LoginSO: " + sviInstruktori);
            instruktor = new Instruktor(-1, null, null, null, null);
            
            for (Instruktor i : sviInstruktori) {
                
                if (i.equals( (Instruktor) param)) {
                    instruktor = i;
                    return;
                }
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LoginSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    public Instruktor getI() {
        return instruktor;
    }
    
}
