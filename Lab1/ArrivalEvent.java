/**
 * This class models a arrival event when customer arrives at a shop.
 * Next possible event after this is either the ServiceBeginEvent
 * or the Departure Event if all counters are serving.
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
    String str = String.format(": Customer %d arrives", this.customer.getCustomerId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    
    if (this.shop.hasAvailable(this.getTime())) {
      Counter counter = shop.availableCounter(this.getTime());
      counter.beginService(this.getTime() + this.customer.getServiceTime());
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter)
      };
    }
    else {
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer)
      };
    }
  }

}
