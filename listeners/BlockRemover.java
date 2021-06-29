package listeners;

import game_settings.Counter;
import game_settings.GameLevel;
import interfaces.HitListener;
import sprites.*;

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /***
     * remove the block from the game.
     * @param game - the game.
     * @param removedBlocks - the removed block.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /***
     * remove the listener from the block that is being removed from the game.
     * @param beingHit - the block was hit.
     * @param hitter - The hitter parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
    }
}