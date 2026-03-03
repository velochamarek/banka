import java.util.Random;

public class Ucet {
    String name;
    
    Random r = new Random();
    int min = 5000, max = 5000000;
    int zustatek = r.nextInt((max-min)+min);
}
