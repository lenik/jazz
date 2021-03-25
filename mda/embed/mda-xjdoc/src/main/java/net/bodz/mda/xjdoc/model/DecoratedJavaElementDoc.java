package net.bodz.mda.xjdoc.model;

import java.util.Collection;
import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public abstract class DecoratedJavaElementDoc
        extends AbstractDecorator<IElementDoc>
        implements IElementDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedJavaElementDoc(IElementDoc _orig) {
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
    public <T> T getTag(String tagName) {
        return getWrapped().getTag(tagName);
    }

    @Override
    public <T> T getTag(String tagName, Class<T> tagValueType) {
        return getWrapped().getTag(tagName, tagValueType);
    }

    @Override
    public void setTag(String tagName, Object tagValue) {
        getWrapped().setTag(tagName, tagValue);
    }

    @Override
    public Object removeTag(String tagName) {
        return getWrapped().removeTag(tagName);
    }

    @Override
    public Map<String, Object> getTagMap() {
        return getWrapped().getTagMap();
    }

    @Override
    public Object getFirstTag(String tagName) {
        return getWrapped().getFirstTag(tagName);
    }

    @Override
    public Collection<?> getAllTag(String tagName) {
        return getWrapped().getAllTag(tagName);
    }

    @Override
    public iString getTextTag(String tagName) {
        return getWrapped().getTextTag(tagName);
    }

    @Override
    public <T> T to(Class<T> clazz) {
        return getWrapped().to(clazz);
    }

}
