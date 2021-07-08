/**
 * This class models an array which takes in classes
 * that have implemented the comparable interface.
 *
 * @author Austin Loh (Group B06)
 * @version CS2030 AY20/21 ST1
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {
    // The only way we can put an object into array is through
    // the method set() and we only put object of type T inside.
    // So it is safe to cast `Object[]` to `T[]`.
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Comparable[size];
    this.array = a; 
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public int length() {
    return this.array.length;
  }

  public T min() {
    if (this.array.length == 0) {
      return null;
    }
    T minimum = this.array[0];
    for (T item : array) {
      if (item.compareTo(minimum) == -1) {
        minimum = item;
      }
    }
    return minimum;
  }
}
