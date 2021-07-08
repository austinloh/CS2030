/**
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T arg) {
    return Box.of(arg);
  }
}
