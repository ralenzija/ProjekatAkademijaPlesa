/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dance.academy.client.forme.model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import dance.academy.common.model.Ucesnik;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleUcesnik extends AbstractTableModel {
    
    List<Ucesnik> lista;
    String[] kolone = {"ID", "Ime i prezime", "Email", "Nivo vestine","Telefon","Datum Rodjenja"};
    private final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public ModelTabeleUcesnik(List<Ucesnik> lista) {
        this.lista = lista;
    }

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
        
        Ucesnik u = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return u.getId();
            case 1:
                return u.getIme() + " " + u.getPrezime();
            case 2:
                return u.getEmail();
            case 3:
                return u.getNivo().getNivo() + " - " + u.getNivo().getVrsta();
            case 4:
                return u.getTelefon();
            case 5:
                return u.getDatumRodjenja().format(FMT);
            default:
                return "NA";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Ucesnik> getLista() {
        return lista;
    }
    
    
    
}
