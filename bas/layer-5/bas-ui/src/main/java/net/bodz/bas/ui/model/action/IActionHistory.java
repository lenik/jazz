package net.bodz.bas.ui.model.action;

public interface IActionHistory {

    int size();

    /** pointer to the operation which has just been rollbacked. */
    int getPosition();

    /**
     * @return <code>false</code> if can't move to the specified position.
     */
    boolean moveTo(int position)
            throws PlaybackException, RollbackException;

    boolean canUndo();

    boolean canRedo();

    /**
     * @return <code>false</code> if can't undo.
     */
    boolean undo()
            throws RollbackException;

    /**
     * @return <code>false</code> if can't redo.
     */
    boolean redo()
            throws PlaybackException;

}
