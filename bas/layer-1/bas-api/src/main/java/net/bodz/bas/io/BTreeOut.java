package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.io.impl.TreeOutImpl;

public class BTreeOut
        extends TreeOutImpl
        implements CharSequence, Appendable {

    BCharOut baseImpl;

    public BTreeOut() {
        this(new BCharOut(), new TextIndention());
    }

    public BTreeOut(BCharOut baseImpl, ITextIndention textIndention) {
        super(baseImpl, textIndention);
        this.baseImpl = baseImpl;
    }

    @Override
    public int length() {
        return baseImpl.length();
    }

    @Override
    public char charAt(int index) {
        return baseImpl.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return baseImpl.subSequence(start, end);
    }

    @Override
    public BTreeOut append(CharSequence csq)
            throws IOException {
        baseImpl.append(csq);
        return this;
    }

    @Override
    public BTreeOut append(CharSequence csq, int start, int end)
            throws IOException {
        baseImpl.append(csq, start, end);
        return this;
    }

    @Override
    public BTreeOut append(char c)
            throws IOException {
        baseImpl.append(c);
        return this;
    }

    @Override
    public String toString() {
        return baseImpl.toString();
    }

}
