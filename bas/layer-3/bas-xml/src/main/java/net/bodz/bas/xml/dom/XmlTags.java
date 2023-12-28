package net.bodz.bas.xml.dom;

import net.bodz.bas.fn.IFilter;

public class XmlTags {

    public static IXmlTag findFirst(IXmlNode start, String tagName) {
        return find(start, tagName, IFilter.TRUE);
    }

    public static IXmlTag find(IXmlNode start, String tagName, IFilter<? super IXmlTag> tagFilter) {
        for (IXmlNode child : start.getChildren()) {
            if (child.getType() != XmlNodeType.ELEMENT)
                continue;

            IXmlTag childTag = (IXmlTag) child;
            if (tagName == null || childTag.getTagName().equals(tagName))
                if (tagFilter.accept(childTag))
                    return childTag;

            IXmlTag result = find(childTag, tagName, tagFilter);
            if (result != null)
                return result;
        }
        return null;
    }

}
