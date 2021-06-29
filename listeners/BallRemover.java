package listeners;

import game_settings.Counter;
import game_settings.GameLevel;
import interfaces.*;;
import sprites.*;


public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /***
     * this is the constructor.
     * @param game - the game.
     * @param removedBalls - the ball.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /***
     * decrease the balls counter and remove the ball from the game.
     * @param beingHit - the block was hit.
     * @param hitter - The hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
