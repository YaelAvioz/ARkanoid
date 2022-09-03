package listeners;

import biuoop.DrawSurface;
import game_settings.Counter;
import game_settings.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/***
 * this class defines the score.
 */
public class ScoreIndicator implements Sprite {

    private Rectangle rect;
    private java.awt.Color color;
    private Counter scoreCounter;

    /***
     * this is the constructor.
     * @param scoreCounter - the counter of the scores.
     * @param rect - the rectangle.
     * @param color -  the color of the rectangle.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        this.scoreCounter = scoreCounter;
    }

    /***
     * draw the image to the game.
     * @param surface - the draw of the block.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point upperLeft = this.rect.getUpperLeft();
        double w = this.rect.getWidth();
        double h = this.rect.getHeight();
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) w, (int) h);
        surface.setColor(Color.black);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) w, (int) h);
        int centerX = (int) ((int) upperLeft.getX() + w / 2 - 50);
        String scoreString = String.format("Score: %d", scoreCounter.getValue());
        surface.drawText(centerX, 15, scoreString, 15);
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
