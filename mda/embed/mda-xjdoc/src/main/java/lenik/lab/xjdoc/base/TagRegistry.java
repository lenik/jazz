package lenik.lab.xjdoc.base;

import java.util.HashMap;
import java.util.Map;

public class TagRegistry {

    static Map<String, TagType> map = new HashMap<String, TagType>();

    public static TagType getTagOption(String tagName) {
        return map.get(tagName);
    }

    public static void setTagOption(String tagName, TagType tagOption) {
        map.put(tagName, tagOption);
    }

    public static void addCommonTags() {
        setTagOption("param", new TagType(1));
        setTagOption("throws", new TagType(1));
    }

}
