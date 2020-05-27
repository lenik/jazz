package net.bodz.bas.c.javax.servlet.http;

public abstract class AbstractServletRequestListener
        implements IServletRequestListener {

    @Override
    public int getPriority() {
        return 0;
    }

}
