package interfaces;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

public interface Collidable {
    /***
     * Return the "collision shape" of the object.
     * @return rectangle.
     */
    Rectangle getCollisionRectangle();

    /***
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter - the ball.
     * @param collisionPoint - the intersection point between the objects.
     * @param currentVelocity - the current velocity of the object.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}