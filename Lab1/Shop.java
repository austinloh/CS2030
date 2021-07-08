/**
 * This class models a Shop that has counters to serve
 * customers.
 * It keeps track of the counters in it.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Shop {
  private Counter[] counters;

  public Shop(int numOfCounters) {
    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter();
    }
  }
  
  /*
   * checks whether there are available counters.
   * this is required as if availableCounter if called 
   * when no available counters, would have NullPointerException
   */
  public boolean hasAvailable(double currentTime) {
    for (Counter counter: this.counters) {
      if (counter.isAvailable(currentTime)) {
        return true;
      }
    }
    return false;
  }

  //return Counter with lowest id as counters are stored
  //in increasing order
  public Counter availableCounter(double currentTime) {
    Counter available = null;
    for (Counter counter: counters) {
      if (counter.isAvailable(currentTime)) {
        available = counter;
        break;
      }
    }
    return available;
  }
 
}
