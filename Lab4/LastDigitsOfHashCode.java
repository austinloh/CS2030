/**
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private int k;

  public LastDigitsOfHashCode(int k) {
    int result = 1;
    for (int i = 0; i < k; i++) {
      result *= 10;
    }
    this.k = result;
  }

  @Override
  public Integer transform(Object arg) {
    return Math.abs(arg.hashCode() % this.k);
  }
}
