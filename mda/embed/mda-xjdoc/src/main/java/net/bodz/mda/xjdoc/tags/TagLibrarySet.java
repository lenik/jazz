package net.bodz.mda.xjdoc.tags;

import java.util.Arrays;
import java.util.LinkedHashSet;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class TagLibrarySet
        extends LinkedHashSet<ITagLibrary>
        implements ITagLibrary {

    private static final long serialVersionUID = 1L;

    public TagLibrarySet(ITagLibrary... taglibs) {
        addAll(Arrays.asList(taglibs));
    }

    @Override
    public ITagType getTagType(String tagName) {
        for (ITagLibrary taglib : this) {
            ITagType tagType = taglib.getTagType(tagName);
            if (tagType != null)
                return tagType;
        }
        return null;
    }

}
