package net.bodz.mda.xjdoc.meta;

import java.util.HashMap;
import java.util.Map;

import javax.free.INegotiation;
import javax.free.NegotiationException;
import javax.free.NegotiationParameter;

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

    public static IXjLanguage negotiate(INegotiation negotiation)
            throws NegotiationException {
        IXjLanguage lang = null;
        if (negotiation != null)
            for (NegotiationParameter np : negotiation) {
                if (np.accept(IXjLanguage.class, true))
                    lang = (IXjLanguage) np.getValue();
                else
                    np.bypass();
            }
        if (lang == null)
            lang = new JavadocXjLang(null); // XXX
        return lang;
    }

}
