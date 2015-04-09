import java.util.ArrayList;


/**
 * Created by jens on 08.04.15.
 */


// TODO skal kunne kjøre simulering over viss tid, finne throughoutput
// TODO skal kunne simulere "ekte" kjøring, dvs ikke-perfekt kjøring, kanskje en bil kjører inn i rundkjøring på et tidspunkt den ikke burde, ikke

public class Simulator {

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.simulate(15); // run for 1000 steps
    }


    // List of roadsegments to and from roundAboutSegments
    private ArrayList<Road> roadSegments = new ArrayList<Road>();

    // The current step of the simulation.
    private int step;

    // Ønsket avstand mellom biler
    private double distanceBetweenCars= 5.0;


    public Simulator() {

        // Opprette veisegmenter.
        // Segmenter i rundingen
        for (int i = 0; i < 4; i++) {
            roadSegments.add(new Road(10, false));
        }
        // Inn- og utkjøring fra rundingen
        for (int i = 0; i < 4; i++) {
            roadSegments.add(new Road(10, true));

        }
    }

    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps; step++) {
            simulateOneStep();
        }

    }

    public void simulateOneStep() {
        step++;

        ArrayList<Car> carsToMove = new ArrayList<Car>();

        // Loope igjennom alle roadsegments

        System.out.println("\nStep " + step);
        // Flytte biler mellom segmenter
        for (int i = 0; i < roadSegments.size(); i++) {
            System.out.println("Flytter biler i segment " + i);
            carsToMove = roadSegments.get(i).moveCar();
            System.out.println("Skal flytte " +  carsToMove.size() + " biler");

            for (int y = 0; y < carsToMove.size(); y++) {
                System.out.println("Flytter bil nr " + y + " som skal ta exit " + carsToMove.get(y).exitNo + " deb kom fra " + carsToMove.get(y).cameFromSegment);
                moveCarBetweenSegments(carsToMove.get(y));

            }


            if (i > 3){
                // accessroad, forsøke å lage ny bil
                roadSegments.get(i).makeNewCar(i);
            }

        }



        // TODO skrive statistiske data

    }

public void moveCarBetweenSegments(Car thecar){
    // Hvis neste er en exit
    thecar.setDistance(0);

    switch (thecar.cameFromSegment) {
        case 0:
            if (thecar.exitNo == 5){
                // Skal kjøre av her
                roadSegments.get(5).addCar(thecar);
            } else {
                // den skal kjøre videre
                roadSegments.get(1).addCar(thecar);
            }
            break;
        case 1:
            if (thecar.exitNo == 6){
                // Skal kjøre av her
                roadSegments.get(6).addCar(thecar);
            } else {
                // den skal kjøre videre
                roadSegments.get(2).addCar(thecar);
            }
            break;
        case 2:
            if (thecar.exitNo == 7){
                // Skal kjøre av her
                roadSegments.get(7).addCar(thecar);
            } else {
                // den skal kjøre videre
                roadSegments.get(3).addCar(thecar);
            }
            break;
        case 3:

            if (thecar.exitNo == 4){
                // Skal kjøre av her
                roadSegments.get(4).addCar(thecar);
            } else {
                // den skal kjøre videre
                roadSegments.get(0).addCar(thecar);
            }
            break;
        case 4:
            if (thecar.exitNo == 4){
                // Skal kjøre av her
                roadSegments.get(4).addCar(thecar);
            } else {
                // den skal kjøre videre
                roadSegments.get(0).addCar(thecar);
            }
            break;
        case 5:
            break;
        case 6:
            break;
        case 7:
            break;
    }




}

}
