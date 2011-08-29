package net.bodz.bas.proxy.java.lang;

import net.bodz.bas.api.proxy.AbstractProxy;

public class ProxyStringBuilder
        extends AbstractProxy<StringBuilder>
        implements Appendable {

    public ProxyStringBuilder(StringBuilder proxy) {
        super(proxy);
    }

    public int length() {
        return proxy.length();
    }

    public int capacity() {
        return proxy.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        proxy.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        proxy.trimToSize();
    }

    public void setLength(int newLength) {
        proxy.setLength(newLength);
    }

    public StringBuilder append(Object obj) {
        return proxy.append(obj);
    }

    public StringBuilder append(String str) {
        return proxy.append(str);
    }

    public StringBuilder append(StringBuffer sb) {
        return proxy.append(sb);
    }

    public char charAt(int index) {
        return proxy.charAt(index);
    }

    @Override
    public StringBuilder append(CharSequence s) {
        return proxy.append(s);
    }

    @Override
    public StringBuilder append(CharSequence s, int start, int end) {
        return proxy.append(s, start, end);
    }

    public int codePointAt(int index) {
        return proxy.codePointAt(index);
    }

    public StringBuilder append(char[] str) {
        return proxy.append(str);
    }

    public StringBuilder append(char[] str, int offset, int len) {
        return proxy.append(str, offset, len);
    }

    public StringBuilder append(boolean b) {
        return proxy.append(b);
    }

    public StringBuilder append(char c) {
        return proxy.append(c);
    }

    public StringBuilder append(int i) {
        return proxy.append(i);
    }

    public StringBuilder append(long lng) {
        return proxy.append(lng);
    }

    public StringBuilder append(float f) {
        return proxy.append(f);
    }

    public StringBuilder append(double d) {
        return proxy.append(d);
    }

    public StringBuilder appendCodePoint(int codePoint) {
        return proxy.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        return proxy.delete(start, end);
    }

    public int codePointBefore(int index) {
        return proxy.codePointBefore(index);
    }

    public StringBuilder deleteCharAt(int index) {
        return proxy.deleteCharAt(index);
    }

    public StringBuilder replace(int start, int end, String str) {
        return proxy.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        return proxy.insert(index, str, offset, len);
    }

    public StringBuilder insert(int offset, Object obj) {
        return proxy.insert(offset, obj);
    }

    public StringBuilder insert(int offset, String str) {
        return proxy.insert(offset, str);
    }

    public StringBuilder insert(int offset, char[] str) {
        return proxy.insert(offset, str);
    }

    public StringBuilder insert(int dstOffset, CharSequence s) {
        return proxy.insert(dstOffset, s);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return proxy.codePointCount(beginIndex, endIndex);
    }

    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        return proxy.insert(dstOffset, s, start, end);
    }

    public StringBuilder insert(int offset, boolean b) {
        return proxy.insert(offset, b);
    }

    public StringBuilder insert(int offset, char c) {
        return proxy.insert(offset, c);
    }

    public StringBuilder insert(int offset, int i) {
        return proxy.insert(offset, i);
    }

    public StringBuilder insert(int offset, long l) {
        return proxy.insert(offset, l);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return proxy.offsetByCodePoints(index, codePointOffset);
    }

    public StringBuilder insert(int offset, float f) {
        return proxy.insert(offset, f);
    }

    public StringBuilder insert(int offset, double d) {
        return proxy.insert(offset, d);
    }

    public int indexOf(String str) {
        return proxy.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return proxy.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return proxy.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return proxy.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        return proxy.reverse();
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        proxy.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    @Override
    public String toString() {
        return proxy.toString();
    }

    public void setCharAt(int index, char ch) {
        proxy.setCharAt(index, ch);
    }

    public String substring(int start) {
        return proxy.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return proxy.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return proxy.substring(start, end);
    }

}
