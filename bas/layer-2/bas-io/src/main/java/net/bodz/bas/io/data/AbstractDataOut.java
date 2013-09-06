package net.bodz.bas.io.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.bodz.bas.io.AbstractByteOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringFlags;

public abstract class AbstractDataOut
        extends AbstractByteOut
        implements IDataOut {

    @Override
    public void writeFloat(float f)
            throws IOException {
        writeDword(Float.floatToIntBits(f));
    }

    @Override
    public void writeDouble(double d)
            throws IOException {
        writeQword(Double.doubleToLongBits(d));
    }

    @Override
    public void writeBoolean(boolean b)
            throws IOException {
        write(b ? (byte) 1 : (byte) 0);
    }

    @Override
    public synchronized void writeUtf8Char(char ch)
            throws IOException {
        if (ch <= 0x7f)
            // 7bit: 0xxxxxxx
            write(ch);

        else if (ch <= 0x7ff) {
            // 11bit: 110xxxxx . 10xxxxxx
            ch -= 0x80;
            write(0xC0 | (ch >> 6));
            write(0x80 | (ch & 0x3F));
        }

        else {
            // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
            ch -= 0x800;
            write(0xE0 | (ch >> 12));
            write(0x80 | ((ch >> 6) & 0x3F));
            write(0x80 | (ch & 0x3F));
        }
    }

    @Override
    public final void writeString(int flags, String str)
            throws IOException {
        writeString(flags, str, null);
    }

}

class _WriteUtfStringImpl {

    private final int flags;
    private final String str;
    private final String encoding;

    String termCharSeq;
    int termCharNum;

    private final int len;
    private final int size;

    private final char[] chars;
    private byte[] bytes;

    private boolean bigEndian;

    public _WriteUtfStringImpl(int flags, String str, String encoding)
            throws UnsupportedEncodingException {

        if ((flags & StringFlags.XXX_TERM_MASK) != 0) {
            if ((flags & StringFlags.CR_TERM) != 0)
                termCharSeq += '\r';
            if ((flags & StringFlags.LF_TERM) != 0)
                termCharSeq += '\n';
            if ((flags & StringFlags.NULL_TERM) != 0)
                termCharSeq += '\0';
            termCharNum = termCharSeq.length();

            int tsc = str.indexOf(termCharSeq);
            if (tsc != -1)
                str = str.substring(0, tsc);
        }

        this.flags = flags;
        this.str = str;
        this.encoding = encoding;

        chars = str.toCharArray();
        len = str.length();

        if (encoding == null)
            if ((flags & StringFlags._32BIT) != 0) {
                // XXX surrogate pairs need to be reduced.
                size = len * 4;
            } else if ((flags & StringFlags._16BIT) != 0) {
                size = len * 2;
            } else {
                int cb = 0;
                for (int i = 0; i < len; i++) {
                    int ch = chars[i];
                    if (ch <= 0x007F)
                        cb++;
                    else if (ch <= 0x07FF)
                        cb += 2;
                    else
                        cb += 3;
                }
                size = cb;
            }
        else
            size = (bytes = str.getBytes(encoding)).length;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    public void write(IDataOut out)
            throws IOException {
        boolean _long = (flags & StringFlags.LONG) != 0;
        boolean lengthPrefix = (flags & StringFlags.LENGTH_PREFIX) != 0;
        boolean sizePrefix = (flags & StringFlags.SIZE_PREFIX) != 0;
        boolean sameEndian = bigEndian == out.isBigEndian();

        if (lengthPrefix)
            if (_long)
                out.writeDword(len);
            else
                out.writeWord(len);

        if (sizePrefix)
            if (_long)
                out.writeDword(size);
            else
                out.writeWord(size);

        if (encoding == null) { // It's unnecessary to allocate byte buffer to write unicode string.

            // UTF-32LE, UTF-32BE
            if ((flags & StringFlags._32BIT) != 0) {
                char high = 0;

                for (int i = 0; i < len; i++) {
                    char ch = chars[i];
                    int dw = ch;

                    if (Character.isSurrogate(ch))
                        if (high == 0) {
                            high = ch;
                            continue;
                        } else if (Character.isSurrogatePair(high, ch)) {
                            dw = (high << 16) | ch;
                            high = 0;
                        } else {
                            if (sameEndian)
                                out.writeDword(high);
                            else
                                out.writeDword(Integer.reverseBytes(high));
                            high = 0;
                        }

                    if (!sameEndian)
                        dw = Integer.reverseBytes(dw);
                    out.writeDword(dw);
                }
            }

            // UTF-16LE, UTF-16BE
            else if ((flags & StringFlags._16BIT) != 0) {
                if (sameEndian)
                    for (int i = 0; i < len; i++)
                        out.writeWord(chars[i]);
                else
                    for (int i = 0; i < len; i++)
                        out.writeWord(Short.reverseBytes((short) chars[i]));
            }

            // UTF-8
            else
                for (int i = 0; i < len; i++) {
                    char ch = chars[i];
                    if (ch <= 0x7f)
                        // 7bit: 0xxxxxxx
                        out.write(ch);

                    else if (ch <= 0x7ff) {
                        // 11bit: 110xxxxx . 10xxxxxx
                        out.write(0xC0 | (ch >> 6));
                        out.write(0x80 | (ch & 0x3F));
                    }

                    else {
                        // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
                        out.write(0xE0 | (ch >> 12));
                        out.write(0x80 | ((ch >> 6) & 0x3F));
                        out.write(0x80 | (ch & 0x3F));
                    }
                }
        } // encoding == null

        else {
            if (bytes == null)
                bytes = str.getBytes(encoding);
            out.write(bytes);
        }

        for (int i = 0; i < termCharNum; i++) {
            char ch = termCharSeq.charAt(i);
            if ((flags & StringFlags._32BIT) != 0)
                out.writeDword(ch);
            else if ((flags & StringFlags._16BIT) != 0)
                out.writeWord(ch);
            else
                out.writeByte(ch);
        }
    }

}
