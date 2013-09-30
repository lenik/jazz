package net.bodz.bas.exec.operation;

public interface IOperationRecord {

    /**
     * @return <code>null</code> if no snapshot.
     */
    Object getSnapshotTarget();

    /**
     * Each succeeded execution will be add to the history.
     */
    void execute()
            throws OperationException;

    /**
     * The history manager guarantees rollback only once for each recorded operation.
     */
    void rollback()
            throws OperationException;

}
