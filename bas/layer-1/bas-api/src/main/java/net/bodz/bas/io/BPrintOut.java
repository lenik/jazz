package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.io.impl.PrintOutImpl;
import net.bodz.bas.meta.decl.NotNull;

public class BPrintOut
        extends PrintOutImpl
        implements CharSequence,
                   Appendable {

    BCharOut baseImpl;

    public BPrintOut() {
        this(new BCharOut());
    }

    public BPrintOut(BCharOut baseImpl) {
        super(baseImpl);
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

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return baseImpl.subSequence(start, end);
    }

    @Override
    public BPrintOut append(CharSequence csq)
            throws IOException {
        baseImpl.append(csq);
        return this;
    }

    @Override
    public BPrintOut append(CharSequence csq, int start, int end)
            throws IOException {
        baseImpl.append(csq, start, end);
        return this;
    }

    @Override
    public BPrintOut append(char c)
            throws IOException {
        baseImpl.append(c);
        return this;
    }

    @Override
    public void flush() {
        baseImpl.flush();
    }

    @NotNull
    @Override
    public String toString() {
        return baseImpl.toString();
    }

}
