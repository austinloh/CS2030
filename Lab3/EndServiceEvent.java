/**
 * This class extends event and models what
 * happen when a counter finish serving a
 * customer.
 * The possible events that can occur after this is
 * the Departure Event of the current serving customer
 * and the ServiceBeginEvent if there are customer
 * waiting in queue.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class EndServiceEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Shop shop;

  public EndServiceEvent(double endTime, Customer customer, Counter counter, Shop shop) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s service done (by %s %s)",
          this.customer, this.counter, this.counter.getQueue());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    DepartureEvent depart = new DepartureEvent(this.getTime(), this.customer);
    if (!this.shop.queueIsEmpty() && !this.counter.queueIsEmpty()) {
      Customer newCounterCustomer = this.counter.leaveQueue();
      Customer newCustomer = this.shop.leaveQueue();
      this.counter.beginService(this.getTime() + newCounterCustomer.getServiceTime());
      ServiceBeginEvent service = new ServiceBeginEvent(this.getTime(),
          newCounterCustomer, this.counter, this.shop);
      JoinCounterQueueEvent join = new JoinCounterQueueEvent(
            this.getTime(), newCustomer, this.counter);
      return new Event[] {depart, service, join};
    } else if (!this.counter.queueIsEmpty()) {
      Customer newCounterCustomer = this.counter.leaveQueue();
      this.counter.beginService(this.getTime() + newCounterCustomer.getServiceTime());
      ServiceBeginEvent service = new ServiceBeginEvent(this.getTime(),
          newCounterCustomer, this.counter, this.shop);
      return new Event[] {depart, service};
    } else if (!this.shop.queueIsEmpty()) {
      Customer newCustomer = this.shop.leaveQueue();
      this.counter.beginService(this.getTime() + newCustomer.getServiceTime());
      ServiceBeginEvent service = new ServiceBeginEvent(this.getTime(),
          newCustomer, this.counter, this.shop);
      return new Event[] {depart, service};
    } else {
      return new Event[] {depart};
    } 
  }

}
