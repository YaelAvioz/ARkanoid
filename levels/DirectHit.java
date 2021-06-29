package levels;

import backgrounds.Background1;
import game_settings.GameEnvironment;
import game_settings.GameLevel;
import interfaces.LevelInformation;
import geometry.*;
import interfaces.*;;
import sprites.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/***
 * this class defines direct hit.
 */
public class DirectHit implements LevelInformation {

    static final int BLOCK_HEIGHT = 20;
    static final int BLOCK_WIDTH = 20;

    /***
     * return the number of balls in this level.
     * @return 1.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /***
     * The initial velocity of each ball.
     * @return
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        velocityList.add(new Velocity(0, -1));
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
        return "Direct Hit";
    }

    /***
     * Returns a sprite with the background of the level.
     * @return
     */
    @Override
    public Sprite getBackground() {
        return new Background1();
    }

    /***
     * The Blocks that make up this level.
     * @return
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        Point point = new Point(370, 200);
        Rectangle rect = new Rectangle(point, BLOCK_WIDTH, BLOCK_HEIGHT);
        Block block = new Block(rect, Color.red);
        blocksList.add(block);
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
        Ball ball = new Ball(380.0, 400.0, 5, Color.white, environment);
        ball.addToGame(g);
        ball.setVelocity(this.initialBallVelocities().get(0));
        ball.setBounds(800);
    }
}