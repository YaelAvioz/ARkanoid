package game_settings;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.*;
import interfaces.*;;
import listeners.*;
import screens.*;
import sprites.*;

import java.awt.Color;

/***
 * the game level class.
 */
public class GameLevel implements Animation {

    static final int BOARD_WIDTH = 800;
    static final int BOARD_HEIGHT = 600;
    static final int BOARDER_SHOT_SIDE = 10;

    static final int PADDLE_HEIGHT = 20;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private ScoreTrackingListener scoreTrackingListener;
    private boolean shouldStop;
    private int framesPerSecond;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /***
     * this is the constructor.
     * @param levelInformation - the level information.
     * @param gui - the gui.
     * @param sleeper - the sleeper.
     * @param framesPerSecond - frames per seconds.
     * @param keyboard - the keyboard sensor.
     * @param animationRunner - the animation runner.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, Sleeper sleeper, int framesPerSecond,
                     KeyboardSensor keyboard, AnimationRunner animationRunner) {
        this.levelInformation = levelInformation;
        this.gui = gui;
        this.sleeper = sleeper;
        this.framesPerSecond = framesPerSecond;
        this.keyboard = keyboard;
        this.runner = animationRunner;
    }

    /***
     * add a new collidable to the list.
     * @param c - the collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /***
     * remove the collidable.
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /***
     * remove the sprite.
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /***
     * add new sprite to the list.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /***
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     * @param scoreCounter - the score counter.
     */
    public void initialize(Counter scoreCounter) {
        int width = BOARD_WIDTH;
        int height = BOARD_HEIGHT;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        DrawSurface d = gui.getDrawSurface();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.shouldStop = false;
        this.sprites.addSprite(levelInformation.getBackground());
        //this part prints the frame
        //score
        this.scoreTrackingListener = new ScoreTrackingListener(scoreCounter);

        Point topScore = new Point(0, 0);
        Rectangle rectScore = new Rectangle(topScore, width + BOARDER_SHOT_SIDE, 3 * BOARDER_SHOT_SIDE);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter, rectScore, Color.white);
        scoreIndicator.addToGame(this);
        new LevelName(levelInformation.levelName(), Color.black, 600, 15).addToGame(this);

        //boundaries
        Point top = new Point(0, 20);
        Rectangle rectLeft = new Rectangle(top, BOARDER_SHOT_SIDE, height);
        Block leftFrame = new Block(rectLeft, Color.gray);
        leftFrame.addToGame(this);

        Rectangle rectTop = new Rectangle(top, width + 10, BOARDER_SHOT_SIDE);
        Block topFrame = new Block(rectTop, Color.gray);
        topFrame.addToGame(this);

        Point right = new Point(width - BOARDER_SHOT_SIDE, BOARDER_SHOT_SIDE + 20);
        Rectangle rectRight = new Rectangle(right, BOARDER_SHOT_SIDE, height - 20);
        Block rightFrame = new Block(rectRight, Color.gray);
        rightFrame.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);

        Point bottom = new Point(0, height - BOARDER_SHOT_SIDE);
        Rectangle rectBottom = new Rectangle(bottom, width, BOARDER_SHOT_SIDE);
        Block bottomFrame = new Block(rectBottom, Color.gray);
        bottomFrame.addHitListener(ballRemover);
        bottomFrame.addToGame(this);

        //this part prints the ball
        levelInformation.createBalls(this.environment, this);

        this.remainingBalls.increase(levelInformation.numberOfBalls());

        Color[] colors = {Color.blue, Color.magenta, Color.pink, Color.orange, Color.green, Color.cyan};

        //this part prints the paddle
        Point paddlePoint = new Point(400 - (levelInformation.paddleWidth() / 2),
                height - BOARDER_SHOT_SIDE - PADDLE_HEIGHT);
        Rectangle paddleRect = new Rectangle(paddlePoint, levelInformation.paddleWidth(), PADDLE_HEIGHT);
        Paddle paddle = new Paddle(paddleRect, Color.ORANGE, gui, levelInformation.paddleSpeed());
        paddle.addToGame(this);

        PrintingHitListener print = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);

        //this part prints the blocks
        for (Block b : levelInformation.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(this.scoreTrackingListener);
            this.remainingBlocks.increase(1);
            b.addToGame(this);
        }
    }

    /***
     * run the game.
     */
    public void run() {
        this.shouldStop = false;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);

        if (remainingBalls.getValue() == 0) {
            this.shouldStop = true;
        }
        if (remainingBlocks.getValue() == 0) {
            this.scoreTrackingListener.getCounter().increase(100);
            this.shouldStop = true;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /***
     * check if exist balls and blocks.
     * @return true or false.
     */
    public boolean existBallsAndBlocks() {
        return remainingBalls.getValue() > 0 && remainingBlocks.getValue() > 0;
    }

    /***
     * check if exist balls or blocks.
     * @return true or false.
     */
    public boolean remainingBalls() {
        return remainingBalls.getValue() > 0;
    }
}