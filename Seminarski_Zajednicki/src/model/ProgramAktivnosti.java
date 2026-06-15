/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */

public class ProgramAktivnosti implements ApstraktniDomenskiObjekat {
    
    private int id;
    private String naziv;
    private int trajanje;
    private double cena;
    private PlesniStil vrsta;
    private boolean aktivan;                     
    private String sala;                         
    private java.time.LocalDate datumPocetka;    
    private java.time.LocalTime termin;          
    private int maxUcesnika;                 
    private String napomena; 

    public ProgramAktivnosti() {
    }

    public ProgramAktivnosti(int id, String naziv, int trajanje, double cena, PlesniStil vrsta, boolean aktivan, String sala
            , LocalDate datumPocetka, LocalTime termin, int maxUcesnika, String napomena) {
        this.id = id;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.cena = cena;
        this.vrsta = vrsta;
        this.aktivan = aktivan;
        this.sala = sala;
        this.datumPocetka = datumPocetka;
        this.termin = termin;
        this.maxUcesnika = maxUcesnika;
        this.napomena = napomena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public PlesniStil getVrsta() {
        return vrsta;
    }

    public void setVrsta(PlesniStil vrsta) {
        this.vrsta = vrsta;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalTime getTermin() {
        return termin;
    }

    public void setTermin(LocalTime termin) {
        this.termin = termin;
    }

    public int getMaxUcesnika() {
        return maxUcesnika;
    }

    public void setMaxUcesnika(int maxUcesnika) {
        this.maxUcesnika = maxUcesnika;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }



   

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ProgramAktivnosti other = (ProgramAktivnosti) obj;
        return this.id == other.id;
    }

    @Override
    public String vratiNazivTabele() {
        return "program_aktivnosti";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    while (rs.next()) {
        int id = rs.getInt("program_aktivnosti.id");
        String naziv = rs.getString("program_aktivnosti.naziv");
        int trajanje = rs.getInt("program_aktivnosti.trajanje");
        double cena = rs.getDouble("program_aktivnosti.cena");
        PlesniStil ples = PlesniStil.valueOf(rs.getString("program_aktivnosti.vrsta"));
        boolean aktivan = rs.getBoolean("program_aktivnosti.aktivan");
        String sala = rs.getString("program_aktivnosti.sala");

        java.sql.Date d = rs.getDate("program_aktivnosti.datum_pocetka");
        LocalDate datumPocetka = (d != null) ? d.toLocalDate() : null;

        java.sql.Time t = rs.getTime("program_aktivnosti.termin");
        LocalTime termin = (t != null) ? t.toLocalTime() : null;

        int maxUcesnika = rs.getInt("program_aktivnosti.max_ucesnika");
        String napomena = rs.getString("program_aktivnosti.napomena");

        ProgramAktivnosti kp = new ProgramAktivnosti(
            id, naziv, trajanje, cena, ples,
            aktivan, sala, datumPocetka, termin, maxUcesnika, napomena
        );
        lista.add(kp);
    }
    return lista;
}

    @Override
    public String vratiKoloneZaUbacivanje() {
    return "naziv,trajanje,cena,vrsta,aktivan,sala,datum_pocetka,termin,max_ucesnika,napomena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "program_aktivnosti.id=" + id;
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
