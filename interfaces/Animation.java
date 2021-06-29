package interfaces;

import biuoop.DrawSurface;

/***
 * defines animation.
 */
public interface Animation {
    /***
     * this method do one frame.
     * @param d - the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /***
     * stop the frame.
     * @return true or false.
     */
    boolean shouldStop();
}

