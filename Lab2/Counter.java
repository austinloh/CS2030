/**
 * This class models a counter in a shop.
 * The counter can only serve one customer at a time.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Counter {
  //serving keeps track of until when counter is serving
  public static int numOfCounters = 0;
  private int counterId;
  private double serving = 0;
  
  public Counter() {
    this.counterId = Counter.numOfCounters;
    Counter.numOfCounters++;
  }

  public int getCounterId() {
    return this.counterId;
  }

  public boolean isAvailable(double currentTime) {
    return currentTime > this.serving;
  }
  
  //update uptil when counter is serving
  public void beginService(double endTime) {
    this.serving = endTime;
  }

  @Override 
  public String toString() {
    return "S" + this.counterId;
  }
}
