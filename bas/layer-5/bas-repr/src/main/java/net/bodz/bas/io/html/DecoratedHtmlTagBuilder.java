package net.bodz.bas.io.html;

import net.bodz.bas.io.xml.IXmlTagBuilder;

@SuppressWarnings("unchecked")
public abstract class DecoratedHtmlTagBuilder<self_t extends IXmlTagBuilder>
        extends AbstractHtmlTagBuilder<self_t> {

    private IXmlTagBuilder orig;

    public DecoratedHtmlTagBuilder(IXmlTagBuilder orig) {
        super();
        this.orig = orig;
    }

    @Override
    public self_t attr(String name, Object value) {
        orig.attr(name, value);
        return (self_t) this;
    }

    @Override
    public self_t id(String id) {
        orig.id(id);
        return (self_t) this;
    }

    @Override
    public self_t tagText(String text) {
        orig.tagText(text);
        return (self_t) this;
    }

}
