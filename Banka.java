public class Banka {

    public static void main(String[] args) {            // hlavní program
        
        System.out.println("-------------PŘIHLÁŠENÍ-------------");
        
        int pin = Input.vytvoreniPinu();

        Input.zadaniPinu(pin); 
        
        System.out.println("             VÍTEJTE             ");

        int volba = 0;

        while(volba!=4){
            System.out.println("---------DOMOVSKÁ STRÁNKA---------");
            System.out.println("Váš zůstatek (Běžný účet): "+String.format("%,d",Akce.Bezny.getZustatek())+" Kč");
            System.out.println("Vyberte si akci:");
            System.out.println("1. Výběr peněz");
            System.out.println("2. Vložit peníze");
            System.out.println("3. Poslat peníze");
            System.out.println("4. Odhlásit se");
            System.out.println("---------------------------------");
            System.out.print("Vaše volba: ");

            volba = Input.overeniInt();

            switch (volba) {
                case 1:
                    Akce.Bezny.setZustatek(Akce.vyberPenez(Akce.Bezny.getZustatek(),pin));
                    break;
                case 2:
                    Akce.Bezny.setZustatek(Akce.vkladPenez(Akce.Bezny.getZustatek(),pin));
                    break;
                case 3:
                    Akce.Bezny.setZustatek(Akce.poslatPenize(Akce.Bezny.getZustatek(),Akce.Devizovy,Akce.Sporici,Akce.Investicni,pin));
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
        }

        System.out.println("Nashledanou :)");
    }   
}