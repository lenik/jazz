package net.bodz.mda.xjdoc.meta;

import java.util.HashMap;
import java.util.Map;

public class XjLanguage
        implements IXjLanguage {

    Map<String, ITagType> tagTypes = new HashMap<String, ITagType>();

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

}
