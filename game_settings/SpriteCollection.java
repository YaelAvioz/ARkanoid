package game_settings;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/***
 * SpriteCollection class.
 */
public class SpriteCollection {

    private List<Sprite> spriteList = new ArrayList<Sprite>();

    /***
     * this method add a new sprite to the list.
     * @param s - the new sprite.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /***
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s : spritesList) {
            s.timePassed();
        }
    }

    /***
     * call drawOn(d) on all sprites.
     * @param d - the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }

    /***
     * remove the sprite.
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}