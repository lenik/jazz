package net.bodz.bas.t.vector;

import java.io.UnsupportedEncodingException;

public class ByteArrayAsVector
        implements
            ByteVector {

    private final byte[] bytes;
    private final int start;
    private final int end;

    public ByteArrayAsVector(byte[] bytes) {
        this(bytes, 0, bytes.length);
    }

    public ByteArrayAsVector(byte[] bytes, int start, int end) {
        if (bytes == null)
            throw new NullPointerException("bytes");
        this.bytes = bytes;
        this.start = start;
        this.end = end;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public Byte get(int index) {
        return bytes[index];
    }

    @Override
    public void set(int index, Byte value) {
        bytes[index] = value == null ? 0 : value.byteValue();
    }

    @Override
    public byte byteAt(int index) {
        return bytes[index];
    }

    @Override
    public ByteIterator iterator() {
        return new ByteArrayByteIterator(bytes, start, end);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (int i = start; i < end; i++)
            hash = hash * 251 + bytes[i];
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        ByteArrayAsVector other = (ByteArrayAsVector) obj;
        if (end - start != other.end - other.start)
            return false;
        int i1 = start;
        int i2 = other.start;
        for (; i1 < end; i1++) {
            if (bytes[i1] != other.bytes[i2])
                return false;
            i2++;
        }
        return true;
    }

    @Override
    public String toString() {
        String charset = "utf-8";
        String str;
        try {
            str = new String(bytes, start, end - start, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return str;
    }

}
