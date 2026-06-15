/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.common.model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */

public class Ucesnik implements ApstraktniDomenskiObjekat {
    
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private NivoVestine nivo;
    private String telefon;
    private java.time.LocalDate datumRodjenja;
    private String napomena;
    public Ucesnik() {
    }

    public Ucesnik(int id, String ime, String prezime, String email, NivoVestine nivo,
            String telefon, LocalDate datumRodjenja, String napomena) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivo = nivo;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.napomena = napomena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NivoVestine getNivo() {
        return nivo;
    }

    public void setNivo(NivoVestine nivo) {
        this.nivo = nivo;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }





    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Ucesnik other = (Ucesnik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "ucesnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

    while (rs.next()) {
        int id = rs.getInt("id");
        String ime = rs.getString("ime");
        String prezime = rs.getString("prezime");
        String email = rs.getString("email");
        String telefon = rs.getString("telefon");
        String napomena = rs.getString("napomena");

        java.sql.Date datumRodjenjaSQL = rs.getDate("datum_rodjenja");
        java.time.LocalDate datumRodjenja = (datumRodjenjaSQL != null) ? datumRodjenjaSQL.toLocalDate() : null;

        int nivoId = rs.getInt("nivo");
        NivoVestine nivo = new NivoVestine();
        nivo.setId(nivoId);

        Ucesnik u = new Ucesnik(id, ime, prezime, email, nivo, telefon, datumRodjenja, napomena);
        lista.add(u);
    }

    return lista;
}

    @Override
    public String vratiKoloneZaUbacivanje() {
    return "ime,prezime,email,telefon,datum_rodjenja,napomena,nivo";
}

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + telefon + "','" + java.sql.Date.valueOf(datumRodjenja) + "','" + napomena + "'," + nivo.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "ucesnik.id=" + id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
            return "'" + ime + "','" + prezime + "','" + email + "','" + telefon + "','" + java.sql.Date.valueOf(datumRodjenja) + "','" + napomena + "'," + nivo.getId();

    }
    
    
    
}
