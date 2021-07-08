/**
 * CS2030 Lab 5
 * AY20/21 Special Term 1
 *
 * @author Austin Loh (Group B06)
 *
 * This is a functional interface that test whether
 * an argument satisfy condition
 *
 * @param <T> the type of value to test
 */

package cs2030.fp;

public interface BooleanCondition<T> {
  boolean test(T arg);
}
