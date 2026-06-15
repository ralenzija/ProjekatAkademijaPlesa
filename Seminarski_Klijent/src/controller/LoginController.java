/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Instruktor;

/**
 *
 * @author Korisnik
 */
public class LoginController {
    
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        String email = lf.getjTextFieldEmail().getText().trim();
        String sifra = new String(lf.getjPasswordField1().getPassword()).trim();

        if (email.isEmpty() || sifra.isEmpty()) {
            JOptionPane.showMessageDialog(lf, "Unesite email i sifru!");
            return;
        }

        // konekcija i login
        Komunikacija.getInstance().konekcija();

        model.Instruktor i = new model.Instruktor();
        i.setEmail(email);
        i.setSifra(sifra);

        model.Instruktor ulogovani = Komunikacija.getInstance().login(i);

        if (ulogovani != null) {
            JOptionPane.showMessageDialog(lf, "Uspešno ste se ulogovali.");
            koodrinator.Koordinator.getInstance().setUlogvani(ulogovani);
            lf.dispose();
            koodrinator.Koordinator.getInstance().otvoriGlavnuFormu();
        } else {
            JOptionPane.showMessageDialog(lf, "Pogrešan email ili šifra.");
        }
    }
});
        
    }

    public void prikaziFormu() {
        
        lf.setVisible(true);
        
    }
    
    
    
}
