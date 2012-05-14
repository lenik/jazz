package net.bodz.bas.disp.view;

import java.io.IOException;

import net.bodz.bas.disp.req.IRequestDispatch;

public abstract class AbstractViewRenderer
        implements IViewRenderer {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isFallback() {
        return false;
    }

    @Override
    public boolean render(Object obj, IRequestDispatch req, IResponseInfo resp)
            throws IOException {
        if (obj == null)
            throw new NullPointerException("obj");

        Class<?> clazz = obj.getClass();

        return render(clazz, obj, req, resp);
    }

    @Override
    public boolean renderTx(Class<?> clazz, Object obj, IRequestDispatch req, IResponseInfo resp)
            throws IOException {
        return render(clazz, obj, req, resp);
    }

    @Override
    public boolean renderTx(Object obj, IRequestDispatch req, IResponseInfo resp)
            throws IOException {
        return render(obj, req, resp);
    }

}
