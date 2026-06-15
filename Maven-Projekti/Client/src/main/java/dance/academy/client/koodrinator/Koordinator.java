/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.client.koodrinator;

import dance.academy.client.controller.GlavnaFormaController;
import dance.academy.client.controller.IzabraniUpisController;
import dance.academy.client.controller.IzabraniUcesnikController;
import dance.academy.client.controller.KreirajUcesnikaController;
import dance.academy.client.controller.KreirajUpisController;
import dance.academy.client.controller.LoginController;
import dance.academy.client.controller.ObrisiUcesnikaController;
import dance.academy.client.controller.PretraziUcesnikaController;
import dance.academy.client.controller.PretraziUcesnikaController;
import dance.academy.client.controller.PretraziUpisController;
import dance.academy.client.controller.PromeniUcesnikaController;
import dance.academy.client.controller.UbaciSertifikatController;
import dance.academy.client.forme.GlavnaForma;
import dance.academy.client.forme.IzabraniUpisForma;
import dance.academy.client.forme.IzabraniUcesnikForma;
import dance.academy.client.forme.KreirajUcesnikaForma;
import dance.academy.client.forme.KreirajUpisForma;
import dance.academy.client.forme.LoginForma;
import dance.academy.client.forme.ObrisiUcesnikaForma;
import dance.academy.client.forme.PretraziUcesnikaForma;
import dance.academy.client.forme.PretraziUpisForma;
import dance.academy.client.forme.PromeniUcesnikaForma;
import dance.academy.client.forme.UbaciSertifikatForma;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;

/**
 *
 * @author Korisnik
 */
public class Koordinator {
    
    private static Koordinator instance;
    
    private Instruktor ulogvani;
    
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PretraziUcesnikaController pretraziUcesnikaController;
    private KreirajUcesnikaController kreirajUcesnikaController;
    private UbaciSertifikatController ubaciSertifikatController;
    private ObrisiUcesnikaController obrisiUcesnikaController;
    private PromeniUcesnikaController promeniUcesnikaController;
    private PretraziUpisController pretraziUpisController;
    private KreirajUpisController kreirajUpisController;
    private IzabraniUpisController izabraniUpisController;
    private IzabraniUcesnikController izabraniUcesnikController;
    
    private Koordinator(){
        
    }
    
    public static Koordinator getInstance(){
        if(instance == null)
            instance = new Koordinator();
        return instance;
    }
    
    public Instruktor getUlogvani() {
        return ulogvani;
    }

    public void setUlogvani(Instruktor ulogvani) {
        this.ulogvani = ulogvani;
    }

    public void otvoriLoginFormu() {
        
        loginController = new LoginController(new LoginForma());
        loginController.prikaziFormu();
        
    }

    public void otvoriGlavnuFormu() {
        
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.prikaziFormu();
        
    }
    
    public void zatvoriGF() {
        
        glavnaFormaController.zatvoriFormu();
        
    }

    public void otvoriPretraziUcesnikaFormu() {
        
        pretraziUcesnikaController = new PretraziUcesnikaController(new PretraziUcesnikaForma());
        pretraziUcesnikaController.pripremiFormuZaPretragu();
        pretraziUcesnikaController.prikaziFormu();
        
    }

    public void otvoriKreirajUcesnikaFormu() {
        
        kreirajUcesnikaController = new KreirajUcesnikaController(new KreirajUcesnikaForma());
        kreirajUcesnikaController.prikaziFormu();
        
    }

    public void otvoriUbaciVrstuSertifikatFormu() {
        
        ubaciSertifikatController = new UbaciSertifikatController(new UbaciSertifikatForma());
        ubaciSertifikatController.prikaziFormu();
        
    }

    public void otvoriObrisiUcesnikaFormu() {
        
        obrisiUcesnikaController = new ObrisiUcesnikaController(new ObrisiUcesnikaForma());
        obrisiUcesnikaController.prikaziFormu();
        
    }

    public void otvoriPretraziUcesnikaFormuZaIzmenu() {
        
        pretraziUcesnikaController = new PretraziUcesnikaController(new PretraziUcesnikaForma());
        pretraziUcesnikaController.pripremiFormuZaIzmenu();
        pretraziUcesnikaController.prikaziFormu();
        
    }

    public void otvoriPromeniUcesnikaFormu(Ucesnik selektovani) {
        
        promeniUcesnikaController = new PromeniUcesnikaController(new PromeniUcesnikaForma());
         promeniUcesnikaController.prikaziFormu(selektovani);
        
    }

    public void osveziTabeluUcesnici() {
        
        pretraziUcesnikaController.osveziTabelu();
        
    }

    public void otvoriPretraziUpisFormu() {
        
        pretraziUpisController = new PretraziUpisController(new PretraziUpisForma());
        pretraziUpisController.pripremiFormuZaPretragu();
         pretraziUpisController.prikaziFormu();
        
    }

    public void otvoriKreirajUpisFormu() {
        
        kreirajUpisController = new KreirajUpisController(new KreirajUpisForma());
        kreirajUpisController.pripremiZaKreiranje();
        kreirajUpisController.prikaziFormu();
        
    }

    public void otvoriPretraziUpisFormuZaIzmenu() {
        
        pretraziUpisController = new PretraziUpisController(new PretraziUpisForma());
        pretraziUpisController.pripremiFormuZaIzmenu();
        pretraziUpisController.prikaziFormu();
        
    }

    public void otvoriPromeniUpisFormu(UpisNaProgram selektovana) {
        
        kreirajUpisController = new KreirajUpisController(new KreirajUpisForma());
        kreirajUpisController.pripremiZaIzmenu(selektovana);
        kreirajUpisController.prikaziFormu();
        
    }

    public void otvoriIzabranUpisFormu(UpisNaProgram selektovana) {
        
        izabraniUpisController = new IzabraniUpisController(new IzabraniUpisForma());
        izabraniUpisController.prikaziFormu(selektovana);
        
    }

    public void otvoriIzabraniUcesnikFormu(Ucesnik selektovani) {
        
        izabraniUcesnikController = new IzabraniUcesnikController(new IzabraniUcesnikForma());
        izabraniUcesnikController.prikaziFormu(selektovani);
        
    }

    public void osveziTabeluUpisi() {
        
        pretraziUpisController.osveziTabelu();
        
    }

}
