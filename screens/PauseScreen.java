package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/***
 * this class defines the pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /***
     * this is the constructor.
     * @param k - the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /***
     * do one frame of the game.
     * @param d - the draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /***
     * check if the game should stop.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}