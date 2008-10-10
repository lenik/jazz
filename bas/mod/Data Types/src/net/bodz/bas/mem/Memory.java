package net.bodz.bas.mem;

/**
 * <code>addr</code> may be int or unsigned int.
 */
public interface Memory {

    byte read(int addr) throws AccessException;

    void write(int addr, byte value) throws AccessException;

    void read(int addr, byte[] buf, int off, int len) throws AccessException;

    void read(int addr, byte[] buf) throws AccessException;

    void write(int addr, byte[] buf, int off, int len) throws AccessException;

    void write(int addr, byte[] buf) throws AccessException;

    Memory offset(long off);

    int LITTLE_ENDIAN = 0;
    int BIG_ENDIAN    = 1;

    int getByteOrder();

    /** in native byte-order */
    short readInt16(int addr) throws AccessException;

    /** in native byte-order */
    void writeInt16(int addr, short value) throws AccessException;

    /** in native byte-order */
    int readInt32(int addr) throws AccessException;

    /** in native byte-order */
    void writeInt32(int addr, int value) throws AccessException;

    /** in native byte-order */
    long readInt64(int addr) throws AccessException;

    /** in native byte-order */
    void writeInt64(int addr, long value) throws AccessException;

}
