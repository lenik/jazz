package net.bodz.bas.rt;

import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.OutOfDomainException;

public abstract class _History implements History {

    protected abstract Operation get(int position);

    @Override
    public void moveTo(int p) {
        int size = size();
        if (p < 0)
            throw new OutOfDomainException("position", p, 0);
        if (p >= size)
            throw new OutOfDomainException("position", p, size);

        int current = getPosition();
        if (current == p)
            return;
        Object largest = null;
        if (p < current) {
            for (int i = p; i < current; i++) {

            }
        }
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
    public void undo() throws OperationException {
        int p = getPosition();
        if (p <= 0)
            throw new IllegalUsageException("Can't undo more");
        moveTo(p - 1);
    }

    @Override
    public void redo() throws OperationException {
        int p = getPosition();
        if (p >= size())
            throw new IllegalUsageException("Can't redo more");
        moveTo(p + 1);
    }

    protected boolean isChildScope(Object parentScope, Object childScope) {
        return false;
    }

}
