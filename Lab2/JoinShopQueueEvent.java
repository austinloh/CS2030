/**
 * This class models what happen when there are
 * no available counters and queue is not full
 * and extends Event.
 * There are no Events happening after this event
 * as EndServiceEvent would call for ServiceBeginEvent
 * if there are people in queue.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class JoinShopQueueEvent extends Event {
  private Customer customer;
  private Shop shop;

  public JoinShopQueueEvent(double arrivalTime, Customer customer, Shop shop) {
    super(arrivalTime);
    this.customer = customer;
    this.shop = shop;
  }

  @Override 
  public String toString() {
    String queueBefore = this.shop.getQueue().toString();
    this.shop.joinQueue(this.customer);
    String str = String.format(": %s joined queue ", this.customer);
    return super.toString() + str + queueBefore;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
