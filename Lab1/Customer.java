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
  private int CustomerId;
  private double serviceTime;

  public Customer(double serviceTime) {
    this.CustomerId = Customer.numOfCustomers;
    Customer.numOfCustomers++;
    this.serviceTime = serviceTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public int getCustomerId() {
    return this.CustomerId;
  }

}
