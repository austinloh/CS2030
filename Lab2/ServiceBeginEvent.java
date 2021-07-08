/**
 * The Begin Service Event extends from Event and models
 * when a counter start serving a customer.
 * The only event that can occur after this is the
 * EndServiceEvent.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class ServiceBeginEvent extends Event {
  private double endTime;
  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceBeginEvent(double arrivalTime, Customer customer, Counter counter, Shop shop) { 
    super(arrivalTime);
    this.endTime = arrivalTime + customer.getServiceTime();
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s service begin (by %s)",
          this.customer, this.counter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {
      new EndServiceEvent(this.endTime, this.customer, this.counter, this.shop)
    };
  }

}
