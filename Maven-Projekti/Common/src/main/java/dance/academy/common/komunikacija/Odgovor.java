package dance.academy.common.komunikacija;
import java.io.Serializable;

public class Odgovor implements Serializable {
    
    private Object odgovor;
    private String jsonOdgovor;
    
    public Odgovor() {
    }
    
    public Odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
    public Object getOdgovor() {
        return odgovor;
    }
    
    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
    public String getJsonOdgovor() {
        return jsonOdgovor;
    }
    
    public void setJsonOdgovor(String jsonOdgovor) {
        this.jsonOdgovor = jsonOdgovor;
    }
}