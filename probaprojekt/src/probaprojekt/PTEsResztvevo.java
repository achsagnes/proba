/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probaprojekt;

import java.util.logging.Logger;

/**
 *
 * @author a
 */
public class PTEsResztvevo extends Resztvevo{
    
    private String pteAzonosito;
    private static double kedvezmenySzazalek;

    public PTEsResztvevo(String nev, String pteAzonosito) {
        super(nev);
        this.pteAzonosito = pteAzonosito;
    }

    @Override
    protected int reszveteliDij(Rendezveny rendezveny) {
        return (int) (super.reszveteliDij(rendezveny)* (1-kedvezmenySzazalek/100)); 
    }

    @Override
    public String toString() {
        return super.toString() + " azonosítója: " + this.pteAzonosito; 
    }
 
    public String getPteAzonosito() {
        return pteAzonosito;
    }

    public static double getKedvezmenySzazalek() {
        return kedvezmenySzazalek;
    }

    public static void setKedvezmenySzazalek(double kedvezmenySzazalek) {
        PTEsResztvevo.kedvezmenySzazalek = kedvezmenySzazalek;
    }   
    
}
