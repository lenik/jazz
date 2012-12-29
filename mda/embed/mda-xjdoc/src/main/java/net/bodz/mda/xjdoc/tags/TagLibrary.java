package net.bodz.mda.xjdoc.tags;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.rtx.INegotiation;

public class TagLibrary
        implements ITagLibrary {

    Map<String, ITagType> tagTypes = new HashMap<String, ITagType>();

    @Override
    public ITagType getTagType(String tagName) {
        ITagType tagType = tagTypes.get(tagName);
        if (tagType == null)
            tagType = getDefaultTagType();
        return tagType;
    }

    public void setTagType(String tagName, ITagType tagType) {
        tagTypes.put(tagName, tagType);
    }

    public ITagType getDefaultTagType() {
        return StringTagType.INSTANCE;
    }

    public static ITagLibrary getInstance(INegotiation negotiation) {
        ITagLibrary instance = null;
        if (negotiation != null)
            instance = negotiation.require(ITagLibrary.class);
        if (instance == null)
            throw new IllegalUsageException("Book isn't provided in negotiation.");
        return instance;
    }

}
