/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.UpisNaProgram;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleUpis extends AbstractTableModel {
    
    List<UpisNaProgram> lista;
    String[] kolone = {"ID", "Datum", "Status", "Ukupan iznos", "Instruktor", "Ucesnik"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UpisNaProgram u = lista.get(rowIndex);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datumFormatiran = sdf.format(u.getDatum());
        
        switch (columnIndex) {
            case 0:
                return u.getId();
            case 1:
                return datumFormatiran;
            case 2:
                return u.getStatus();
            case 3:
                return u.getUkupanIznos();
            case 4:
                return u.getInstruktor().getIme() + " " + u.getInstruktor().getPrezime();
            case 5:
                return u.getUcesnik().getIme()+ " " + u.getUcesnik().getPrezime();
            default:
                return "N/A";
        }
    }

    public ModelTabeleUpis(List<UpisNaProgram> lista) {
        lista.sort(Comparator.comparing(u -> ((UpisNaProgram) u).getId()));
        this.lista = lista;
    }

    public List<UpisNaProgram> getLista() {
        return lista;
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
}
