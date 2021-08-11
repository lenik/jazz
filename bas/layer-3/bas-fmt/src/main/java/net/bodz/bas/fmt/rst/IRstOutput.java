package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.api.IDataCodec;
import net.bodz.bas.fmt.api.StdDataCodec;
import net.bodz.bas.t.set.FramedMarks;

public interface IRstOutput {

    IDataCodec codec = new StdDataCodec();

    FramedMarks getMarks();

    void element(String name, IRstSerializable child, String... args)
            throws IOException, FormatException;

    void beginElement(String name, String... args)
            throws IOException;

    void endElement()
            throws IOException;

    void _attribute(String name, String data)
            throws IOException;

    void attribute(String name, int value)
            throws IOException;

    void attribute(String name, long value)
            throws IOException;

    void attributeHex(String name, byte value)
            throws IOException;

    void attributeHex(String name, short value)
            throws IOException;

    void attributeHex(String name, int value)
            throws IOException;

    void attributeHex(String name, long value)
            throws IOException;

    void attribute(String name, boolean value)
            throws IOException;

    void attribute(String name, float value)
            throws IOException;

    void attribute(String name, double value)
            throws IOException;

    void attribute(String name, char value)
            throws IOException;

    void attribute(String name, Enum<?> value)
            throws IOException;

    void attribute(String name, String value)
            throws IOException;

    void attribute(String name, byte[] buf)
            throws IOException;

    void attribute(String name, byte[] buf, int off, int len)
            throws IOException;

    void attribute(String name, short[] buf)
            throws IOException;

    void attribute(String name, short[] buf, int off, int len)
            throws IOException;

    void attribute(String name, int[] buf)
            throws IOException;

    void attribute(String name, int[] buf, int off, int len)
            throws IOException;

    void attribute(String name, long[] buf)
            throws IOException;

    void attribute(String name, long[] buf, int off, int len)
            throws IOException;

    void attribute(String name, float[] buf)
            throws IOException;

    void attribute(String name, float[] buf, int off, int len)
            throws IOException;

    void attribute(String name, double[] buf)
            throws IOException;

    void attribute(String name, double[] buf, int off, int len)
            throws IOException;

    void attribute(String name, boolean[] buf)
            throws IOException;

    void attribute(String name, boolean[] buf, int off, int len)
            throws IOException;

    void attribute(String name, char[] buf)
            throws IOException;

    void attribute(String name, char[] buf, int off, int len)
            throws IOException;

    void attribute(String name, String[] buf)
            throws IOException;

    void attribute(String name, String[] buf, int off, int len)
            throws IOException;

}
