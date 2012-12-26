package net.bodz.bas.data.mem;

/**
 * <code>addr</code> may be int or unsigned int.
 */
public interface Memory {

    byte read(int addr)
            throws MemoryAccessException;

    void write(int addr, byte value)
            throws MemoryAccessException;

    void read(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException;

    void read(int addr, byte[] buf)
            throws MemoryAccessException;

    void write(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException;

    void write(int addr, byte[] buf)
            throws MemoryAccessException;

    Memory offset(long off);

    int LITTLE_ENDIAN = 0;
    int BIG_ENDIAN = 1;

    int getByteOrder();

    /** in native byte-order */
    short readInt16(int addr)
            throws MemoryAccessException;

    /** in native byte-order */
    void writeInt16(int addr, short value)
            throws MemoryAccessException;

    /** in native byte-order */
    int readInt32(int addr)
            throws MemoryAccessException;

    /** in native byte-order */
    void writeInt32(int addr, int value)
            throws MemoryAccessException;

    /** in native byte-order */
    long readInt64(int addr)
            throws MemoryAccessException;

    /** in native byte-order */
    void writeInt64(int addr, long value)
            throws MemoryAccessException;

}
