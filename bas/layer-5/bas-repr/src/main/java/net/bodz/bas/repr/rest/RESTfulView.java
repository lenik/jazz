package net.bodz.bas.repr.rest;

import java.io.IOException;

public abstract class RESTfulView
        implements IRESTfulView {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isFallback() {
        return false;
    }

    @Override
    public boolean render(Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException {
        if (obj == null)
            throw new NullPointerException("obj");

        Class<?> clazz = obj.getClass();

        return render(clazz, obj, req, resp);
    }

    @Override
    public boolean renderTx(Class<?> clazz, Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException {
        return render(clazz, obj, req, resp);
    }

    @Override
    public boolean renderTx(Object obj, IRESTfulRequest req, IRESTfulResponse resp)
            throws IOException {
        return render(obj, req, resp);
    }

}
