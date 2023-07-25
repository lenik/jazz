package net.bodz.bas.bean.java;

import java.beans.ExceptionListener;

import net.bodz.bas.bean.api.IExceptionListener;

public class JbExceptionListener
        implements
            IExceptionListener {

    ExceptionListener el;

    public JbExceptionListener(ExceptionListener el) {
        if (el == null)
            throw new NullPointerException("el");
        this.el = el;
    }

    @Override
    public void exceptionThrown(Exception e) {
        el.exceptionThrown(e);
    }

    public static JbExceptionListener convert(ExceptionListener o) {
        if (o == null)
            return null;
        else
            return new JbExceptionListener(o);
    }

    public static JbExceptionListener[] convert(ExceptionListener[] src) {
        if (src == null)
            return null;
        JbExceptionListener[] dst = new JbExceptionListener[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

    public static ExceptionListener convert(IExceptionListener o) {
        if (o == null)
            return null;
        else
            return new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    o.exceptionThrown(e);
                }
            };
    }

    public static ExceptionListener[] convert(IExceptionListener[] src) {
        if (src == null)
            return null;
        ExceptionListener[] dst = new ExceptionListener[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
