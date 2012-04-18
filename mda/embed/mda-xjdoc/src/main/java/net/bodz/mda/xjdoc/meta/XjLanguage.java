package net.bodz.mda.xjdoc.meta;

import java.util.HashMap;
import java.util.Map;

import javax.free.AbstractNegotiable;
import javax.free.INegotiation;
import javax.free.NegotiationException;
import javax.free.NegotiationParameter;

public class XjLanguage
        extends AbstractNegotiable
        implements IXjLanguage {

    Map<String, ITagType> tagTypes = new HashMap<String, ITagType>();

    @Override
    public boolean negotiate(NegotiationParameter negotiationparameter)
            throws NegotiationException {
        return false;
    }

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

    public static IXjLanguage getInstance(INegotiation negotiation)
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
            lang = new JavadocXjLang();
        return lang;
    }

}
