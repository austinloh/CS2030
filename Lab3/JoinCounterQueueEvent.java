/**
 * This class models joining a counter queue and 
 * extends Event.
 * No event occur after this event.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class JoinCounterQueueEvent extends Event {
  private Customer customer;
  private Counter counter;

  public JoinCounterQueueEvent(double arrivalTime, Customer customer, Counter counter) {
    super(arrivalTime);
    this.customer = customer;
    this.counter = counter;
  }

  @Override 
  public String toString() {
    String queueBefore = this.counter.getQueue().toString();
    this.counter.joinQueue(this.customer);
    String str = String.format(": %s joined counter queue (at %s %s)", 
        this.customer, this.counter, queueBefore);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
