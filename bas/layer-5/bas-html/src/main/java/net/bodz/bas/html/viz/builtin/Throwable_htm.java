package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlHeader;
import net.bodz.bas.html.io.tag.HtmlLi;
import net.bodz.bas.html.io.tag.HtmlSpan;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.preorder.PackageMap;
import net.bodz.bas.ui.dom1.IUiRef;

public class Throwable_htm
        extends AbstractHtmlViewBuilder<Throwable> {

    static final Logger logger = LoggerFactory.getLogger(Throwable_htm.class);

    String title;
    PackageMap<String> pkgStyleMap;

    public Throwable_htm() {
        pkgStyleMap = new PackageMap<>();
        pkgStyleMap.put("net.bodz", "p-bodz");
        pkgStyleMap.put("org.eclipse", "p-eclipse");
        pkgStyleMap.put("java", "p-java");
        pkgStyleMap.put("javax", "p-java");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<Throwable> ref) {
        URLResource css = ClassResource.getData(Throwable_htm.class, "css");
        String style;
        try {
            style = css.read().readString();
            out.style().text(style);
        } catch (Exception e) {
            logger.error("Can't read class resource " + css, e);
        }

        Throwable e = ref.get();

        out = out.div().class_("exception");
        HtmlHeader hdr = out.header();
        if (title != null) {
            hdr.h1().text(title);
            hdr.hr();
        }
        hdr.text("Exception: " + e.toString());

        StackTraceElement[] stackTrace = e.getStackTrace();
        HtmlUl ul = out.ul().class_("stack-trace");
        for (StackTraceElement elm : stackTrace) {
            HtmlLi li = ul.li();

            String className = elm.getClassName();
            String fileName = elm.getFileName();
            int lineNumber = elm.getLineNumber();
            boolean hasSource = fileName != null && lineNumber >= 0;

            String pkgStyle = pkgStyleMap.meet(className);
            li.class_(pkgStyle, elm.isNativeMethod() ? "native" : null, hasSource ? null : "no-source");

            li.span().class_("class").text(elm.getClassName());
            li.span().class_("method").text(elm.getMethodName());

            HtmlSpan loc = li.span().class_("loc");
            if (elm.isNativeMethod())
                loc.text("(Native Method)");
            else {
                if (hasSource)
                    loc.text("(" + fileName + ":" + lineNumber + ")");
                else if (fileName != null)
                    loc.text("(" + fileName + ")");
                else
                    loc.text("(Unknown Source)");
            }
        }
        out.end();
        return null;
    }

}
