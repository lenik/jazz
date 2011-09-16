package net.bodz.bas.mem;

public abstract class AbstractRefType extends AbstractType implements RefType {

    protected final Type targetType;

    public AbstractRefType(Type targetType) throws AccessException {
        this.targetType = targetType;
    }

    /**
     * @return memory position of the referent
     */
    @Override
    public abstract MemoryWrapOffset get(Memory memory, int offset) throws AccessException;

    @Override
    public void put(Memory memory, int offset, Object targetAddr) throws AccessException {
        MemoryWrapOffset target = (MemoryWrapOffset) targetAddr;
        Memory targetMemory = target.getOrig();
        int targetOffset = target.getOffset();
        if (memory == targetMemory)
            putLocal(memory, offset, targetOffset);
        else
            putRemote(memory, offset, targetMemory, targetOffset);
    }

    protected void putLocal(Memory memory, int offset, int targetOffset) throws AccessException {
        putRemote(memory, offset, memory, targetOffset);
    }

    protected abstract void putRemote(Memory memory, int offset, Memory targetMemory, int targetOffset)
            throws AccessException;

    @Override
    public Object getTarget(Memory memory, int offset) throws AccessException {
        MemoryWrapOffset target = get(memory, offset);
        return targetType.get(target.getOrig(), target.getOffset());
    }

    @Override
    public void putTarget(Memory memory, int offset, Object value) throws AccessException {
        MemoryWrapOffset target = get(memory, offset);
        targetType.put(target.getOrig(), target.getOffset(), value);
    }

}
