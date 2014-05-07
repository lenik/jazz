package net.bodz.bas.html;

import java.io.IOException;

import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.io.xml.IXmlTagBuilder;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.css3.Border;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiValue;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public abstract class AbstractHtmlViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHtmlViewBuilder<T> {

    public AbstractHtmlViewBuilder(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public ContentType getContentType(T value) {
        return ContentTypes.text_html;
    }

    @Override
    public boolean isOrigin(T value) {
        return false;
    }

    @Override
    public boolean isFrame() {
        return false;
    }

    @Override
    public void getRequirements(IRequirements requires) {
    }

    @Override
    public void buildTitle(StringBuilder buffer, T value) {
        if (value == null)
            throw new NullPointerException("value");
        String string = value.toString();
        if (buffer.length() != 0)
            buffer.append(" - ");
        buffer.append(string);
    }

    @Override
    public final Object buildView(Object _ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException {
        IHttpReprContext ctx = (IHttpReprContext) _ctx;
        try {
            IHttpReprContext innerCtx = buildHtmlView(ctx, ref, options);
            buildHtmlViewTail(ctx, ref, options);
            return innerCtx;
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
    }

    @Override
    public final IHttpReprContext buildHtmlView(IHttpReprContext ctx, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return buildHtmlView(ctx, ref, IOptions.NULL);
    }

    @Override
    public final void buildHtmlViewTail(IHttpReprContext ctx, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        buildHtmlViewTail(ctx, ref, IOptions.NULL);
    }

    @Override
    public void buildHtmlViewTail(IHttpReprContext ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        // Do nothing in default implementation.
    }

    static IndexedHtmlViewBuilderFactory factory = IndexedHtmlViewBuilderFactory.getInstance();

    protected <_t> void embed(IHttpReprContext ctx, Object obj, String... features)
            throws ViewBuilderException, IOException {
        UiValue<Object> entry = UiValue.wrap(obj);
        embed(ctx, entry, features);
    }

    protected <_t> void embed(IHttpReprContext ctx, IUiRef<_t> ref, String... features)
            throws ViewBuilderException, IOException {
        Class<? extends _t> type = ref.getValueType();

        IHtmlViewBuilder<_t> viewBuilder = factory.getViewBuilder(type, features);
        if (viewBuilder == null)
            throw new ViewBuilderException("Can't build view for " + type);

        viewBuilder.buildHtmlView(ctx, ref);
    }

    protected IXmlTagBuilder createTag(IHttpReprContext ctx, String tagName, IUiElementStyleDeclaration style)
            throws IOException {
        IHtmlOut out = ctx.getOut();
        IXmlTagBuilder tag = out.start(tagName);

        Border border = style.getBorder();
        if (border != null) {
            border.getWidth();
            border.getColor();

            style.getBorderTop();
            style.getBorderRight();
            style.getBorderBottom();
            style.getBorderLeft();
        }

        return tag;
    }

    protected static boolean enter(IHttpReprContext ctx)
            throws IOException {
        if (ctx.getTokenQueue().isEntered())
            return false;

        StringBuffer url = ctx.getRequest().getRequestURL();
        url.append('/');

        String queryString = ctx.getRequest().getQueryString();
        if (queryString != null) {
            url.append('?');
            url.append(queryString);
        }

        ctx.getResponse().sendRedirect(url.toString());
        return true;
    }

}
