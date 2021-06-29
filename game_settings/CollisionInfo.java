package game_settings;

import geometry.Point;
import interfaces.Collidable;

public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collidableObject;

    /***
     * this is the constructor.
     * @param collisionP - the collision Point.
     * @param collidableO - the collidable Object.
     */
    public CollisionInfo(Point collisionP, Collidable collidableO) {
        this.collisionPoint = collisionP;
        this.collidableObject = collidableO;
    }

    /***
     * Returns the point at which the collision occurs.
     * @return this.collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /***
     * Returns the collidable object involved in the collision.
     * @return this.collidableObject.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}