package net.bodz.mda.xjdoc.meta;

import java.util.HashMap;
import java.util.Map;

public class XjLanguage {

    static Map<String, ITagType> tagTypes = new HashMap<String, ITagType>();

    public static ITagType getTagType(String tagName) {
        return tagTypes.get(tagName);
    }

    public static void setKeyword(String tagName, ITagType tagType) {
        tagTypes.put(tagName, tagType);
    }

    public static void addCommonTags() {
        setKeyword("param", new ParameterizedDocTagType());
        setKeyword("throws", new ParameterizedDocTagType());
    }
}
