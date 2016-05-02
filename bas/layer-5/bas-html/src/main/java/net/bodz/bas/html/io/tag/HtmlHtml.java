package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.gen._HtmlHtml;

public class HtmlHtml
        extends _HtmlHtml<HtmlHtml> {

    public HtmlHtml(HtmlDoc doc) {
        super(doc);
    }

    @Override
    protected void pretag(IHtmlOut parent) {
        parent.verbatim("<!--[if lt IE 7]> <html class=\"lt-ie10 lt-ie9 lt-ie8 lt-ie7\"> <![endif]-->\n");
        parent.verbatim("<!--[if IE 7]>    <html class=\"lt-ie10 lt-ie9 lt-ie8\"> <![endif]-->\n");
        parent.verbatim("<!--[if IE 8]>    <html class=\"lt-ie10 lt-ie9\"> <![endif]-->\n");
        parent.verbatim("<!--[if IE 9]>    <html class=\"lt-ie10\"> <![endif]-->\n");
        parent.verbatim("<!--[if gt IE 9]><!-->");
    }

    @Override
    protected void startTagClose() {
        super.startTagClose();
        treeOut.println("<!--<![endif]-->");
    }

    @Override
    public AbstractRecHtmlOut<?> end() {
        return super.end();
    }

}
