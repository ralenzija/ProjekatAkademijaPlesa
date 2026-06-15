/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum.baza;

import repozitorijum.Repository;

/**
 *
 * @author Korisnik
 */
public interface DBRepository<T> extends Repository<T> {
    
    default public void uspostaviKonekcijuSaBazom() throws Exception{
       DBConnectionFactory.getInstance().getConnection();
    }
    
    default public void raskiniKonekcijuSaBazom() throws Exception{
       DBConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void potvrdiTransakciju() throws Exception{
       DBConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void ponistiTransakciju() throws Exception{
       DBConnectionFactory.getInstance().getConnection().rollback();
    }
    
}
