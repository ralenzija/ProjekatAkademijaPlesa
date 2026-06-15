/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.common.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */

public class StavkaUpisa implements ApstraktniDomenskiObjekat {
    
    private int rb;
    private double iznos;
    private UpisNaProgram upis;
    private ProgramAktivnosti program;

    public StavkaUpisa() {
    }

    public StavkaUpisa(int rb, double iznos, UpisNaProgram upis, ProgramAktivnosti program) {
        this.rb = rb;
        this.iznos = iznos;
        this.upis = upis;
        this.program = program;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public UpisNaProgram getUpis() {
        return upis;
    }

    public void setUpis(UpisNaProgram upis) {
        this.upis = upis;
    }

    public ProgramAktivnosti getProgram() {
        return program;
    }

    public void setProgram(ProgramAktivnosti program) {
        this.program = program;
    }

    

    @Override
    public String toString() {
        return "StavkaPrijave{" + "prijava=" + upis + ", program=" + program + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final StavkaUpisa other = (StavkaUpisa) obj;
        return Objects.equals(this.program, other.program);
    }


    @Override
    public String vratiNazivTabele() {
        return "stavka_upisa";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {
            
            int idS = rs.getInt("stavka_upisa.id");
            double iznosS = rs.getDouble("stavka_upisa.iznos");
            int idUpis = rs.getInt("upis_na_program.id");
            int idProgram = rs.getInt("program_aktivnosti.id");
            
            UpisNaProgram pnk = new UpisNaProgram();
            pnk.setId(idUpis);
            
            ProgramAktivnosti pa = new ProgramAktivnosti();
            pa.setId(idProgram);
            
            StavkaUpisa su = new StavkaUpisa(idS, iznosS, pnk, pa);
            lista.add(su);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "upis,iznos,program";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return upis.getId() + ", " + iznos + ", " + program.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_upisa.id=" + rb + " AND stavka_upisa.upis=" + upis.getId();
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
