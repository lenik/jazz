package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedBasicTokenQueue
        extends AbstractDecorator<IForwardOnlyTokenQueue>
        implements
            IForwardOnlyTokenQueue {

    private static final long serialVersionUID = 1L;

    public DecoratedBasicTokenQueue(IForwardOnlyTokenQueue _orig) {
        super(_orig);
    }

    @Override
    public IForwardOnlyTokenQueue clone() {
        return getWrapped().clone();
    }

    @Override
    public int available() {
        return getWrapped().available();
    }

    @Override
    public String getRemainingPath() {
        return getWrapped().getRemainingPath();
    }

    @Override
    public boolean isEntered() {
        return getWrapped().isEntered();
    }

    @Override
    public boolean isDone() {
        return getWrapped().isDone();
    }

    @Override
    public void skip(int n) {
        getWrapped().skip(n);
    }

    @Override
    public String[] shift(int n) {
        return getWrapped().shift(n);
    }

    @Override
    public String[] shiftAll() {
        return getWrapped().shiftAll();
    }

    @Override
    public String shift() {
        return getWrapped().shift();
    }

    @Override
    public Integer shiftInt()
            throws ParseException {
        return getWrapped().shiftInt();
    }

    @Override
    public Long shiftLong()
            throws ParseException {
        return getWrapped().shiftLong();
    }

    @Override
    public String peek() {
        return getWrapped().peek();
    }

    @Override
    public String peekAhead(int offset) {
        return getWrapped().peekAhead(offset);
    }

    @Override
    public Integer peekInt()
            throws ParseException {
        return getWrapped().peekInt();
    }

    @Override
    public Integer peekIntAhead(int offset)
            throws ParseException {
        return getWrapped().peekIntAhead(offset);
    }

    @Override
    public Long peekLong()
            throws ParseException {
        return getWrapped().peekLong();
    }

    @Override
    public Long peekLongAhead(int offset)
            throws ParseException {
        return getWrapped().peekLongAhead(offset);
    }

    @Override
    public boolean isStopped() {
        return getWrapped().isStopped();
    }

    @Override
    public void stop() {
        getWrapped().stop();
    }

}
