import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;
import game_settings.*;
import interfaces.*;
import levels.*;

/**
 * Main class.
 */
public class Ass6Game {

    static final int BOARD_WIDTH = 800;
    static final int BOARD_HEIGHT = 600;
    static final int FPS = 60;

    /***
     * the main of the game class.
     * @param args - the parameters.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", BOARD_WIDTH, BOARD_HEIGHT);
        int framesPerSecond = FPS;
        Sleeper sleeper = new Sleeper();
        AnimationRunner animationRunner = new AnimationRunner(gui, framesPerSecond, sleeper);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        GameLevel gameLevel = new GameLevel(levels.get(0), gui, sleeper, framesPerSecond,
                keyboardSensor, animationRunner);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gameLevel, gui, sleeper, framesPerSecond);
        gameFlow.runLevels(levels);
    }
}
