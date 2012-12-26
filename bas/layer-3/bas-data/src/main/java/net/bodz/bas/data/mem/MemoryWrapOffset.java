package net.bodz.bas.data.mem;

public class MemoryWrapOffset
        extends AbstractMemory {

    private final Memory orig;

    /** addr_t */
    private final int offset;

    public MemoryWrapOffset(Memory orig, int offset) {
        assert orig != null;
        this.orig = orig;
        this.offset = offset;
    }

    public Memory getOrig() {
        return orig;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public MemoryWrapOffset offset(long off) {
        int off32 = (int) off;
        if (off32 == 0)
            return this;
        return new MemoryWrapOffset(orig, this.offset + (int) off32);
    }

    @Override
    public byte read(int addr)
            throws MemoryAccessException {
        return orig.read(addr + offset);
    }

    @Override
    public void write(int addr, byte value)
            throws MemoryAccessException {
        orig.write(addr + offset, value);
    }

    @Override
    public void read(int addr, byte[] buf)
            throws MemoryAccessException {
        orig.read(addr + offset, buf);
    }

    @Override
    public void read(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException {
        orig.read(addr + offset, buf, off, len);
    }

    @Override
    public void write(int addr, byte[] buf)
            throws MemoryAccessException {
        orig.write(addr + offset, buf);
    }

    @Override
    public void write(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException {
        orig.write(addr + offset, buf, off, len);
    }

}
