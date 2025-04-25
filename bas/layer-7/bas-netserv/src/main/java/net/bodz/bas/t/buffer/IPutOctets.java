package net.bodz.bas.t.buffer;

import java.nio.ByteBuffer;

public interface IPutOctets {

    /**
     * Adds an octet to the buffer.
     *
     * @param octet The byte value to be added to the buffer.
     */
    void putOctet(int octet);

    /**
     * Adds an octet to the buffer.
     *
     * @param octet The byte value to be added to the buffer.
     */
    void putOctet(byte octet);

    default void putOctets(ByteBuffer buf) {
        int len = buf.limit();
        int pos = buf.position();
        if (buf.hasArray()) {  // optim:  && len > 2
            byte[] array = buf.array();
            int off = buf.arrayOffset();
            putOctets(array, off + pos, len - pos);
            buf.position(len);
        } else {
            // while (buf.hasRemaining()) {
            for (int i = pos; i < len; i++) {
                byte b = buf.get();
                putOctet(b);
            }
        }
    }

    /**
     * Adds a sequence of octets to the buffer.
     *
     * @param octets The array containing the octets to be added to the buffer.
     * @param off    The start offset in the data.
     * @param len    The number of bytes to process.
     */
    default void putOctets(byte[] octets, int off, int len) {
        int pos = off;
        for (int i = 0; i < len; i++)
            putOctet(octets[pos++] & 0xFF);
    }

    default void putOctets(IByteBuffer buf) {
        byte[] backedArray = buf.getBackedArray();
        int backedArrayOffset = buf.getBackedArrayOffset();
        int len = buf.length();
        putOctets(backedArray, backedArrayOffset, len);
    }

}
