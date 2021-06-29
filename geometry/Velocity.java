package geometry;

public class Velocity {

    // initialization
    private double dx;
    private double dy;
    private Point p;

    /***
     * the constructor.
     * @param dx - the velocity on the x side.
     * @param dy - the velocity on the y side.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /***
     * gets speed and angel and return a point with the speed in x side and y side.
     * @param angle - the angle of the velocity.
     * @param speed - the speed of the ball.
     * @return point of velocity of x side and y side.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx;
        if (angle != 3 * Math.PI / 2) {
            dx = speed * Math.cos(angle);
        } else {
            dx = 0;
        }
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

    /***
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param applyPoint - the point the method gets.
     * @return the new point after moving it.
     */
    public Point applyToPoint(Point applyPoint) {
        return new Point(applyPoint.getX() + this.dx, applyPoint.getY() + this.dy);
    }

    /**
     * Return the dx value of the ball.
     *
     * @return the dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Return the dy value of the ball.
     *
     * @return the dy value.
     */
    public double getDy() {
        return this.dy;
    }
}
