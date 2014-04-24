package net.bodz.bas.io.html;

import net.bodz.bas.io.xml.IXmlTagBuilder;

@SuppressWarnings("unchecked")
public abstract class DecoratedHtmlTagBuilder<self_t extends IHtmlTagBuilder<?>>
        extends AbstractHtmlTagBuilder<self_t> {

    private IXmlTagBuilder orig;

    public DecoratedHtmlTagBuilder(IXmlTagBuilder orig) {
        super();
        this.orig = orig;
    }

    @Override
    public self_t start() {
        return (self_t) orig.start();
    }

    @Override
    public self_t end() {
        return (self_t) orig.end();
    }

    @Override
    public int getExit() {
        return orig.getExit();
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
    public self_t text(String text) {
        orig.text(text);
        return (self_t) this;
    }

    @Override
    public self_t textf(String format, Object... args) {
        orig.textf(format, args);
        return (self_t) this;
    }

}
