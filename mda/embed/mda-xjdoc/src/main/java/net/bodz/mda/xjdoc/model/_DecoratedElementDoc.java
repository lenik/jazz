package net.bodz.mda.xjdoc.model;

import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public abstract class _DecoratedElementDoc<T extends IElementDoc>
        extends AbstractDecorator<T>
        implements
            IElementDoc {

    private static final long serialVersionUID = 1L;

    public _DecoratedElementDoc(T _orig) {
        super(_orig);
    }

    @Override
    public ITagLibrary getTagLibrary() {
        return getWrapped().getTagLibrary();
    }

    @Override
    public iString getText() {
        return getWrapped().getText();
    }

    @Override
    public void setText(iString text) {
        getWrapped().setText(text);
    }

    @Override
    public Map<String, IDocTag<?>> getTagMap() {
        return getWrapped().getTagMap();
    }

    @Override
    public boolean isTagPresent(String tagName) {
        return getWrapped().isTagPresent(tagName);
    }

    @Override
    public <Tag extends IDocTag<?>> Tag getTag(String tagName) {
        return getWrapped().getTag(tagName);
    }

    @Override
    public String getString(String tagName) {
        return getWrapped().getString(tagName);
    }

    @Override
    public String getString(String tagName, String defaultValue) {
        return getWrapped().getString(tagName, defaultValue);
    }

    @Override
    public iString getText(String tagName) {
        return getWrapped().getText(tagName);
    }

    @Override
    public iString getText(String tagName, iString defaultValue) {
        return getWrapped().getText(tagName, defaultValue);
    }

    @Override
    public <_T> _T to(Class<_T> clazz) {
        return getWrapped().to(clazz);
    }

}
