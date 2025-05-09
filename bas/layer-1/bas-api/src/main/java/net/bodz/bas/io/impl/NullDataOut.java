package net.bodz.bas.io.impl;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringLengthType;
import net.bodz.bas.meta.decl.NotNull;

public class NullDataOut
        implements IDataOut {

    private final boolean bigEndian;

    public NullDataOut(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    @Override
    public boolean isBigEndian() {
        return bigEndian;
    }

    @Override
    public Charset getCharset() {
        return null;
    }

    @Override
    public void setCharset(Charset charset) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public void write(@NotNull byte[] buf) {
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len) {
    }

    @Override
    public void write(ByteBuffer buffer) {
    }

    @Override
    public void writeByte(int b) {
    }

    @Override
    public void writeWord(int s) {
    }

    @Override
    public void writeDword(int n) {
    }

    @Override
    public void writeQword(long l) {
    }

    @Override
    public void writeFloat(float f) {
    }

    @Override
    public void writeDouble(double d) {
    }

    @Override
    public void writeBool(boolean b) {
    }

    @Override
    public void writeWChar(char ch) {
    }

    @Override
    public void writeChar(char ch) {
    }

    @Override
    public int writeUtf8Char(char ch) {
        return 0;
    }

    @Override
    public int writeChar(char ch, Charset charset) {
        return 0;
    }

    @Override
    public void writeWords(short[] buf, int off, int len) {
    }

    @Override
    public void writeDwords(int[] buf, int off, int len) {
    }

    @Override
    public void writeQwords(long[] buf, int off, int len) {
    }

    @Override
    public void writeFloats(float[] buf, int off, int len) {
    }

    @Override
    public void writeDoubles(double[] buf, int off, int len) {
    }

    @Override
    public void writeBools(boolean[] buf, int off, int len) {
    }

    @Override
    public int writeChars(char[] buf, int off, int len) {
        return 0;
    }

    @Override
    public int writeUtf8Chars(char[] buf, int off, int len) {
        return 0;
    }

    @Override
    public int writeChars(char[] buf, int off, int len, Charset charset) {
        return 0;
    }

    @Override
    public void writeString(StringLengthType lengthType, String str) {
    }

    @Override
    public void writeUtf8String(StringLengthType lengthType, String str) {
    }

    @Override
    public void writeString(StringLengthType lengthType, String str, Charset charset) {
    }

    @Override
    public int writeCharsOfSize(int fixedSize, char padding, char[] buf, int off, int len) {
        return 0;
    }

    @Override
    public int writeUtf8CharsOfSize(int fixedSize, char padding, char[] buf, int off, int len) {
        return 0;
    }

    @Override
    public int writeCharsOfSize(int fixedSize, char padding, char[] buf, int off, int len, Charset charset) {
        return 0;
    }

    @Override
    public int writeStringOfLength(int maxBytes, char padding, String str) {
        return 0;
    }

    @Override
    public int writeUtf8StringOfLength(int maxBytes, char padding, String str) {
        return 0;
    }

    @Override
    public int writeStringOfLength(int maxBytes, char padding, String str, Charset charset) {
        return 0;
    }

    @Override
    public int writeStringOfSize(int fixedSize, char padding, String str) {
        return 0;
    }

    @Override
    public int writeUtf8StringOfSize(int fixedSize, char padding, String str) {
        return 0;
    }

    @Override
    public int writeStringOfSize(int fixedSize, char padding, String str, Charset charset) {
        return 0;
    }

}
