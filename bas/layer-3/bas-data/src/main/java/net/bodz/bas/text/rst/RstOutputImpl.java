package net.bodz.bas.text.rst;

import java.io.IOException;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public class RstOutputImpl
        extends AbstractRstOutput {

    private final ITreeOut out;

    private RstOutputImpl(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    public static IRstOutput from(ICharOut out) {
        return from(TreeOutImpl.from(out));
    }

    public static IRstOutput from(IPrintOut out) {
        return from(TreeOutImpl.from(out));
    }

    public static IRstOutput from(ITreeOut out) {
        if (out instanceof IRstOutput)
            return (IRstOutput) out;
        else
            return new RstOutputImpl(out);
    }

    @Override
    public void beginElement(String name, String... args)
            throws IOException {
        out.print(name);
        for (String arg : args) {
            out.print(' ');
            out.print(arg);
        }
        out.println(" {");
        out.enter();
    }

    @Override
    public void endElement()
            throws IOException {
        out.leave();
        out.println("}");
    }

    @Override
    public void _attribute(String name, String data)
            throws IOException {
        out.println(name + ": " + data);
    }

}
