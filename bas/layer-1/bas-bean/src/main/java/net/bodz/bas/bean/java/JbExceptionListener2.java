package net.bodz.bas.bean.java;

import java.beans.ExceptionListener;

import net.bodz.bas.bean.api.IExceptionListener;

public class JbExceptionListener2
        implements
            ExceptionListener {

    IExceptionListener el;

    public JbExceptionListener2(IExceptionListener el) {
        if (el == null)
            throw new NullPointerException("el");
        this.el = el;
    }

    @Override
    public void exceptionThrown(Exception e) {
        el.exceptionThrown(e);
    }

}
