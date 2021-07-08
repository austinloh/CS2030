/**
 * The Begin Service Event extends from Event and models
 * when a counter start serving a customer.
 * The only event that can occur after this is the
 * EndServiceEvent.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */

//can only end service from here
class ServiceBeginEvent extends Event{
  double endTime;
  Customer customer;
  Counter counter;

  public ServiceBeginEvent(double arrivalTime, Customer customer, Counter counter) { 
    super(arrivalTime);
    this.endTime = arrivalTime + customer.getServiceTime();
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = String.format(": Customer %d service begin (by Counter %d)",
          this.customer.getCustomerId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {
      new EndServiceEvent(this.endTime, this.customer, this.counter)
    };
  }

}
