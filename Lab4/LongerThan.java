/**
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class LongerThan implements BooleanCondition<String> {
  private int cutoff;

  public LongerThan(int cutoff) {
    this.cutoff = cutoff;
  }

  @Override
  public boolean test(String testing) {
    return testing.length() > cutoff;
  }
}
