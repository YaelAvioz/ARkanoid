package sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game_settings.*;
import geometry.*;
import interfaces.*;;

import java.awt.Color;

/***
 * The paddle class implementation.
 */
public class Paddle implements Sprite, Collidable {

    static final int BOARD_WIDTH = 800;
    static final int BOARD_HEIGHT = 600;
    static final int BOARDER_SHOT_SIDE = 10;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int paddleSpeed;

    /**
     * The constructor.
     *
     * @param rectangle   - The paddle rectangle.
     * @param color       - The paddle color.
     * @param gui         - The game gui.
     * @param paddleSpeed - the paddle speed.
     */
    public Paddle(Rectangle rectangle, Color color, GUI gui, int paddleSpeed) {
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = rectangle;
        this.color = color;
        this.paddleSpeed = paddleSpeed;
    }

    /***
     * moves the paddle left.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() >= BOARDER_SHOT_SIDE) {
            this.rectangle.setUpperLeft(this.rectangle.getUpperLeft().getX() - paddleSpeed);
        }
    }

    /***
     * moves the paddle right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() <= BOARD_WIDTH - BOARDER_SHOT_SIDE - rectangle.getWidth()) {
            this.rectangle.setUpperLeft(this.rectangle.getUpperLeft().getX() + paddleSpeed);
        }
    }

    /**
     * Sprite time pass method implementation.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /***
     * draw the paddle.
     * @param surface - the draw of the paddle.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point upperLeft = this.rectangle.getUpperLeft();
        double w = this.rectangle.getWidth();
        double h = this.rectangle.getHeight();
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) w, (int) h);
        surface.setColor(Color.black);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) w, (int) h);
    }

    /**
     * getCollisionRectangle Collidable method implementation.
     *
     * @return paddle rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /***
     * hit Collidable method implementation.
     * @param hitter - the ball.
     * @param collisionPoint - the intersection point between the objects.
     * @param currentVelocity - the current velocity of the object.
     * @return new velocity after hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double fifthPaddle = this.rectangle.getWidth() / 5;
        Point p = this.rectangle.getUpperLeft();
        double left = p.getX();
        double right = p.getX() + this.rectangle.getWidth();
        double top = p.getY();
        double bottom = p.getY() + this.rectangle.getHeight();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double vSize = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        if (collisionPoint.getX() == left || collisionPoint.getX() == right) {
            return new Velocity(-1 * dx, dy);
        } else if (collisionPoint.getX() < left + fifthPaddle) {
            //-60 deg
            return Velocity.fromAngleAndSpeed(Math.PI * 210 / 180, vSize);

        } else if (collisionPoint.getX() < left + (fifthPaddle * 2) && collisionPoint.getX() > left + fifthPaddle) {
            //-30 deg
            return Velocity.fromAngleAndSpeed(Math.PI * 240 / 180, vSize);
        } else if (collisionPoint.getX() < left + (fifthPaddle * 3)
                && collisionPoint.getX() > left + (fifthPaddle * 2)) {
            //180 deg
            return Velocity.fromAngleAndSpeed(Math.PI * 3 / 2, vSize);

        } else if (collisionPoint.getX() < left + (fifthPaddle * 4)
                && collisionPoint.getX() > left + (fifthPaddle * 3)) {
            //30 deg
            return Velocity.fromAngleAndSpeed(Math.PI * 300 / 180, vSize);
        } else if (collisionPoint.getX() < left + (fifthPaddle * 5)) {
            //60 deg
            return Velocity.fromAngleAndSpeed(Math.PI * 330 / 180, vSize);
        }
        return null;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - The game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}