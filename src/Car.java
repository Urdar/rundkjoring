import java.util.Random;

/**
 * Created by jens on 08.04.15.
 */

public class Car {

    boolean drivingOut;

    // Distanse kjørt på vegsegment
    double distance;

    // Aggresivitet. Avhengig av denne tar bilføreren risikoer, kjører fortere, mindre avstand til neste bil..
    int aggressiveness;

    int exitNo;

    int speed;


    public Car(int aggressiveness) {
        this.drivingOut = false;
        this.distance = 0;
        this.exitNo = randInt(0,4);
        this.speed = randInt(50,70);
    }

    public static int randInt(int min, int max) {
        // Lage variabel fra min til max
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
