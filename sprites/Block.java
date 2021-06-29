package sprites;

import biuoop.DrawSurface;
import game_settings.*;
import geometry.*;
import interfaces.*;;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class defines a basic block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectBlock;
    private java.awt.Color color;
    private List<HitListener> hitList;

    /***
     * this is the constructor.
     * @param rect - the rectangle.
     * @param color - the color of the rectangle.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rectBlock = rect;
        this.color = color;
        this.hitList = new ArrayList<HitListener>();
    }

    /***
     * Returns the block.
     * @return this.rectBlock.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectBlock;
    }

    /***
     * Changes the velocity to a new velocity after its hits the block.
     * @param collisionPoint - the intersection point of the ball with the block lines.
     * @param currentVelocity - the velocity before the ball hits the block.
     * @return new velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point p = this.rectBlock.getUpperLeft();
        double left = p.getX();
        double right = p.getX();
        double top = p.getY();
        double bottom = p.getY();
        if (currentVelocity.getDx() < 0) {
            right += this.rectBlock.getWidth();
        } else {
            left += this.rectBlock.getWidth();
        }
        if (currentVelocity.getDy() < 0) {
            bottom += this.rectBlock.getHeight();
        } else {
            top += this.rectBlock.getHeight();
        }
        this.notifyHit(hitter);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (collisionPoint.getX() == left || collisionPoint.getX() == right) {
            return new Velocity(-1 * dx, dy);
        } else if (collisionPoint.getY() == top || collisionPoint.getY() == bottom) {
            return new Velocity(dx, -1 * dy);
        }
        return null;
    }

    /***
     * draw the block.
     * @param surface the draw of the block.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point upperLeft = this.rectBlock.getUpperLeft();
        double w = this.rectBlock.getWidth();
        double h = this.rectBlock.getHeight();
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) w, (int) h);
        surface.setColor(Color.black);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) w, (int) h);
    }

    @Override
    public void timePassed() {
    }

    /***
     * add the image to the game.
     * @param g - the game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /***
     * remove the collidable from the game.
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitList.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitList.remove(hl);
    }

    /***
     * notify hit.
     * @param hitter - the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitList);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}