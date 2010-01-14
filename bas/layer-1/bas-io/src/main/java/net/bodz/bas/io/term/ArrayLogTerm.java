package net.bodz.bas.io.term;



public abstract class ArrayLogTerm extends LogTerm {

    private ITerminal[] array;

    public ArrayLogTerm(ITerminal... array) {
        setArray(array);
    }

    public ITerminal[] getArray() {
        return array;
    }

    public void setArray(ITerminal[] array) {
        if (array == null)
            throw new NullPointerException("array"); 
        this.array = array;
    }

    protected abstract int getIndexOf(int level);

    @Override
    public final ITerminal filter(int level) {
        int targetIndex = getIndexOf(level);
        ITerminal target = array[targetIndex];
        return target;
    }

}
