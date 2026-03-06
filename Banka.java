import java.util.Scanner;

public class Banka {
    static Scanner sc = new Scanner(System.in);

    static int vytvoreniPinu(int pin){          // metoda - vytvoření Pinu (použito jednou na začátku programu)
        System.out.println("Vytvořte si 4-místný PIN");
        while (true) {
            if (sc.hasNextInt()){
                pin = sc.nextInt();
                if(String.valueOf(Math.abs(pin)).length()==4){
                    break;
                } else {
                    System.out.println("Neplatná délka PINu");
                }
            } else {
                System.out.println("Neplatný vstup");
                sc.next();
            }
        }
        return pin;
    }

    static int overeniIntPocetPenez(int pocetPenez){         // metoda - ověření vstupu typu integer pro počet peněz
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

    static void zadaniPinu(int pin){           // metoda - ověření PIN kódem
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

    static int vyberPenez(int zustatek, int pin){            // metoda - vybrání peněz
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
                zadaniPinu(pin);
                zustatek -= pocetPenez;
                System.out.println("Vybral/a jste "+pocetPenez+" Kč.");
            }
        }
        return zustatek;
    }

    static int vkladPenez(int zustatek, int pin){            // metoda - vklad peněz
        int pocetPenez = 1;
        while(pocetPenez != 0){
            System.out.print("Zadejte, kolik peněz chcete vložit (pro návrat do hlavní nabídky zadejte 0): ");
            pocetPenez = overeniIntPocetPenez(pocetPenez);
            if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            }  else {
                zadaniPinu(pin);
                zustatek += pocetPenez;
                System.out.println("Vložil/a jste "+pocetPenez+" Kč.");
            }
        }
        return zustatek;
    }

    static int poslatPenize(int zustatek,Ucet Devizovy,Ucet Sporici,Ucet Investicni,int pin){          // metoda - poslání peněz jiné osobě
        int pocetPenez = 1;
        int vyberUctu = 0;
        boolean pokracovat = false;

        System.out.println("---------------SEZNAM-------------");
        System.out.println("1. "+Devizovy.name+"           "+String.format("%,d",Devizovy.getZustatek())+" Kč");
        System.out.println("2. "+Sporici.name+"           "+String.format("%,d",Sporici.getZustatek())+" Kč");
        System.out.println("3. "+Investicni.name+"           "+String.format("%,d",Investicni.getZustatek())+" Kč");
        System.out.println("---------------------------------");

        Ucet seznamUctu[] = {Devizovy,Sporici,Investicni};

        while(pocetPenez != 0){
            
            while(pokracovat == false){
                System.out.print("Zadejte, na jaký účet chcete poslat peníze: ");
                while (true) {
                    if (sc.hasNextInt()){
                        vyberUctu = sc.nextInt();
                        break;
                    } else {
                        System.out.println("Neplatný vstup");
                        sc.next();
                    }
                }
                if(vyberUctu >=1 && vyberUctu <= 3){
                    pokracovat = true;
                } else {
                    System.out.println("Neplatný účet");
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
                zadaniPinu(pin);
                zustatek -= pocetPenez;
                seznamUctu[vyberUctu-1].setZustatek(seznamUctu[vyberUctu-1].getZustatek() + pocetPenez);
                System.out.println("Poslal jste si "+pocetPenez+" Kč na "+seznamUctu[vyberUctu-1].name+".");
            }
        }
        return zustatek;
    }

    public static void main(String[] args) {            // hlavní program
        
        System.out.println("-------------PŘIHLÁŠENÍ-------------");
        
        int pin = vytvoreniPinu(0);

        zadaniPinu(pin);
        
        System.out.println("             VÍTEJTE             ");

        Ucet Bezny = new Ucet("Běžný účet");
        Ucet Devizovy = new Ucet("Devizový účet");
        Ucet Sporici = new Ucet("Spořicí účet");
        Ucet Investicni = new Ucet("Investiční účet");
        
        int volba = 0;

        while(volba!=4){
            System.out.println("---------DOMOVSKÁ STRÁNKA---------");
            System.out.println("Váš zůstatek (Běžný účet): "+String.format("%,d",Bezny.getZustatek())+" Kč");
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
                    Bezny.setZustatek(vyberPenez(Bezny.getZustatek(),pin));
                    break;
                case 2:
                    Bezny.setZustatek(vkladPenez(Bezny.getZustatek(),pin));
                    break;
                case 3:
                    Bezny.setZustatek(poslatPenize(Bezny.getZustatek(),Devizovy,Sporici,Investicni,pin));
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