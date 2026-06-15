/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */

public class InstruktorSertifikat implements ApstraktniDomenskiObjekat {
    
    private Instruktor instruktor;
    private Sertifikat sertifikat;
    private Date datumSticanja;

    public InstruktorSertifikat() {
    }

    public InstruktorSertifikat(Instruktor instruktor, Sertifikat sertifikat, Date datumSticanja) {
        this.instruktor = instruktor;
        this.sertifikat = sertifikat;
        this.datumSticanja = datumSticanja;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "InstruktorSertifikat{" + "instruktor=" + instruktor + ", sertifikat=" + sertifikat + ", datumSticanja=" + datumSticanja + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InstruktorSertifikat other = (InstruktorSertifikat) obj;
        if (!Objects.equals(this.instruktor, other.instruktor)) {
            return false;
        }
        if (!Objects.equals(this.sertifikat, other.sertifikat)) {
            return false;
        }
        return Objects.equals(this.datumSticanja, other.datumSticanja);
    }

    @Override
    public String vratiNazivTabele() {
        return "instruktor_sertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "instruktor,sertifikat,datumSticanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return instruktor.getId() + "," + sertifikat.getId() + ",'" + datumSticanja + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "instruktor_sertifikat.instruktor=" + instruktor.getId() + " AND instruktor_sertifikat.sertifikat=" + sertifikat.getId();
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
