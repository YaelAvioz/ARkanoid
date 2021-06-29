package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/***
 * this class defines the background of level "Wide Easy".
 */
public class Background2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawRectangle(0, 0, 800, 600);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.pink);
        for (int i = 0; i < 70; i++) {
            d.drawLine(150, 150, i * 10, 300);
        }
        d.setColor(Color.yellow);
        d.drawCircle(150, 150, 60);
        d.fillCircle(150, 150, 60);
        d.setColor(Color.ORANGE);
        d.drawCircle(150, 150, 50);
        d.fillCircle(150, 150, 50);

    }

    @Override
    public void timePassed() {
    }
}