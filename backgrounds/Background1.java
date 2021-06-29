package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/***
 * this class defines the background of level "Direct Hit".
 */
public class Background1 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle(0, 0, 800, 600);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        d.drawCircle(380, 210, 100);
        d.drawCircle(380, 210, 70);
        d.drawCircle(380, 210, 40);
        d.drawLine(380, 80, 380, 200);
        d.drawLine(380, 220, 380, 340);
        d.drawLine(370, 210, 250, 210);
        d.drawLine(390, 210, 510, 210);
    }

    @Override
    public void timePassed() {
    }
}