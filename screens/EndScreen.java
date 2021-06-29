package screens;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game_settings.*;
import interfaces.Animation;

/***
 * this class defines the end screen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private GUI gui;
    private Counter scoreCounter;
    private GameLevel level;

    /***
     * this is the constructor.
     * @param k - the keyboard sensor.
     * @param scoreCounter - the score counter.
     * @param gui - the gui.
     * @param level - the game level.
     */
    public EndScreen(KeyboardSensor k, Counter scoreCounter, GUI gui, GameLevel level) {
        this.keyboard = k;
        this.scoreCounter = scoreCounter;
        this.gui = gui;
        this.level = level;
    }

    @Override
    /***
     * this method do one frame in the game.
     */
    public void doOneFrame(DrawSurface d) {
        if (!level.remainingBalls()) {
            d.drawText(10, d.getHeight() / 2,
                    String.format("Game Over!\n Your score is %d", scoreCounter.getValue()), 32);
        } else {

            d.drawText(10, d.getHeight() / 2,
                    String.format("You Win!\n Your score is %d", scoreCounter.getValue()), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            gui.close();
        }
    }

    @Override
    /***
     * check if the game should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}