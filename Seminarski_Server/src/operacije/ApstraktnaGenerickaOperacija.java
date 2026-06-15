/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repozitorijum.Repository;
import repozitorijum.baza.DBRepository;
import repozitorijum.baza.implementacija.DBBroker;

/**
 *
 * @author Korisnik
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DBBroker();
    }
    
    public final void izvrsi(Object objekat,Object kljuc)throws Exception{
        try {
            
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
            
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
           //ugasiKonekciju();
        }
        
    }

    protected abstract void preduslovi(Object param)throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DBRepository)broker).uspostaviKonekcijuSaBazom();
    }

    protected abstract void izvrsiOperaciju(Object param, Object kljuc);

    private void potvrdiTransakciju() throws Exception {
        ((DBRepository)broker).potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepository)broker).ponistiTransakciju();
    }

    private void ugasiKonekciju() throws Exception {
        ((DBRepository)broker).raskiniKonekcijuSaBazom();
    }
    
}
