package net.bodz.bas.ui.model.action;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.OutOfDomainException;

public abstract class AbstractActionHistory
        implements IActionHistory {

    IActionContext actionContext;

    protected abstract IAction get(int position);

    @Override
    public synchronized boolean moveTo(int p)
            throws PlaybackException, RollbackException {
        int size = size();
        if (p < 0)
            throw new OutOfDomainException("position", p, 0);
        if (p >= size)
            throw new OutOfDomainException("position", p, size);

        int current = getPosition();
        if (current == p)
            return true;

        while (p < current)
            if (!redo())
                return false;

        while (p > current)
            if (!undo())
                return false;

        return true;
    }

    @Override
    public boolean canUndo() {
        return getPosition() > 0;
    }

    @Override
    public boolean canRedo() {
        return getPosition() < size();
    }

    @Override
    public synchronized boolean undo()
            throws RollbackException {
        int p = getPosition();
        if (p <= 0)
            throw new IllegalUsageException("Can\'t undo more");

        IAction action = get(p);
        if (!action.canRollback())
            return false;

        action.rollback(actionContext);
        p--;
        return true;
    }

    @Override
    public synchronized boolean redo()
            throws PlaybackException {
        int p = getPosition();
        if (p >= size())
            throw new IllegalUsageException("Can\'t redo more");

        IAction action = get(p);
        if (!action.canPlay())
            return false;

        try {
            action.play(actionContext);
        } catch (Exception e) {
            throw new PlaybackException(e.getMessage(), e);
        }
        p++;
        return true;
    }

    protected boolean isChildScope(Object parentScope, Object childScope) {
        return false;
    }

}
