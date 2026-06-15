/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.server.operacije;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.NivoVestine;

/**
 *
 * @author Korisnik
 */
public class UcitajNivoeSO extends ApstraktnaGenerickaOperacija {
    
    private NivoVestine nivo;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        NivoVestine nv = (NivoVestine) param;
        String n = String.valueOf(nv.getNivo());
        String v = String.valueOf(nv.getVrsta());
        
        String uslov = " WHERE nivo_vestine.nivo='" + n + "' AND nivo_vestine.vrsta='" + v + "'";
        
        List<NivoVestine> nivoi = new ArrayList<>();
        
        try {
            nivoi = broker.getAll((NivoVestine)param, uslov);
            nivo = nivoi.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UcitajUcesnikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public NivoVestine getNivo() {
        return nivo;
    }
    
    
    
}
