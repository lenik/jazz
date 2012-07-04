package net.bodz.mda.xjdoc.tags;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.NegotiationException;

public class TagBook
        implements ITagBook {

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

    public static ITagBook getInstance(INegotiation negotiation)
            throws NegotiationException {
        ITagBook instance = null;
        if (negotiation != null)
            for (IParameter param : negotiation) {
                if (param.idEquals(ITagBook.class))
                    instance = (ITagBook) param.getValue();
                else
                    param.ignore();
            }
        if (instance == null)
            throw new IllegalUsageException("Book isn't provided in negotiation.");
        return instance;
    }

}
