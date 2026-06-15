/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.IzabraniUpisForma;
import forme.model.ModelTabeleStavkaUpisa;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import komunikacija.Komunikacija;
import model.Instruktor;
import model.Ucesnik;
import model.UpisNaProgram;
import model.StavkaUpisa;

/**
 *
 * @author Korisnik
 */
public class IzabraniUpisController {
    
    private final IzabraniUpisForma iuf;
    
    public IzabraniUpisController(IzabraniUpisForma iuf) {
        this.iuf = iuf;
    }
    
    public void prikaziFormu(UpisNaProgram selektovana) {
        pripremiFormu(selektovana);
        iuf.setVisible(true);
    }

    private void pripremiFormu(UpisNaProgram selektovana) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datum = sdf.format(selektovana.getDatum());
        
        //Combo box Instruktori
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            iuf.getjComboBoxInstruktor().addItem(i);
        }

        //Combo box Polaznici
        Komunikacija.getInstance().konekcija();
        List<Ucesnik> ucesnici = Komunikacija.getInstance().ucitajUcesnike();
        for (Ucesnik u : ucesnici) {
            iuf.getjComboBoxUcesnik().addItem(u);
        }
        
        iuf.getjTextFieldDatum().setText(datum);
        iuf.getjComboBoxInstruktor().setSelectedItem(selektovana.getInstruktor());
        iuf.getjComboBoxUcesnik().setSelectedItem(selektovana.getUcesnik());
        iuf.getjComboBoxStatus().setSelectedItem(selektovana.getStatus());
        iuf.getjTextFieldUkupanIznos().setText(selektovana.getUkupanIznos()+"");
        
        //Popunjavanje tabele stavkama
        List<StavkaUpisa> sveStavke = selektovana.getStavke();
        ModelTabeleStavkaUpisa mtsp = new ModelTabeleStavkaUpisa(sveStavke);
        iuf.getjTable1().setModel(mtsp);
        
        iuf.getjTextFieldUkupanIznos().setEnabled(false);
        iuf.getjTextFieldDatum().setEnabled(false);
        iuf.getjComboBoxInstruktor().setEnabled(false);
        iuf.getjComboBoxUcesnik().setEnabled(false);
        iuf.getjComboBoxStatus().setEnabled(false);
        
    }
    
}
