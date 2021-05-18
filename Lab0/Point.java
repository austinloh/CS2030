/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2020/21
 *
 * The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */
class Point {
  private double x;
  private double y;
 
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
 
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
 
  public double getX() {
    return this.x;
  }
 
  public double getY() {
    return this.y;
  }
 
  public double distanceTo(Point p) {
  return Math.sqrt(Math.pow((this.x - p.getX()), 2) +
  Math.pow((this.y - p.getY()), 2));
  }
}

