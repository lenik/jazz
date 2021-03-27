package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.meta.bean.Transient;

public abstract class AbstractRstObject
        implements
            IRstSerializable,
            IRstHandler,
            IRstOverrides {

    private transient IRstHandler _handler;

    private IRstHandler _handler() {
        if (_handler == null)
            synchronized (this) {
                if (_handler == null)
                    _handler = RstFn.getDefaultHandler(this);
            }
        return _handler;
    }

    /** ⇱ Implementation Of {@link IRstSerializable}. */
    /* _____________________________ */static section.iface __RST_SERIALIZABLE__;

    @Override
    public IRstHandler getElementHandler() {
        return this;
    }

    /** ⇱ Implementation Of {@link IRstHandler}. */
    /* _____________________________ */static section.iface __ELEMENT_HANDLER__;

    @Override
    public boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException {
        return _handler().attribute(name, data);
    }

    @Override
    public IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        return _handler().beginChild(name, args);
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return _handler().endChild(element);
    }

    @Override
    public void complete(IRstElement element)
            throws ElementHandlerException {
        _handler().complete(element);
    }

    /** ⇱ Implementation Of {@link IRstOverrides}. */
    /* _____________________________ */static section.iface __OVERRIDES__;

    @Transient
    @Override
    public String[] getRstElementArguments() {
        return IEmptyConsts.emptyStringArray;
    }

    @Override
    public boolean writeSpecialRstEntry(IRstOutput out, String field)
            throws IOException {
        return false;
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

    @Override
    public String toString() {
        BCharOut buf = new BCharOut(1024);
        IRstOutput out = RstOutputImpl.from(buf);
        try {
            writeObject(out);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        String rst = buf.toString();
        return rst;
    }

}
