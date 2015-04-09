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

    public ArrayList<Car> moveCar(int segmentNo) {

        ArrayList<Car> returnedCars = new ArrayList<Car>();

        System.out.println("Biler: " + cars.size());

        for (int i = 0; i < cars.size(); i++) {

            System.out.println("Èn skal kjøre av på vei " + cars.get(i).exitNo);
            // Flytter bil 1 frem
            // TODO sjekke om det er plass etc...
            cars.get(i).setDistance(cars.get(i).getDistance() + 1);


            // Hvis bil er på ende av segment
            if (isAtSegmentEnd(cars.get(i).getDistance())) {
                cars.get(i).setCameFromSegment(segmentNo);
                returnedCars.add(cars.get(i));
                cars.remove(i);
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
            System.out.println("Ny bil kom på veien!");
            cars.add(new Car(gjeldendeSegment));
        } else {
            // System.out.println("Ikke plass til ny bil");
        }
    }

    public void deleteCar() {

    }

    public boolean isAtSegmentEnd(int currentPosition) {

        if (currentPosition >= length) {
            return true;
        } else {
            return false;
        }

    }

}
