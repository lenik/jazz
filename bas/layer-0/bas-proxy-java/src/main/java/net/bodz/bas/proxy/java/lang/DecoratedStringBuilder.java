package net.bodz.bas.proxy.java.lang;

import net.bodz.bas.model.IWrapper;

public class DecoratedStringBuilder
        implements Appendable, IWrapper<StringBuilder> {

    private StringBuilder _orig;

    public DecoratedStringBuilder(StringBuilder _orig) {
        this._orig = _orig;
    }

    @Override
    public StringBuilder getWrapped() {
        return _orig;
    }

    public void setWrapped(StringBuilder stringBuilder) {
        this._orig = stringBuilder;
    }

    public int length() {
        return _orig.length();
    }

    public int capacity() {
        return _orig.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        _orig.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        _orig.trimToSize();
    }

    public void setLength(int newLength) {
        _orig.setLength(newLength);
    }

    public StringBuilder append(Object obj) {
        return _orig.append(obj);
    }

    public StringBuilder append(String str) {
        return _orig.append(str);
    }

    public StringBuilder append(StringBuffer sb) {
        return _orig.append(sb);
    }

    public char charAt(int index) {
        return _orig.charAt(index);
    }

    @Override
    public StringBuilder append(CharSequence s) {
        return _orig.append(s);
    }

    @Override
    public StringBuilder append(CharSequence s, int start, int end) {
        return _orig.append(s, start, end);
    }

    public int codePointAt(int index) {
        return _orig.codePointAt(index);
    }

    public StringBuilder append(char[] str) {
        return _orig.append(str);
    }

    public StringBuilder append(char[] str, int offset, int len) {
        return _orig.append(str, offset, len);
    }

    public StringBuilder append(boolean b) {
        return _orig.append(b);
    }

    public StringBuilder append(char c) {
        return _orig.append(c);
    }

    public StringBuilder append(int i) {
        return _orig.append(i);
    }

    public StringBuilder append(long lng) {
        return _orig.append(lng);
    }

    public StringBuilder append(float f) {
        return _orig.append(f);
    }

    public StringBuilder append(double d) {
        return _orig.append(d);
    }

    public StringBuilder appendCodePoint(int codePoint) {
        return _orig.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        return _orig.delete(start, end);
    }

    public int codePointBefore(int index) {
        return _orig.codePointBefore(index);
    }

    public StringBuilder deleteCharAt(int index) {
        return _orig.deleteCharAt(index);
    }

    public StringBuilder replace(int start, int end, String str) {
        return _orig.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        return _orig.insert(index, str, offset, len);
    }

    public StringBuilder insert(int offset, Object obj) {
        return _orig.insert(offset, obj);
    }

    public StringBuilder insert(int offset, String str) {
        return _orig.insert(offset, str);
    }

    public StringBuilder insert(int offset, char[] str) {
        return _orig.insert(offset, str);
    }

    public StringBuilder insert(int dstOffset, CharSequence s) {
        return _orig.insert(dstOffset, s);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return _orig.codePointCount(beginIndex, endIndex);
    }

    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        return _orig.insert(dstOffset, s, start, end);
    }

    public StringBuilder insert(int offset, boolean b) {
        return _orig.insert(offset, b);
    }

    public StringBuilder insert(int offset, char c) {
        return _orig.insert(offset, c);
    }

    public StringBuilder insert(int offset, int i) {
        return _orig.insert(offset, i);
    }

    public StringBuilder insert(int offset, long l) {
        return _orig.insert(offset, l);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return _orig.offsetByCodePoints(index, codePointOffset);
    }

    public StringBuilder insert(int offset, float f) {
        return _orig.insert(offset, f);
    }

    public StringBuilder insert(int offset, double d) {
        return _orig.insert(offset, d);
    }

    public int indexOf(String str) {
        return _orig.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return _orig.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return _orig.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return _orig.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        return _orig.reverse();
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        _orig.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    @Override
    public String toString() {
        return _orig.toString();
    }

    public void setCharAt(int index, char ch) {
        _orig.setCharAt(index, ch);
    }

    public String substring(int start) {
        return _orig.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return _orig.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return _orig.substring(start, end);
    }

}
