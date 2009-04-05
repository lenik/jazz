package net.bodz.bas.rt;

public interface History {

    int size();

    /** pointer to the operation which has just been rollbacked. */
    int getPosition();

    void moveTo(int position) throws OperationException;

    // utilities
    boolean canUndo();

    boolean canRedo();

    void undo() throws OperationException;

    void redo() throws OperationException;

}
