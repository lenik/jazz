package net.bodz.mda.xjdoc.meta;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.negotiation.AbstractNegotiable;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.NegotiationException;

public class XjLanguage
        extends AbstractNegotiable
        implements IXjLanguage {

    Map<String, ITagType> tagTypes = new HashMap<String, ITagType>();

    @Override
    public boolean negotiate(IParameter parameter)
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
            for (IParameter param : negotiation) {
                if (param.is(IXjLanguage.class))
                    lang = (IXjLanguage) param.getValue();
                else
                    param.ignore();
            }
        if (lang == null)
            lang = new JavadocXjLang();
        return lang;
    }

}
