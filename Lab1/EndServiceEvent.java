/**
 * The end service event extends event and models
 * what happen when a ocunter finish serving a 
 * customer.
 * The only possible event that can occur after this is
 * the Departure Event.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
//can only depart from here
class EndServiceEvent extends Event{
  private Customer customer;
  private Counter counter;

  public EndServiceEvent(double endTime, Customer customer, Counter counter) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = String.format(": Customer %d service done (by Counter %d)",
          this.customer.getCustomerId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {
      new DepartureEvent(this.getTime(), this.customer)
      };
  }

}
