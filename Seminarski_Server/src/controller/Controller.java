/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Instruktor;
import model.ProgramAktivnosti;
import model.NivoVestine;
import model.Ucesnik;
import model.UpisNaProgram;
import model.Sertifikat;
import model.kriterijum.KriterijumPretrage;
import operacije.KreirajUcesnikaSO;
import operacije.KreirajUpisSO;
import operacije.LoginSO;
import operacije.ObrisiUcesnikaSO;
import operacije.PretraziUcesnikeSO;
import operacije.PretraziUpiseSO;
import operacije.IzmeniUcesnikaSO;
import operacije.IzmeniUpisSO;
import operacije.PreporuciProgramSO;
import operacije.PromeniStatusUpisaSO;
import operacije.UcitajNivoeSO;
import operacije.UbaciSertifikatSO;
import operacije.UcitajInstruktoreSO;
import operacije.UcitajProgrameSO;
import operacije.UcitajUcesnikeSO;
import operacije.UcitajUpiseSO;

/**
 *
 * @author Korisnik
 */
public class Controller {
    
    private static Controller instance;
    
    private Controller(){
        
    }
    
    public static Controller getInstance(){
        if(instance == null)
            instance=new Controller();
        return instance;
    }

    public Instruktor login(Instruktor i) throws Exception {
        
        LoginSO operacija = new LoginSO();
        operacija.izvrsi(i, null);
        System.out.println("Klasa Controller: " + operacija.getI());
        return operacija.getI();
        
    }

    public List<Ucesnik> ucitajUcesnike() throws Exception {
        
        UcitajUcesnikeSO operacija = new UcitajUcesnikeSO();
        operacija.izvrsi(new Ucesnik(), null);
        System.out.println("Klasa Controller: " + operacija.getUcesnici());
        return operacija.getUcesnici();
        
    }

    public List<Ucesnik> pretraziUcesnike(Ucesnik u) throws Exception {
        
        PretraziUcesnikeSO operacija = new PretraziUcesnikeSO();
        operacija.izvrsi(u, null);
        System.out.println("Klasa Controller: " + operacija.getUcesnici());
        return operacija.getUcesnici();
        
    }

    public NivoVestine ucitajNivoe(NivoVestine nv) throws Exception {
        
        UcitajNivoeSO operacija = new UcitajNivoeSO();
        operacija.izvrsi(nv, null);
        System.out.println("Klasa Controller: " + operacija.getNivo());
        return operacija.getNivo();
        
    }

    public boolean kreirajUcesnika(Ucesnik u) {
        
        try {
        
            KreirajUcesnikaSO operacija = new KreirajUcesnikaSO();
            operacija.izvrsi(u, null);
            System.out.println("Klasa Controller: " + operacija.getUcesnik());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean dodajSertifikat(Sertifikat s) {
        
        try {
        
            UbaciSertifikatSO operacija = new UbaciSertifikatSO();
            operacija.izvrsi(s, null);
            System.out.println("Klasa Controller: " + operacija.getS());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean obrisiUcesnika(Ucesnik u2) {
        
        try {
        
            ObrisiUcesnikaSO operacija = new ObrisiUcesnikaSO();
            operacija.izvrsi(u2, null);
            System.out.println("Klasa Controller: " + operacija.getU());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean izmeniUcesnika(Ucesnik u3) {
        
        try {
        
            IzmeniUcesnikaSO operacija = new IzmeniUcesnikaSO();
            operacija.izvrsi(u3, null);
            System.out.println("Klasa Controller: " + operacija.getU());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public List<Instruktor> ucitajInstruktore() throws Exception {
        
        UcitajInstruktoreSO operacija = new UcitajInstruktoreSO();
        operacija.izvrsi(new Instruktor(), null);
        System.out.println("Klasa Controller: " + operacija.getInstruktori());
        return operacija.getInstruktori();
        
    }

    public List<UpisNaProgram> ucitajUpise() throws Exception {
        
        UcitajUpiseSO operacija = new UcitajUpiseSO();
        operacija.izvrsi(new UpisNaProgram(), null);
        System.out.println("Klasa Controller: " + operacija.getUpise());
        return operacija.getUpise();
        
    }

    public List<UpisNaProgram> pretraziUpise(KriterijumPretrage kp) throws Exception {
        
        PretraziUpiseSO operacija = new PretraziUpiseSO();
        operacija.izvrsi(new UpisNaProgram(), kp);
        System.out.println("Klasa Controller: " + operacija.getUpise());
        return operacija.getUpise();
        
    }

    public List<ProgramAktivnosti> ucitajPrograme() throws Exception {
        
        UcitajProgrameSO operacija = new UcitajProgrameSO();
        operacija.izvrsi(new ProgramAktivnosti(), null);
        System.out.println("Klasa Controller: " + operacija.getProgrami());
        return operacija.getProgrami();
        
    }

    public boolean kreirajUpis(UpisNaProgram unp) {
        
        try {
        
            KreirajUpisSO operacija = new KreirajUpisSO();
            operacija.izvrsi(unp, null);
            System.out.println("Klasa Controller: " + operacija.getUpis());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean izmeniUpis(UpisNaProgram zaIzmenu) {
        
        try {
        
            IzmeniUpisSO operacija = new IzmeniUpisSO();
            operacija.izvrsi(zaIzmenu, null);
            System.out.println("Klasa Controller: " + operacija.getUnp());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean promeniStatusUpisa(UpisNaProgram upisSaNovimStatusom) {
        try {
            PromeniStatusUpisaSO operacija = new PromeniStatusUpisaSO();
            operacija.izvrsi(upisSaNovimStatusom, null);
            System.out.println("Klasa Controller (promeniStatusUpisa): " + operacija.getUpis());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<ProgramAktivnosti> preporuciProgram(ProgramAktivnosti program) throws Exception {
        PreporuciProgramSO operacija = new PreporuciProgramSO();
        operacija.izvrsi(program, null);
        System.out.println("Klasa Controller (preporuciProgram): " + operacija.getPrograme());
        return operacija.getPrograme();
    }

    
}
