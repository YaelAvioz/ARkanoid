package interfaces;

import game_settings.GameEnvironment;
import game_settings.GameLevel;
import geometry.Velocity;
import sprites.Block;

import java.util.List;

/***
 * defines the level information.
 */
public interface LevelInformation {
    /***
     * the number of balls.
     * @return number of balls.
     */
    int numberOfBalls();

    /***
     * The initial velocity of each ball.
     * @return new velocity.
     */
    List<Velocity> initialBallVelocities();

    /***
     * the paddle speed.
     * @return the paddle speed.
     */
    int paddleSpeed();

    /***
     * The paddle width.
     * @return the paddle width.
     */
    int paddleWidth();

    /***
     * The level name will be displayed at the top of the screen.
     * @return the level name.
     */
    String levelName();

    /***
     * Returns a sprite with the background of the level.
     * @return sprite with the background of the level.
     */
    Sprite getBackground();

    /***
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /***
     * Number of blocks that should be removed.
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();

    /***
     * creat the balls.
     * @param environment - the game environment.
     * @param g - the game level.
     */
    void createBalls(GameEnvironment environment, GameLevel g);
}