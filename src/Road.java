import java.util.ArrayList;

/**
 * Created by jens on 08.04.15.
 */

public class Road {

    private int length;

    private boolean accessRoad;

    // ArrayList of vehicles within road segment
    private ArrayList<Car> cars = new ArrayList<Car>();

    public Road(int length, boolean accessroad) {
        this.length = length;
        this.accessRoad = accessroad;
    }


    public void moveVehicles() {

        for (int i = 0; i < cars.size(); i++) {
            System.out.println("Skjekker biler i segmentet " + i);
            cars.get(i).roomForNewCar();

        // Loope gjennom liste av biler i segment og flytte de
        // TODO sjekke om klar bane (avtand til bil fremme, andre segmenter)
        // TODO sjekke om ende av segment


    }

    public double distanceToEnd() {
        return 100;
        // TODO regne ut distanse til enden
    }

    public int distanceToNextCar(int start) {

        double distanceToNextCar = 999;

        for (int i = 0; i < this.cars.size(); i++) {

            double distance = cars.get(i).distance-start;

            if (distance < distanceToNextCar) {
                distanceToNextCar = distance;
            }

        }
        return (int) distanceToNextCar;

    }

    public boolean roomForNewCar() {
        if (distanceToNextCar(0) > 5) {
            //Plass til ny bil

        }
        return true;
    }

    public void makeNewCar() {

        if (distanceToNextCar(0)>5) {
            System.out.println("Lager ny bil");
            cars.add(new Car());
        } else {
            System.out.println("Ikke plass til ny bil");
        }


        // Sjekke om plass til ny bil
        // Lage ny bil hvis plass
    }


}
