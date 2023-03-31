package net.bodz.bas.data.codec.builtin;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.string.CharFeature;
import net.bodz.bas.c.string.StringSearch;
import net.bodz.bas.data.codec.AbstractByteCodec;
import net.bodz.bas.err.EncodeException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;

public class HexCodec
        extends AbstractByteCodec {

    private final char[] enctbl = CharFeature.n2lc;
    private final byte[] dectbl = CharFeature.c2n;

    private final int bytesPerColumn;
    private final String columnSeparator;
    private final int columnsPerRow;
    private final String rowSeparator;

    private int width = 2;
    private char paddingChar = '0';

    public HexCodec() {
        this(" ");
    }

    public HexCodec(String columnSeparator) {
        this(columnSeparator, 1);
    }

    public HexCodec(String columnSeparator, int bytesPerColumn) {
        this(columnSeparator, bytesPerColumn, null, 0);
    }

    public HexCodec(String columnSeparator, String rowSeparator, int columnsPerRow) {
        this(columnSeparator, 1, rowSeparator, columnsPerRow);
    }

    public HexCodec(String columnSeparator, int bytesPerColumn, String rowSeparator, int columnsPerRow) {
        super(bytesPerColumn, 2 * bytesPerColumn + (columnSeparator == null ? 0 : columnSeparator.length()));
        this.columnSeparator = columnSeparator;
        this.bytesPerColumn = bytesPerColumn;
        this.rowSeparator = rowSeparator;
        this.columnsPerRow = columnsPerRow;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char getPaddingChar() {
        return paddingChar;
    }

    public void setPaddingChar(char paddingChar) {
        this.paddingChar = paddingChar;
    }

    public HexCodec padding(int width, char paddingChar) {
        this.width = width;
        this.paddingChar = paddingChar;
        return this;
    }

    @Override
    public Charset getPreferredCharsetForEncodedContents() {
        return Charsets.ISO_8859_1;
    }

    @Override
    public void encode(IByteIn in, ICharOut out)
            throws IOException, EncodeException {
        boolean startOfRow = true;
        int byteIndex = 0;
        int columnIndex = 0;
        StringBuilder column = new StringBuilder(bytesPerColumn + width);
        char ch;

        for (int b = in.read(); b != -1; b = in.read()) {
            if (bytesPerColumn != 0)
                if (byteIndex >= bytesPerColumn) {
                    byteIndex = 0;

                    if (!startOfRow) {
                        // flush
                        if (column.length() == 0)
                            column.append('0');
                        for (int pad = column.length(); pad < width; pad++)
                            out.write(paddingChar);
                        out.write(column);
                        column.setLength(0);

                        if (columnsPerRow != 0 && ++columnIndex >= columnsPerRow) {
                            out.write(rowSeparator);
                            columnIndex = 0;
                            startOfRow = true;
                        } else {
                            out.write(columnSeparator);
                        }
                    }
                }

            int hi = b >> 4;
            if (hi != 0) {
                ch = enctbl[hi];
                column.append(ch);
            }
            int lo = b & 0xF;
            if (lo != 0) {
                ch = enctbl[lo];
                column.append(ch);
            }
            startOfRow = false;
            byteIndex++;
        }
        if (byteIndex != 0) { // flush
            if (column.length() == 0)
                column.append('0');
            for (int pad = column.length(); pad < width; pad++)
                out.write(paddingChar);
            out.write(column);
        }
    }

    @Override
    public void decode(ICharIn in, IByteOut out)
            throws IOException, EncodeException {
        int byt = 0;
        int digits = 0;
        for (int c = in.read(); c != -1; c = in.read()) {
            char ch = (char) c;
            if (StringSearch.contains(columnSeparator, ch) || StringSearch.contains(rowSeparator, ch)
                    || Character.isWhitespace(c)) {
                if (digits != 0) {
                    out.write(byt);
                    byt = 0;
                    digits = 0;
                }
                continue;
            }
            byte hexval = dectbl[c];
            if (digits == 0) {
                byt = hexval;
                digits = 1;
            } else {
                byt = (byt << 4) | hexval;
                out.write(byt);
                byt = 0;
                digits = 0;
            }
        }
        if (digits != 0)
            out.write(byt);
    }

    public static HexCodec getInstance() {
        return new HexCodec();
    }

}
