/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probaprojekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a
 */
public class Vezerles {

    private final double KEDVEZMENY_SZAZALEK = 10;
    private final String RENDEZVENY_ELERES = "rendezvenyek.txt";
    private final String RESZTVEVOK_ELERES = "resztvevok.txt";    
    
    private List<Rendezveny> rendezvenyek = new ArrayList<>();
    private List<Resztvevo> resztvevok = new ArrayList<>();

    public Vezerles() {
    }

    void start() {
        adatBevitel();
        adatKiiras();
        jubileum();
        eredmenyKiiras();
    }

//    private void adatBevitel() {
//        Scanner scanner = new Scanner(System.in);
//        String cim, idoPont, nev;
//        int ar;
//        
//        System.out.print("A rendezvény címe: ");
//        cim = scanner.nextLine();
//        System.out.print("időpontja: ");
//        idoPont = scanner.nextLine();        
//        System.out.print("ára: ");
//        ar = scanner.nextInt();        
//        scanner.nextLine(); // a nextInt() nem olvassa be a sorvégjelet.
//        //  https://stackoverflow.com/questions/32798803/understanding-scanners-nextline-next-and-nextint-methods
//        rendezveny = new Rendezveny(cim, idoPont, ar);     
//
//        System.out.print("A résztvevő neve: ");
//        nev = scanner.nextLine();        
//        resztvevo1 = new Resztvevo(nev);
//        
//        PTEsResztvevo.setKedvezmenySzazalek(KEDVEZMENY_SZAZALEK);
//        System.out.print("A PTE-s résztvevő neve: ");
//        nev = scanner.nextLine();        
//        resztvevo2 = new PTEsResztvevo(nev, "111");
//    }
//
//    private void adatKiiras() {
//        System.out.println("A rendezvény: ");
//        System.out.println(rendezveny);
//        System.out.println("A résztvevő:");
//        System.out.println(resztvevo1);
//        
//        System.out.println("részvételi díjak: ");
//        System.out.println(resztvevo1.reszveteliDij(rendezveny) + " " + resztvevo2.reszveteliDij(rendezveny));
//    }
    private void adatBevitel() {
        PTEsResztvevo.setKedvezmenySzazalek(KEDVEZMENY_SZAZALEK);

        try {
            Scanner fajlScanner = new Scanner(new File(RENDEZVENY_ELERES));
            String cim, idoPont, nev;
            int ar;
            String sor, adatok[];
            Rendezveny rendezveny;
            while (fajlScanner.hasNextLine()) {
                sor = fajlScanner.nextLine();
                adatok = sor.split(";");
                cim = adatok[0];
                idoPont = adatok[1];
                ar = Integer.parseInt(adatok[2]);
                rendezveny = new Rendezveny(cim, idoPont, ar);
                rendezvenyek.add(rendezveny);
            }
            fajlScanner.close();

            fajlScanner = new Scanner(new File(RESZTVEVOK_ELERES));
            Resztvevo resztvevo = null;
            while (fajlScanner.hasNextLine()) {
                sor = fajlScanner.nextLine();
                adatok = sor.split(";");
                if(adatok.length == 1) resztvevo = new Resztvevo(adatok[0]);
                if(adatok.length == 2) resztvevo = new PTEsResztvevo(adatok[0], adatok[1]);
                resztvevo.setPenz(10000);
                resztvevok.add(resztvevo);
            }
            fajlScanner.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vezerles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void adatKiiras() {
        System.out.println("A rendezvények: ");
        for (Rendezveny rendezveny : rendezvenyek) {
            System.out.println(rendezveny);
        }
        System.out.println("\nA résztvevők:");
        for (Resztvevo resztvevo : resztvevok) {
            System.out.println(resztvevo);
        }
    }

    private void jubileum() {
        for (Rendezveny rendezveny : rendezvenyek) {
            for (Resztvevo resztvevo : resztvevok) {
                rendezveny.resztVesz(resztvevo);
            }
        }
    }

    private void eredmenyKiiras() {
        System.out.println("\nÖsszesítés: ");
        for (Rendezveny rendezveny : rendezvenyek) {
            System.out.printf("%10s, %3d résztvevő, %5d Ft bevétel \n",
                    rendezveny.getCim(),
                    rendezveny.getResztvevokSzama(),
                    rendezveny.getBevetel());
        }
        
        for (Resztvevo resztvevo : resztvevok) {
            System.out.println("\n" + resztvevo + "ezeken a rendezvényeken vett részt:");
            for (Rendezveny rendezveny : resztvevo.getRendezvenyek()) {
                System.out.println(rendezveny);
            }
        }
    }
}
