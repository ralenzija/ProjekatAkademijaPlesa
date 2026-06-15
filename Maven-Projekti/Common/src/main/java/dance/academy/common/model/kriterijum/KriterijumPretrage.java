/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.common.model.kriterijum;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import dance.academy.common.model.ApstraktniDomenskiObjekat;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;

/**
 *
 * @author Korisnik
 */


public class KriterijumPretrage implements ApstraktniDomenskiObjekat  {
    
    private Instruktor i;
    private Ucesnik p;
    private Date d;

    public KriterijumPretrage(Instruktor i, Ucesnik p, Date d) {
        this.i = i;
        this.p = p;
        this.d = d;
    }

    public KriterijumPretrage() {
    }

    public Date getD() {
        return d;
    }

    public Instruktor getI() {
        return i;
    }

    public Ucesnik getP() {
        return p;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public void setI(Instruktor i) {
        this.i = i;
    }

    public void setP(Ucesnik p) {
        this.p = p;
    }

    @Override
    public String vratiNazivTabele() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
