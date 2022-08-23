package net.bodz.bas.io;

import java.io.IOException;

public interface ISimpleCharOut
        extends
            Appendable {

    /**
     * @param ch
     *            The character to be written is contained in the 16 low-order bits of the given
     *            integer value; the 16 high-order bits are ignored.
     */
    void write(int ch)
            throws IOException;

    @Override
    default Appendable append(CharSequence csq)
            throws IOException {
        return append(csq, 0, csq.length());
    }

    @Override
    default Appendable append(CharSequence csq, int start, int end)
            throws IOException {
        for (int i = start; i < end; i++)
            write(csq.charAt(i));
        return this;
    }

    @Override
    default Appendable append(char c)
            throws IOException {
        write(c);
        return this;
    }

}
