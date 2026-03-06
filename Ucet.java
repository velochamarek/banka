import java.util.Random;

public class Ucet {
    
    String name;
    public Ucet(String ucetName){
        name = ucetName;
    }
    
    
    Random r = new Random();
    private int min = 5000, max = 5000000;
    private int zustatek = r.nextInt((max-min)+min);

    public void setZustatek(int novaHodnota){
        if(novaHodnota >= 0){
            this.zustatek = novaHodnota;
        } else {
            System.out.println("Neplatná hodnota");
        }

    }

    public int getZustatek(){
        return zustatek;
    }
}
