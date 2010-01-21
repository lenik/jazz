package net.bodz.bas.flow.units.builtin.sinks;

import java.io.Flushable;
import java.io.IOException;

import net.bodz.bas.flow.units.sinks.CharOutSinkUnitTest;
import net.bodz.bas.sio.ICharOut;

/**
 * @test {@link CharOutSinkUnitTest}
 */
public class CharOutSinkUnit
        extends TextProcessSinkUnit {

    private ICharOut out;
    private Flushable flushable;

    public CharOutSinkUnit(ICharOut out, boolean autoFlush)
            throws IOException {
        this.out = out;
        setAutoFlush(autoFlush);
    }

    public CharOutSinkUnit(ICharOut out)
            throws IOException {
        this(out, false);
    }

    public ICharOut getCharOut() {
        return out;
    }

    public void setCharOut(ICharOut out) {
        this.out = out;
    }

    public boolean isAutoFlush() {
        return flushable != null;
    }

    public void setAutoFlush(boolean autoFlush) {
        if (autoFlush && out instanceof Flushable)
            this.flushable = out;
    }

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        if (out instanceof Flushable)
            ((Flushable) out).flush();
    }

    @Override
    public void recv(char[] chars, int start, int end)
            throws IOException {
        int length = end - start;
        out.write(chars, start, length);
        if (flushable != null)
            flushable.flush();
    }

}
