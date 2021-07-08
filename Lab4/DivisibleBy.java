/**
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class DivisibleBy implements BooleanCondition<Integer> {
  private int divider;

  public DivisibleBy(int divider) {
    this.divider = divider;
  }

  @Override
  public boolean test(Integer number) {
    return number % divider == 0;
  }
}
