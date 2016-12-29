package net.bodz.bas.fmt.rst.bean;

import java.io.IOException;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.rst.*;
import net.bodz.bas.io.BCharOut;

public abstract class RstBeanObject
        implements IRstSerializable, IElementHandler, IRstFormat {

    /** ⇱ Implementation Of {@link IRstSerializable}. */
    /* _____________________________ */static section.iface __RST_SERIALIZABLE__;

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        BeanRstDumper.getInstance().dump(out, this);
    }

    @Override
    public IElementHandler getElementHandler() {
        return this;
    }

    /** ⇱ Implementation Of {@link IElementHandler}. */
    /* _____________________________ */static section.iface __ELEMENT_HANDLER__;

    @Override
    public boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException {
        return new BeanElementHandler(this).attribute(name, data);
    }

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        return new BeanElementHandler(this).beginChild(name, args);
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return false;
    }

    @Override
    public void complete(IRstElement element)
            throws ElementHandlerException {
    }

    /** ⇱ Implementation Of {@link IRstFormat}. */
    /* _____________________________ */static section.iface __OVERRIDES__;

    @Override
    public String[] getRstElementArguments() {
        return IEmptyConsts.emptyStringArray;
    }

    @Override
    public boolean writeEntryOverride(IRstOutput out, String name)
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
