import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);

    static int vytvoreniPinu(int pin){          // metoda - vytvoření Pinu (použito jednou na začátku programu)
        System.out.print("Vytvořte si 4-místný PIN: ");
        while (true) {
            pin = overeniInt();
            if(String.valueOf(Math.abs(pin)).length()==4){
                break;
            } else {
                System.out.println("Neplatná délka PINu");
            }
        }
        return pin;
    }

    static int overeniInt(){         // metoda - ověření vstupu typu integer
        while (true) {
            if (sc.hasNextInt()){
                return sc.nextInt();
            } else {
                System.out.println("Neplatný vstup");
                sc.nextLine();
            }
        }
    }

    static void zadaniPinu(int pin){           // metoda - ověření PIN kódem, po 3 neúspěšných pokusech se spustí metoda zablokovaniPristupu()
        int pinInput = 0;
            for (int i = 0; i < 3; i++){
                System.out.print("Zadejte PIN: ");
                pinInput = overeniInt();
                if(pinInput!=pin){
                    System.out.println("NESPRÁVNÝ PIN");
                    if (i == 2){
                        zablokovaniPristupu();
                    }
                } else {
                    break;
                }
            }
    }

    static void zablokovaniPristupu(){          // metoda - zablokování přístupu a ukončení programu
        System.out.println("PŘÍSTUP ZABLOKOVÁN");
        System.exit(0);
    }
}
