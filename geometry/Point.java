package geometry;

public class Point {

    private double x;
    private double y;

    /**
     * This is the constructor.
     *
     * @param x - the value for the X coordinate.
     * @param y - the value for the Y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - return the distance of this point to the other point.
     *
     * @param other - the point from which we want to calculate the distance.
     * @return distance between the points.
     */
    public double distance(Point other) {
        double distance = Math.pow((this.x - other.getX()), 2) + Math.pow((this.y - other.getY()), 2);
        return Math.sqrt(distance);
    }

    /**
     * equals - return true is the points are equal, false otherwise.
     *
     * @param other - the point for the equation.
     * @return true if the points are equals or false if not.
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * Return the x value of this point.
     *
     * @return the x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return the y value.
     */
    public double getY() {
        return this.y;
    }

    /***
     * set newY value.
     * @param newY - the newY value.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /***
     * set x value.
     * @param newX - the y value.
     */
    public void setX(double newX) {
        this.x = newX;
    }
}
