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
import net.bodz.bas.meta.codegen.CopyAndPaste;

@CopyAndPaste(HexCodec.class)
public class DecimalCodec
        extends AbstractByteCodec {

    private final int bytesPerColumn;
    private final String columnSeparator;
    private final int columnsPerRow;
    private final String rowSeparator;

    private int radix = 10;
    private int width = 2;
    private char paddingChar = '0';

    public DecimalCodec() {
        this(" ");
    }

    public DecimalCodec(String columnSeparator) {
        this(columnSeparator, 1);
    }

    public DecimalCodec(String columnSeparator, int bytesPerColumn) {
        this(columnSeparator, bytesPerColumn, null, 0);
    }

    public DecimalCodec(String columnSeparator, String rowSeparator, int columnsPerRow) {
        this(columnSeparator, 1, rowSeparator, columnsPerRow);
    }

    public DecimalCodec(String columnSeparator, int bytesPerColumn, String rowSeparator, int columnsPerRow) {
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

    public DecimalCodec padding(int width, char paddingChar) {
        this.width = width;
        this.paddingChar = paddingChar;
        return this;
    }

    public DecimalCodec radix(int radix) {
        this.radix = radix;
        return this;
    }

    @Override
    public Charset getPreferredCharsetForEncodedContents() {
        return Charsets.ASCII7;
    }

    @Override
    public void encode(IByteIn in, ICharOut out)
            throws IOException, EncodeException {
        boolean startOfRow = true;
        int byteIndex = 0;
        int columnIndex = 0;
        long val = 0;

        for (int b = in.read(); b != -1; b = in.read()) {
            if (bytesPerColumn != 0)
                if (byteIndex >= bytesPerColumn) {
                    byteIndex = 0;

                    if (!startOfRow) {
                        // flush
                        String s = Long.toString(val, radix);
                        for (int pad = s.length(); pad < width; pad++)
                            out.write(paddingChar);
                        out.write(s);
                        val = 0;

                        if (columnsPerRow != 0 && ++columnIndex >= columnsPerRow) {
                            out.write(rowSeparator);
                            columnIndex = 0;
                            startOfRow = true;
                        } else {
                            out.write(columnSeparator);
                        }
                    }
                }

            val = val * 0x100 + b;
            startOfRow = false;
            byteIndex++;
        }
        if (byteIndex != 0) { // flush
            String s = Long.toString(val, radix);
            for (int pad = s.length(); pad < width; pad++)
                out.write(paddingChar);
            out.write(s);
            val = 0;
        }
    }

    @Override
    public void decode(ICharIn in, IByteOut out)
            throws IOException, EncodeException {
        long val = 0;
        int digits = 0;
        int c;
        while (true) {
            c = in.read();
            char ch = (char) c;
            if (c == -1 || StringSearch.contains(columnSeparator, ch) || StringSearch.contains(rowSeparator, ch)
                    || Character.isWhitespace(c)) {
                if (digits != 0) {
                    byte[] column = new byte[bytesPerColumn];
                    for (int i = 0; i < bytesPerColumn; i++) {
                        int lastByte = (int) (val % 0x100);
                        column[i] = (byte) lastByte;
                        val >>= 8;
                    }
                    for (int i = bytesPerColumn - 1; i >= 0; i--)
                        out.write(column[i]);
                    val = 0;
                    digits = 0;
                }
                if (c == -1)
                    break;
                else
                    continue;
            }

            int c2n = CharFeature.c2n[c] & 0xFF;
            val = val * radix + c2n;
            digits++;
        }
    }

}
