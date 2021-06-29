package game_settings;

import biuoop.DrawSurface;
import geometry.*;
import interfaces.*;;

/***
 * this class defines the level name.
 */
public class LevelName implements Sprite {

    private Rectangle rect;
    private java.awt.Color color;
    private String levelName;
    private int centerX;
    private int centerY;

    /***
     * this is the constructor.
     * @param levelName - the counter of the scores.
     * @param color -  the color of the rectangle.
     * @param centerX - the center of x.
     * @param centerY - the center of y.
     */
    public LevelName(String levelName, java.awt.Color color, int centerX, int centerY) {
        this.color = color;
        this.levelName = levelName;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /***
     * draw the image to the game.
     * @param surface - the draw of the block.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        String scoreString = String.format("Name: %s", levelName);
        surface.drawText(centerX, centerY, scoreString, 15);
    }

    /***
     * add the score to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
    }
}
