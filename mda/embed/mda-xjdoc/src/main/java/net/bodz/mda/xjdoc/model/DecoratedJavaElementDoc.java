package net.bodz.mda.xjdoc.model;

import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedJavaElementDoc
        extends AbstractDecorator<IJavaElementDoc>
        implements IJavaElementDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedJavaElementDoc(IJavaElementDoc _orig) {
        super(_orig);
    }

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public void setName(String name) {
        getWrapped().setName(name);
    }

    @Override
    public iString getLabel() {
        return getWrapped().getLabel();
    }

    @Override
    public void setLabel(iString label) {
        getWrapped().setLabel(label);
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
    public Object getTag(String tagName) {
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
    public <T> T as(Class<T> decoratedType) {
        return getWrapped().as(decoratedType);
    }

}
