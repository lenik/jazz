package net.bodz.mda.xjdoc.model;

import java.util.Collections;
import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.sugar.Tooling;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public class NullElementDoc
        implements
            IElementDoc {

    @Override
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).getWrapper(clazz);
    }

    @Override
    public ITagLibrary getTagLibrary() {
        return ITagLibrary.NULL;
    }

    @Override
    public String getName() {
        return "(no name)";
    }

    @Override
    public iString getText() {
        return iString.NULL;
    }

    @Override
    public void setText(iString text) {
    }

    @Override
    public Map<String, IDocTag<?>> getTagMap() {
        return Collections.emptyMap();
    }

    @Override
    public boolean isTagPresent(String tagName) {
        return false;
    }

    @Override
    public <T extends IDocTag<?>> T getTag(String tagName) {
        return null;
    }

    @Override
    public Object getTagData(String tagName, Object defaultValue) {
        return defaultValue;
    }

    @Override
    public IContainer<?> getContainer(String tagName) {
        return null;
    }

    @Override
    public String getString(String tagName) {
        return null;
    }

    @Override
    public String getString(String tagName, String defaultValue) {
        return defaultValue;
    }

    @Override
    public iString getText(String tagName) {
        return iString.NULL;
    }

    @Override
    public iString getText(String tagName, iString defaultValue) {
        return defaultValue;
    }

    @Override
    public void accept(IDocVisitor visitor) {
    }

}
