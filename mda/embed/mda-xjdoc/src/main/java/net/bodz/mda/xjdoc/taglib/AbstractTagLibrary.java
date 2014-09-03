package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.preorder.PackageMap;
import net.bodz.mda.xjdoc.tagtype.ITagType;
import net.bodz.mda.xjdoc.tagtype.RepeatForListTagType;
import net.bodz.mda.xjdoc.tagtype.StringTagType;

public abstract class AbstractTagLibrary
        implements ITagLibrary {

    private PackageMap<ITagType> tagMap = new PackageMap<>();

    // private ITagType defaultTagType = StringTagType.getInstance();
    private ITagType defaultTagType = new RepeatForListTagType(StringTagType.getInstance());

    @Override
    public String getName() {
        String name = getClass().getSimpleName();
        name = StringPart.rtrim(name, "TagLibrary");
        name = Strings.lcfirst(name);
        return name;
    }

    @Override
    public String getRootTagName(String tagName) {
        String rootTagName = tagMap.meetKey(tagName);
        return rootTagName;
    }

    @Override
    public ITagType getTagType(String rootTagName) {
        ITagType tagType = tagMap.get(rootTagName);
        return tagType;
    }

    public void addTagType(String rootTagName, ITagType tagType) {
        if (rootTagName == null)
            throw new NullPointerException("rootTagName");

        ITagType existing = tagMap.get(rootTagName);
        if (existing != null)
            throw new DuplicatedKeyException(rootTagName);

        tagMap.put(rootTagName, tagType);
    }

    @Override
    public ITagType getDefaultTagType() {
        return defaultTagType;
    }

    public static ITagLibrary fromOptions(IOptions options) {
        if (options == null)
            throw new NullPointerException("options");

        ITagLibrary instance = options.get(ITagLibrary.class);
        if (instance == null)
            throw new IllegalUsageException("Tag library isn't provided in options.");

        return instance;
    }

}
