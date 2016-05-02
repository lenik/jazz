package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlLi;
import net.bodz.bas.html.io.tag.HtmlSpan;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.t.preorder.PackageMap;
import net.bodz.bas.ui.dom1.IUiRef;

/**
 * @style .exception { color: red; }
 */
public class Throwable_htm
        extends AbstractHtmlViewBuilder<Throwable> {

    PackageMap<String> pkgStyleMap;

    public Throwable_htm() {
        pkgStyleMap = new PackageMap<>();
        pkgStyleMap.put("net.bodz", "p-bodz");
        pkgStyleMap.put("org.eclipse", "p-eclipse");
        pkgStyleMap.put("java", "p-java");
        pkgStyleMap.put("javax", "p-java");
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<Throwable> ref)
            throws ViewBuilderException, IOException {

        URLResource css = ClassResource.getData(Throwable_htm.class, "css");
        String style = css.read().readString();

        out.style().text(style);

        Throwable e = ref.get();

        out = out.div().class_("exception");
        out.header().text("Exception: " + e.toString());

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
