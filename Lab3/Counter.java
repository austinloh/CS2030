/**
 * This class models a counter in a shop.
 * The counter can only serve one customer at a time.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Counter implements Comparable<Counter> {
  //serving keeps track of until when counter is serving
  private static int numOfCounters = 0;
  private int counterId;
  private double serving = 0;
  private Queue<Customer> queue; 
  
  public Counter(int queueSize) {
    this.counterId = Counter.numOfCounters;
    Counter.numOfCounters++;
    queue = new Queue<Customer>(queueSize);
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

  public Queue<Customer> getQueue() {
    return this.queue;
  }
  
  public boolean queueIsFull() {
    return this.queue.isFull();
  }

  public void joinQueue(Customer customer) {
    this.queue.enq(customer);
  }

  public boolean queueIsEmpty() {
    return this.queue.isEmpty();
  }

  public Customer leaveQueue() {
    return this.queue.deq();
  }

  @Override 
  public String toString() {
    return "S" + this.counterId;
  }

  @Override
  public int compareTo(Counter c) {
    if (this.queue.length() < c.queue.length()) {
      return -1;
    } else if (this.queue.length() > c.queue.length()) {
      return 1;
    } else {
      if (this.counterId < c.counterId) {
        return -1;
      } else {
        return 1;
      }
    }
  }
}
