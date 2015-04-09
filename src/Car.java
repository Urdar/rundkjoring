import java.util.Random;

/**
 * Created by jens on 08.04.15.
 */

public class    Car {

    boolean drivingOut;

    // Distanse kjørt på vegsegment
    int distance;

    // Aggresivitet. Avhengig av denne tar bilføreren risikoer, kjører fortere, mindre avstand til neste bil..
    int aggressiveness;

    int exitNo;

    int speed;

    int cameFromSegment;

    public int getDistance() {
        return distance;
    }

    public boolean getDrivingOut() {
        return drivingOut;
    }

    public void setDrivingOut(boolean drivingOut) {
        this.drivingOut = drivingOut;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Car(int segment) {
        this.drivingOut = false;
        this.distance = 1;
        this.exitNo = randInt(4,7);
        this.speed = randInt(50, 70);
        this.aggressiveness = randInt(0,10);
        this.cameFromSegment = segment;
    }

    public static int randInt(int min, int max) {
        // Lage variabel fra min til max
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public int getCameFromSegment() {
        return cameFromSegment;
    }

    public void setCameFromSegment(int cameFromSegment) {
        this.cameFromSegment = cameFromSegment;
    }
}
