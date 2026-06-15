/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.baza.implementacija;

import java.util.ArrayList;
import java.util.List;
import model.ApstraktniDomenskiObjekat;
import repozitorijum.baza.DBRepository;
import repozitorijum.baza.DBConnectionFactory;
import java.sql.*;

/**
 *
 * @author Korisnik
 */
public class DBBroker implements DBRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        
        if (uslov != null) {
            upit += uslov;
        }
        
        System.out.println(upit);
        
        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        
        rs.close();
        st.close();
        
        return lista;
        
    }

    @Override
    public int add(ApstraktniDomenskiObjekat param) throws Exception {
        
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje()
                + ") VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";
        
        System.out.println(upit);
        
        int primarniKljuc = -1;

        try (PreparedStatement pst = DBConnectionFactory.getInstance()
                .getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS)) {

            pst.executeUpdate();

            try ( ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    primarniKljuc = rs.getInt(1);
                }
            }
        }
        
        return primarniKljuc;
        
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + 
                " WHERE " + param.vratiPrimarniKljuc();
        
        System.out.println(upit);
        
        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        
        st.close();
        
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        String upit = "DELETE FROM " + param.vratiNazivTabele();
        
        if (uslov != null) {
            upit += uslov;
        } else {
            upit += " WHERE " + param.vratiPrimarniKljuc();
        }
        
        System.out.println(upit);
        
        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        
        st.close();
        
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
