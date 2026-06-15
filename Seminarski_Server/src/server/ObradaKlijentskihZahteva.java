/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import komunikacija.Odgovor;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.Posiljalac;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ObradaKlijentskihZahteva extends Thread {
    
    Socket socket;
    Primalac receiver;
    Posiljalac sender;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        
        this.socket = socket;
        receiver = new Primalac(socket);
        sender = new Posiljalac(socket);
        
    }

    @Override
    public void run() {
        
        while (!kraj) {
            
            try {
                Zahtev zahtev = (Zahtev) receiver.primi();
                Odgovor odgovor = new Odgovor();
                
                switch (zahtev.getOperacija()) {
                    
                    case LOGIN:
                        
                        Instruktor i = (Instruktor) zahtev.getParametar();
                        i = Controller.getInstance().login(i);
                        odgovor.setOdgovor(i);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_UCESNIKE:
                        
                        List<Ucesnik> lista = Controller.getInstance().ucitajUcesnike();
                        odgovor.setOdgovor(lista);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PRETRAZI_UCESNIKE:
                        
                        Ucesnik u = (Ucesnik) zahtev.getParametar();
                        List<Ucesnik> filtrirani = Controller.getInstance().pretraziUcesnike(u);
                        odgovor.setOdgovor(filtrirani);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_NIVOE:
                        
                        NivoVestine nv = (NivoVestine) zahtev.getParametar();
                        nv = Controller.getInstance().ucitajNivoe(nv);
                        odgovor.setOdgovor(nv);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case KREIRAJ_UCESNIKA:
                        
                        Ucesnik u1 = (Ucesnik) zahtev.getParametar();
                        boolean uspeh1 = Controller.getInstance().kreirajUcesnika(u1);
                        odgovor.setOdgovor(uspeh1);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UBACI_SERTIFIKAT:
                        
                        Sertifikat s = (Sertifikat) zahtev.getParametar();
                        boolean uspeh2 = Controller.getInstance().dodajSertifikat(s);
                        odgovor.setOdgovor(uspeh2);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case OBRISI_UCESNIKA:
                        
                        Ucesnik u2 = (Ucesnik) zahtev.getParametar();
                        boolean uspeh3 = Controller.getInstance().obrisiUcesnika(u2);
                        odgovor.setOdgovor(uspeh3);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case IZMENI_UCESNIKA:
                        
                        Ucesnik u3 = (Ucesnik) zahtev.getParametar();
                        boolean uspeh4 =Controller.getInstance().izmeniUcesnika(u3);
                        odgovor.setOdgovor(uspeh4);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_INSTRUKTORE:
                        
                        List<Instruktor> instruktori = Controller.getInstance().ucitajInstruktore();
                        odgovor.setOdgovor(instruktori);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_UPISE:
                        
                        List<UpisNaProgram> upisi = Controller.getInstance().ucitajUpise();
                        odgovor.setOdgovor(upisi);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PRETRAZI_UPISE:
                        
                        KriterijumPretrage kp = (KriterijumPretrage) zahtev.getParametar();
                        List<UpisNaProgram> filtrirane = Controller.getInstance().pretraziUpise(kp);
                        odgovor.setOdgovor(filtrirane);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_PROGRAME:
                        
                        List<ProgramAktivnosti> programi = Controller.getInstance().ucitajPrograme();
                        odgovor.setOdgovor(programi);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case KREIRAJ_UPIS:
                        
                        UpisNaProgram unp = (UpisNaProgram) zahtev.getParametar();
                        boolean uspeh5 = Controller.getInstance().kreirajUpis(unp);
                        odgovor.setOdgovor(uspeh5);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case IZMENI_UPIS:
                        
                        UpisNaProgram zaIzmenu = (UpisNaProgram) zahtev.getParametar();
                        boolean uspeh6 = Controller.getInstance().izmeniUpis(zaIzmenu);
                        odgovor.setOdgovor(uspeh6);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PROMENI_STATUS_UPISA:
                        
                        UpisNaProgram zaPromenu = (UpisNaProgram) zahtev.getParametar();
                        boolean uspeh7 = Controller.getInstance().promeniStatusUpisa(zaPromenu);
                        odgovor.setOdgovor(uspeh7);
                        sender.posalji(odgovor);
                        break;
                        
                    case PREPORUCI_PROGRAM:
                        
                        ProgramAktivnosti filter = (ProgramAktivnosti) zahtev.getParametar();
                        List<ProgramAktivnosti> preporuceni = Controller.getInstance().preporuciProgram(filter);
                        odgovor.setOdgovor(preporuceni);
                        sender.posalji(odgovor);
                        break;
                        
                    default:
                        System.out.println("Greska, ta operacija ne postoji");
                }
                
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void prekiniNit() throws IOException {
        
        kraj = true;
        socket.close();
        interrupt();
        
    }
    
}
