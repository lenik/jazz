package net.bodz.mda.xjdoc.taglib;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.mda.xjdoc.model.IDocTag;

@ExcludedFromIndex
public class TagLibrarySet
        extends TreeSet<ITagLibrary>
        implements
            ITagLibrary {

    private static final long serialVersionUID = 1L;

    public TagLibrarySet(ITagLibrary... taglibs) {
        this(Arrays.asList(taglibs));
    }

    public TagLibrarySet(Collection<ITagLibrary> taglibs) {
        super(PriorityComparator.INSTANCE);
        addAll(taglibs);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (ITagLibrary lib : this) {
            if (sb.length() != 0)
                sb.append(", ");
            sb.append(lib.getName());
        }
        return sb.toString();
    }

    @Override
    public String getRootTagName(String tagName) {
        for (ITagLibrary taglib : this) {
            String rootTagName = taglib.getRootTagName(tagName);
            if (rootTagName != null)
                return rootTagName;
        }
        return null;
    }

    @Override
    public IDocTag<?> createTag(String rootTagName) {
        for (ITagLibrary taglib : this) {
            IDocTag<?> tag = taglib.createTag(rootTagName);
            if (tag != null)
                return tag;
        }
        return null;
    }

}
