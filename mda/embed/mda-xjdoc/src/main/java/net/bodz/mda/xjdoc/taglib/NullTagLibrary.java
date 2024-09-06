package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.mda.xjdoc.model.IDocTag;

@ExcludedFromIndex
public class NullTagLibrary
        implements
            ITagLibrary {

    final int priority;

    public NullTagLibrary() {
        this(0);
    }

    public NullTagLibrary(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

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

    public static final NullTagLibrary INSTANCE = new NullTagLibrary(0);

}
