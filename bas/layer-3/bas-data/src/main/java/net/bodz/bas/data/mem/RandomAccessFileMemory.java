package net.bodz.bas.data.mem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.err.OutOfDomainException;

public class RandomAccessFileMemory
        extends AbstractMemory {

    private RandomAccessFile file;
    private long offset;

    private static boolean precheck = false;

    public RandomAccessFileMemory(File file, long offset)
            throws FileNotFoundException {
        this(new RandomAccessFile(file, "rw"), offset);
    }

    public RandomAccessFileMemory(RandomAccessFile file, long offset) {
        if (file == null)
            throw new NullPointerException();
        if (precheck) {
            if (offset < 0)
                throw new OutOfDomainException("offset", offset, 0);
            try {
                long len = file.length();
                if (offset > len)
                    throw new OutOfDomainException("offset", offset, len);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.file = file;
        this.offset = offset;
    }

    public RandomAccessFile getFile() {
        return file;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public RandomAccessFileMemory offset(long off) {
        return new RandomAccessFileMemory(file, this.offset + off);
    }

    @Override
    public void read(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException {
        try {
            file.seek(offset + addr);
            file.readFully(buf, off, len);
        } catch (IOException e) {
            throw new MemoryAccessException(e);
        }
    }

    @Override
    public int read(int addr)
            throws MemoryAccessException {
        try {
            file.seek(offset + addr);
            int b = file.read();
            if (b == -1)
                throw new MemoryAccessException("end of file");
            return b;
        } catch (IOException e) {
            throw new MemoryAccessException(e);
        }
    }

    @Override
    public void write(int addr, byte value)
            throws MemoryAccessException {
        try {
            file.seek(offset + addr);
            file.write(IntMath.unsign(value));
        } catch (IOException e) {
            throw new MemoryAccessException(e);
        }
    }

    @Override
    public void write(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException {
        try {
            file.seek(offset + addr);
            file.write(buf, off, len);
        } catch (IOException e) {
            throw new MemoryAccessException(e);
        }
    }

}
