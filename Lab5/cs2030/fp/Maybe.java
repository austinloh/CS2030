package cs2030.fp;

import java.util.NoSuchElementException;

/**
 * CS2030 Lab 5
 * AY20/21 Special Term 1
 *
 * @author Austin Loh (Group B06)
 *
 * A container which may or may not contain a value.
 *
 * @param <T> type of value
 */
public abstract class Maybe<T> {
  private static final None NONE = new None();

  public static <T> Maybe<T> none() {
    // Since None contains no value, it can be cast
    // to any type. Safe to cast
    @SuppressWarnings("unchecked")
    Maybe<T> t = (Maybe<T>) NONE;
    return t;
  }

  public static <T> Some<T> some(T t) {
    return new Some<T>(t);
  }

  /**
   * If value of t is null, return None, else return Some
   *
   * @param T the type of value
   * @return a Maybe containing the value or lack thereof
   */
  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return Maybe.none();
    }
    return Maybe.some(t);
  }

  /**
   * If value in Maybe is missing, return NoSuchelementException,
   * or value stored.
   *
   * @return value in Maybe
   */
  protected abstract T get();

  //this shit also
  /**
   * Takes in BooleanCondition to test value of Maybe if exist
   *
   * @param BooleanCondition to test on a value
   * @return a Maybe with value if pass the test, or a empty Maybe
   */
  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);

  /**
   * Takes in a transformer to transform value in Maybe if exist
   *
   * @param transformer the Transformer to apply to value, if exist
   * @return a Maybe with value transformed, or empty Maybe or Exception
   */
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  /**
   * Takes in a transformer to transform value in Maybe and will return a Maybe<?>
   *
   * @param transformer the Transformer to apply to value, if exist
   * @return a Maybe with a value inside that cannot be another Maybe
   */
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends 
      Maybe<? extends U>> transformer);

  /**
   * return value if present, else arg
   *
   * @param arg return this if value not present
   * @return value if present, else arg
   */
  public abstract T orElse(T arg);

  /**
   * If value not present, return value produce by producer
   *
   * @param producer produce a value if value in Maybe not present
   * @return value in Maybe if exist, else value produced by producer
   */
  public abstract T orElseGet(Producer<? extends T> producer);

  /**
   * A class that contains no value inside
   */
  public static final class None extends Maybe<Object> {
    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof None);
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      return Maybe.none();
    }
    
    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> transformer) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, 
        ? extends Maybe<? extends U>> transformer) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object arg) {
      return arg;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }
    
  }

  /**
   * A class that contains a value inside.
   */
  public static final class Some<T> extends Maybe<T> {
    private final T value;

    public Some(T t) {
      this.value = t;
    }

    @Override
    public String toString() {
      return "[" + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if (obj instanceof Some) {
        //Checked that obj is of class Some
        //Safe to cast
        @SuppressWarnings("unchecked")
        Some<?> some = (Some<?>) obj;
        if (this.value == null) {
          return some.value == null;
        }
        return this.value.equals(some.value);
      }
      return false;
    }

    @Override
    protected T get() {
      return this.value;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.value != null && !condition.test(this.value)) {
        return Maybe.none();
      }
      return this;
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) 
        throws NullPointerException {
      return Maybe.some(transformer.transform(this.value));
    }
   
    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, 
        ? extends Maybe<? extends U>> transformer) {
      //transformer return type is Maybe<? extends U> as defined in scope.
      //Safe to cast to Maybe<U>
      @SuppressWarnings("unchecked")
      Maybe<U> canHelpMe = (Maybe<U>) transformer.transform(this.value);
      return canHelpMe;
    }

    @Override
    public T orElse(T arg) {
      return this.value;
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.value;
    }
  }
}
