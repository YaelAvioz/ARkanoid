package interfaces;

import biuoop.DrawSurface;

/***
 * sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - the object.
     */
    void drawOn(DrawSurface d);

    /***
     * notify the sprite that time has passed.
     */
    void timePassed();

}
