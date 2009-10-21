package net.bodz.bas.util;

import net.bodz.bas.io.term.Terminal;

public abstract class ArrayLogTerm extends LogTerm {

    private Terminal[] array;

    public ArrayLogTerm(Terminal... array) {
        setArray(array);
    }

    public Terminal[] getArray() {
        return array;
    }

    public void setArray(Terminal[] array) {
        if (array == null)
            throw new NullPointerException("array"); //$NON-NLS-1$
        this.array = array;
    }

    protected abstract int getIndexOf(int level);

    @Override
    public final Terminal filter(int level) {
        int targetIndex = getIndexOf(level);
        Terminal target = array[targetIndex];
        return target;
    }

}
