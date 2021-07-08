/**
 * CS2030 Lab 5
 * AY20/21 Special Term 1
 *
 * @author Austin Loh (Group B06)
 *
 * This is a functional interface that transfrom a value
 * of type T to U
 * @param <T> intial type of value
 * @param <U> return type of value
 */

package cs2030.fp;

public interface Transformer<T, U> {
  U transform(T arg);
}
