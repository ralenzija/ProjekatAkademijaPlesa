/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Server extends Thread {
    
    boolean kraj = false;
    ServerSocket serverSocket;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();

    @Override
    public void run() {
        
        try {
            
            serverSocket = new ServerSocket(9000);
            
            while (!kraj) {
            
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
            
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void zaustaviServer() throws IOException {
        
        kraj = true;
        
        for (ObradaKlijentskihZahteva k : klijenti) {
            k.prekiniNit();
        }
        
        serverSocket.close();
        
    }
    
}
