package net.bodz.bas.sio;

import java.io.IOException;

public class BPrintOut
        extends PrintOutImpl
        implements CharSequence, Appendable {

    BCharOut baseImpl;

    public BPrintOut() {
        this(new BCharOut());
    }

    public BPrintOut(BCharOut baseImpl) {
        super(baseImpl);
        this.baseImpl = baseImpl;
    }

    @Override
    public String toString() {
        return getBaseImpl().toString();
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

}
