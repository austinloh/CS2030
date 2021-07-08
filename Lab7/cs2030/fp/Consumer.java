package cs2030.fp;

@FunctionalInterface
public interface Consumer<T> {
  void consume(T t);
}
