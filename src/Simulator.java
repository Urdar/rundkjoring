import java.util.ArrayList;


/**
 * Created by jens on 08.04.15.
 */

// TODO Lese oppgavearket. Teste hypotese. F.eks øke farten, vil vi få flere biler igjennom eller færre? F.eks fra 30 til 50 km/t. Om vi øker farten vil biler naturlig prøve å holde lengre avstand..
// HVORDAN gjøre en hypotesetest og HVORDAN får vi ut nødvendig data

// TODO Justere steps i forhold til tid etc
// TODO Kontroll på når/hyppighet av nye biler
// TODO Integrere fart og aksellerasjon
// TODO Biler må holde vikeplikten
// TODO Lagre data til fil for senere analyse
// TODO Grafisk fremstilling
// TODO Evnt skal kunne simulere "ekte" kjøring, dvs ikke-perfekt kjøring, kanskje en bil kjører inn i rundkjøring på et tidspunkt den ikke burde etc

/*
        NOTAT fra andre fremvisninger:
        steps
        time/steps
        spawn probalility
        speed
        speed variance

        cars in roundabout
        throughoutput rate
        throughoutput

        Hans: Kunne kjøre xxx simuleringer (forskjellige parametre) for å sammenligne

*/

public class Simulator {

    public static void main(String[] args) {


        Simulator simulator = new Simulator();
        simulator.simulate(100); // run for xxx steps
        System.out.println("\n" + throughoutput + " biler kjørte gjennom rundkjøringen");


    }


    // List of roadsegments to and from roundAbout segments
    private ArrayList<Road> roadSegments = new ArrayList<Road>();

    // The current step of the simulation.
    private int step;

    // Ønsket avstand mellom biler
    private double distanceBetweenCars = 5.0;

    // Sannsynlighet for ny bil (hvis det er plass)
    private static final double CAR_CREATION_PROBABILITY = 0.5;

    // Antall biler som har kjørt igjennom
    private static int throughoutput = 0;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Simulator() {

        // Opprette veisegmenter.
        // Segmenter i rundingen
        for (int i = 0; i < 4; i++) {
            roadSegments.add(new Road(4, false));
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

        System.out.println(ANSI_GREEN + "\nStep " + step + ANSI_RESET);
        // Flytte biler mellom segmenter
        for (int i = 0; i < roadSegments.size(); i++) {

            System.out.println("\nBehandler segment " + i);

            carsToMove = roadSegments.get(i).moveCar(i);

            if (carsToMove.size() != 0) {
                System.out.println("Skal flytte " + carsToMove.size() + " biler til annet segment");
            }

            for (int y = 0; y < carsToMove.size(); y++) {
                System.out.println("Flytter bil som kom fra segment nr " + carsToMove.get(y).cameFromSegment + " og skal til  " + carsToMove.get(y).exitNo);
                moveCarBetweenSegments(carsToMove.get(y));

            }


            if (i > 3) {
                // accessroad, forsøke å lage ny bil
                roadSegments.get(i).makeNewCar(i);
            }

        }


        // TODO skrive statistiske data. Biler i rundkjøring, antall nye biler, antall biler som står stille, gjennomsnittsfart etc etc

    }

    public void moveCarBetweenSegments(Car thecar) {
        // Hvis neste er en exit
        thecar.setDistance(0);

        switch (thecar.cameFromSegment) {
            // 0-3 er "selve rundingen"
            case 0:
                if (thecar.exitNo == 5) {
                    // Skal kjøre av her
                    thecar.setDrivingOut(true);
                    roadSegments.get(5).addCar(thecar);
                    System.out.println("En bil kjørte av til vei 5");
                } else {
                    // den skal kjøre videre
                    roadSegments.get(1).addCar(thecar);
                }


                break;
            case 1:
                if (thecar.exitNo == 6) {
                    // Skal kjøre av her
                    thecar.setDrivingOut(true);
                    roadSegments.get(6).addCar(thecar);
                    System.out.println("En bil kjørte av til vei 6");
                } else {
                    // den skal kjøre videre
                    roadSegments.get(2).addCar(thecar);
                }
                break;
            case 2:
                if (thecar.exitNo == 7) {
                    // Skal kjøre av her
                    thecar.setDrivingOut(true);
                    roadSegments.get(7).addCar(thecar);
                    System.out.println("En bil kjørte av til vei 7");
                } else {
                    // den skal kjøre videre
                    roadSegments.get(3).addCar(thecar);
                }
                break;
            case 3:

                if (thecar.exitNo == 4) {
                    // Skal kjøre av her
                    thecar.setDrivingOut(true);
                    roadSegments.get(4).addCar(thecar);
                    System.out.println("En bil kjørte av til vei 4");
                } else {
                    // den skal kjøre videre
                    roadSegments.get(0).addCar(thecar);
                }
                break;
            // 4-7 er "innkjøringsveiene"
            case 4:
                if (thecar.drivingOut) {
                    // Er ferdig, skal bare slettes, aka ikke gjør noe
                    System.out.println("En bil kjørte til ende på vei 4 og ble fjernet fra simulering");
                    throughoutput++;
                } else {
                    // den skal inn i rundkjøring
                    //TODO lage til så den sjekker om det er klart der
                    roadSegments.get(0).addCar(thecar);
                }
                break;
            case 5:
                if (thecar.drivingOut) {
                    // Er ferdig, skal bare slettes, aka ikke gjør noe
                    System.out.println("En bil kjørte til ende på vei 5 og ble fjernet fra simulering");
                    throughoutput++;
                } else {
                    // den skal inn i rundkjøring
                    roadSegments.get(1).addCar(thecar);
                }
                break;
            case 6:
                if (thecar.drivingOut) {
                    // Er ferdig, skal bare slettes, aka ikke gjør noe
                    System.out.println("En bil kjørte til ende på vei 6 og ble fjernet fra simulering");
                    throughoutput++;
                } else {
                    // den skal inn i rundkjøring
                    roadSegments.get(2).addCar(thecar);
                }
                break;
            case 7:
                if (thecar.drivingOut) {
                    // Er ferdig, skal bare slettes, aka ikke gjør noe
                    System.out.println("En bil kjørte til ende på vei 7 og ble fjernet fra simulering");
                    throughoutput++;
                } else {
                    // den skal inn i rundkjøring
                    roadSegments.get(3).addCar(thecar);
                }
                break;
        }


    }

}
