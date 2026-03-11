public class Akce {
    static int vyberPenez(int zustatek, int pin){            // metoda - vybrání peněz
        int pocetPenez = 1;
        while(pocetPenez != 0){
            System.out.print("Zadejte, kolik peněz chcete vybrat (pro návrat do hlavní nabídky zadejte 0): ");
            pocetPenez = Input.overeniInt();
            if (pocetPenez > zustatek){
                System.out.println("Nedostatečný zůstatek");
            } else if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            } else {
                Input.zadaniPinu(pin);
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
            pocetPenez = Input.overeniInt();
            if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            }  else {
                Input.zadaniPinu(pin);
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
                vyberUctu = Input.overeniInt();
                if(vyberUctu >=1 && vyberUctu <= 3){
                    pokracovat = true;
                } else {
                    System.out.println("Neplatný účet");
                }
            }
            
            System.out.print("Zadejte, kolik peněz chcete poslat (pro návrat do hlavní nabídky zadejte 0): ");
            pocetPenez = Input.overeniInt();
            if(pocetPenez > zustatek){
                System.out.println("Nedostatečný zůstatek");
            } else if (pocetPenez<0) {
                System.out.println("Neplatná částka");
            } else if (pocetPenez == 0) {
                break;
            } else {
                Input.zadaniPinu(pin);
                zustatek -= pocetPenez;
                seznamUctu[vyberUctu-1].setZustatek(seznamUctu[vyberUctu-1].getZustatek() + pocetPenez);
                System.out.println("Poslal jste si "+pocetPenez+" Kč na "+seznamUctu[vyberUctu-1].name+".");
            }
        }
        return zustatek;
    }

    static Ucet Bezny = new Ucet("Běžný účet");
    static Ucet Devizovy = new Ucet("Devizový účet");
    static Ucet Sporici = new Ucet("Spořicí účet");
    static Ucet Investicni = new Ucet("Investiční účet");
}