package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Define a basic rectangle.
 */
public class Rectangle {

    private double height;
    private double width;
    private Point upperLeft;
    private Line rightLine;
    private Line leftLine;
    private Line bottomLine;
    private Line topLine;
    private double x;
    private double y;

    // Create a new rectangle with location and width/height.

    /***
     * this is the constructor.
     * @param upperLeft - the upper-left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height -the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {

        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.setLines();
    }

    /***
     * defines the lines of the rectangle.
     */
    private void setLines() {
        this.rightLine = getRightLine();
        this.leftLine = getLeftLine();
        this.topLine = getTopLine();
        this.bottomLine = getBottomLine();
    }

    /***
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line - one of the rectangle.
     * @return pointsList - list of all the intersection points with the given line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<Point>();
        if (line.isIntersecting(this.rightLine)) {
            Point interPoint = line.intersectionWith(this.rightLine);
            pointsList.add(interPoint);
        }
        if (line.isIntersecting(this.leftLine)) {
            Point interPoint = line.intersectionWith(this.leftLine);
            pointsList.add(interPoint);
        }
        if (line.isIntersecting(this.topLine)) {
            Point interPoint = line.intersectionWith(this.topLine);
            if (interPoint.getY() != this.upperLeft.getY()) {
                interPoint.setY(this.upperLeft.getY());
            }
            pointsList.add(interPoint);
        }
        if (line.isIntersecting(this.bottomLine)) {
            Point interPoint = line.intersectionWith(this.bottomLine);
            if (interPoint.getY() != this.upperLeft.getY() + this.height) {
                interPoint.setY(this.upperLeft.getY() + this.height);
            }
            pointsList.add(interPoint);
        }
        return pointsList;
    }

    /***
     * Return the width of the rectangle.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /***
     * Return the height of the rectangle.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    /***
     * Returns the upper-left point of the rectangle.
     * @return point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /***
     * Returns the right line of the rectangle.
     * @return line.
     */
    public Line getRightLine() {
        x = this.upperLeft.getX();
        y = this.upperLeft.getY();
        Line line = new Line(x + this.width, y, x + this.width, y + this.height);
        return line;
    }

    /***
     * Returns the top line of the rectangle.
     * @return line.
     */
    public Line getTopLine() {
        x = this.upperLeft.getX();
        y = this.upperLeft.getY();
        Line line = new Line(x, y, x + this.width, y);
        return line;
    }

    /***
     * Returns the left line of the rectangle.
     * @return line.
     */
    public Line getLeftLine() {
        x = this.upperLeft.getX();
        y = this.upperLeft.getY();
        Line line = new Line(x, y, x, y + this.height);
        return line;
    }

    /***
     * Returns the bottom line of the rectangle.
     * @return line.
     */
    public Line getBottomLine() {
        x = this.upperLeft.getX();
        y = this.upperLeft.getY();
        Line line = new Line(x, y + this.height, x + this.width, y + this.height);
        return line;
    }

    /**
     * Set the upper left point of the rectangle.
     *
     * @param xPoint - new point to set.
     */
    public void setUpperLeft(double xPoint) {
        this.upperLeft = new Point(xPoint, this.upperLeft.getY());
        this.setLines();
    }

}