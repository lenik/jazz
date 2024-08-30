package net.bodz.mda.xjdoc.taglib;

import net.bodz.mda.xjdoc.model.IDocTag;

public class NullTagLibrary
        implements
            ITagLibrary {

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public String getRootTagName(String tagName) {
        return tagName;
    }

    @Override
    public IDocTag<?> createTag(String rootTagName) {
        return null;
    }

    public static final NullTagLibrary INSTANCE = new NullTagLibrary();

}
