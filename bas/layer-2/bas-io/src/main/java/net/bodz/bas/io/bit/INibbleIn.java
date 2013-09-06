package net.bodz.bas.io.bit;

import java.io.IOException;

public interface INibbleIn {

    /**
     * @return <code>false</code> If current position is at a byte boundary, or <code>false</code>
     *         if current in middle of a byte.
     */
    boolean isBuffered();

    /**
     * @return 0 to 15 if read succeeded, or -1 if EOF reached.
     */
    int read4b()
            throws IOException;

    /**
     * bit 7-4 the first nibble, 3-0 the second
     * 
     * @return nibble count successfully read, -1 if EOF reached.
     */
    int read4b(byte[] buf, int nibbleOffset, int nibbleCount)
            throws IOException;

    /**
     * Read <code>buf.length*2</code> nibbles into <code>buf</code>.
     * 
     * @return nibble count successfully read, -1 if EOF reached.
     */
    int read4b(byte[] buf)
            throws IOException;

    void close()
            throws IOException;

}
