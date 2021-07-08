package cs2030.fp;

/**
 * This class is an infinte list that delays computation until required.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 Special Term 1
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {
  /** The first value in list */
  private final Lazy<Maybe<T>> head;

  /** The rest of the list */
  private final Lazy<InfiniteList<T>> tail;

  /** Instance of empty list */
  private static InfiniteList<?> EMPTY =  (InfiniteList<?>) new EmptyList<>();

  /**
   * Default Constructor.
   */
  InfiniteList() { 
    head = null; 
    tail = null;
  }

  /**
   * Creates an infinite list with the same value.
   *
   * @param <T> Type of value in list.
   * @param producer Produces a value.
   * @return new InfiniteList.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    Lazy<Maybe<T>> tempHead = Lazy.of(() -> Maybe.some(producer.produce()));
    Lazy<InfiniteList<T>> tempTail = Lazy.of(() -> InfiniteList.generate(producer));
    return new InfiniteList<>(tempHead, tempTail);
  }

  /**
   * Creates an infinite list with values changing according to Transformer.
   *
   * @param <T> Type of value in list.
   * @param seed Initial value in list.
   * @param next Supply the next value in list.
   * @return new InfiniteList.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /**
   * Constructor of InfiniteList.
   *
   * @param head Initial value of list.
   * @param tail Creates the tail of list.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * Constructor of InfiniteList.
   *
   * @param head Head of list.
   * @param tail Tail of list.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Get the value at the head of list.
   *
   * @return value of the head.
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * Get the tail of the list.
   *
   * @return InfiniteList without the current head.
   */
  public InfiniteList<T> tail() { 
    return this.head.get().map(x -> this.tail.get())
      .orElseGet(() -> this.tail.get().tail()); 
  }

  /**
   * Transform the value in list from type T to type R.
   *
   * @param <R> The type of value after transforming.
   * @param mapper The Transformer to transform the value.
   * @return InfinitList of type R.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    Lazy<Maybe<R>> tempHead = this.head.map(x -> x.map(mapper));
    return new InfiniteList<>(tempHead, Lazy.of(() -> this.tail.get().map(mapper)));
  }

  /**
   * Checks if value in list satisfy condition.
   *
   * @param predicate The condition to test against.
   * @return The same list but value that fail test would hold no value.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> tempHead = this.head.map(x -> x.filter(predicate));
    return new InfiniteList<>(tempHead, 
        Lazy.of(() -> this.tail.get().filter(predicate)));
  }

  /**
   * Creates an empty InfiniteList.
   *
   * @param <T> Type of the empty InfiniteList.
   * @return empty InfiniteList.
   */
  public static <T> InfiniteList<T> empty() {
    //Since EMPTY is of type wildcard, can be cast to any
    //type. Safe to cast
    @SuppressWarnings("unchecked")
    InfiniteList<T> t = (InfiniteList<T>) EMPTY;
    return t;
  }

  /**
   * Gets a truncated list by specifying how many values if required.
   *
   * @param n Number of values to be truncated to.
   * @return a truncated InfiniteList.
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.empty();
    }
    Lazy<InfiniteList<T>> tempTail = Lazy.of(() -> this.head.get()
        .map(x -> this.tail.get().limit(n - 1))
        .orElseGet(() -> this.tail.get().limit(n)));
    return new InfiniteList<>(this.head, tempTail); 
  }

  /**
   * Truncate list until a value fail a test.
   *
   * @param predicate The condition to test against.
   * @return Truncated list which pass the test.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Boolean> failTest = this.head.filter(x -> x.filter(predicate).equals(Maybe.none()));
    Lazy<Boolean> toBeNeglected = this.head.filter(x -> x.equals(Maybe.none()));
    Lazy<Boolean> reallyFailTest = failTest.combine(toBeNeglected, (x, y) -> (x && !y));
    Lazy<Maybe<T>> newHead = reallyFailTest.map(x -> x ? Maybe.none() : this.head.get());
    Lazy<InfiniteList<T>> newTail = reallyFailTest.map(x -> x ? 
        InfiniteList.empty() : this.tail.get().takeWhile(predicate));
    return new InfiniteList<>(newHead, newTail);
  }

  /**
   * Whether list is empty.
   *
   * @return whether list is empty.
   */
  public boolean isEmpty() {
    return false;
  }

  /**
   * Combine all value in list to a single value.
   *
   * @param <U> Type of value to return.
   * @param identity Starting value before combining.
   * @param accumulator Specify how value are combined.
   * @return Value after combining all values.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    InfiniteList<T> list = this;
    U result = identity;
    while (!list.isEmpty()) {
      Maybe<T> current = list.head.get();
      if (!current.equals(Maybe.none())) {
        result = accumulator.combine(result, current.get());
      }
      list = list.tail.get();
    }  
    return result;
  }

  /**
   * Get how many values in list.
   *
   * @return number of values in list.
   */
  public long count() {
    return this.reduce(0L, (x, y) -> x + 1L);
  }

  /**
   * Change InfiniteList to an ArrayList.
   *
   * @return List version of the InfiniteList.
   */
  public List<T> toList() {
    ArrayList<T> array = new ArrayList<>();
    InfiniteList<T> infinite = this;
    while (!infinite.isEmpty()) {
      infinite.head.get().consumeWith(array::add);
      infinite = infinite.tail.get();
    }
    return array;
  }

  /**
   * String version of InfiniteList.
   *
   * @return string representation of InfiniteList.
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /**
   * An empty list that extends InfiniteList.
   * Override most methods in InfiniteList and have the same logic
   * just different return result.
   */
  private static class EmptyList<T> extends InfiniteList<T> {
    public boolean isEmpty() {
      return true;
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
      return InfiniteList.empty();
    }

    @Override 
    public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
      return InfiniteList.empty();
    }

    @Override
    public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
      return InfiniteList.empty();
    }

    @Override
    public InfiniteList<T> tail() {
      return InfiniteList.empty();
    }

    @Override
    public InfiniteList<T> limit(long n) {
      return InfiniteList.empty();
    }

    @Override
    public T head() {
      throw new NoSuchElementException();
    }

    @Override
    public String toString() {
      return "-";
    }
  }
}
