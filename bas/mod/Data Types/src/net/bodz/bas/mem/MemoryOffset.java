package net.bodz.bas.mem;

public class MemoryOffset extends _Memory {

    private final Memory orig;

    /** addr_t */
    private final int    offset;

    public MemoryOffset(Memory orig, int offset) {
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
    public byte read(int addr) throws AccessException {
        return orig.read(addr + offset);
    }

    @Override
    public void write(int addr, byte value) throws AccessException {
        orig.write(addr + offset, value);
    }

    @Override
    public void read(int addr, byte[] buf) throws AccessException {
        orig.read(addr + offset, buf);
    }

    @Override
    public void read(int addr, byte[] buf, int off, int len)
            throws AccessException {
        orig.read(addr + offset, buf, off, len);
    }

    @Override
    public void write(int addr, byte[] buf) throws AccessException {
        orig.write(addr + offset, buf);
    }

    @Override
    public void write(int addr, byte[] buf, int off, int len)
            throws AccessException {
        orig.write(addr + offset, buf, off, len);
    }

}
