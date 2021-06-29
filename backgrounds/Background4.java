package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/***
 * this class defines the background of level "Final Four".
 */
public class Background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.drawRectangle(0, 0, 800, 600);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.gray);
        for (int i = 0; i < 2; i++) {
            d.drawCircle(100 + (450 * i), 400, 30);
            d.fillCircle(100 + (450 * i), 400, 30);
            d.drawCircle(150 + (450 * i), 420, 40);
            d.fillCircle(150 + (450 * i), 420, 40);
            d.drawCircle(170 + (450 * i), 450, 35);
            d.fillCircle(170 + (450 * i), 450, 35);
            d.drawCircle(120 + (450 * i), 440, 35);
            d.fillCircle(120 + (450 * i), 440, 35);
            d.drawCircle(200 + (450 * i), 430, 30);
            d.fillCircle(200 + (450 * i), 430, 30);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(550 + (10 * i), 400, 530 + (10 * i), 560);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + (10 * i), 400, 80 + (10 * i), 560);
        }
    }

    @Override
    public void timePassed() {
    }
}