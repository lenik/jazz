package net.bodz.bas.http.viz;

import java.io.IOException;

import net.bodz.bas.io.IPrintOut;
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
    public Object buildView(IQueryable _ctx, Object _out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException {
        IHttpViewContext ctx = (IHttpViewContext) _ctx;
        IPrintOut out = (IPrintOut) _out;
        try {
            buildHttpView(ctx, out, ref, options);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void buildHttpView(IHttpViewContext ctx, IPrintOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        buildHttpView(ctx, out, ref, IOptions.NULL);
    }

}
