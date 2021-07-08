package cs2030.fp;

/**
 * This class delays computation until required.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 Special Term 1
 *
 */
public class Lazy<T> {
  /** The interface to produce a value */
  private Producer<T> producer;

  /** A wrapper class that encapsulate a value that maybe null */
  private Maybe<T> value;

  /**
   * Constructor of the Lazy class.
   *
   * @param value The wrapper class containing value.
   * @param producer The producer produces a value if not evaluated.
   */
  private Lazy(Maybe<T> value, Producer<T> producer) {
    this.producer = producer;
    this.value = value;
  }

  /**
   * Static factory method of class. 
   * Used when producer is not given.
   *
   * @param <T> The type of the value to encapsulate.
   * @param v The value to encapsulate.
   * @return new Lazy instance.
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(Maybe.some(v), null);
  }

  /**
   * Static factory method of class.
   * Used when value if not given.
   *
   * @param <T> The type of the value that producer would produce.
   * @param s The producer that produce value
   * @return new Lazy instance.
   */
  public static <T> Lazy<T> of(Producer<T> s) {
    return new Lazy<T>(Maybe.none(), s);
  }

  /**
   * Returns value encapsulated. 
   * If value has not been evaluated, producer is called
   * and result is stored, so no further evaluation is 
   * needed.
   *
   * @return The value encapsulated
   */
  public T get() {
    this.value = Maybe.some(this.value.orElseGet(this.producer));
    this.producer = null;
    return this.value.orElseGet(this.producer);
  }

  /**
   * Apply a Transformer on the value encapsulated, to get a new value.
   * New instance is created.
   *
   * @param <U> The type of the returned value after transforming.
   * @param transformer The transformer to transform value.
   * @return New Lazy instance with new value not evaluated yet.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()));
  }

  /**
   * Apply a Transformer on value encapsulated, to get a Lazy instance.
   *
   * @param <U> The type of the new encapsulated value.
   * @param transformer The Transformer to transform value.
   * @return New Lazy instance with value not evaluated.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>>
      transformer) {
    return Lazy.of(() -> transformer.transform(this.get()).get());
  }

  /**
   * Check whether encapsulated value satisfy condition.
   *
   * @param condition The BooleanCondition to test value.
   * @return New Lazy instance, with new Boolean value representing
   * whether value satisfy condition.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return Lazy.of(() -> condition.test(this.get()));
  }

  /**
   * Combine 2 Lazy instances with a Combiner to return a 
   * new Lazy instance.
   *
   * @param <R> The type of value that return Lazy encapsulate.
   * @param <S> The type of value that Lazy encapsulate, to be combined.
   * @param obj The other Lazy instance to be combined.
   * @param combiner The Combiner to combine 2 Lazy instance.
   * @return A new Lazy instance which combine value of 2 other Lazy instances.
   */
  public <S, R> Lazy<R> combine(Lazy<S> obj, 
      Combiner<? super T, ? super S, ? extends R> combiner) {
    return Lazy.of(() -> combiner.combine(this.get(), obj.get()));
  }

  /**
   * Override toString method in Object. 
   * Return value if evaluated, else return "?".
   *
   * @return The String representation of the Lazy instance.
   */
  @Override
  public String toString() {
    if (this.producer != null) {
      return "?";
    }
    T result = this.value.orElseGet(this.producer);
    if (result == null) {
      return "null";
    }
    return result.toString();
  }

  /**
   * Overrides the equals method in Object.
   * Checks if value in Lazy instance in equal to 
   * value of another object if it is also a Lazy 
   * instance.
   *
   * @param obj The other object to compare with.
   * @return Whether values are equal.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Lazy) {
      //Checked that obj is of class Lazy
      //Safe to cast
      @SuppressWarnings("unchecked")
      Lazy<?> other = (Lazy<?>) obj;
      if (this.get() == null) {
        return other.get() == null;
      }
      return this.get().equals(other.get());
    }
    return false;
  }
}
