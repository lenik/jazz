package net.bodz.bas.c.string;

import java.io.Serializable;

public class AbstractStringBuilder
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final StringBuilder buf;

    public AbstractStringBuilder() {
        buf = new StringBuilder();
    }

    public AbstractStringBuilder(int capacity) {
        buf = new StringBuilder(capacity);
    }

    public AbstractStringBuilder(String str) {
        buf = new StringBuilder(str);
    }

    public AbstractStringBuilder(CharSequence seq) {
        buf = new StringBuilder(seq);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractStringBuilder))
            return false;
        AbstractStringBuilder o = (AbstractStringBuilder) obj;

        if (!buf.equals(o.buf))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x7385128a;
        hash += buf.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return buf.toString();
    }

    // Delegated methods

    public int length() {
        return buf.length();
    }

    public int capacity() {
        return buf.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        buf.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        buf.trimToSize();
    }

    public void setLength(int newLength) {
        buf.setLength(newLength);
    }

    public StringBuilder append(Object obj) {
        return buf.append(obj);
    }

    public StringBuilder append(String str) {
        return buf.append(str);
    }

    public StringBuilder append(StringBuffer sb) {
        return buf.append(sb);
    }

    public char charAt(int index) {
        return buf.charAt(index);
    }

    public StringBuilder append(CharSequence s) {
        return buf.append(s);
    }

    public StringBuilder append(CharSequence s, int start, int end) {
        return buf.append(s, start, end);
    }

    public int codePointAt(int index) {
        return buf.codePointAt(index);
    }

    public StringBuilder append(char[] str) {
        return buf.append(str);
    }

    public StringBuilder append(char[] str, int offset, int len) {
        return buf.append(str, offset, len);
    }

    public StringBuilder append(boolean b) {
        return buf.append(b);
    }

    public StringBuilder append(char c) {
        return buf.append(c);
    }

    public StringBuilder append(int i) {
        return buf.append(i);
    }

    public StringBuilder append(long lng) {
        return buf.append(lng);
    }

    public StringBuilder append(float f) {
        return buf.append(f);
    }

    public StringBuilder append(double d) {
        return buf.append(d);
    }

    public StringBuilder appendCodePoint(int codePoint) {
        return buf.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        return buf.delete(start, end);
    }

    public int codePointBefore(int index) {
        return buf.codePointBefore(index);
    }

    public StringBuilder deleteCharAt(int index) {
        return buf.deleteCharAt(index);
    }

    public StringBuilder replace(int start, int end, String str) {
        return buf.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        return buf.insert(index, str, offset, len);
    }

    public StringBuilder insert(int offset, Object obj) {
        return buf.insert(offset, obj);
    }

    public StringBuilder insert(int offset, String str) {
        return buf.insert(offset, str);
    }

    public StringBuilder insert(int offset, char[] str) {
        return buf.insert(offset, str);
    }

    public StringBuilder insert(int dstOffset, CharSequence s) {
        return buf.insert(dstOffset, s);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return buf.codePointCount(beginIndex, endIndex);
    }

    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        return buf.insert(dstOffset, s, start, end);
    }

    public StringBuilder insert(int offset, boolean b) {
        return buf.insert(offset, b);
    }

    public StringBuilder insert(int offset, char c) {
        return buf.insert(offset, c);
    }

    public StringBuilder insert(int offset, int i) {
        return buf.insert(offset, i);
    }

    public StringBuilder insert(int offset, long l) {
        return buf.insert(offset, l);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return buf.offsetByCodePoints(index, codePointOffset);
    }

    public StringBuilder insert(int offset, float f) {
        return buf.insert(offset, f);
    }

    public StringBuilder insert(int offset, double d) {
        return buf.insert(offset, d);
    }

    public int indexOf(String str) {
        return buf.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return buf.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return buf.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return buf.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        return buf.reverse();
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        buf.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public void setCharAt(int index, char ch) {
        buf.setCharAt(index, ch);
    }

    public String substring(int start) {
        return buf.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return buf.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return buf.substring(start, end);
    }

}
