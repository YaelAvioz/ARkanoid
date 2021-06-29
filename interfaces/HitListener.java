package interfaces;

import sprites.*;

public interface HitListener {
    /***
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block was hit.
     * @param hitter - The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}