package net.bodz.bas.repr.path;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedTokenQueue
        extends AbstractDecorator<ITokenQueue>
        implements
            ITokenQueue {

    private static final long serialVersionUID = 1L;

    public DecoratedTokenQueue(ITokenQueue _orig) {
        super(_orig);
    }

    @Override
    public ITokenQueue clone() {
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
    public boolean isEmpty() {
        return getWrapped().isEmpty();
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
    public Integer shiftInt() {
        return getWrapped().shiftInt();
    }

    @Override
    public Long shiftLong() {
        return getWrapped().shiftLong();
    }

    @Override
    public String peek() {
        return getWrapped().peek();
    }

    @Override
    public String peek(int offset) {
        return getWrapped().peek(offset);
    }

    @Override
    public Integer peekInt() {
        return getWrapped().peekInt();
    }

    @Override
    public Integer peekInt(int offset) {
        return getWrapped().peekInt(offset);
    }

    @Override
    public Long peekLong() {
        return getWrapped().peekLong();
    }

    @Override
    public Long peekLong(int offset) {
        return getWrapped().peekLong(offset);
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
