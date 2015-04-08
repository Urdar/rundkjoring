import java.util.ArrayList;


/**
 * Created by jens on 08.04.15.
 */


// TODO skal kunne kjøre simulering over viss tid, finne throughoutput
// TODO skal kunne simulere "ekte" kjøring, dvs ikke-perfekt kjøring, kanskje en bil kjører inn i rundkjøring på et tidspunkt den ikke burde, ikke

public class Simulator {

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.simulate(1000); // run for 1000 steps
    }


    // List of roadsegments to and from roundAboutSegments
    private ArrayList<Road> roadSegments = new ArrayList<Road>();

    // The current step of the simulation.
    private int step;

    // Avstand mellom biler
    private double distanceBetweenCars= 5.0;


    public Simulator() {

        // Opprette veisegmenter.
        // Segmenter i rundingen
        for (int i = 0; i < 4; i++) {
            roadSegments.add(new Road(200, false));
        }
        // Inn- og utkjøring fra rundingen
        for (int i = 0; i < 4; i++) {
            roadSegments.add(new Road(400, true));

        }
    }

    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps; step++) {
            simulateOneStep();
        }

    }

    public void simulateOneStep() {
        step++;

        // Loope igjennom alle roadsegments
        System.out.println("\nStep " + step);

        for (int i = 0; i < roadSegments.size(); i++) {
            System.out.println("Flytter biler i segment " + i);
            roadSegments.get(i).moveVehicles();

            if (i > 3){
                // accessroad, forsøke å lage ny bil
                roadSegments.get(i).makeNewCar();
            }

        }

        // TODO skrive statistiske data

    }


}
