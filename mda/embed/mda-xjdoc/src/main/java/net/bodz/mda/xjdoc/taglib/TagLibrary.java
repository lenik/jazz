package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.t.preorder.DomainMap;
import net.bodz.mda.xjdoc.tagtype.ITagType;

public class TagLibrary
        implements ITagLibrary {

    private DomainMap<ITagType> tagMap = new DomainMap<>();

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

    public static ITagLibrary getInstance(INegotiation negotiation) {
        ITagLibrary instance = null;
        if (negotiation != null)
            instance = negotiation.require(ITagLibrary.class);
        if (instance == null)
            throw new IllegalUsageException("Tag library isn't provided in negotiation.");
        return instance;
    }

}
