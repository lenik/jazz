package net.bodz.mda.xjdoc.meta;

import net.bodz.bas.lang.negotiation.INegotiable;

public interface IXjLanguage
        extends INegotiable {

    ITagType getTagType(String tagName);

}
