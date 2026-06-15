/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaUpisa;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleStavkaUpisa extends AbstractTableModel {
    
    List<StavkaUpisa> lista;
    String[] kolone = {"Redni broj", "Naziv programa", "Iznos"};

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
        StavkaUpisa s = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return s.getRb();
            case 1:
                return s.getProgram().getNaziv();
            case 2:
                return s.getIznos();
            default:
                return "N/A";
        }
    }

    public ModelTabeleStavkaUpisa(List<StavkaUpisa> lista) {
        this.lista = lista;
    }

    public List<StavkaUpisa> getLista() {
        return lista;
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
}

