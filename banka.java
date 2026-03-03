import java.util.Scanner;
import java.util.Random;

public class banka {
    static Scanner sc = new Scanner(System.in);

    static int overeniIntPocetPenez(int pocetPenez){         // metoda pro ověření vstupu typu integer pro počet peněz
        while (true) {
            if (sc.hasNextInt()){
                pocetPenez = sc.nextInt();
                break;
            } else {
                System.out.println("Neplatný vstup");
                sc.next();
            }
        }
        return pocetPenez;
    }

    static void zadaniPinu(){           // metoda pro ověření PIN kódem
        int pin = 1234;
        int pinInput = 0;
        while(pinInput!=pin){
            System.out.print("Zadejte PIN: ");
            while (true) {
                if (sc.hasNextInt()){
                    pinInput = sc.nextInt();
                    break;
                } else {
                    System.out.println("Neplatný vstup");
                    sc.next();
                }
            }
            if(pinInput!=pin){
                 System.out.println("NESPRÁVNÝ PIN");
            } else {
                break;
            }
        }
    }

    static int vyberPenez(int zustatek){            // metoda pro vybrání peněz
        int pocetPenez = 1;
        while(pocetPenez != 0){
            System.out.print("Zadejte, kolik peněz chcete vybrat (pro návrat do hlavní nabídky zadejte 0): ");
            pocetPenez = overeniIntPocetPenez(pocetPenez);
            if (pocetPenez > zustatek){
                System.out.println("Nedostatečný zůstatek");
            } else if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            } else {
                zadaniPinu();
                zustatek -= pocetPenez;
                System.out.println("Vybral/a jste "+pocetPenez+" Kč.");
            }
        }
        return zustatek;
    }

    static int vkladPenez(int zustatek){            // metoda pro vklad peněz
        int pocetPenez = 1;
        while(pocetPenez != 0){
            System.out.print("Zadejte, kolik peněz chcete vložit (pro návrat do hlavní nabídky zadejte 0): ");
            pocetPenez = overeniIntPocetPenez(pocetPenez);
            if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            }  else {
                zadaniPinu();
                zustatek += pocetPenez;
                System.out.println("Vložil/a jste "+pocetPenez+" Kč.");
            }
        }
        return zustatek;
    }

    static int poslatPenize(int zustatek){          // metoda pro poslání peněz jiné osobě
        int pocetPenez = 1;
        int vyberOsoby = 0;
        boolean pokracovat = false;

        System.out.println("---------------SEZNAM-------------");
        System.out.println("1. Petr Novák");
        System.out.println("2. Pavel Svoboda");
        System.out.println("3. Jitka Novotná");
        System.out.println("---------------------------------");
            
        while(pocetPenez != 0){
            
            while(pokracovat == false){
                System.out.print("Zadejte, komu chcete poslat peníze: ");
                while (true) {
                    if (sc.hasNextInt()){
                        vyberOsoby = sc.nextInt();
                        break;
                    } else {
                        System.out.println("Neplatný vstup");
                        sc.next();
                    }
                }
                if(vyberOsoby == 1 || vyberOsoby == 2 || vyberOsoby == 3){
                    pokracovat = true;
                } else {
                    System.out.println("Neplatná osoba");
                }
            }
            
            System.out.print("Zadejte, kolik peněz chcete poslat (pro návrat do hlavní nabídky zadejte 0): ");
            pocetPenez = overeniIntPocetPenez(pocetPenez);
            if(pocetPenez > zustatek){
                System.out.println("Nedostatečný zůstatek");
            } else if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            } else {
                zadaniPinu();
                zustatek -= pocetPenez;
                switch (vyberOsoby) {
                    case 1:
                        System.out.println("Poslal/a jste "+pocetPenez+" Kč Petru Novákovi");
                        break;
                    case 2:
                        System.out.println("Poslal/a jste "+pocetPenez+" Kč Pavlu Svobodovi");
                        break;
                    case 3:
                        System.out.println("Poslal/a jste "+pocetPenez+" Kč Jitce Novotné");
                        break;
                }
            }
        }
        return zustatek;
    }

    public static void main(String[] args) {            // hlavní program
        Random r = new Random();
        
        System.out.println("-------------PŘIHLÁŠENÍ-------------");
        
        zadaniPinu();
        
        System.out.println("             VÍTEJTE             ");
        
        int min = 5000, max = 5000000;
        int zustatek = r.nextInt((max-min)+min);
        
        int volba = 0;

        while(volba!=4){
            System.out.println("---------DOMOVSKÁ STRÁNKA---------");
            System.out.println("Váš zůstatek: "+String.format("%,d",zustatek)+" Kč");
            System.out.println("Vyberte si akci:");
            System.out.println("1. Výběr peněz");
            System.out.println("2. Vložit peníze");
            System.out.println("3. Poslat peníze");
            System.out.println("4. Odhlásit se");
            System.out.println("---------------------------------");
            System.out.print("Vaše volba: ");

            while (true) {
                if (sc.hasNextInt()){
                    volba = sc.nextInt();
                    break;
                } else {
                    System.out.println("Neplatný vstup");
                    sc.next();
                }
            }

            switch (volba) {
                case 1:
                    zustatek = vyberPenez(zustatek);
                    break;
                case 2:
                    zustatek = vkladPenez(zustatek);
                    break;
                case 3:
                    zustatek = poslatPenize(zustatek);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
        }

        System.out.println("Nashledanou :)");

        sc.close();
    }   
}