package net.bodz.bas.html.io;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class BHtmlOut_htm
        extends AbstractHtmlViewBuilder<BHtmlOut> {

    public BHtmlOut_htm() {
        super(BHtmlOut.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, BHtmlOut value) {
        return ContentTypes.text_html;
    }

    @Override
    public boolean isOrigin(BHtmlOut value) {
        return true;
    }

    @Override
    public boolean isFrame() {
        return false;
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<BHtmlOut> ref)
            throws ViewBuilderException, IOException {
        String html = ref.toString();
        out.verbatim(html);
        return null;
    }

}
