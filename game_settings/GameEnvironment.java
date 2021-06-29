package game_settings;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/***
 * the GameEnviroment class.
 */
public class GameEnvironment {

    // add the given collidable to the environment.
    private List<Collidable> collidablesList = new ArrayList<Collidable>();

    /***
     * add a new collidable to the list.
     * @param c - the new collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidablesList.add(c);
    }

    /***
     * remove the collidable.
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesList.remove(c);
    }

    /***
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - the ball path.
     * @return null - if there is no intersection point else return the collisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDist = Double.POSITIVE_INFINITY;
        double curDist;
        Point nearestPoint = null;
        Collidable nearestCollidable = null;
        for (Collidable c : this.collidablesList) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                curDist = trajectory.start().distance(p);
                if (curDist < minDist) {
                    nearestPoint = p;
                    nearestCollidable = c;
                    minDist = curDist;
                }
            }
        }

        if (nearestPoint != null) {
            return new CollisionInfo(nearestPoint, nearestCollidable);
        } else {
            return null;
        }
    }
}