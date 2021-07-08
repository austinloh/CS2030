/**
 * This class models a arrival event when customer arrives at a shop.
 * Next possible event after this is either the ServiceBeginEvent
 * or the JoinShopQueueEvent if all counters are serving and queue
 * is not full or DepartureEvent.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class ArrivalEvent extends Event {
  private Customer customer;
  private Shop shop;

  public ArrivalEvent(double arrivalTime, Customer customer, Shop shop) {
    super(arrivalTime);
    this.customer = customer;
    this.shop = shop;
  }
    
  @Override
  public String toString() {
    String str = String.format(": %s arrived ", this.customer);
    return super.toString() + str + shop.getQueue().toString();
  }

  @Override
  public Event[] simulate() {
    
    if (this.shop.hasAvailable(this.getTime())) {
      Counter counter = shop.availableCounter(this.getTime());
      counter.beginService(this.getTime() + this.customer.getServiceTime());
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter, this.shop)
      };
    } else {
      if (!this.shop.queueIsFull()) {
        return new Event[] {
          new JoinShopQueueEvent(this.getTime(), this.customer, this.shop)
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer)
        };
      }
    }
  }

}
