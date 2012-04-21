package net.bodz.bas.proxy.java.lang;

import net.bodz.bas.mode.AbstractDecorator;

public class StringBuilderDecorator
        extends AbstractDecorator<StringBuilder>
        implements Appendable {

    public StringBuilderDecorator(StringBuilder impl) {
        super(impl);
    }

    public int length() {
        return impl.length();
    }

    public int capacity() {
        return impl.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        impl.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        impl.trimToSize();
    }

    public void setLength(int newLength) {
        impl.setLength(newLength);
    }

    public StringBuilder append(Object obj) {
        return impl.append(obj);
    }

    public StringBuilder append(String str) {
        return impl.append(str);
    }

    public StringBuilder append(StringBuffer sb) {
        return impl.append(sb);
    }

    public char charAt(int index) {
        return impl.charAt(index);
    }

    @Override
    public StringBuilder append(CharSequence s) {
        return impl.append(s);
    }

    @Override
    public StringBuilder append(CharSequence s, int start, int end) {
        return impl.append(s, start, end);
    }

    public int codePointAt(int index) {
        return impl.codePointAt(index);
    }

    public StringBuilder append(char[] str) {
        return impl.append(str);
    }

    public StringBuilder append(char[] str, int offset, int len) {
        return impl.append(str, offset, len);
    }

    public StringBuilder append(boolean b) {
        return impl.append(b);
    }

    public StringBuilder append(char c) {
        return impl.append(c);
    }

    public StringBuilder append(int i) {
        return impl.append(i);
    }

    public StringBuilder append(long lng) {
        return impl.append(lng);
    }

    public StringBuilder append(float f) {
        return impl.append(f);
    }

    public StringBuilder append(double d) {
        return impl.append(d);
    }

    public StringBuilder appendCodePoint(int codePoint) {
        return impl.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        return impl.delete(start, end);
    }

    public int codePointBefore(int index) {
        return impl.codePointBefore(index);
    }

    public StringBuilder deleteCharAt(int index) {
        return impl.deleteCharAt(index);
    }

    public StringBuilder replace(int start, int end, String str) {
        return impl.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        return impl.insert(index, str, offset, len);
    }

    public StringBuilder insert(int offset, Object obj) {
        return impl.insert(offset, obj);
    }

    public StringBuilder insert(int offset, String str) {
        return impl.insert(offset, str);
    }

    public StringBuilder insert(int offset, char[] str) {
        return impl.insert(offset, str);
    }

    public StringBuilder insert(int dstOffset, CharSequence s) {
        return impl.insert(dstOffset, s);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return impl.codePointCount(beginIndex, endIndex);
    }

    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        return impl.insert(dstOffset, s, start, end);
    }

    public StringBuilder insert(int offset, boolean b) {
        return impl.insert(offset, b);
    }

    public StringBuilder insert(int offset, char c) {
        return impl.insert(offset, c);
    }

    public StringBuilder insert(int offset, int i) {
        return impl.insert(offset, i);
    }

    public StringBuilder insert(int offset, long l) {
        return impl.insert(offset, l);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return impl.offsetByCodePoints(index, codePointOffset);
    }

    public StringBuilder insert(int offset, float f) {
        return impl.insert(offset, f);
    }

    public StringBuilder insert(int offset, double d) {
        return impl.insert(offset, d);
    }

    public int indexOf(String str) {
        return impl.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return impl.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return impl.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return impl.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        return impl.reverse();
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        impl.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    @Override
    public String toString() {
        return impl.toString();
    }

    public void setCharAt(int index, char ch) {
        impl.setCharAt(index, ch);
    }

    public String substring(int start) {
        return impl.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return impl.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return impl.substring(start, end);
    }

}
