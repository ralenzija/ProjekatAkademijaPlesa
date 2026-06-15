/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.StatusUpisa;
import model.UpisNaProgram;

/**
 *
 * @author HP ProBook 440 G5
 */
public class PromeniStatusUpisaSO extends ApstraktnaGenerickaOperacija{

   
    private UpisNaProgram upis;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if (!(param instanceof UpisNaProgram up) || up.getId() <= 0 || up.getStatus() == null) {
            throw new Exception("Nedovoljan parametar za promenu statusa upisa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        UpisNaProgram up = (UpisNaProgram) param;
        try {
            // Oslanjamo se na vratiVrednostiZaIzmenu() u UpisNaProgram – on menja sve kolone.
            // Ako želiš da menjaš ISKLJUČIVO status, možeš u DB brokeru da pozoveš edit sa uslovom koji setuje samo status.
            broker.edit(up);
            upis = up;
        } catch (Exception ex) {
            Logger.getLogger(PromeniStatusUpisaSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UpisNaProgram getUpis() {
        return upis;
    }
    
}
