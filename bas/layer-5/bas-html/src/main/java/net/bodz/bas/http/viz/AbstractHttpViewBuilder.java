package net.bodz.bas.http.viz;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractHttpViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHttpViewBuilder<T> {

    public AbstractHttpViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    public AbstractHttpViewBuilder(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public HttpViewBuilderFamily getFamily() {
        return HttpViewBuilderFamily.GENERAL;
    }

    @Override
    public String getEncoding() {
        return null;
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
    public void preview(IHttpViewContext ctx, IUiRef<T> ref, IOptions options) {
    }

    @Override
    public Object buildView(IQueryable _ctx, Object _out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException {
        IHttpViewContext ctx = (IHttpViewContext) _ctx;
        try {
            buildHttpView(ctx, ctx.getResponse(), ref, options);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void buildHttpView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        buildHttpView(ctx, resp, ref, IOptions.NULL);
    }

}
