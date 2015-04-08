import java.util.ArrayList;

/**
 * Created by jens on 08.04.15.
 */

public class Road {

    private int length;

    private Road startRoad;
    private Road endRoad;

    private Road exitRoad;

    // ArrayList of vehicles within road segment
    private ArrayList<Car> cars = new ArrayList<Car>();

    public Road(int length) {
        this.length = length;

    }


    public void moveVehicles() {

        // Loope gjennom liste av biler i segment og flytte de
        // TODO sjekke om klar bane (avtand til bil fremme, andre segmenter)
        // TODO sjekke om ende av segment


    }

    public double distanceToEnd() {
        return 100;
        // TODO regne ut distanse til enden
    }

    public double distanceToNextCar(int start) {

        double distanceToNextCar = 0;

        for (int i = 0; i < this.cars.size(); i++) {
            double distance = this.length - cars.get(i).distance;
            if (distance>distanceToNextCar){
                distanceToNextCar = distance;
            }

        }
        return distanceToNextCar;

    }

    public boolean roomForNewCar() {
        if(distanceToNextCar(0)>5){
            //Plass til ny bil
            cars.add(new Car());
        }
    }


}
