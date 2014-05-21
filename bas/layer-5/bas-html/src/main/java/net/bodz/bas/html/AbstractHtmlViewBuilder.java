package net.bodz.bas.html;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.IArtifactDependency;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.artifact.MutableWebArtifact;
import net.bodz.bas.i18n.dom1.IElement;
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
    public void preview(IHtmlViewContext ctx, IUiRef<T> ref, IOptions options) {
        IHtmlMetaData metaData = ctx.getMetaData();

        T value = ref.get();
        if (value == null)
            throw new NullPointerException("value");

        StringBuilder title = new StringBuilder();
        if (value instanceof IElement) {
            IElement elm = (IElement) value;
            title.append(elm.getLabel());
        } else {
            title.append(value);
        }
        if (metaData.getTitle() != null) {
            title.append(" - ");
            title.append(metaData.getTitle());
        }
        metaData.setTitle(title.toString());
    }

    @Override
    public final Object buildView(Object _ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException {
        IHtmlViewContext ctx = (IHtmlViewContext) _ctx;
        try {
            IHtmlViewContext innerCtx = buildHtmlView(ctx, ref, options);
            buildHtmlViewTail(ctx, ref, options);
            return innerCtx;
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
    }

    @Override
    public final IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return buildHtmlView(ctx, ref, IOptions.NULL);
    }

    @Override
    public final void buildHtmlViewTail(IHtmlViewContext ctx, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        buildHtmlViewTail(ctx, ref, IOptions.NULL);
    }

    @Override
    public void buildHtmlViewTail(IHtmlViewContext ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        // Do nothing in default implementation.
    }

    protected static boolean enter(IHtmlViewContext ctx)
            throws IOException {
        String uri = ctx.getRequest().getRequestURI();
        if (uri.endsWith("/"))
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

    protected static void writeHeadMetas(IHtmlViewContext ctx)
            throws IOException {
        IHtmlOut out = ctx.getOut();
        IHtmlMetaData metaData = ctx.getMetaData();

        String title = metaData.getTitle().trim();
        if (title != null)
            out.title().text(title);

        out.meta().httpEquiv(IHtmlMetaData.HTTP_CONTENT_TYPE).content(ctx.getResponse().getContentType());
        out.meta().name(IHtmlMetaData.META_GENERATOR).content("Jazz BAS Repr/HTML 2.0");

        for (Entry<String, String> entry : metaData.getHttpEquivMetaMap().entrySet())
            out.meta().httpEquiv(entry.getKey()).content(entry.getValue());

        for (Entry<String, String> entry : metaData.getMetaMap().entrySet())
            out.meta().name(entry.getKey()).content(entry.getValue());
    }

    protected static void writeHeadImports(IHtmlViewContext ctx)
            throws IOException {
        IHtmlOut out = ctx.getOut();
        IArtifactManager artifactManager = ctx.query(IArtifactManager.class);
        IHtmlMetaData metaData = ctx.getMetaData();

        for (IArtifact artifact : artifactManager.getClosure(metaData, IArtifactDependency.STYLESHEET, null)) {
            MutableWebArtifact wa = (MutableWebArtifact) artifact;
            out.link().css(wa.getAnchor().toString());
        }

        for (IArtifact artifact : artifactManager.getClosure(metaData, IArtifactDependency.SCRIPT, null)) {
            MutableWebArtifact wa = (MutableWebArtifact) artifact;
            out.script().javascriptSrc(wa.getAnchor().toString());
        }
    }

    static IndexedHtmlViewBuilderFactory factory = IndexedHtmlViewBuilderFactory.getInstance();

    protected <_t> void embed(IHtmlViewContext ctx, Object obj, String... features)
            throws ViewBuilderException, IOException {
        UiValue<Object> entry = UiValue.wrap(obj);
        embed(ctx, entry, features);
    }

    protected <_t> void embed(IHtmlViewContext ctx, IUiRef<_t> ref, String... features)
            throws ViewBuilderException, IOException {
        Class<? extends _t> type = ref.getValueType();

        IHtmlViewBuilder<_t> viewBuilder = factory.getViewBuilder(type, features);
        if (viewBuilder == null)
            throw new ViewBuilderException("Can't build view for " + type);

        viewBuilder.buildHtmlView(ctx, ref);
    }

    protected IXmlTagBuilder createTag(IHtmlViewContext ctx, String tagName, IUiElementStyleDeclaration style)
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

}
