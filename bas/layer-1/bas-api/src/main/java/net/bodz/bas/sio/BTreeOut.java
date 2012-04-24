package net.bodz.bas.sio;

import java.io.IOException;

import net.bodz.bas.sio.util.ITextIndention;
import net.bodz.bas.sio.util.TextIndention;

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
    public Appendable append(CharSequence csq)
            throws IOException {
        return baseImpl.append(csq);
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end)
            throws IOException {
        return baseImpl.append(csq, start, end);
    }

    @Override
    public Appendable append(char c)
            throws IOException {
        return baseImpl.append(c);
    }

    @Override
    public String toString() {
        return baseImpl.toString();
    }

}
