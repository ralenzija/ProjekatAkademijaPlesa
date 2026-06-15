/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.server.operacije;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dance.academy.common.model.PlesniStil;
import dance.academy.common.model.ProgramAktivnosti;
/**
 *
 * @author HP ProBook 440 G5
 */
public class PreporuciProgramSO extends ApstraktnaGenerickaOperacija{

    
    private List<ProgramAktivnosti> programi = new ArrayList<>();

    @Override
    protected void preduslovi(Object param) throws Exception {

        if (!(param instanceof ProgramAktivnosti pa) || pa.getVrsta() == null) {
            throw new Exception("Nedovoljan parametar za preporuku programa (vrsta je obavezna).");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        ProgramAktivnosti pa = (ProgramAktivnosti) param;

        String uslov = " WHERE program_aktivnosti.vrsta='" + pa.getVrsta().name() + "'";
        if (pa.isAktivan()) {
            uslov += " AND program_aktivnosti.aktivan=1";
        }
        uslov += " ORDER BY program_aktivnosti.cena ASC";

        try {
            programi = broker.getAll(new ProgramAktivnosti(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(PreporuciProgramSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ProgramAktivnosti> getPrograme() {
        return programi;
    }
    
}
