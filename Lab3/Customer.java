/**
 * This class models a customer entering a shop.
 * It keeps track of the total number of customer.
 *
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Customer {
  private static int numOfCustomers = 0;
  private int customerId;
  private double serviceTime;

  public Customer(double serviceTime) {
    this.customerId = Customer.numOfCustomers;
    Customer.numOfCustomers++;
    this.serviceTime = serviceTime;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  @Override
  public String toString() {
    return "C" + this.customerId;
  }
}
