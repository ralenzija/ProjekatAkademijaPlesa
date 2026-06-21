package dance.academy.server.server;

import dance.academy.server.controller.Controller;
import java.io.IOException;
import dance.academy.common.komunikacija.Odgovor;
import dance.academy.common.komunikacija.Primalac;
import dance.academy.common.komunikacija.Zahtev;
import dance.academy.common.komunikacija.Posiljalac;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.Instruktor;
import dance.academy.common.model.ProgramAktivnosti;
import dance.academy.common.model.NivoVestine;
import dance.academy.common.model.Ucesnik;
import dance.academy.common.model.UpisNaProgram;
import dance.academy.common.model.Sertifikat;
import dance.academy.common.model.StavkaUpisa;
import dance.academy.common.model.kriterijum.KriterijumPretrage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

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

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class,
                                (JsonSerializer<LocalDate>) (src, typeOfSrc, context)
                                -> new JsonPrimitive(src.toString()))
                        .registerTypeAdapter(LocalDate.class,
                                (JsonDeserializer<LocalDate>) (json, typeOfT, context)
                                -> LocalDate.parse(json.getAsString()))
                        .registerTypeAdapter(LocalTime.class,
                                (JsonSerializer<LocalTime>) (src, typeOfSrc, context)
                                -> new JsonPrimitive(src.toString()))
                        .registerTypeAdapter(LocalTime.class,
                                (JsonDeserializer<LocalTime>) (json, typeOfT, context)
                                -> LocalTime.parse(json.getAsString()))
                        .create();

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
                        odgovor.setJsonOdgovor(gson.toJson(lista));
                        sender.posalji(odgovor);
                        break;

                    case PRETRAZI_UCESNIKE:
                        Ucesnik u = (Ucesnik) zahtev.getParametar();
                        List<Ucesnik> filtrirani = Controller.getInstance().pretraziUcesnike(u);
                        odgovor.setOdgovor(filtrirani);
                        odgovor.setJsonOdgovor(gson.toJson(filtrirani));
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
                        boolean uspeh4 = Controller.getInstance().izmeniUcesnika(u3);
                        odgovor.setOdgovor(uspeh4);
                        sender.posalji(odgovor);
                        break;

                    case UCITAJ_INSTRUKTORE:
                        List<Instruktor> instruktori = Controller.getInstance().ucitajInstruktore();
                        odgovor.setOdgovor(instruktori);
                        odgovor.setJsonOdgovor(gson.toJson(instruktori));
                        sender.posalji(odgovor);
                        break;

                    case UCITAJ_UPISE:
                        List<UpisNaProgram> upisi = Controller.getInstance().ucitajUpise();
                        for (UpisNaProgram unp2 : upisi) {
                            if (unp2.getStavke() != null) {
                                for (StavkaUpisa stavka : unp2.getStavke()) {
                                    stavka.setUpis(null);
                                }
                            }
                        }
                        odgovor.setOdgovor(upisi);
                        odgovor.setJsonOdgovor(gson.toJson(upisi));
                        sender.posalji(odgovor);
                        break;

                    case PRETRAZI_UPISE:
                        KriterijumPretrage kp = (KriterijumPretrage) zahtev.getParametar();
                        List<UpisNaProgram> filtrirane = Controller.getInstance().pretraziUpise(kp);
                        for (UpisNaProgram unp2 : filtrirane) {
                            if (unp2.getStavke() != null) {
                                for (StavkaUpisa stavka : unp2.getStavke()) {
                                    stavka.setUpis(null);
                                }
                            }
                        }
                        odgovor.setOdgovor(filtrirane);
                        odgovor.setJsonOdgovor(gson.toJson(filtrirane));
                        sender.posalji(odgovor);
                        break;

                    case UCITAJ_PROGRAME:
                        List<ProgramAktivnosti> programi = Controller.getInstance().ucitajPrograme();
                        odgovor.setOdgovor(programi);
                        odgovor.setJsonOdgovor(gson.toJson(programi));
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
                        odgovor.setJsonOdgovor(gson.toJson(preporuceni));
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