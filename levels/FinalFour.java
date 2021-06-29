package levels;

import backgrounds.Background4;
import game_settings.GameEnvironment;
import game_settings.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import sprites.*;

/***
 * this class define the "Final Four" level.
 */
public class FinalFour implements LevelInformation {
    static final int BOARD_WIDTH = 800;
    static final int BOARDER_SHOT_SIDE = 10;

    static final int BLOCK_HEIGHT = 20;
    static final int BLOCK_WIDTH = (BOARD_WIDTH - (2 * BOARDER_SHOT_SIDE)) / 15;

   private Color[] colors = {Color.red, Color.magenta, Color.pink, Color.cyan,
           Color.green, Color.orange, Color.lightGray};

    /***
     * return the number of balls in this level.
     * @return 1.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /***
     * The initial velocity of each ball.
     * @return
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(new Velocity(0, -1));
        }
        return velocityList;
    }

    /***
     * the paddle speed.
     * @return paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 1;
    }

    /***
     * return the paddle width.
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return 150;
    }

    /***
     * The level name will be displayed at the top of the screen.
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /***
     * Returns a sprite with the background of the level.
     * @return
     */
    @Override
    public Sprite getBackground() {
        return new Background4();
    }

    /***
     * The Blocks that make up this level.
     * @return
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < 15; j++) {
                Point point = new Point(j * BLOCK_WIDTH + BOARDER_SHOT_SIDE,
                        100 + (i * BLOCK_HEIGHT));
                Rectangle rect = new Rectangle(point, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, colors[i]);
                blocksList.add(block);
            }
        }
        return blocksList;
    }

    /***
     * Number of blocks that should be removed.
     * @return number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public void createBalls(GameEnvironment environment, GameLevel g) {
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Ball ball = new Ball(330.0 + (i * 60), 400.0, 5, Color.white, environment);
            ball.addToGame(g);
            ball.setVelocity(this.initialBallVelocities().get(i));
            ball.setBounds(800);
        }
    }
}