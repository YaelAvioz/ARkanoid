package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/***
 * this class defines the background of level "Green3".
 */
public class Background3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.cyan);
        d.drawRectangle(0, 0, 800, 600);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawRectangle(60, 400, 90, 200);
        d.fillRectangle(60, 400, 90, 200);
        d.setColor(Color.white);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                d.drawRectangle(70 + (j * 20), 410 + (i * 30), 10, 20);
                d.fillRectangle(70 + (j * 20), 410 + (i * 30), 10, 20);
            }
        }
        d.setColor(Color.darkGray);
        d.drawRectangle(90, 360, 30, 40);
        d.fillRectangle(90, 360, 30, 40);
        d.setColor(Color.gray);
        d.drawRectangle(100, 200, 10, 160);
        d.fillRectangle(100, 200, 10, 160);
        d.setColor(Color.yellow);
        d.drawCircle(105, 190, 15);
        d.fillCircle(105, 190, 15);
        d.setColor(Color.orange);
        d.drawCircle(105, 190, 10);
        d.fillCircle(105, 190, 10);
        d.setColor(Color.red);
        d.drawCircle(105, 190, 5);
        d.fillCircle(105, 190, 5);
    }

    @Override
    public void timePassed() {
    }
}