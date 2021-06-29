package game_settings;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import interfaces.LevelInformation;
import screens.EndScreen;
import screens.KeyPressStoppableAnimation;

import java.util.List;

/***
 * this class defines game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInformation;
    private GameLevel gameLevel;
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /***
     * this is  the constructor.
     * @param ar - the animation runner.
     * @param ks - thr keyboard sensor.
     * @param gameLevel - the game level.
     * @param gui - the gui.
     * @param sleeper - the sleeper.
     * @param framesPerSecond - framed per second.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GameLevel gameLevel, GUI gui,
                    Sleeper sleeper, int framesPerSecond) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gameLevel = gameLevel;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
        this.gui = gui;
    }

    /***
     * this runs levels.
     * @param levels - the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter scoreCounter = new Counter(0);
        for (LevelInformation levelInfo : levels) {

            this.gameLevel = new GameLevel(levelInfo, this.gui, this.sleeper, this.framesPerSecond,
                    this.keyboardSensor, this.animationRunner);

            this.gameLevel.initialize(scoreCounter);

            while (this.gameLevel.existBallsAndBlocks()) {
                this.gameLevel.run();
            }

            if (!this.gameLevel.remainingBalls()) {
                break;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(keyboardSensor, scoreCounter, gui, this.gameLevel)));

    }
}