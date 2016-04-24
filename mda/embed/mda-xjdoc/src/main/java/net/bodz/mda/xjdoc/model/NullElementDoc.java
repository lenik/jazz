package net.bodz.mda.xjdoc.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.sugar.Tooling;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public class NullElementDoc
        implements IElementDoc {

    @Override
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).getWrapper(clazz);
    }

    @Override
    public ITagLibrary getTagLibrary() {
        return null;
    }

    @Override
    public iString getText() {
        return iString.NULL;
    }

    @Override
    public void setText(iString text) {
    }

    @Override
    public Object getTag(String tagName) {
        return null;
    }

    @Override
    public <T> T getTag(String tagName, Class<T> tagValueType) {
        return null;
    }

    @Override
    public void setTag(String tagName, Object tagValue) {
    }

    @Override
    public Object removeTag(String tagName) {
        return null;
    }

    @Override
    public Map<String, Object> getTagMap() {
        return Collections.emptyMap();
    }

    @Override
    public Object getFirstTag(String tagName) {
        return null;
    }

    @Override
    public Collection<?> getAllTag(String tagName) {
        return Collections.emptySet();
    }

    @Override
    public iString getTextTag(String tagName) {
        return null;
    }

}
