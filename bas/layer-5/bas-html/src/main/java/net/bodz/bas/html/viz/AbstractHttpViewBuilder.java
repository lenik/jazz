package net.bodz.bas.html.viz;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.html.artifact.ArtifactType;
import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.artifact.MutableWebArtifact;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.req.IViewOfRequest;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.css3.Border;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiValue;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public abstract class AbstractHttpViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHttpViewBuilder<T> {

    public AbstractHttpViewBuilder(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, T value) {
        IViewOfRequest view = (IViewOfRequest) request.getAttribute(IViewOfRequest.class.getName());
        if (view != null)
            return view.getContentType();
        else
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
    public void preview(IHttpViewContext ctx, IUiRef<T> ref, IOptions options) {
        IHtmlHeadData metaData = ctx.getHeadData();

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
    public final Object buildView(IQueryable _ctx, Object out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException {
        IHttpViewContext ctx = (IHttpViewContext) _ctx;
        try {
            return buildHtmlView(ctx, (IHtmlTag) out, ref, options);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
    }

    @Override
    public final IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return buildHtmlView(ctx, out, ref, IOptions.NULL);
    }

    protected static boolean enter(IHttpViewContext ctx, IUiRef<?> ref)
            throws IOException {
        Object obj = ref.get();
        IPathArrival arrival = ctx.query(IPathArrival.class);
        boolean arrivedHere = arrival.getPrevious(obj).getRemainingPath() == null;
        return arrivedHere && enter(ctx);
    }

    protected static boolean enter(IHttpViewContext ctx)
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

    protected static void writeHeadMetas(IHttpViewContext ctx, IHtmlTag head)
            throws IOException {
        IHtmlHeadData metaData = ctx.getHeadData();

        String title = metaData.getTitle().trim();
        if (title != null)
            head.title().text(title);

        head.meta().httpEquiv(IHtmlHeadData.HTTP_CONTENT_TYPE).content(ctx.getResponse().getContentType());
        head.meta().name(IHtmlHeadData.META_GENERATOR).content("Jazz BAS Repr/HTML 2.0");

        for (Entry<String, String> entry : metaData.getHttpEquivMetaMap().entrySet())
            head.meta().httpEquiv(entry.getKey()).content(entry.getValue());

        for (Entry<String, String> entry : metaData.getMetaMap().entrySet())
            head.meta().name(entry.getKey()).content(entry.getValue());
    }

    protected static void writeHeadImports(IHttpViewContext ctx, IHtmlTag head)
            throws IOException {
        IArtifactManager artifactManager = ctx.query(IArtifactManager.class);
        IHtmlHeadData metaData = ctx.getHeadData();

        for (IArtifact artifact : artifactManager.getClosure(metaData, ArtifactType.STYLESHEET, null)) {
            MutableWebArtifact wa = (MutableWebArtifact) artifact;
            head.link().css(wa.getAnchor().toString());
        }

        for (IArtifact artifact : artifactManager.getClosure(metaData, ArtifactType.SCRIPT, null)) {
            MutableWebArtifact wa = (MutableWebArtifact) artifact;
            head.script().javascriptSrc(wa.getAnchor().toString());
        }
    }

    static IndexedHttpViewBuilderFactory factory = IndexedHttpViewBuilderFactory.getInstance();

    protected <_t> void embed(IHttpViewContext ctx, IHtmlTag out, Object obj, String... features)
            throws ViewBuilderException, IOException {
        UiValue<Object> entry = UiValue.wrap(obj);
        embed(ctx, out, entry, features);
    }

    protected <_t> void embed(IHttpViewContext ctx, IHtmlTag out, IUiRef<_t> ref, String... features)
            throws ViewBuilderException, IOException {
        Class<? extends _t> type = ref.getValueType();

        IHttpViewBuilder<_t> viewBuilder = factory.getViewBuilder(type, features);
        if (viewBuilder == null)
            throw new ViewBuilderException("Can't build view for " + type);

        viewBuilder.buildHtmlView(ctx, out, ref);
    }

    protected IHtmlTag createTag(IHtmlTag out, String tagName, IUiElementStyleDeclaration style)
            throws IOException {
        IHtmlTag tag = out.insert(tagName);

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
