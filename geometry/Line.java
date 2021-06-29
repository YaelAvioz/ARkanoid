package geometry;

import java.util.List;

/**
 * Line class.
 */
public class Line {

    private Point start;
    private Point end;
    private double m;
    private double b;

    /***
     * This is the constructor.
     * @param start -the value for the starting point of the line.
     * @param end the value for the ending point of the line.
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
        this.m = this.calcM();
        this.b = this.calcB();
    }

    /***
     *line - get the values of the X,Y coordinates and creat a line.
     * @param x1 - the value for the X coordinate of the starting point.
     * @param y1 - the value for the Y coordinate of the starting point.
     * @param x2 - the value for the X coordinate of the ending point.
     * @param y2 - the value for the Y coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
        this.m = this.calcM();
        this.b = this.calcB();
    }

    /***
     *calcM - calculate the Incline value of the line (y = mx + b).
     * @return m -the incline of the line
     */
    private double calcM() {
        double mSlope = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        if (mSlope == Double.POSITIVE_INFINITY || mSlope == Double.NEGATIVE_INFINITY) {
            mSlope = Double.POSITIVE_INFINITY;
        }
        return mSlope;

    }

    /***
     * calcB -  calculate the bias of the line (y = mx + b).
     * @return the b value.
     */
    private double calcB() {
        double bias = (this.start.getY() - this.m * this.start.getX());
        if (bias == Double.POSITIVE_INFINITY || bias == Double.NEGATIVE_INFINITY) {
            bias = Double.POSITIVE_INFINITY;
        }
        return bias;
    }

    /***
     * length - Return the length of the line.
     * @return the length value.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /***
     * middle - Returns the middle point of the line.
     * @return point(x, y) - the location of the middle point.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /***
     *Returns the start point of the line.
     * @return the location of start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /***
     *Returns the end point of the line.
     * @return the location of end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /***
     * return the incline of the line.
     * @return the m value.
     */
    public double getM() {
        return this.m;
    }

    /***
     * return the bias of the line.
     * @return the b value.
     */
    public double getB() {
        return this.b;
    }

    /***
     * Returns true if the lines intersect, false otherwise.
     * @param other - the line we want to compare with.
     * @return true or false.
     */
    public boolean isIntersecting(Line other) {
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());

        if (this.equals(other)) {
            return true;
        }
        if (this.end.getX() < other.start.getX() || (other.end.getX() < this.start.getX())) {
            return false;
        }
        if ((this.m == other.getM()) && (this.b == other.getB())) {
            if (this.m != Double.POSITIVE_INFINITY) {
                return true;
            } else {
                return this.start.getX() == this.start.getX();
            }
        }
        if ((this.m == other.getM()) && (this.b != other.getB())) {
            return false;
        }
        Point inter = this.intersectionWith(other);
        if (this.m == Double.POSITIVE_INFINITY) {
            if (!(thisMinY <= inter.getY() && thisMaxY >= inter.getY())) {
                return false;
            }
        }
        if (other.getM() == Double.POSITIVE_INFINITY) {
            if (!(otherMinY <= inter.getY() && otherMaxY >= inter.getY())) {
                return false;
            }
        }
        return (this.start.getX() <= inter.getX()) && (this.end.getX() >= inter.getX())
                && (other.start.getX() <= inter.getX()) && (other.end.getX() >= inter.getX());
    }

    /***
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other the line we want to compare with
     * @return the intersection point or null
     */
    public Point intersectionWith(Line other) {
        double x = 0;
        double y = 0;
        if (other.getM() != Double.POSITIVE_INFINITY && this.m != Double.POSITIVE_INFINITY) {
            x = ((this.b - other.getB()) / (other.getM() - this.m));
            y = (this.m * x) + this.b;
        } else if (other.getM() == Double.POSITIVE_INFINITY) {
            x = other.start().getX();
            y = (this.m * x) + this.b;
        } else if (this.getM() == Double.POSITIVE_INFINITY) {
            x = this.start().getX();
            y = (other.getM() * x) + other.getB();
        }
        return new Point(x, y);
    }

    /***
     * equals -- return true is the lines are equal, false otherwise.
     * @param other - the line we want to compare with.
     * @return true or false.
     */
    public boolean equals(Line other) {
        return ((this.start == other.start) && (this.end == other.end));
    }

    /***
     * return the closest point to the start of the line.
     * @param pointsList - the list of the intersection points.
     * @return the closest point to the start of the line.
     */
    public Point closestPoint(List<Point> pointsList) {
        Point closestP = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Point p : pointsList) {
            if (this.start.distance(p) <= dist) {
                dist = this.start.distance(p);
                closestP = new Point(p.getX(), p.getY());
            }
        }
        return closestP;
    }

    /***
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect - the closest intersection point to the start of the line.
     * @return null or rect.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointsList = rect.intersectionPoints(this);
        if (pointsList.isEmpty()) {
            return null;
        } else {
            return closestPoint(pointsList);
        }
    }
}