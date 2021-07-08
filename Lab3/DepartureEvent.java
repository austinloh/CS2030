/**
 * This class models a Departure event and extends from Event.
 * There is no other event that would occur after the 
 * customer departs.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class DepartureEvent extends Event {
  private Customer customer;

  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }
  
  @Override
  public String toString() {
    String str = String.format(": %s departed", this.customer);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {}; 
  }

}  
