package net.bodz.bas.c.action;

public interface IActionHistory {

    int size();

    /** pointer to the operation which has just been rollbacked. */
    int getPosition();

    void moveTo(int position)
            throws PlaybackException;

    // utilities
    boolean canUndo();

    boolean canRedo();

    void undo()
            throws PlaybackException;

    void redo()
            throws PlaybackException;

}
