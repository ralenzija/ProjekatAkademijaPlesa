/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.client.komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.Sertifikat;
import dance.academy.common.model.kriterijum.KriterijumPretrage;
import dance.academy.common.komunikacija.Zahtev;
import dance.academy.common.komunikacija.Odgovor;
import dance.academy.common.komunikacija.Operacija;
import dance.academy.common.komunikacija.Posiljalac;
import dance.academy.common.komunikacija.Primalac;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

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
        if (instance == null) {
            instance = new Komunikacija();
        }
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

        Zahtev z = new Zahtev(Operacija.UCITAJ_UCESNIKE, null);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ucesnik>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);

    }

    public List<Ucesnik> pretraziUcesnike(Ucesnik u) {

        Zahtev z = new Zahtev(Operacija.PRETRAZI_UCESNIKE, u);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ucesnik>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);

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

        Zahtev z = new Zahtev(Operacija.KREIRAJ_UCESNIKA, u);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }

    }

    public void dodajSertifikat(Sertifikat s) {

        Zahtev z = new Zahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }

    }

    public void obrisiUcesnika(Ucesnik selektovani) {

        Zahtev z = new Zahtev(Operacija.OBRISI_UCESNIKA, selektovani);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }

    }

    public void promeniUcesnika(Ucesnik uc) {

        NivoVestine nv = pronadjiNivoVestine(uc.getNivo());
        uc.setNivo(nv);

        Zahtev z = new Zahtev(Operacija.IZMENI_UCESNIKA, uc);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }

    }

    public List<Instruktor> ucitajInstruktore() {

        Zahtev z = new Zahtev(Operacija.UCITAJ_INSTRUKTORE, null);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Instruktor>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);

    }

    public List<UpisNaProgram> ucitajUpise() {

        Zahtev z = new Zahtev(Operacija.UCITAJ_UPISE, null);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<UpisNaProgram>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);

    }

    public List<UpisNaProgram> pretraziUpise(KriterijumPretrage kp) {

        Zahtev z = new Zahtev(Operacija.PRETRAZI_UPISE, kp);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<UpisNaProgram>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);

    }

    public List<ProgramAktivnosti> ucitajPrograme() {

        Zahtev z = new Zahtev(Operacija.UCITAJ_PROGRAME, null);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProgramAktivnosti>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);

    }

    public void kreirajUpis(UpisNaProgram unp) {

        Zahtev z = new Zahtev(Operacija.KREIRAJ_UPIS, unp);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }

    }

    public void izmeniUpis(UpisNaProgram zaIzmenu) {

        Zahtev z = new Zahtev(Operacija.IZMENI_UPIS, zaIzmenu);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();

        if (uspeh) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Neuspeh");
        }

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

        Zahtev z = new Zahtev(Operacija.PREPORUCI_PROGRAM, filter);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProgramAktivnosti>>() {
        }.getType();
        return gson.fromJson(o.getJsonOdgovor(), type);
    }

}
