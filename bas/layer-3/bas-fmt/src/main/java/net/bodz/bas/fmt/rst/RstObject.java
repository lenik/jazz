package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.rst.bean.BeanElementHandler;
import net.bodz.bas.fmt.rst.bean.BeanRst;
import net.bodz.bas.fmt.rst.bean.BeanRstDumper;
import net.bodz.bas.fmt.rst.reflect.ReflectElementHandler;
import net.bodz.bas.fmt.rst.reflect.ReflectRstDumper;
import net.bodz.bas.io.BCharOut;

public abstract class RstObject
        implements IRstSerializable, IElementHandler, IRstFormat {

    private transient IRstDumper _dumper;
    private transient IElementHandler _handler;

    public RstObject() {
        _init(getClass().isAnnotationPresent(BeanRst.class));
    }

    public RstObject(boolean beanMode) {
        _init(beanMode);
    }

    private void _init(boolean beanMode) {
        if (beanMode) {
            _dumper = BeanRstDumper.getInstance();
            _handler = new BeanElementHandler(this);
        } else {
            _dumper = ReflectRstDumper.getInstance();
            _handler = new ReflectElementHandler(this);
        }
    }

    /** ⇱ Implementation Of {@link IRstSerializable}. */
    /* _____________________________ */static section.iface __RST_SERIALIZABLE__;

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        _dumper.dump(out, this);
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
        return _handler.attribute(name, data);
    }

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        return _handler.beginChild(name, args);
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return _handler.endChild(element);
    }

    @Override
    public void complete(IRstElement element)
            throws ElementHandlerException {
        _handler.complete(element);
    }

    /** ⇱ Implementation Of {@link IRstFormat}. */
    /* _____________________________ */static section.iface __OVERRIDES__;

    @Override
    public String[] getRstElementArguments() {
        return IEmptyConsts.emptyStringArray;
    }

    @Override
    public boolean writeEntryOverride(IRstOutput out, String field)
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
