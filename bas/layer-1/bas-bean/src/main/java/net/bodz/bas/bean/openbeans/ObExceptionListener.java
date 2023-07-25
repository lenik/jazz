package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IExceptionListener;

import com.googlecode.openbeans.ExceptionListener;

public class ObExceptionListener
        implements
            IExceptionListener {

    ExceptionListener el;

    public ObExceptionListener(ExceptionListener el) {
        if (el == null)
            throw new NullPointerException("el");
        this.el = el;
    }

    @Override
    public void exceptionThrown(Exception e) {
        el.exceptionThrown(e);
    }

    public static ObExceptionListener convert(ExceptionListener o) {
        if (o == null)
            return null;
        else
            return new ObExceptionListener(o);
    }

    public static ObExceptionListener[] convert(ExceptionListener[] src) {
        if (src == null)
            return null;
        ObExceptionListener[] dst = new ObExceptionListener[src.length];
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
