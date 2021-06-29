package sprites;

import biuoop.DrawSurface;
import game_settings.*;
import geometry.*;
import interfaces.*;;

import java.awt.Color;
import java.util.List;

/***
 * This is the ball class, it defines the balls properties and allows the user to move the ball
 * according to the velocity that it has. Also the ball is defined in a specific range in which it moves.
 */
public class Ball implements Sprite {
    // The class fields
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private int boardWidth;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitList;

    /***
     * The Ball constructor which receives the center, radius and color of the ball.
     * @param center - the ball center point.
     * @param r - the radios of the ball.
     * @param color - the color of the ball.
     * @param environment - the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = environment;
    }

    /***
     *The Ball constructor which receives the center in coordinates, the radius and the color of the ball.
     * @param x - the x value of the center point of the ball.
     * @param y - the y value of the center point of the ball.
     * @param r - the radius of the ball.
     * @param color - the color of the ball.
     * @param environment - the game environment.
     */
    public Ball(Double x, Double y, int r, Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = environment;
    }

    /**
     * Return the x values of the ball center point.
     *
     * @return the x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Return the y values of the ball center point.
     *
     * @return the y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Return the radius values of the ball.
     *
     * @return the r value.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Return the color of the ball.
     *
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Return the velocity value of the ball.
     *
     * @return the velocity value.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /***
     * Set a new velocity to the ball.
     * @param v - the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /***
     * Set a new velocity to the ball.
     * @param dx - the velocity on the x side
     * @param dy - the velocity on the y side
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /***
     * set the boundaries of the frame.
     * @param width - the bounds.
     */
    public void setBounds(int width) {
        this.boardWidth = width;
    }

    /***
     * Draw the ball on the given DrawSurface.
     * @param surface - the draw of the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);
    }

    /***
     * this method is running the method moveOneStep.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /***
     * Moves the ball one step according to the speed.
     */
    public void moveOneStep() {
        Line curTrajectory = this.getNewTrajectory();
        CollisionInfo newCollision = this.gameEnvironment.getClosestCollision(curTrajectory);
        if (newCollision != null) {
            int nSteps = calcSteps(newCollision.collisionPoint());
            if (nSteps <= 2) {
                Velocity newVel = newCollision.collisionObject().hit(
                        this, newCollision.collisionPoint(), this.velocity);
                this.setVelocity(newVel);
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /***
     * this is calculate the new trajectory of the ball.
     * @return the new trajectory.
     */
    public Line getNewTrajectory() {
        double endX, endY;
        double m = this.velocity.getDy() / this.velocity.getDx();
        double b = this.center.getY() - (this.center.getX() * m);
        if (this.velocity.getDx() != 0) {
            endX = this.center.getX() + 2 * this.getVelocity().getDx();
            endY = (m * endX) + b;
        } else {
            endX = this.center.getX();
            endY = this.center.getY() + 2 * this.getVelocity().getDy();
        }
        return new Line(this.center.getX(), this.center.getY(), endX, endY);
    }

    /***
     * calculate the steps to the calculate.
     * @param collisionPoint the hit point of the ball.
     * @return steps.
     */
    public int calcSteps(Point collisionPoint) {
        double y = this.center.getY();
        double colY = collisionPoint.getY();
        double dy = this.velocity.getDy();
        int steps = (int) ((colY - y) / dy);
        return steps;
    }

    /***
     * add the ball to the game.
     * @param g - the game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /***
     * remove the sprite from the game.
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}