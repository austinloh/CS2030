/**
 * This class models a Shop that has counters to serve
 * customers.
 * It keeps track of the counters in it and entrance
 * queue.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Shop {
  private Array<Counter> counters;
  private Queue<Customer> queue;

  public Shop(int numOfCounters, int counterQueue, int queueSize) {
    this.counters = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      counters.set(i, new Counter(counterQueue));
    }
    this.queue = new Queue<Customer>(queueSize);
  }
  
  /*
   * checks whether there are available counters.
   * this is required as if availableCounter if called
   * when no available counters, would have NullPointerException
   */
  public boolean hasAvailable(double currentTime) {
    for (int i = 0; i < this.counters.length(); i++) {
      Counter counter = this.counters.get(i);
      if (counter.isAvailable(currentTime)) {
        return true;
      }
    }
    return false;
  }

  //return Counter with lowest id as counters are stored
  //in increasing order
  public Counter availableCounter(double currentTime) {
    for (int i = 0; i < this.counters.length(); i++) {
      Counter counter = this.counters.get(i);
      if (counter.isAvailable(currentTime)) {
        return counter;
      }
    }
    return null;
  }

  public Counter availableCounterQueue() {
    Counter available = counters.min();
    if (!available.queueIsFull()) {
      return available;
    }
    return null;
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
}

