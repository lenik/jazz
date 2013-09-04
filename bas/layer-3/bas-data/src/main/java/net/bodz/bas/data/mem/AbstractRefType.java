package net.bodz.bas.data.mem;

public abstract class AbstractRefType
        extends AbstractType
        implements RefType {

    protected final Type targetType;

    public AbstractRefType(Type targetType)
            throws MemoryAccessException {
        this.targetType = targetType;
    }

    /**
     * @return memory position of the referent
     */
    @Override
    public abstract MemoryWrapOffset get(IMemory memory, int offset)
            throws MemoryAccessException;

    @Override
    public void put(IMemory memory, int offset, Object targetAddr)
            throws MemoryAccessException {
        MemoryWrapOffset target = (MemoryWrapOffset) targetAddr;
        IMemory targetMemory = target.getOrig();
        int targetOffset = target.getOffset();
        if (memory == targetMemory)
            putLocal(memory, offset, targetOffset);
        else
            putRemote(memory, offset, targetMemory, targetOffset);
    }

    protected void putLocal(IMemory memory, int offset, int targetOffset)
            throws MemoryAccessException {
        putRemote(memory, offset, memory, targetOffset);
    }

    protected abstract void putRemote(IMemory memory, int offset, IMemory targetMemory, int targetOffset)
            throws MemoryAccessException;

    @Override
    public Object getTarget(IMemory memory, int offset)
            throws MemoryAccessException {
        MemoryWrapOffset target = get(memory, offset);
        return targetType.get(target.getOrig(), target.getOffset());
    }

    @Override
    public void putTarget(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        MemoryWrapOffset target = get(memory, offset);
        targetType.put(target.getOrig(), target.getOffset(), value);
    }

}
