/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author Korisnik
 */
public interface Repository<T> {
    
    List<T> getAll(T param, String uslov) throws Exception;
    int add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param, String uslov) throws Exception;
    List<T> getAll();
    
}
