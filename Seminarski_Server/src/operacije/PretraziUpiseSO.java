/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instruktor;
import model.Ucesnik;
import model.UpisNaProgram;
import model.kriterijum.KriterijumPretrage;

/**
 *
 * @author Korisnik
 */
public class PretraziUpiseSO extends ApstraktnaGenerickaOperacija {
    
    private List<UpisNaProgram> upisi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        KriterijumPretrage kp = (KriterijumPretrage) kljuc;
        Instruktor i = kp.getI();
        Ucesnik u = kp.getP();
        Date d = kp.getD();

        java.sql.Date sqlDate = null;
        if (d != null) {
            sqlDate = new java.sql.Date(d.getTime());
        }

        String uslov = " JOIN instruktor ON upis_na_program.instruktor=instruktor.id"
                + " JOIN ucesnik ON upis_na_program.ucesnik=ucesnik.id";

        if (i != null && u == null && d == null) {
            uslov += " WHERE instruktor.id=" + i.getId();
        }

        if (i == null && u != null && d == null) {
            uslov += " WHERE ucesnik.id=" + u.getId();
        }

        if (i == null && u == null && d != null) {
            uslov += " WHERE upis_na_program.datum='" + sqlDate + "'";
        }

        if (i != null && u != null && d == null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND ucesnik.id=" + u.getId();
        }

        if (i != null && u == null && d != null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND upis_na_program.datum='" + sqlDate + "'";
        }

        if (i == null && u != null && d != null) {
            uslov += " WHERE ucesnik.id=" + u.getId() + " AND upis_na_program.datum='" + sqlDate + "'";
        }

        if (i != null && u != null && d != null) {
            uslov += " WHERE instruktor.id=" + i.getId()
                    + " AND ucesnik.id=" + u.getId()
                    + " AND upis_na_program.datum='" + sqlDate + "'";
        }

        try {
            upisi = broker.getAll((UpisNaProgram) param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(PretraziUpiseSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<UpisNaProgram> getUpise() {
        return upisi;
    }
    
}
