package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IExceptionListener;

import com.googlecode.openbeans.ExceptionListener;

public class ObExceptionListener2
        implements
            ExceptionListener {

    IExceptionListener el;

    public ObExceptionListener2(IExceptionListener el) {
        if (el == null)
            throw new NullPointerException("el");
        this.el = el;
    }

    @Override
    public void exceptionThrown(Exception e) {
        el.exceptionThrown(e);
    }

}
