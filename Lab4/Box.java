/**
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Box<T> {
  private final T field;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T field) {
    this.field = field;
  }

  public static <T> Box<T> of(T field) {
    if (field == null) {
      return null;
    }
    return new Box<T>(field);
  }

  public static <T> Box<T> empty() {
    //Since content of EMPTY_BOX is null 
    //and null can be cast to any type
    //Safe to cast
    @SuppressWarnings("unchecked")
    Box<T> temp = (Box<T>) EMPTY_BOX;
    return temp;
  }

  public boolean isPresent() {
    return this.field != null;
  }

  public static <T> Box<T> ofNullable(T field) {
    if (field == null) {
      return empty();
    }
    return Box.of(field);
  }

  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (!isPresent() || !condition.test(this.field)) {
      return empty();
    }
    return this;
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    if (!isPresent()) {
      return empty();
    }
    return Box.ofNullable(transformer.transform(this.field));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Box<?>) {
      //Already check that obj is of class Box
      //Safe to cast
      @SuppressWarnings("unchecked")
      Box<?> box = (Box<?>) obj;
      if (this.field == null) {
        return box.field == null;
      }
      return this.field.equals(box.field);  
    }
    return false;
  }

  @Override
  public String toString() {
    if (this.field != null) {
      return String.format("[%s]", this.field);
    }
    return "[]";
  }
}
