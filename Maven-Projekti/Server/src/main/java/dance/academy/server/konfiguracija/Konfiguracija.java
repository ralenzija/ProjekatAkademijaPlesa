package dance.academy.server.konfiguracija;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Konfiguracija {
    
    private static Konfiguracija instance;
    private Properties konfiguracija;
    private KonfiguracijaJSON jsonKonfiguracija;
    
    private Konfiguracija() {
        ucitajProperties();
        ucitajJSON();
    }
    
    private void ucitajProperties() {
        try {
            konfiguracija = new Properties();
            konfiguracija.load(new FileInputStream("config/config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ucitajJSON() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("config/config.json");
            jsonKonfiguracija = gson.fromJson(reader, KonfiguracijaJSON.class);
            System.out.println("JSON konfiguracija ucitana: " + jsonKonfiguracija);
        } catch (Exception ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Konfiguracija getInstance() {
        if (instance == null)
            instance = new Konfiguracija();
        return instance;
    }
    
    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
    
    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream("config/config.properties"), null);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public KonfiguracijaJSON getJsonKonfiguracija() {
        return jsonKonfiguracija;
    }
    
    public static class KonfiguracijaJSON {
        private String host;
        private String port;
        private String baza;
        private String korisnik;
        private String lozinka;
        
        public String getHost() { return host; }
        public String getPort() { return port; }
        public String getBaza() { return baza; }
        public String getKorisnik() { return korisnik; }
        public String getLozinka() { return lozinka; }
        
        @Override
        public String toString() {
            return "Host=" + host + ", Port=" + port + ", Baza=" + baza;
        }
    }
}