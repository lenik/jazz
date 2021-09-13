package net.bodz.bas.repr.viz.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractHttpViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHttpViewBuilder<T> {

    public AbstractHttpViewBuilder() {
        super();
    }

    public AbstractHttpViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public HttpViewBuilderFamily getFamily() {
        return HttpViewBuilderFamily.GENERAL;
    }

    @Override
    public String getEncoding(T value) {
        return "utf-8";
    }

    @Override
    public boolean isOrigin(T value) {
        return true;
    }

    @Override
    public boolean isFrame() {
        return false;
    }

    @Override
    public void precompile(IHttpViewContext ctx, IUiRef<T> ref) {
    }

    @Override
    public Object buildViewStart(IQueryable _ctx, Object parent, IUiRef<T> ref)
            throws ViewBuilderException {
        IHttpViewContext ctx = (IHttpViewContext) _ctx;
        HttpServletResponse resp = ctx.getResponse();
        try {
            buildHttpViewStart(ctx, resp, ref);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        return parent;
    }

    @Override
    public void buildHttpViewEnd(IHttpViewContext ctx, HttpServletResponse resp, Object o, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
    }

}
