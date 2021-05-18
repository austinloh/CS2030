/**
* @author AustinLoh (Group B06)
*/
import java.util.Random;
 
class RandomPoint extends Point {
  public static Random rng = new Random(1);

  public RandomPoint(double minX, double maxX, double minY, double maxY) {
    super(minX + (maxX - minX) * rng.nextDouble(),
    minY + (maxY - minY) * rng.nextDouble());
  }
 
  public static void setSeed(int x) {
    RandomPoint.rng = new Random(x);
  }
}

