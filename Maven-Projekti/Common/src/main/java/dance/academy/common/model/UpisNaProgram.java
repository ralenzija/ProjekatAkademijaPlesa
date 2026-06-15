/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.common.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Korisnik
 */

public class UpisNaProgram implements ApstraktniDomenskiObjekat {
    
    private int id;
    private Date datum;
    private StatusUpisa status;
    private double ukupanIznos;
    private Instruktor instruktor;
    private Ucesnik ucesnik;

    private List<StavkaUpisa> stavke = new ArrayList<>();

    public UpisNaProgram() {
    }

    public UpisNaProgram(int id, Date datum, StatusUpisa status, double ukupanIznos,
                         Instruktor instruktor, Ucesnik ucesnik, List<StavkaUpisa> stavke) {
        this.id = id;
        this.datum = datum;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.ucesnik = ucesnik;
        this.stavke = stavke;
    }
    
    public UpisNaProgram(int id, Date datum, StatusUpisa status, double ukupanIznos, Instruktor instruktor, Ucesnik ucesnik) {
        this.id = id;
        this.datum = datum;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.ucesnik = ucesnik;
    }

    public List<StavkaUpisa> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaUpisa> stavke) {
        this.stavke = stavke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public StatusUpisa getStatus() {
        return status;
    }

    public void setStatus(StatusUpisa status) {
        this.status = status;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Ucesnik getUcesnik() {
        return ucesnik;
    }

    public void setUcesnik(Ucesnik ucesnik) {
        this.ucesnik = ucesnik;
    }

    

    @Override
    public String toString() {
        return "UpisNaKurs{" + "datum=" + datum + ", status=" + status + ", ukupanIznos=" + ukupanIznos + ", instruktor=" + instruktor + ", ucesnik=" + ucesnik + '}';
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
        final UpisNaProgram other = (UpisNaProgram) obj;
        return this.id == other.id;
    }

    @Override
    public String vratiNazivTabele() {
        return "upis_na_program";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            
            //Instruktor
            int idIns = rs.getInt("instruktor.id");
            String imeIns = rs.getString("instruktor.ime");
            String prezimeIns = rs.getString("instruktor.prezime");
            String emailIns = rs.getString("instruktor.email");
            String sifraIns = rs.getString("instruktor.sifra");
            
            Instruktor i = new Instruktor(idIns, imeIns, prezimeIns, emailIns, sifraIns);
            
            //Ucesnik
            int idU = rs.getInt("ucesnik.id");
            String imeU = rs.getString("ucesnik.ime");
            String prezimeU = rs.getString("ucesnik.prezime");
            String emailU = rs.getString("ucesnik.email");
            String telefonU = rs.getString("ucesnik.telefon");
            java.sql.Date dRodj = rs.getDate("ucesnik.datum_rodjenja");
            java.time.LocalDate datumRodjU = (dRodj != null) ? dRodj.toLocalDate() : null;
            String napomenaU = rs.getString("ucesnik.napomena");

            Ucesnik p = new Ucesnik(idU, imeU, prezimeU, emailU,null, telefonU, datumRodjU, napomenaU);
            
            //PrijavaNaKurs
            int idUp = rs.getInt("upis_na_program.id");
            java.sql.Date datumSQL = rs.getDate("upis_na_program.datum");
            String statusStr = rs.getString("upis_na_program.status");
            double ukupanIznosUp = rs.getDouble("upis_na_program.ukupanIznos");
            StatusUpisa sp = StatusUpisa.valueOf(statusStr);

            java.util.Date datumUp = (datumSQL != null) ? new java.util.Date(datumSQL.getTime()) : null;

            UpisNaProgram prijava = new UpisNaProgram(idUp, datumUp, sp, ukupanIznosUp, i, p, null);
            
            //Ucitavanje stavki
          String upitStavke =
                   "SELECT * FROM stavka_upisa " +
                    "JOIN program_aktivnosti ON stavka_upisa.program = program_aktivnosti.id " +
                      "WHERE upis=" + idUp;

            List<StavkaUpisa> sveStavke = new ArrayList<>();

            try (Statement st = rs.getStatement().getConnection().createStatement(); ResultSet rsStavke = st.executeQuery(upitStavke)) {

                while (rsStavke.next()) {
                    int idStavka = rsStavke.getInt("stavka_upisa.id");
                    double iznos = rsStavke.getDouble("stavka_upisa.iznos");

                    int pid = rsStavke.getInt("program_aktivnosti.id");
                    String pnaziv = rsStavke.getString("program_aktivnosti.naziv");
                    int ptrajanje = rsStavke.getInt("program_aktivnosti.trajanje");
                    double pcena = rsStavke.getDouble("program_aktivnosti.cena");
                    String pvrsta = rsStavke.getString("program_aktivnosti.vrsta");

                    boolean paktivan = rsStavke.getBoolean("program_aktivnosti.aktivan");
                    String psala = rsStavke.getString("program_aktivnosti.sala");

                    java.sql.Date d = rsStavke.getDate("program_aktivnosti.datum_pocetka");
                    java.time.LocalDate pDatumPocetka = (d != null) ? d.toLocalDate() : null;

                    java.sql.Time t = rsStavke.getTime("program_aktivnosti.termin");
                    java.time.LocalTime pTermin = (t != null) ? t.toLocalTime() : null;

                    int pMax = rsStavke.getInt("program_aktivnosti.max_ucesnika");
                    String pNapomena = rsStavke.getString("program_aktivnosti.napomena");

                    ProgramAktivnosti kurs = new ProgramAktivnosti(
                            pid, pnaziv, ptrajanje, pcena, PlesniStil.valueOf(pvrsta),
                            paktivan, psala, pDatumPocetka, pTermin, pMax, pNapomena
                    );

                    StavkaUpisa stavka = new StavkaUpisa(idStavka, iznos, prijava, kurs);
                    sveStavke.add(stavka);

                }
            }

            prijava.setStavke(sveStavke);

            lista.add(prijava);

        }

        return lista;

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,status,ukupanIznos,instruktor,ucesnik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datum + "','" + status + "'," + ukupanIznos + "," + instruktor.getId() + "," + ucesnik.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "upis_na_program.id=" + id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + datum + "', status='" + status + "', ukupanIznos=" + ukupanIznos +
                ", instruktor=" + instruktor.getId() + ", ucesnik=" + ucesnik.getId();
    }
    
    
    
}
