package net.bodz.mda.xjdoc.taglib;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.mda.xjdoc.model.IDocTag;
import net.bodz.mda.xjdoc.tagtype.StringTag;

@ExcludedFromIndex
public class TagLibrarySet
        extends LinkedHashSet<ITagLibrary>
        implements
            ITagLibrary {

    private static final long serialVersionUID = 1L;

    IDocTagFactory defaultTagType = () -> new StringTag();

    public TagLibrarySet(ITagLibrary... taglibs) {
        this(Arrays.asList(taglibs));
    }

    public TagLibrarySet(Collection<ITagLibrary> taglibs) {
        addAll(taglibs);
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

    public IDocTagFactory getDefaultTagType() {
        return defaultTagType;
    }

    public void setDefaultTagType(IDocTagFactory defaultTagType) {
        this.defaultTagType = defaultTagType;
    }

    @Override
    public IDocTag<?> createTag(String rootTagName) {
        for (ITagLibrary taglib : this) {
            IDocTag<?> tag = taglib.createTag(rootTagName);
            if (tag != null)
                return tag;
        }
        if (defaultTagType != null)
            return defaultTagType.get();
        return null;
    }

}
