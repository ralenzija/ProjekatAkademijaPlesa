/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
import model.ProgramAktivnosti;
import model.NivoVestine;
import model.Ucesnik;
import model.UpisNaProgram;
import model.Sertifikat;
import model.kriterijum.KriterijumPretrage;

/**
 *
 * @author Korisnik
 */
public class Komunikacija {
    
    private Socket socket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
        
    }

    public static Komunikacija getInstance() {
        if (instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    public void konekcija() {
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Greska u klasi Komunikacija, fja konekcija");
        }
    }

    public Instruktor login(Instruktor i) {
        
        Zahtev z = new Zahtev(Operacija.LOGIN, i);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        i = (Instruktor) o.getOdgovor();
        
        return i;
        
    }

    public List<Ucesnik> ucitajUcesnike() {
        
        List<Ucesnik> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_UCESNIKE, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<Ucesnik>) o.getOdgovor();
        
        return lista;
        
    }

    public List<Ucesnik> pretraziUcesnike(Ucesnik u) {
        
        List<Ucesnik> filtrirani = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.PRETRAZI_UCESNIKE, u);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        filtrirani = (List<Ucesnik>) o.getOdgovor();
        
        return filtrirani;
        
    }
    
    public NivoVestine pronadjiNivoVestine(NivoVestine nv) {
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_NIVOE, nv);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        nv = (NivoVestine) o.getOdgovor();
        
        return nv;
        
    }

    public void kreirajUcesnika(Ucesnik u) {
        
        NivoVestine nv = pronadjiNivoVestine(u.getNivo());
        u.setNivo(nv);
        
        List<Ucesnik> lista = ucitajUcesnike();
        
        Zahtev z = new Zahtev(Operacija.KREIRAJ_UCESNIKA, u);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void dodajSertifikat(Sertifikat s) {
        
        Zahtev z = new Zahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void obrisiUcesnika(Ucesnik selektovani) {
        
        Zahtev z = new Zahtev(Operacija.OBRISI_UCESNIKA, selektovani);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void promeniUcesnika(Ucesnik uc) {
        
        NivoVestine nv = pronadjiNivoVestine(uc.getNivo());
        uc.setNivo(nv);
        
        Zahtev z = new Zahtev(Operacija.IZMENI_UCESNIKA, uc);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public List<Instruktor> ucitajInstruktore() {
        
        List<Instruktor> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_INSTRUKTORE, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<Instruktor>) o.getOdgovor();
        
        return lista;
        
    }

    public List<UpisNaProgram> ucitajUpise() {
        
        List<UpisNaProgram> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_UPISE, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<UpisNaProgram>) o.getOdgovor();
        
        return lista;
        
    }

    public List<UpisNaProgram> pretraziUpise(KriterijumPretrage kp) {
        
        List<UpisNaProgram> filtrirani = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.PRETRAZI_UPISE, kp);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        filtrirani = (List<UpisNaProgram>) o.getOdgovor();
        
        return filtrirani;
        
    }

    public List<ProgramAktivnosti> ucitajPrograme() {
        
        List<ProgramAktivnosti> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_PROGRAME, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<ProgramAktivnosti>) o.getOdgovor();
        
        return lista;
        
    }

    public void kreirajUpis(UpisNaProgram unp) {
        
        Zahtev z = new Zahtev(Operacija.KREIRAJ_UPIS, unp);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void izmeniUpis(UpisNaProgram zaIzmenu) {
        
        Zahtev z = new Zahtev(Operacija.IZMENI_UPIS, zaIzmenu);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void promeniStatusUpisa(UpisNaProgram up) {
        Zahtev z = new Zahtev(Operacija.PROMENI_STATUS_UPISA, up);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }
    }
    
    public List<ProgramAktivnosti> preporuciProgram(ProgramAktivnosti filter) {

        List<ProgramAktivnosti> lista = new ArrayList<>();

        Zahtev z = new Zahtev(Operacija.PREPORUCI_PROGRAM, filter);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<ProgramAktivnosti>) o.getOdgovor();

        return lista;
    }
   

}
