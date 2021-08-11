package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.t.set.FramedMarks;

public abstract class AbstractRstOutput
        implements
            IRstOutput {

    private String separator = " ";
    private FramedMarks marks = new FramedMarks();

    @Override
    public FramedMarks getMarks() {
        return marks;
    }

    @Override
    public void element(String name, IRstSerializable child, String... args)
            throws IOException, FormatException {
        if (child == null)
            return;

        beginElement(name, args);
        child.writeObject(this);
        endElement();
    }

    @Override
    public final void attribute(String name, int value)
            throws IOException {
        _attribute(name, Integer.toString(value));
    }

    @Override
    public final void attribute(String name, long value)
            throws IOException {
        _attribute(name, Long.toString(value));
    }

    @Override
    public final void attributeHex(String name, byte value)
            throws IOException {
        String hex = Integer.toHexString(value);
        if (hex.length() > 2)
            hex = hex.substring(hex.length() - 2);
        _attribute(name, "0x" + hex);
    }

    @Override
    public final void attributeHex(String name, short value)
            throws IOException {
        String hex = Integer.toHexString(value);
        if (hex.length() > 4)
            hex = hex.substring(hex.length() - 4);
        _attribute(name, "0x" + hex);
    }

    @Override
    public final void attributeHex(String name, int value)
            throws IOException {
        _attribute(name, "0x" + Integer.toHexString(value));
    }

    @Override
    public final void attributeHex(String name, long value)
            throws IOException {
        _attribute(name, "0x" + Long.toHexString(value));
    }

    @Override
    public final void attribute(String name, float value)
            throws IOException {
        _attribute(name, Float.toString(value));
    }

    @Override
    public final void attribute(String name, double value)
            throws IOException {
        _attribute(name, Double.toString(value));
    }

    @Override
    public final void attribute(String name, boolean value)
            throws IOException {
        _attribute(name, Boolean.toString(value));
    }

    @Override
    public final void attribute(String name, char value)
            throws IOException {
        _attribute(name, StringQuote.qqJavaString(Character.toString(value)));
    }

    @Override
    public void attribute(String name, Enum<?> value)
            throws IOException {
        _attribute(name, value.name());
    }

    @Override
    public void attribute(String name, String value)
            throws IOException {
        _attribute(name, StringQuote.qqJavaString(value));
    }

    @Override
    public final void attribute(String name, byte[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, byte[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 4);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, short[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, short[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 7); // -32768
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, int[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, int[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 12); // -2147483648
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, long[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, long[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 20); // -9223372036854775808
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, float[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, float[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 16);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, double[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, double[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 30);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, boolean[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, boolean[] buf, int off, int len)
            throws IOException {
        StringBuilder sb = new StringBuilder(len * 6);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            sb.append(buf[off++]);
        }
        _attribute(name, sb.toString());
    }

    @Override
    public final void attribute(String name, char[] buf)
            throws IOException {
        attribute(name, new String(buf));
    }

    @Override
    public final void attribute(String name, char[] buf, int off, int len)
            throws IOException {
        attribute(name, new String(buf, off, len));
    }

    @Override
    public final void attribute(String name, String[] buf)
            throws IOException {
        attribute(name, buf, 0, buf.length);
    }

    @Override
    public final void attribute(String name, String[] buf, int off, int len)
            throws IOException {
        int totalLength = 0;
        for (int i = 0; i < len; i++)
            totalLength += (buf[i] == null ? 4 : buf[i].length()) + 1;

        StringBuilder sb = new StringBuilder(totalLength);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            String qq = StringQuote.qqJavaString(buf[off++]);
            sb.append(qq);
        }
        _attribute(name, sb.toString());
    }

}
