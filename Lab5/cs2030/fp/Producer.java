/**
 * CS2030 Lab 5
 * AY20/21 Special Term 1
 *
 * @author Austin Loh (Group B06)
 *
 * This is a functional interface that produces
 * a value
 * 
 * @param <T> type of value return
 */

package cs2030.fp;

public interface Producer<T> {
  T produce();
}
