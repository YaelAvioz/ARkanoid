package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/***
 * this class defines Key Press Stoppable Animation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;
    private boolean stop = false;

    /***
     * this is the constructor.
     * @param sensor - the keyboard sensor.
     * @param key - the string kry.
     * @param animation - the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    /***
     * do one frame of the game.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    /***
     * check if the game should stop.
     */
    public boolean shouldStop() {
        return stop;
    }
}
