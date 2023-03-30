package net.bodz.bas.io.data;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class AbstractCharCodec {

    protected static final int decodeBufferSize = 3;
    protected static final int blockSize = 5;
    protected static final int maxPrealloc = 4096;

    byte[] tmpByteBlock;
    char[] tmpCharBlock;

    public AbstractCharCodec() {
    }

    public AbstractCharCodec(boolean byBlocks) {
        tmpByteBlock = new byte[blockSize];
        tmpCharBlock = new char[blockSize];
    }

    protected int copy(OutputStream out, ByteBuffer src)
            throws IOException {
        int length = src.limit();
        int blockSize = tmpByteBlock.length;
        for (int pos = 0; pos < length; pos += blockSize) {
            if (pos + blockSize > length)
                blockSize = length - pos;
            src.get(tmpByteBlock, 0, blockSize);
            out.write(tmpByteBlock, 0, blockSize);
        }
        return length;
    }

    protected int copy(StringBuilder out, CharBuffer src)
            throws IOException {
        int length = src.limit();
        int blockSize = tmpCharBlock.length;
        for (int pos = 0; pos < length; pos += blockSize) {
            if (pos + blockSize > length)
                blockSize = length - pos;
            src.get(tmpCharBlock, 0, blockSize);
            out.append(tmpCharBlock, 0, blockSize);
        }
        return length;
    }

    protected static int copy(ByteBuffer buf, byte[] array, int off, int len) {
        int min = Math.min(buf.remaining(), len);
        for (int i = 0; i < min; i++) {
            buf.put(array[off + i]);
        }
        return min;
    }

    protected static int copy(CharBuffer buf, char[] array, int off, int len) {
        int min = Math.min(buf.remaining(), len);
        for (int i = 0; i < min; i++) {
            buf.put(array[off + i]);
        }
        return min;
    }

}
