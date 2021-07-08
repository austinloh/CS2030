import java.util.Scanner;

/**
 * The main class for CS2030S Lab 3.
 *
 * @author Austin Loh
 * @version CS2030 AY20/21 Special Term 1
 */
class Lab3 {
  public static void main(String[] args) {

    // Create a scanner to read from standard input.
    Scanner sc = new Scanner(System.in);

    // Create a simulation.  The ShopSimulation 
    // constructor will read the simulation parameters 
    // and initial events using the scanner.
    Simulation simulation = new ShopSimulation(sc);

    // Create a new simulator and run the simulation
    new Simulator(simulation).run();

    // Clean up the scanner.
    sc.close();
  }
}
