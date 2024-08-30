package net.bodz.mda.xjdoc.model;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.tagtype.StringTag;
import net.bodz.mda.xjdoc.tagtype.TextTag;

public interface IMutableElementDoc
        extends
            IElementDoc {

    <T extends IDocTag<?>> T makeTag(String name);

    @Deprecated
    void setTag(String tagName, IDocTag<?> tag);

    default void setTag(String tagName, String string) {
        setTag(tagName, new StringTag(string));
    }

    default void setTag(String tagName, iString text) {
        setTag(tagName, new TextTag(text));
    }

    /**
     * Remove the named tag.
     *
     * @return Value of the removed tag. <code>null</code> if the tag was not existed, or its value
     *         is <code>null</code>.
     */
    IDocTag<?> removeTag(String tagName);

}
