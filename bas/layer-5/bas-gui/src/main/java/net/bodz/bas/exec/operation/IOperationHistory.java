package net.bodz.bas.exec.operation;

public interface IOperationHistory {

    int size();

    /** pointer to the operation which has just been rollbacked. */
    int getPosition();

    void moveTo(int position)
            throws OperationException;

    // utilities
    boolean canUndo();

    boolean canRedo();

    void undo()
            throws OperationException;

    void redo()
            throws OperationException;

}
