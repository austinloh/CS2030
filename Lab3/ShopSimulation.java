import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */ 
class ShopSimulation extends Simulation {

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private  Event[] initEvents;

  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  
  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    Shop shop = new Shop(sc.nextInt(), sc.nextInt(), sc.nextInt());

    int i = 0;
    while (sc.hasNextDouble()) {    
      double arrivalTime = sc.nextDouble();
      Customer customer = new Customer(sc.nextDouble());
      initEvents[i++] = new ArrivalEvent(arrivalTime, customer, shop);
    }
  }
  


  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
