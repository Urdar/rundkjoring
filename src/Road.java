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
                System.out.println("Bilen har nådd enden");

                if (this.accessRoad) {
                    if (cars.get(i).getDrivingOut()) {
                        // Den har fullført runden sin, slette den
                      cars.remove(i);
                    } else {
                        // Den har kommet til endes på segment og skal videre

                        returnedCars.add(cars.get(i));

                     cars.remove(i);
                    }

                } else {
                // Bilen kjører inni sirkelen og har kommet til endes på segment
                    // Den skal videre
                    returnedCars.add(cars.get(i));
                  cars.remove(i);
                }




            } else {
                // Bilen har ikke nådd ende av segment, skal bare kjøre videre
                // Sjekke om det er plass foran for å flytte fremover
                System.out.println("Bil " + i + " har distanse " + cars.get(i).getDistance());

                if (true) {
                    // TODO lage til denne sjekken

                    System.out.println("Kan flytte fremover");
                    cars.get(i).setDistance(cars.get(i).getDistance() + 1);

                } else {
                    System.out.println("Kan ikke flytte fremover");
                }

            }


            // TODO sjekke om klar bane (avtand til bil fremme, andre segmenter)
            // TODO sjekke om ende av segment

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

    public void makeNewCar() {
        if (distanceToNextCar(0) > 5) {
            System.out.println("Lager ny bil");
            cars.add(new Car());
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
