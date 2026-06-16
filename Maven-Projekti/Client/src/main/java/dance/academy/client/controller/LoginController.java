package dance.academy.client.controller;
import dance.academy.client.forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dance.academy.client.komunikacija.Komunikacija;
import dance.academy.client.koodrinator.Koordinator;
import dance.academy.common.model.Instruktor;

/**
 * Controller klasa za login formu aplikacije.
 * <p>
 * Upravlja procesom prijave instruktora na sistem. Pri kliku na dugme
 * "Login" proverava da li su email i šifra uneti, uspostavlja konekciju
 * sa serverom i šalje zahtev za prijavu. Ako je prijava uspešna,
 * ulogovani instruktor se čuva u {@link Koordinator}-u i otvara se
 * glavna forma aplikacije.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public class LoginController {

    /** Referenca na login formu kojom ovaj controller upravlja. */
    private final LoginForma lf;

    /**
     * Konstruktor koji prima formu i registruje action listenere.
     *
     * @param lf login forma aplikacije
     */
    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    /**
     * Registruje action listener na dugme za prijavu.
     * <p>
     * Proverava da li su email i šifra uneti, uspostavlja konekciju
     * sa serverom i pokušava prijavu. Ako je prijava uspešna, čuva
     * ulogovanog instruktora i otvara glavnu formu. U suprotnom
     * prikazuje poruku o grešci.
     * </p>
     */
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
                Komunikacija.getInstance().konekcija();
                Instruktor i = new Instruktor();
                i.setEmail(email);
                i.setSifra(sifra);
                Instruktor ulogovani = Komunikacija.getInstance().login(i);
                if (ulogovani != null) {
                    JOptionPane.showMessageDialog(lf, "Uspešno ste se ulogovali.");
                    Koordinator.getInstance().setUlogvani(ulogovani);
                    lf.dispose();
                    Koordinator.getInstance().otvoriGlavnuFormu();
                } else {
                    JOptionPane.showMessageDialog(lf, "Pogrešan email ili šifra.");
                }
            }
        });
    }

    /**
     * Prikazuje login formu korisniku.
     */
    public void prikaziFormu() {
        lf.setVisible(true);
    }
}