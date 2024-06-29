package net.bodz.bas.c.jakarta.servlet.http;

public abstract class AbstractServletRequestListener
        implements
            IServletRequestListener {

    @Override
    public int getPriority() {
        return 0;
    }

}
