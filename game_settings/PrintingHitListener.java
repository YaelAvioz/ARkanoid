package game_settings;

import interfaces.*;;
import sprites.*;

public class PrintingHitListener implements HitListener {
    /***
     * print a meesage when the ball hit a block.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}