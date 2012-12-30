package net.bodz.mda.xjdoc.taglib;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.mda.xjdoc.tagtype.ITagType;

@ExcludedFromIndex
public class TagLibrarySet
        extends LinkedHashSet<ITagLibrary>
        implements ITagLibrary {

    private static final long serialVersionUID = 1L;

    public TagLibrarySet(ITagLibrary... taglibs) {
        this(Arrays.asList(taglibs));
    }

    public TagLibrarySet(Collection<ITagLibrary> taglibs) {
        addAll(taglibs);
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
    public ITagType getTagType(String rootTagName) {
        for (ITagLibrary taglib : this) {
            ITagType tagType = taglib.getTagType(rootTagName);
            if (tagType != null)
                return tagType;
        }
        return null;
    }

}
