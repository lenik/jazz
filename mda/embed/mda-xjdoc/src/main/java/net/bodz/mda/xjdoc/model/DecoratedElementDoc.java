package net.bodz.mda.xjdoc.model;

import java.util.Map;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.model.AbstractDecorator;

public abstract class DecoratedElementDoc
        extends AbstractDecorator<IElementDoc>
        implements IElementDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedElementDoc(IElementDoc _orig) {
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
    public DomainString getText() {
        return getWrapped().getText();
    }

    @Override
    public void setText(DomainString text) {
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

}