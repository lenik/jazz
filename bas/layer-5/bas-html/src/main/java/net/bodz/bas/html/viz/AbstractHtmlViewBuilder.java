package net.bodz.bas.html.viz;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.html.artifact.ArtifactType;
import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.artifact.MutableWebArtifact;
import net.bodz.bas.html.dom.IHtmlHeadData;
import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.http.viz.AbstractHttpViewBuilder;
import net.bodz.bas.http.viz.HttpViewBuilderFamily;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.http.viz.IndexedHttpViewBuilderFactory;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.req.IViewOfRequest;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.css3.Border;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiValue;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public abstract class AbstractHtmlViewBuilder<T>
        extends AbstractHttpViewBuilder<T>
        implements IHtmlViewBuilder<T> {

    public AbstractHtmlViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public HttpViewBuilderFamily getFamily() {
        return HttpViewBuilderFamily.HTML;
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
    public String getEncoding() {
        return "utf-8";
    }

    @Override
    public boolean isOrigin(T value) {
        return false;
    }

    @Override
    public final void precompile(IHttpViewContext _ctx, IUiRef<T> ref) {
        IHtmlViewContext ctx = (IHtmlViewContext) _ctx;
        precompile(ctx, ref);
    }

    public void precompile(IHtmlViewContext ctx, IUiRef<T> ref) {
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
    public final Object buildViewStart(IQueryable _ctx, Object out, IUiRef<T> ref)
            throws ViewBuilderException {
        IHtmlViewContext ctx = (IHtmlViewContext) _ctx;
        try {
            buildHtmlViewStart(ctx, (IHtmlOut) out, ref);
            return out;
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
    }

    @Override
    public final Object buildHttpViewStart(IHttpViewContext _ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        IHtmlViewContext ctx = (IHtmlViewContext) _ctx;

        HtmlDoc doc = new HtmlDoc(resp.getWriter(), HtmlOutputFormat.DEFAULT);
        IHtmlOut html = doc.newHtmlOut();
        try {
            IHtmlOut body = buildHtmlViewStart(ctx, html, ref);
            buildHtmlViewEnd(ctx, html, body, ref);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        html.close();
        return null;
    }

    @Override
    public final void buildHttpViewEnd(IHttpViewContext ctx, HttpServletResponse resp, Object o, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
    }

    @Override
    public void buildHtmlViewEnd(IHtmlViewContext ctx, IHtmlOut out, IHtmlOut body, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return;
    }

    public final void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        IHtmlOut body = buildHtmlViewStart(ctx, out, ref);
        buildHtmlViewEnd(ctx, out, body, ref);
    }

    protected static boolean addSlash(IHttpViewContext ctx, IUiRef<?> ref)
            throws IOException {
        Object obj = ref.get();
        IPathArrival arrival = ctx.query(IPathArrival.class);
        boolean arrivedHere = arrival.getPrevious(obj).getRemainingPath() == null;
        return arrivedHere && addSlash(ctx);
    }

    protected static boolean addSlash(IHttpViewContext ctx)
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
        ctx.stop();
        return true;
    }

    protected static void writeHeadMetas(IHtmlViewContext ctx, IHtmlOut head)
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

    protected static void writeHeadImports(IHtmlViewContext ctx, IHtmlOut head)
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

    static IHttpViewBuilderFactory factory = IndexedHttpViewBuilderFactory.getInstance();

    protected <_t> void embed(IHtmlViewContext ctx, IHtmlOut out, Object obj, String... features)
            throws ViewBuilderException, IOException {
        UiValue<Object> entry = UiValue.wrap(obj);
        embed(ctx, out, entry, features);
    }

    protected <_t> void embed(IHtmlViewContext ctx, IHtmlOut out, IUiRef<_t> ref, String... features)
            throws ViewBuilderException, IOException {
        Class<? extends _t> type = ref.getValueType();

        IHtmlViewBuilder<_t> viewBuilder = factory.getViewBuilders(type, features).findFirst(IHtmlViewBuilder.class);
        if (viewBuilder == null)
            throw new ViewBuilderException("Can't build view for " + type);

        viewBuilder.buildHtmlViewStart(ctx, out, ref);
    }

    protected IHtmlOut createTag(IHtmlOut out, String tagName, IUiElementStyleDeclaration style)
            throws IOException {
        IHtmlOut tag = out.begin(tagName);

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
