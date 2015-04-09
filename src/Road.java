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

    public void addCar(Car car) {
        cars.add(car);
    }

    public ArrayList<Car> moveCar() {
        ArrayList<Car> returnedCars = new ArrayList<Car>();

        for (int i = 0; i < cars.size(); i++) {
            if (isAtSegmentEnd(cars.get(i).getDistance())) {
                returnedCars.add(cars.get(i));

            }
        }
        return returnedCars;
    }

    public double distanceToEnd(int current) {
        return length - current;
    }

    public int distanceToNextCar(int start) {
        double distanceToNextCar = 999;

        for (int i = 0; i < this.cars.size(); i++) {

            double distance = cars.get(i).distance - start;

            if (distance < distanceToNextCar) {
                distanceToNextCar = distance;
            }

        }
        return (int) distanceToNextCar;

    }

    public boolean roomForNewCar() {
        boolean svar = false;
        if (distanceToNextCar(0) > 5) {
            //Plass til ny bil
            svar = true;
        }
        return svar;
    }

    public void makeNewCar(int gjeldendeSegment) {
        if (distanceToNextCar(0) > 5) {
            System.out.println("Lager ny bil");
            cars.add(new Car(gjeldendeSegment));
        } else {
            System.out.println("Ikke plass til ny bil");
        }
    }

    public void deleteCar() {

    }

    public boolean isAtSegmentEnd(int currentPosition) {

        if (currentPosition == length) {
            return true;
        } else {
            return false;
        }

    }

}
