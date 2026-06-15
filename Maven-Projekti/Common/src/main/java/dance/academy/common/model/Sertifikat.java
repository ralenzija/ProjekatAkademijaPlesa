/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Korisnik
 */

public class Sertifikat implements ApstraktniDomenskiObjekat {
    
    private int id;
    private PlesniStil ples;
    private String organizacija;

    public Sertifikat() {
    }

    public Sertifikat(int id, PlesniStil naziv, String organizacija) {
        this.id = id;
        this.ples = naziv;
        this.organizacija = organizacija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPles(PlesniStil ples) {
        this.ples = ples;
    }

    public PlesniStil getPles() {
        return ples;
    }

    public String getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(String organizacija) {
        this.organizacija = organizacija;
    }

    @Override
    public String toString() {
        return "Sertifikat{" + "ples=" + ples + ", organizacija=" + organizacija + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Sertifikat other = (Sertifikat) obj;
        return this.id == other.id;
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ples,organizacija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + String.valueOf(ples) + "','" + organizacija + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sertifikat.id=" + id;
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
