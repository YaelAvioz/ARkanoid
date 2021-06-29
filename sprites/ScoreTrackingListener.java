package sprites;

import game_settings.Counter;
import interfaces.HitListener;

public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /***
     * this is the constructor.
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /***
     * add 5 points when the ball hit the blocks.
     * @param beingHit - the block was hit.
     * @param hitter - the ball was hitter.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.removeHitListener(this);
    }

    /***
     * the current number of scores.
     * @return the current score.
     */
    public Counter getCounter() {
        return this.currentScore;
    }
}