package net.bodz.bas.io.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringLengthType;

class _WriteUtfStringImpl {

    private final StringLengthType lengthType;
    boolean ucs32;
    private final String str;
    private final String encoding;

    String termCharSeq;
    int termCharNum;

    private final int len;
    private final int size;

    private final char[] chars;
    private byte[] bytes;

    private boolean bigEndian;

    public _WriteUtfStringImpl(StringLengthType lengthType, String str, String encoding)
            throws UnsupportedEncodingException {

//        if ((flags & StringFlags.XXX_TERM_MASK) != 0) {
//            if ((flags & StringFlags.CR_TERM) != 0)
//                termCharSeq += '\r';
//            if ((flags & StringFlags.LF_TERM) != 0)
//                termCharSeq += '\n';
//            if ((flags & StringFlags.NULL_TERM) != 0)
//                termCharSeq += '\0';
//            termCharNum = termCharSeq.length();
//
//            int tsc = str.indexOf(termCharSeq);
//            if (tsc != -1)
//                str = str.substring(0, tsc);
//        }

        this.lengthType = lengthType;
        this.str = str;
        this.encoding = encoding;

        chars = str.toCharArray();
        len = str.length();

        if (encoding == null)
            if (lengthType.countByChar) {
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
        boolean _long = lengthType.countFieldBytes == 4;
        boolean lengthPrefix = lengthType.countByChar;
        boolean sizePrefix = lengthType.countByByte;
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
            if (ucs32) {
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
            else if (lengthType.countByChar) {
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
                        out.writeByte(ch);

                    else if (ch <= 0x7ff) {
                        // 11bit: 110xxxxx . 10xxxxxx
                        out.writeByte(0xC0 | (ch >> 6));
                        out.writeByte(0x80 | (ch & 0x3F));
                    }

                    else {
                        // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
                        out.writeByte(0xE0 | (ch >> 12));
                        out.writeByte(0x80 | ((ch >> 6) & 0x3F));
                        out.writeByte(0x80 | (ch & 0x3F));
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
            if (ucs32)
                out.writeDword(ch);
            else if (lengthType.countByChar)
                out.writeWord(ch);
            else
                out.writeByte(ch);
        }
    }

}
